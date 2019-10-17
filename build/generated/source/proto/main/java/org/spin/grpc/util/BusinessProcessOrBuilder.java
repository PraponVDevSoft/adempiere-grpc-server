// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: businessdata.proto

package org.spin.grpc.util;

public interface BusinessProcessOrBuilder extends
    // @@protoc_insertion_point(interface_extends:data.BusinessProcess)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string uuid = 1;</code>
   */
  java.lang.String getUuid();
  /**
   * <code>string uuid = 1;</code>
   */
  com.google.protobuf.ByteString
      getUuidBytes();

  /**
   * <code>string instanceUuid = 2;</code>
   */
  java.lang.String getInstanceUuid();
  /**
   * <code>string instanceUuid = 2;</code>
   */
  com.google.protobuf.ByteString
      getInstanceUuidBytes();

  /**
   * <code>bool isError = 3;</code>
   */
  boolean getIsError();

  /**
   * <code>string summary = 4;</code>
   */
  java.lang.String getSummary();
  /**
   * <code>string summary = 4;</code>
   */
  com.google.protobuf.ByteString
      getSummaryBytes();

  /**
   * <code>string resultTableName = 5;</code>
   */
  java.lang.String getResultTableName();
  /**
   * <code>string resultTableName = 5;</code>
   */
  com.google.protobuf.ByteString
      getResultTableNameBytes();

  /**
   * <code>bool isProcessing = 6;</code>
   */
  boolean getIsProcessing();

  /**
   * <code>int64 lastRun = 7;</code>
   */
  long getLastRun();

  /**
   * <code>repeated .data.ProcessInfoLog logs = 8;</code>
   */
  java.util.List<org.spin.grpc.util.ProcessInfoLog> 
      getLogsList();
  /**
   * <code>repeated .data.ProcessInfoLog logs = 8;</code>
   */
  org.spin.grpc.util.ProcessInfoLog getLogs(int index);
  /**
   * <code>repeated .data.ProcessInfoLog logs = 8;</code>
   */
  int getLogsCount();
  /**
   * <code>repeated .data.ProcessInfoLog logs = 8;</code>
   */
  java.util.List<? extends org.spin.grpc.util.ProcessInfoLogOrBuilder> 
      getLogsOrBuilderList();
  /**
   * <code>repeated .data.ProcessInfoLog logs = 8;</code>
   */
  org.spin.grpc.util.ProcessInfoLogOrBuilder getLogsOrBuilder(
      int index);

  /**
   * <code>map&lt;string, .data.Value&gt; parameters = 9;</code>
   */
  int getParametersCount();
  /**
   * <code>map&lt;string, .data.Value&gt; parameters = 9;</code>
   */
  boolean containsParameters(
      java.lang.String key);
  /**
   * Use {@link #getParametersMap()} instead.
   */
  @java.lang.Deprecated
  java.util.Map<java.lang.String, org.spin.grpc.util.Value>
  getParameters();
  /**
   * <code>map&lt;string, .data.Value&gt; parameters = 9;</code>
   */
  java.util.Map<java.lang.String, org.spin.grpc.util.Value>
  getParametersMap();
  /**
   * <code>map&lt;string, .data.Value&gt; parameters = 9;</code>
   */

  org.spin.grpc.util.Value getParametersOrDefault(
      java.lang.String key,
      org.spin.grpc.util.Value defaultValue);
  /**
   * <code>map&lt;string, .data.Value&gt; parameters = 9;</code>
   */

  org.spin.grpc.util.Value getParametersOrThrow(
      java.lang.String key);

  /**
   * <code>.data.ProcessOutput output = 10;</code>
   */
  boolean hasOutput();
  /**
   * <code>.data.ProcessOutput output = 10;</code>
   */
  org.spin.grpc.util.ProcessOutput getOutput();
  /**
   * <code>.data.ProcessOutput output = 10;</code>
   */
  org.spin.grpc.util.ProcessOutputOrBuilder getOutputOrBuilder();
}
