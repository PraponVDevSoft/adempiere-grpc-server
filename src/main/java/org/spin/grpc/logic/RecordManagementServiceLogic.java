/************************************************************************************
 * Copyright (C) 2018-present E.R.P. Consultores y Asociados, C.A.                  *
 * Contributor(s): Edwin Betancourt, EdwinBetanc0urt@outlook.com                    *
 * This program is free software: you can redistribute it and/or modify             *
 * it under the terms of the GNU General Public License as published by             *
 * the Free Software Foundation, either version 2 of the License, or                *
 * (at your option) any later version.                                              *
 * This program is distributed in the hope that it will be useful,                  *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of                   *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.	See the                     *
 * GNU General Public License for more details.                                     *
 * You should have received a copy of the GNU General Public License                *
 * along with this program. If not, see <https://www.gnu.org/licenses/>.            *
 ************************************************************************************/
package org.spin.grpc.logic;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.adempiere.core.domains.models.I_AD_Tab;
import org.adempiere.core.domains.models.I_AD_Window;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.model.ZoomInfoFactory;
import org.compiere.model.MQuery;
import org.compiere.model.MTab;
import org.compiere.model.MTable;
import org.compiere.model.MWindow;
import org.compiere.model.PO;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.compiere.util.Util;
import org.spin.backend.grpc.record_management.ExistsRecordReferencesRequest;
import org.spin.backend.grpc.record_management.ExistsRecordReferencesResponse;
import org.spin.backend.grpc.record_management.ListRecordReferencesRequest;
import org.spin.backend.grpc.record_management.ListRecordReferencesResponse;
import org.spin.backend.grpc.record_management.ListZoomWindowsRequest;
import org.spin.backend.grpc.record_management.ListZoomWindowsResponse;
import org.spin.backend.grpc.record_management.RecordReferenceInfo;
import org.spin.backend.grpc.record_management.ToggleIsActiveRecordRequest;
import org.spin.backend.grpc.record_management.ToggleIsActiveRecordResponse;
import org.spin.backend.grpc.record_management.ToggleIsActiveRecordsBatchRequest;
import org.spin.backend.grpc.record_management.ToggleIsActiveRecordsBatchResponse;
import org.spin.backend.grpc.record_management.ZoomWindow;
import org.spin.base.util.RecordUtil;
import org.spin.service.grpc.util.value.StringManager;
import org.spin.service.grpc.util.value.ValueManager;

/**
 * @author Edwin Betancourt, EdwinBetanc0urt@outlook.com, https://github.com/EdwinBetanc0urt
 * Service Logic for backend of Record Management
 */
public class RecordManagementServiceLogic {

	/**
	 * Validate table exists.
	 * @return table
	 */
	private static MTable validateAndGetTable(String tableName) {
		// validate table
		if (Util.isEmpty(tableName, true)) {
			throw new AdempiereException("@FillMandatory@ @AD_Table_ID@");
		}
		MTable table = MTable.get(Env.getCtx(), tableName);
		if (table == null || table.getAD_Table_ID() <= 0) {
			throw new AdempiereException("@AD_Table_ID@ @NotFound@");
		}
		return table;
	}


	public static ToggleIsActiveRecordsBatchResponse.Builder toggleIsActiveRecords(ToggleIsActiveRecordsBatchRequest request) {
		StringBuilder errorMessage = new StringBuilder();
		AtomicInteger recordsChanges = new AtomicInteger(0);

		Trx.run(transactionName -> {
			MTable table = validateAndGetTable(request.getTableName());
			List<Integer> ids = request.getIdsList();
			if (ids.size() > 0) {
				ids.stream().forEach(id -> {
					PO entity = table.getPO(id, transactionName);
					if (entity != null && entity.get_ID() > 0) {
						if (entity.get_ColumnIndex("Processed") >= 0 && entity.get_ValueAsBoolean("Processed")) {
							return;
						}
						entity.setIsActive(request.getIsActive());
						try {
							entity.saveEx();
							recordsChanges.incrementAndGet();
						} catch (Exception e) {
							e.printStackTrace();
							errorMessage.append(
								e.getLocalizedMessage()
							);
						}
					}
				});
			}
		});
		ToggleIsActiveRecordsBatchResponse.Builder builder = ToggleIsActiveRecordsBatchResponse.newBuilder()
			.setMessage(
				StringManager.getValidString(
					Msg.parseTranslation(
						Env.getCtx(),
						errorMessage.toString()
					)
				)
			)
			.setTotalChanges(
				recordsChanges.get()
			)
		;

		//	Return
		return builder;
	}

