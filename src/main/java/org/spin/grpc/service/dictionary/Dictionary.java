/************************************************************************************
 * Copyright (C) 2012-2023 E.R.P. Consultores y Asociados, C.A.                     *
 * Contributor(s): Yamel Senih ysenih@erpya.com                                     *
 * This program is free software: you can redistribute it and/or modify             *
 * it under the terms of the GNU General Public License as published by             *
 * the Free Software Foundation, either version 2 of the License, or                *
 * (at your option) any later version.                                              *
 * This program is distributed in the hope that it will be useful,                  *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of                   *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the                     *
 * GNU General Public License for more details.                                     *
 * You should have received a copy of the GNU General Public License                *
 * along with this program. If not, see <https://www.gnu.org/licenses/>.            *
 ************************************************************************************/
package org.spin.grpc.service.dictionary;

import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.core.domains.models.I_AD_Field;
import org.adempiere.core.domains.models.I_AD_Form;
import org.adempiere.core.domains.models.I_AD_Tab;
import org.adempiere.core.domains.models.X_AD_Reference;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.model.MBrowse;
import org.compiere.model.MColumn;
import org.compiere.model.MField;
import org.compiere.model.MForm;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MLookupInfo;
import org.compiere.model.MProcess;
import org.compiere.model.MTab;
import org.compiere.model.MTable;
import org.compiere.model.MWindow;
import org.compiere.model.M_Element;
import org.compiere.model.Query;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Language;
import org.compiere.util.Util;
import org.spin.backend.grpc.dictionary.Browser;
import org.spin.backend.grpc.dictionary.DictionaryGrpc.DictionaryImplBase;
import org.spin.backend.grpc.dictionary.EntityRequest;
import org.spin.backend.grpc.dictionary.Field;
import org.spin.backend.grpc.dictionary.FieldRequest;
import org.spin.backend.grpc.dictionary.Form;
import org.spin.backend.grpc.dictionary.ListFieldsRequest;
import org.spin.backend.grpc.dictionary.ListFieldsResponse;
import org.spin.backend.grpc.dictionary.Process;
import org.spin.backend.grpc.dictionary.Reference;
import org.spin.backend.grpc.dictionary.ReferenceRequest;
import org.spin.backend.grpc.dictionary.Tab;
import org.spin.backend.grpc.dictionary.Window;
import org.spin.util.ASPUtil;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;

/**
 * @author Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com
 * Dictionary service
 * Get all dictionary meta-data
 */
public class Dictionary extends DictionaryImplBase {
	/**	Logger			*/
	private CLogger log = CLogger.getCLogger(Dictionary.class);
	
	@Override
	public void getWindow(EntityRequest request, StreamObserver<Window> responseObserver) {
		try {
			Window.Builder windowBuilder = getWindow(Env.getCtx(), request.getId(), true);
			responseObserver.onNext(windowBuilder.build());
			responseObserver.onCompleted();
		} catch (Exception e) {
			log.severe(e.getLocalizedMessage());
			e.printStackTrace();
			responseObserver.onError(
				Status.INTERNAL
					.withDescription(e.getLocalizedMessage())
					.withCause(e)
					.asRuntimeException()
			);
		}
	}
	/**
	 * Request Window: can be only window or child
	 * @param request
	 * @param uuid
	 * @param id
	 * @param withTabs
	 */
	private Window.Builder getWindow(Properties context, int id, boolean withTabs) {
		MWindow window = MWindow.get(context, id);
		if(window == null) {
			return Window.newBuilder();
		}
		return WindowConvertUtil.convertWindow(
			context,
			window,
			withTabs
		);
	}



	@Override
	public void getTab(EntityRequest request, StreamObserver<Tab> responseObserver) {
		try {
			Tab.Builder tabBuilder = getTab(Env.getCtx(), request.getId(), true);
			responseObserver.onNext(tabBuilder.build());
			responseObserver.onCompleted();
		} catch (Exception e) {
			log.severe(e.getLocalizedMessage());
			e.printStackTrace();
			responseObserver.onError(
				Status.INTERNAL
					.withDescription(e.getLocalizedMessage())
					.withCause(e)
					.asRuntimeException()
			);
		}
	}

	/**
	 * Convert Tabs from UUID
	 * @param uuid
	 * @param withFields
	 * @return
	 */
	private Tab.Builder getTab(Properties context, int id, boolean withFields) {
		MTab tab = MTab.get(context, id);
		//	Convert
		return WindowConvertUtil.convertTab(context, tab, null, withFields);
	}



