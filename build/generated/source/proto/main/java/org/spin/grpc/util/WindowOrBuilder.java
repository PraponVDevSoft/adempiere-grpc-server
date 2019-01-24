// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: dictionary.proto

package org.spin.grpc.util;

public interface WindowOrBuilder extends
    // @@protoc_insertion_point(interface_extends:dictionary.Window)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>.dictionary.ErrorMessage errorMessage = 1;</code>
   */
  boolean hasErrorMessage();
  /**
   * <code>.dictionary.ErrorMessage errorMessage = 1;</code>
   */
  org.spin.grpc.util.ErrorMessage getErrorMessage();
  /**
   * <code>.dictionary.ErrorMessage errorMessage = 1;</code>
   */
  org.spin.grpc.util.ErrorMessageOrBuilder getErrorMessageOrBuilder();

  /**
   * <code>string uuid = 2;</code>
   */
  java.lang.String getUuid();
  /**
   * <code>string uuid = 2;</code>
   */
  com.google.protobuf.ByteString
      getUuidBytes();

  /**
   * <code>string name = 4;</code>
   */
  java.lang.String getName();
  /**
   * <code>string name = 4;</code>
   */
  com.google.protobuf.ByteString
      getNameBytes();

  /**
   * <code>string description = 5;</code>
   */
  java.lang.String getDescription();
  /**
   * <code>string description = 5;</code>
   */
  com.google.protobuf.ByteString
      getDescriptionBytes();

  /**
   * <code>string help = 6;</code>
   */
  java.lang.String getHelp();
  /**
   * <code>string help = 6;</code>
   */
  com.google.protobuf.ByteString
      getHelpBytes();

  /**
   * <code>bool isSOTrx = 7;</code>
   */
  boolean getIsSOTrx();

  /**
   * <pre>
   *	External Info
   * </pre>
   *
   * <code>.dictionary.ContextInfo contextInfo = 8;</code>
   */
  boolean hasContextInfo();
  /**
   * <pre>
   *	External Info
   * </pre>
   *
   * <code>.dictionary.ContextInfo contextInfo = 8;</code>
   */
  org.spin.grpc.util.ContextInfo getContextInfo();
  /**
   * <pre>
   *	External Info
   * </pre>
   *
   * <code>.dictionary.ContextInfo contextInfo = 8;</code>
   */
  org.spin.grpc.util.ContextInfoOrBuilder getContextInfoOrBuilder();

  /**
   * <code>repeated .dictionary.Tab tabs = 9;</code>
   */
  java.util.List<org.spin.grpc.util.Tab> 
      getTabsList();
  /**
   * <code>repeated .dictionary.Tab tabs = 9;</code>
   */
  org.spin.grpc.util.Tab getTabs(int index);
  /**
   * <code>repeated .dictionary.Tab tabs = 9;</code>
   */
  int getTabsCount();
  /**
   * <code>repeated .dictionary.Tab tabs = 9;</code>
   */
  java.util.List<? extends org.spin.grpc.util.TabOrBuilder> 
      getTabsOrBuilderList();
  /**
   * <code>repeated .dictionary.Tab tabs = 9;</code>
   */
  org.spin.grpc.util.TabOrBuilder getTabsOrBuilder(
      int index);
}
