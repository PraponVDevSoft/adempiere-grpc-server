// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: dictionary.proto

package org.spin.grpc.util;

public interface FormOrBuilder extends
    // @@protoc_insertion_point(interface_extends:dictionary.Form)
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
   * <code>string name = 3;</code>
   */
  java.lang.String getName();
  /**
   * <code>string name = 3;</code>
   */
  com.google.protobuf.ByteString
      getNameBytes();

  /**
   * <code>string description = 4;</code>
   */
  java.lang.String getDescription();
  /**
   * <code>string description = 4;</code>
   */
  com.google.protobuf.ByteString
      getDescriptionBytes();

  /**
   * <code>string help = 5;</code>
   */
  java.lang.String getHelp();
  /**
   * <code>string help = 5;</code>
   */
  com.google.protobuf.ByteString
      getHelpBytes();

  /**
   * <code>int32 accessLevel = 6;</code>
   */
  int getAccessLevel();

  /**
   * <code>string className = 7;</code>
   */
  java.lang.String getClassName();
  /**
   * <code>string className = 7;</code>
   */
  com.google.protobuf.ByteString
      getClassNameBytes();
}