	public static ToggleIsActiveRecordResponse.Builder toggleIsActiveRecord(ToggleIsActiveRecordRequest request) {
		StringBuilder errorMessage = new StringBuilder();
		AtomicInteger recordsChanges = new AtomicInteger(0);

		Trx.run(transactionName -> {
			MTable table = validateAndGetTable(request.getTableName());
			if (request.getId() <= 0 && !RecordUtil.isValidId(request.getId(), table.getAccessLevel())) {
				throw new AdempiereException("@FillMandatory@ @Record_ID@");
			}
			if (request.getId() > 0) {
				PO entity = RecordUtil.getEntity(Env.getCtx(), request.getTableName(), request.getId(), transactionName);
				if (entity != null && entity.get_ID() > 0) {
					if (entity.get_ColumnIndex("Processed") >= 0 && entity.get_ValueAsBoolean("Processed")) {
						return;
					}
					entity.setIsActive(request.getIsActive());
					try {
						entity.saveEx();
						recordsChanges.incrementAndGet();
					} catch (Exception e) {
						e.printStackTrace();
						errorMessage.append(
							e.getLocalizedMessage()
						);
					}
				}
			}
		});
		ToggleIsActiveRecordResponse.Builder builder = ToggleIsActiveRecordResponse.newBuilder()
			.setMessage(
				StringManager.getValidString(
					Msg.parseTranslation(
						Env.getCtx(),
						errorMessage.toString()
					)
				)
			)
		;
		//	Return
		return builder;
	}


	/**
	 * Convert Zoom Window from ID
	 * @param context
	 * @param windowId
	 * @param tableName
	 * @param isPurchase
	 * @return
	 */
	public static ZoomWindow.Builder convertZoomWindow(Properties context, int windowId, String tableName, boolean isPurchase) {
		String language = Env.getAD_Language(context);
		boolean isBaseLanguage = Env.isBaseLanguage(context, null);

		MWindow window = MWindow.get(context, windowId);
		ZoomWindow.Builder builder = ZoomWindow.newBuilder()
			.setId(
				window.getAD_Window_ID()
			)
			.setUuid(
				StringManager.getValidString(
					window.getUUID()
				)
			)
			.setName(
				StringManager.getValidString(
					window.getName()
				)
			)
			.setDescription(
				StringManager.getValidString(
					window.getDescription()
				)
			)
			.setIsSalesTransaction(
				window.isSOTrx()
			)
			.setIsPurchase(
				isPurchase
			)
		;
		if (!isBaseLanguage) {
			builder.setName(
				StringManager.getValidString(
						window.get_Translation(
							I_AD_Window.COLUMNNAME_Name,
							language
						)
					)
				)
				.setDescription(
					StringManager.getValidString(
						window.get_Translation(
							I_AD_Window.COLUMNNAME_Description,
							language
						)
					)
				)
			;
		}

		MTable table = MTable.get(context, tableName);
		Optional<MTab> maybeTab = Arrays.asList(
			window.getTabs(false, null)
		)
			.stream().filter(currentTab -> {
				if (!currentTab.isActive()) {
					return false;
				}
				return currentTab.getAD_Table_ID() == table.getAD_Table_ID();
			})
			.findFirst()
		;
		if (maybeTab.isPresent()) {
			MTab tab = maybeTab.get();
			builder.setTabId(
					tab.getAD_Tab_ID()
				)
				.setTabUuid(
					StringManager.getValidString(
						tab.getUUID()
					)
				)
				.setTabName(
					StringManager.getValidString(
						tab.getName()
					)
				)
				.setIsParentTab(
					tab.getTabLevel() == 0
				)
			;
			if (!isBaseLanguage) {
				builder.setTabName(
					StringManager.getValidString(
						window.get_Translation(
							I_AD_Tab.COLUMNNAME_Name,
							language
						)
					)
				);
			}
		}

		//	Return
		return builder;
	}