	@Override
	public void getReference(ReferenceRequest request, StreamObserver<Reference> responseObserver) {
		try {
			Reference.Builder fieldBuilder = getReference(Env.getCtx(), request);
			responseObserver.onNext(fieldBuilder.build());
			responseObserver.onCompleted();
		} catch (Exception e) {
			log.severe(e.getLocalizedMessage());
			e.printStackTrace();
			responseObserver.onError(
				Status.INTERNAL
					.withDescription(e.getLocalizedMessage())
					.withCause(e)
					.asRuntimeException()
			);
		}
	}

	/**
	 * Convert reference from a request
	 * @param context
	 * @param request
	 * @return
	 */
	private Reference.Builder getReference(Properties context, ReferenceRequest request) {
		Reference.Builder builder = Reference.newBuilder();
		MLookupInfo info = null;
		if(request.getId() > 0) {
			X_AD_Reference reference = new X_AD_Reference(context, request.getId(), null);
			if(reference.getValidationType().equals(X_AD_Reference.VALIDATIONTYPE_TableValidation)) {
				info = MLookupFactory.getLookupInfo(context, 0, 0, DisplayType.Search, Language.getLanguage(Env.getAD_Language(context)), null, reference.getAD_Reference_ID(), false, null, false);
			} else if(reference.getValidationType().equals(X_AD_Reference.VALIDATIONTYPE_ListValidation)) {
				info = MLookupFactory.getLookup_List(Language.getLanguage(Env.getAD_Language(context)), reference.getAD_Reference_ID());
			}
		} else if(!Util.isEmpty(request.getColumnName())) {
			info = MLookupFactory.getLookupInfo(context, 0, 0, DisplayType.TableDir, Language.getLanguage(Env.getAD_Language(context)), request.getColumnName(), 0, false, null, false);
		}

		if (info != null) {
			builder = DictionaryConvertUtil.convertReference(context, info);
		}

		return builder;
	}



	@Override
	public void getProcess(EntityRequest request, StreamObserver<Process> responseObserver) {
		try {
			Process.Builder processBuilder = getProcess(Env.getCtx(), request.getId(), true);
			responseObserver.onNext(processBuilder.build());
			responseObserver.onCompleted();
		} catch (Exception e) {
			log.severe(e.getLocalizedMessage());
			e.printStackTrace();
			responseObserver.onError(
				Status.INTERNAL
					.withDescription(e.getLocalizedMessage())
					.withCause(e)
					.asRuntimeException()
			);
		}
	}

	/**
	 * Convert Process from UUID
	 * @param id
	 * @param withParameters
	 * @return
	 */
	private Process.Builder getProcess(Properties context, int id, boolean withParameters) {
		MProcess process = MProcess.get(context, id);
		if(process == null) {
			return Process.newBuilder();
		}
		//	Convert
		return ProcessConvertUtil.convertProcess(
			context,
			process,
			withParameters
		);
	}


	@Override
	public void getBrowser(EntityRequest request, StreamObserver<Browser> responseObserver) {
		try {
			Browser.Builder browserBuilder = getBrowser(Env.getCtx(), request.getId(), true);
			responseObserver.onNext(browserBuilder.build());
			responseObserver.onCompleted();
		} catch (Exception e) {
			log.severe(e.getLocalizedMessage());
			e.printStackTrace();
			responseObserver.onError(
				Status.INTERNAL
					.withDescription(e.getLocalizedMessage())
					.withCause(e)
					.asRuntimeException()
			);
		}
	}

	/**
	 * Convert Browser from UUID
	 * @param uuid
	 * @param withFields
	 * @return
	 */
	private Browser.Builder getBrowser(Properties context, int id, boolean withFields) {
		MBrowse browser = ASPUtil.getInstance(context).getBrowse(id);
		//	Convert
		return BrowseConverUtil.convertBrowser(
			context,
			browser,
			withFields
		);
	}



	@Override
	public void getForm(EntityRequest request, StreamObserver<Form> responseObserver) {
		try {
			Form.Builder formBuilder = getForm(Env.getCtx(), request.getId());
			responseObserver.onNext(formBuilder.build());
			responseObserver.onCompleted();
		} catch (Exception e) {
			log.severe(e.getLocalizedMessage());
			e.printStackTrace();
			responseObserver.onError(
				Status.INTERNAL
					.withDescription(e.getLocalizedMessage())
					.withCause(e)
					.asRuntimeException()
			);
		}
	}