	public static ListZoomWindowsResponse.Builder listZoomWindows(ListZoomWindowsRequest request) {
		MTable table = null;
		if (request.getTableId() > 0) {
			table = MTable.get(Env.getCtx(), request.getTableId());
		} else {
			table = validateAndGetTable(
				request.getTableName()
			);
		}

		List<String> keyColumnsList = Arrays.asList(
			table.getKeyColumns()
		);
		String keyColumnName = null;
		if (keyColumnsList != null && !keyColumnsList.isEmpty()) {
			keyColumnName = keyColumnsList.get(0);
		}
		ListZoomWindowsResponse.Builder builder = ListZoomWindowsResponse.newBuilder()
			.setTableId(
				table.getAD_Table_ID()
			)
			.setTableName(
				StringManager.getValidString(
					table.getTableName()
				)
			)
			.setKeyColumnName(
				StringManager.getValidString(
					keyColumnName
				)
			)
			.addAllKeyColumns(
				keyColumnsList
			)
		;

		if (table.getAD_Window_ID() > 0) {
			ZoomWindow.Builder windowSalesBuilder = convertZoomWindow(
				table.getCtx(),
				table.getAD_Window_ID(),
				table.getTableName(),
				false
			);
			builder.addZoomWindows(
				windowSalesBuilder.build()
			);
		}
		if (table.getPO_Window_ID() > 0) {
			ZoomWindow.Builder windowPurchaseBuilder = convertZoomWindow(
				table.getCtx(),
				table.getPO_Window_ID(),
				table.getTableName(),
				true
			);
			builder.addZoomWindows(
				windowPurchaseBuilder.build()
			);
		}
		return builder;
	}



	public static ExistsRecordReferencesResponse.Builder existsRecordReferences(ExistsRecordReferencesRequest request) {
		// validate tab
		if (request.getTabId() <= 0) {
			throw new AdempiereException("@AD_Tab_ID@ @Mandatory@");
		}
		MTab tab = (MTab) RecordUtil.getEntity(Env.getCtx(), I_AD_Tab.Table_Name, request.getTabId(), null);
		if (tab == null || tab.getAD_Tab_ID() <= 0) {
			throw new AdempiereException("@AD_Tab_ID@ @NotFound@");
		}

		// builder
		ExistsRecordReferencesResponse.Builder builder = ExistsRecordReferencesResponse.newBuilder();

		MTable table = MTable.get(Env.getCtx(), tab.getAD_Table_ID());
		// validate multiple keys as accounting tables and translation tables
		if (table == null || !table.isSingleKey()) {
			return builder;
		}

		// validate record
		int recordId = request.getRecordId();
		RecordUtil.validateRecordId(recordId, table.getAccessLevel());

		PO entity = RecordUtil.getEntity(Env.getCtx(), table.getTableName(), recordId, null);
		if (entity == null) {
			// 	throw new AdempiereException("@Record_ID@ @NotFound@");
			return builder;
		}

		List<ZoomInfoFactory.ZoomInfo> zoomInfos = ZoomInfoFactory.retrieveZoomInfos(entity, tab.getAD_Window_ID())
			.stream()
			.filter(zoomInfo -> {
				return zoomInfo.query.getRecordCount() > 0;
			})
			.collect(Collectors.toList());
		if (zoomInfos == null || zoomInfos.isEmpty()) {
			return builder;
		}
		int recordCount = zoomInfos.size();
		//	Return
		return builder.setRecordCount(recordCount);
	}

	/**
	 * Convert references to gRPC
	 * @param Env.getCtx()
	 * @param request
	 * @return
	 */
	public static ListRecordReferencesResponse.Builder listRecordReferences(ListRecordReferencesRequest request) {
		// validate tab
		if (request.getTabId() <= 0) {
			throw new AdempiereException("@AD_Tab_ID@ @Mandatory@");
		}
		MTab tab = (MTab) RecordUtil.getEntity(Env.getCtx(), I_AD_Tab.Table_Name, request.getTabId(), null);
		if (tab == null || tab.getAD_Tab_ID() <= 0) {
			throw new AdempiereException("@AD_Tab_ID@ @NotFound@");
		}

		// builder
		ListRecordReferencesResponse.Builder builder = ListRecordReferencesResponse.newBuilder();

		MTable table = MTable.get(Env.getCtx(), tab.getAD_Table_ID());
		// validate multiple keys as accounting tables and translation tables
		if (!table.isSingleKey()) {
			return builder;
		}

		// validate multiple keys as accounting tables and translation tables
		if (table == null || !table.isSingleKey()) {
			return builder;
		}

		// validate record
		int recordId = request.getRecordId();
		RecordUtil.validateRecordId(recordId, table.getAccessLevel());

		PO entity = RecordUtil.getEntity(Env.getCtx(), table.getTableName(), recordId, null);
		if (entity == null) {
			// 	throw new AdempiereException("@Record_ID@ @NotFound@");
			return builder;
		}

		List<ZoomInfoFactory.ZoomInfo> zoomInfos = ZoomInfoFactory.retrieveZoomInfos(entity, tab.getAD_Window_ID())
			.stream()
			.filter(zoomInfo -> {
				return zoomInfo.query.getRecordCount() > 0;
			})
			.collect(Collectors.toList());
		if (zoomInfos == null || zoomInfos.isEmpty()) {
			return builder;
		}

		zoomInfos.stream().forEach(zoomInfo -> {
			MQuery zoomQuery = zoomInfo.query;
			//
			RecordReferenceInfo.Builder recordReferenceBuilder = RecordReferenceInfo.newBuilder();

			MWindow referenceWindow = MWindow.get(Env.getCtx(), zoomInfo.windowId);
			MTable referenceTable = MTable.get(Env.getCtx(), zoomQuery.getZoomTableName());

			int tabId = DB.getSQLValue(
				null,
				"SELECT AD_Tab_ID FROM AD_Tab WHERE AD_Window_ID= ? AND AD_Table_ID = ? ORDER BY SeqNo",
				referenceWindow.getAD_Window_ID(), referenceTable.getAD_Table_ID()
			);
			MTab referenceTab = MTab.get(Env.getCtx(), tabId);

			String uuidRerefenced = referenceWindow.getUUID() + "|" + referenceTab.getUUID() + "|" + referenceTable.getTableName() + "|" + zoomQuery.getZoomColumnName();
			RecordUtil.referenceWhereClauseCache.put(uuidRerefenced, zoomQuery.getWhereClause());

			recordReferenceBuilder.setUuid(uuidRerefenced)
				.setWindowId(
					referenceWindow.getAD_Window_ID()
				)
				.setTabId(
					referenceTab.getAD_Tab_ID()
				)
				.setTableName(
					StringManager.getValidString(
						zoomQuery.getZoomTableName()
					)
				)
				.setWhereClause(
					StringManager.getValidString(
						zoomQuery.getWhereClause()
					)
				)
				.setRecordCount(
					zoomQuery.getRecordCount()
				)
				.setDisplayName(
					zoomInfo.destinationDisplay + " (#" + zoomQuery.getRecordCount() + ")"
				)
				.setColumnName(
					StringManager.getValidString(
						zoomQuery.getZoomColumnName()
					)
				)
				.setValue(
					ValueManager.getValueFromObject(
						zoomQuery.getZoomValue()
					).build()
				)
			;

			//	Add to list
			builder.addReferences(recordReferenceBuilder.build());
		});

		builder.setRecordCount(zoomInfos.size());

		//	Return
		return builder;
	}

}