	/**
	 * Request Form from uuid
	 * @param context
	 * @param uuid
	 * @param id
	 */
	private Form.Builder getForm(Properties context, int formId) {
		final String whereClause = I_AD_Form.COLUMNNAME_AD_Form_ID + " = ?";
		MForm form = new Query(
			context,
			I_AD_Form.Table_Name,
			whereClause,
			null
		)
			.setParameters(formId)
			.setOnlyActiveRecords(true)
			.first();
		return DictionaryConvertUtil.convertForm(context, form);
	}



//	/**
//	 * Get Field group from Tab
//	 * @param tabId
//	 * @return
//	 */
//	private int[] getFieldGroupIdsFromTab(int tabId) {
//		return DB.getIDsEx(null, "SELECT f.AD_FieldGroup_ID "
//				+ "FROM AD_Field f "
//				+ "INNER JOIN AD_FieldGroup fg ON(fg.AD_FieldGroup_ID = f.AD_FieldGroup_ID) "
//				+ "WHERE f.AD_Tab_ID = ? "
//				+ "AND fg.FieldGroupType = ? "
//				+ "GROUP BY f.AD_FieldGroup_ID", tabId, X_AD_FieldGroup.FIELDGROUPTYPE_Tab);
//	}



	@Override
	public void getField(FieldRequest request, StreamObserver<Field> responseObserver) {
		try {
			Field.Builder fieldBuilder = getField(request);
			responseObserver.onNext(fieldBuilder.build());
			responseObserver.onCompleted();
		} catch (Exception e) {
			log.severe(e.getLocalizedMessage());
			e.printStackTrace();
			responseObserver.onError(
				Status.INTERNAL
					.withDescription(e.getLocalizedMessage())
					.withCause(e)
					.asRuntimeException()
			);
		}
	}

	/**
	 * Convert field from request
	 * @param context
	 * @param request
	 * @return
	 */
	private Field.Builder getField(FieldRequest request) {
		Field.Builder builder = Field.newBuilder();
		//	For UUID
		if(request.getId() > 0) {
			builder = convertFieldById(Env.getCtx(), request.getId());
		} else if(request.getColumnId() > 0) {
			MColumn column = MColumn.get(Env.getCtx(), request.getColumnId());
			if (column == null || column.getAD_Column_ID() <= 0) {
				throw new AdempiereException("@AD_Column_ID@ @NotFound@");
			}
			builder = DictionaryConvertUtil.convertFieldByColumn(Env.getCtx(), column);
		} else if(request.getElementId() > 0) {
			M_Element element = new M_Element(Env.getCtx(), request.getElementId(), null);
			builder = DictionaryConvertUtil.convertFieldByElemnt(Env.getCtx(), element);
		} else if(!Util.isEmpty(request.getElementColumnName())) {
			M_Element element = M_Element.get(Env.getCtx(), request.getElementColumnName());
			builder = DictionaryConvertUtil.convertFieldByElemnt(Env.getCtx(), element);
		} else if(!Util.isEmpty(request.getTableName(), true)
				&& !Util.isEmpty(request.getColumnName(), true)) {
			MTable table = MTable.get(Env.getCtx(), request.getTableName());
			if (table == null || table.getAD_Table_ID() <= 0) {
				throw new AdempiereException("@AD_Table_ID@ @NotFound@");
			}
			MColumn column = table.getColumn(request.getColumnName());
			if (column == null || column.getAD_Column_ID() <= 0) {
				throw new AdempiereException("@AD_Column_ID@ @NotFound@");
			}
			builder = DictionaryConvertUtil.convertFieldByColumn(Env.getCtx(), column);
		}
		return builder;
	}
	
	/**
	 * Convert Field from UUID
	 * @param id
	 * @return
	 */
	private Field.Builder convertFieldById(Properties context, int id) {
		MField field = new Query(context, I_AD_Field.Table_Name, I_AD_Field.COLUMNNAME_AD_Field_ID + " = ?", null)
				.setParameters(id)
				.setOnlyActiveRecords(true)
				.first();
		int fieldId = field.getAD_Field_ID();
		List<MField> customFields = ASPUtil.getInstance(context).getWindowFields(field.getAD_Tab_ID());
		if(customFields != null) {
			Optional<MField> maybeField = customFields.stream().filter(customField -> customField.getAD_Field_ID() == fieldId).findFirst();
			if(maybeField.isPresent()) {
				field = maybeField.get();

				// TODO: Remove conditional with fix the issue https://github.com/solop-develop/backend/issues/28
				String language = context.getProperty(Env.LANGUAGE);
				if(!Language.isBaseLanguage(language)) {
					//	Name
					String value = field.get_Translation(I_AD_Field.COLUMNNAME_Name, language);
					if (!Util.isEmpty(value, true)) {
						field.set_ValueOfColumn(I_AD_Field.COLUMNNAME_Name, value);
					}
					//	Description
					value = field.get_Translation(I_AD_Field.COLUMNNAME_Description, language);
					if (!Util.isEmpty(value, true)) {
						field.set_ValueOfColumn(I_AD_Field.COLUMNNAME_Description, value);
					}
					//	Help
					value = field.get_Translation(I_AD_Tab.COLUMNNAME_Help, language);
					if (!Util.isEmpty(value, true)) {
						field.set_ValueOfColumn(I_AD_Field.COLUMNNAME_Help, value);
					}
				}
			}
		}
		//	Convert
		return WindowConvertUtil.convertField(
			context,
			field,
			true
		);
	}
	



	@Override
	public void listIdentifiersFields(ListFieldsRequest request, StreamObserver<ListFieldsResponse> responseObserver) {
		try {
			if (request == null) {
				throw new AdempiereException("Object Request Null");
			}
			ListFieldsResponse.Builder fielsListBuilder = getIdentifierFields(request);
			responseObserver.onNext(fielsListBuilder.build());
			responseObserver.onCompleted();
		} catch (Exception e) {
			log.severe(e.getLocalizedMessage());
			e.printStackTrace();
			responseObserver.onError(
				Status.INTERNAL
					.withDescription(e.getLocalizedMessage())
					.withCause(e)
					.asRuntimeException()
			);
		}
	}

	private ListFieldsResponse.Builder getIdentifierFields(ListFieldsRequest request) {
		if (Util.isEmpty(request.getTableName(), true)) {
			throw new AdempiereException("@FillMandatory@ @AD_Table_ID@");
		}
		Properties context = Env.getCtx();
		MTable table = MTable.get(context, request.getTableName());
		if (table == null || table.getAD_Table_ID() <= 0) {
			throw new AdempiereException("@AD_Table_ID@ @NotFound@");
		}

		ListFieldsResponse.Builder fieldsListBuilder = ListFieldsResponse.newBuilder();

		final String sql = "SELECT c.AD_Column_ID"
			// + ", c.ColumnName, t.AD_Table_ID, t.TableName, c.ColumnSql "
			+ " FROM AD_Table AS t "
			+ "	INNER JOIN AD_Column c ON (t.AD_Table_ID=c.AD_Table_ID) "
			+ "	WHERE c.AD_Reference_ID = 10 "
			+ " AND t.AD_Table_ID = ? "
			//	Displayed in Window
			+ "	AND EXISTS (SELECT * FROM AD_Field AS f "
			+ "	WHERE f.AD_Column_ID=c.AD_Column_ID "
			+ " AND f.IsDisplayed='Y' AND f.IsEncrypted='N' AND f.ObscureType IS NULL) "
			+ "	ORDER BY c.IsIdentifier DESC, c.SeqNo "
		;

		DB.runResultSet(null, sql, List.of(table.getAD_Table_ID()), resultSet -> {
			int recordCount = 0;
			while(resultSet.next()) {
				MColumn column = MColumn.get(context, resultSet.getInt(MColumn.COLUMNNAME_AD_Column_ID));
				if (column != null) {
					Field.Builder fieldBuilder = DictionaryConvertUtil.convertFieldByColumn(
						context,
						column
					);
					fieldsListBuilder.addFields(fieldBuilder.build());
				}
				recordCount++;
			}
			fieldsListBuilder.setRecordCount(recordCount);
		}).onFailure(throwable -> {
			log.log(Level.SEVERE, sql, throwable);
		});

		//	empty general info
		// if (fieldsListBuilder.getFieldsList().size() == 0) {
		// }

		return fieldsListBuilder;
	}



	@Override
	public void listTableSearchFields(ListFieldsRequest request, StreamObserver<ListFieldsResponse> responseObserver) {
		try {
			if (request == null) {
				throw new AdempiereException("Object Request Null");
			}
			ListFieldsResponse.Builder fielsListBuilder = DictionaryServiceLogic.listTableSearchFields(request);
			responseObserver.onNext(fielsListBuilder.build());
			responseObserver.onCompleted();
		} catch (Exception e) {
			log.severe(e.getLocalizedMessage());
			e.printStackTrace();
			responseObserver.onError(
				Status.INTERNAL
					.withDescription(e.getLocalizedMessage())
					.withCause(e)
					.asRuntimeException()
			);
		}
	}



	@Override
	public void listSearchInfoFields(ListFieldsRequest request, StreamObserver<ListFieldsResponse> responseObserver) {
		try {
			if (request == null) {
				throw new AdempiereException("Object Request Null");
			}
			ListFieldsResponse.Builder fielsListBuilder = DictionaryServiceLogic.listSearchInfoFields(request);
			responseObserver.onNext(fielsListBuilder.build());
			responseObserver.onCompleted();
		} catch (Exception e) {
			log.severe(e.getLocalizedMessage());
			e.printStackTrace();
			responseObserver.onError(
				Status.INTERNAL
					.withDescription(e.getLocalizedMessage())
					.withCause(e)
					.asRuntimeException()
			);
		}
	}

}