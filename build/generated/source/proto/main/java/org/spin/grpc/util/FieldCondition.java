// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: dictionary.proto

package org.spin.grpc.util;

/**
 * <pre>
 *	Field Condition
 * </pre>
 *
 * Protobuf type {@code dictionary.FieldCondition}
 */
public  final class FieldCondition extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:dictionary.FieldCondition)
    FieldConditionOrBuilder {
private static final long serialVersionUID = 0L;
  // Use FieldCondition.newBuilder() to construct.
  private FieldCondition(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private FieldCondition() {
    uuid_ = "";
    condition_ = "";
    stylesheet_ = "";
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private FieldCondition(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          default: {
            if (!parseUnknownFieldProto3(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
          case 10: {
            org.spin.grpc.util.ErrorMessage.Builder subBuilder = null;
            if (errorMessage_ != null) {
              subBuilder = errorMessage_.toBuilder();
            }
            errorMessage_ = input.readMessage(org.spin.grpc.util.ErrorMessage.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(errorMessage_);
              errorMessage_ = subBuilder.buildPartial();
            }

            break;
          }
          case 18: {
            java.lang.String s = input.readStringRequireUtf8();

            uuid_ = s;
            break;
          }
          case 26: {
            java.lang.String s = input.readStringRequireUtf8();

            condition_ = s;
            break;
          }
          case 34: {
            java.lang.String s = input.readStringRequireUtf8();

            stylesheet_ = s;
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return org.spin.grpc.util.ADempiereDictionary.internal_static_dictionary_FieldCondition_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return org.spin.grpc.util.ADempiereDictionary.internal_static_dictionary_FieldCondition_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            org.spin.grpc.util.FieldCondition.class, org.spin.grpc.util.FieldCondition.Builder.class);
  }

  public static final int ERRORMESSAGE_FIELD_NUMBER = 1;
  private org.spin.grpc.util.ErrorMessage errorMessage_;
  /**
   * <code>.dictionary.ErrorMessage errorMessage = 1;</code>
   */
  public boolean hasErrorMessage() {
    return errorMessage_ != null;
  }
  /**
   * <code>.dictionary.ErrorMessage errorMessage = 1;</code>
   */
  public org.spin.grpc.util.ErrorMessage getErrorMessage() {
    return errorMessage_ == null ? org.spin.grpc.util.ErrorMessage.getDefaultInstance() : errorMessage_;
  }
  /**
   * <code>.dictionary.ErrorMessage errorMessage = 1;</code>
   */
  public org.spin.grpc.util.ErrorMessageOrBuilder getErrorMessageOrBuilder() {
    return getErrorMessage();
  }

  public static final int UUID_FIELD_NUMBER = 2;
  private volatile java.lang.Object uuid_;
  /**
   * <code>string uuid = 2;</code>
   */
  public java.lang.String getUuid() {
    java.lang.Object ref = uuid_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      uuid_ = s;
      return s;
    }
  }
  /**
   * <code>string uuid = 2;</code>
   */
  public com.google.protobuf.ByteString
      getUuidBytes() {
    java.lang.Object ref = uuid_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      uuid_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int CONDITION_FIELD_NUMBER = 3;
  private volatile java.lang.Object condition_;
  /**
   * <code>string condition = 3;</code>
   */
  public java.lang.String getCondition() {
    java.lang.Object ref = condition_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      condition_ = s;
      return s;
    }
  }
  /**
   * <code>string condition = 3;</code>
   */
  public com.google.protobuf.ByteString
      getConditionBytes() {
    java.lang.Object ref = condition_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      condition_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int STYLESHEET_FIELD_NUMBER = 4;
  private volatile java.lang.Object stylesheet_;
  /**
   * <code>string Stylesheet = 4;</code>
   */
  public java.lang.String getStylesheet() {
    java.lang.Object ref = stylesheet_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      stylesheet_ = s;
      return s;
    }
  }
  /**
   * <code>string Stylesheet = 4;</code>
   */
  public com.google.protobuf.ByteString
      getStylesheetBytes() {
    java.lang.Object ref = stylesheet_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      stylesheet_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (errorMessage_ != null) {
      output.writeMessage(1, getErrorMessage());
    }
    if (!getUuidBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, uuid_);
    }
    if (!getConditionBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, condition_);
    }
    if (!getStylesheetBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 4, stylesheet_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (errorMessage_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, getErrorMessage());
    }
    if (!getUuidBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, uuid_);
    }
    if (!getConditionBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, condition_);
    }
    if (!getStylesheetBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(4, stylesheet_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof org.spin.grpc.util.FieldCondition)) {
      return super.equals(obj);
    }
    org.spin.grpc.util.FieldCondition other = (org.spin.grpc.util.FieldCondition) obj;

    boolean result = true;
    result = result && (hasErrorMessage() == other.hasErrorMessage());
    if (hasErrorMessage()) {
      result = result && getErrorMessage()
          .equals(other.getErrorMessage());
    }
    result = result && getUuid()
        .equals(other.getUuid());
    result = result && getCondition()
        .equals(other.getCondition());
    result = result && getStylesheet()
        .equals(other.getStylesheet());
    result = result && unknownFields.equals(other.unknownFields);
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    if (hasErrorMessage()) {
      hash = (37 * hash) + ERRORMESSAGE_FIELD_NUMBER;
      hash = (53 * hash) + getErrorMessage().hashCode();
    }
    hash = (37 * hash) + UUID_FIELD_NUMBER;
    hash = (53 * hash) + getUuid().hashCode();
    hash = (37 * hash) + CONDITION_FIELD_NUMBER;
    hash = (53 * hash) + getCondition().hashCode();
    hash = (37 * hash) + STYLESHEET_FIELD_NUMBER;
    hash = (53 * hash) + getStylesheet().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static org.spin.grpc.util.FieldCondition parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.spin.grpc.util.FieldCondition parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.spin.grpc.util.FieldCondition parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.spin.grpc.util.FieldCondition parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.spin.grpc.util.FieldCondition parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.spin.grpc.util.FieldCondition parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.spin.grpc.util.FieldCondition parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static org.spin.grpc.util.FieldCondition parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static org.spin.grpc.util.FieldCondition parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static org.spin.grpc.util.FieldCondition parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static org.spin.grpc.util.FieldCondition parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static org.spin.grpc.util.FieldCondition parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(org.spin.grpc.util.FieldCondition prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * <pre>
   *	Field Condition
   * </pre>
   *
   * Protobuf type {@code dictionary.FieldCondition}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:dictionary.FieldCondition)
      org.spin.grpc.util.FieldConditionOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return org.spin.grpc.util.ADempiereDictionary.internal_static_dictionary_FieldCondition_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return org.spin.grpc.util.ADempiereDictionary.internal_static_dictionary_FieldCondition_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              org.spin.grpc.util.FieldCondition.class, org.spin.grpc.util.FieldCondition.Builder.class);
    }

    // Construct using org.spin.grpc.util.FieldCondition.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    public Builder clear() {
      super.clear();
      if (errorMessageBuilder_ == null) {
        errorMessage_ = null;
      } else {
        errorMessage_ = null;
        errorMessageBuilder_ = null;
      }
      uuid_ = "";

      condition_ = "";

      stylesheet_ = "";

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return org.spin.grpc.util.ADempiereDictionary.internal_static_dictionary_FieldCondition_descriptor;
    }

    public org.spin.grpc.util.FieldCondition getDefaultInstanceForType() {
      return org.spin.grpc.util.FieldCondition.getDefaultInstance();
    }

    public org.spin.grpc.util.FieldCondition build() {
      org.spin.grpc.util.FieldCondition result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public org.spin.grpc.util.FieldCondition buildPartial() {
      org.spin.grpc.util.FieldCondition result = new org.spin.grpc.util.FieldCondition(this);
      if (errorMessageBuilder_ == null) {
        result.errorMessage_ = errorMessage_;
      } else {
        result.errorMessage_ = errorMessageBuilder_.build();
      }
      result.uuid_ = uuid_;
      result.condition_ = condition_;
      result.stylesheet_ = stylesheet_;
      onBuilt();
      return result;
    }

    public Builder clone() {
      return (Builder) super.clone();
    }
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.setField(field, value);
    }
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof org.spin.grpc.util.FieldCondition) {
        return mergeFrom((org.spin.grpc.util.FieldCondition)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(org.spin.grpc.util.FieldCondition other) {
      if (other == org.spin.grpc.util.FieldCondition.getDefaultInstance()) return this;
      if (other.hasErrorMessage()) {
        mergeErrorMessage(other.getErrorMessage());
      }
      if (!other.getUuid().isEmpty()) {
        uuid_ = other.uuid_;
        onChanged();
      }
      if (!other.getCondition().isEmpty()) {
        condition_ = other.condition_;
        onChanged();
      }
      if (!other.getStylesheet().isEmpty()) {
        stylesheet_ = other.stylesheet_;
        onChanged();
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      org.spin.grpc.util.FieldCondition parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (org.spin.grpc.util.FieldCondition) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private org.spin.grpc.util.ErrorMessage errorMessage_ = null;
    private com.google.protobuf.SingleFieldBuilderV3<
        org.spin.grpc.util.ErrorMessage, org.spin.grpc.util.ErrorMessage.Builder, org.spin.grpc.util.ErrorMessageOrBuilder> errorMessageBuilder_;
    /**
     * <code>.dictionary.ErrorMessage errorMessage = 1;</code>
     */
    public boolean hasErrorMessage() {
      return errorMessageBuilder_ != null || errorMessage_ != null;
    }
    /**
     * <code>.dictionary.ErrorMessage errorMessage = 1;</code>
     */
    public org.spin.grpc.util.ErrorMessage getErrorMessage() {
      if (errorMessageBuilder_ == null) {
        return errorMessage_ == null ? org.spin.grpc.util.ErrorMessage.getDefaultInstance() : errorMessage_;
      } else {
        return errorMessageBuilder_.getMessage();
      }
    }
    /**
     * <code>.dictionary.ErrorMessage errorMessage = 1;</code>
     */
    public Builder setErrorMessage(org.spin.grpc.util.ErrorMessage value) {
      if (errorMessageBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        errorMessage_ = value;
        onChanged();
      } else {
        errorMessageBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.dictionary.ErrorMessage errorMessage = 1;</code>
     */
    public Builder setErrorMessage(
        org.spin.grpc.util.ErrorMessage.Builder builderForValue) {
      if (errorMessageBuilder_ == null) {
        errorMessage_ = builderForValue.build();
        onChanged();
      } else {
        errorMessageBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.dictionary.ErrorMessage errorMessage = 1;</code>
     */
    public Builder mergeErrorMessage(org.spin.grpc.util.ErrorMessage value) {
      if (errorMessageBuilder_ == null) {
        if (errorMessage_ != null) {
          errorMessage_ =
            org.spin.grpc.util.ErrorMessage.newBuilder(errorMessage_).mergeFrom(value).buildPartial();
        } else {
          errorMessage_ = value;
        }
        onChanged();
      } else {
        errorMessageBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.dictionary.ErrorMessage errorMessage = 1;</code>
     */
    public Builder clearErrorMessage() {
      if (errorMessageBuilder_ == null) {
        errorMessage_ = null;
        onChanged();
      } else {
        errorMessage_ = null;
        errorMessageBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.dictionary.ErrorMessage errorMessage = 1;</code>
     */
    public org.spin.grpc.util.ErrorMessage.Builder getErrorMessageBuilder() {
      
      onChanged();
      return getErrorMessageFieldBuilder().getBuilder();
    }
    /**
     * <code>.dictionary.ErrorMessage errorMessage = 1;</code>
     */
    public org.spin.grpc.util.ErrorMessageOrBuilder getErrorMessageOrBuilder() {
      if (errorMessageBuilder_ != null) {
        return errorMessageBuilder_.getMessageOrBuilder();
      } else {
        return errorMessage_ == null ?
            org.spin.grpc.util.ErrorMessage.getDefaultInstance() : errorMessage_;
      }
    }
    /**
     * <code>.dictionary.ErrorMessage errorMessage = 1;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        org.spin.grpc.util.ErrorMessage, org.spin.grpc.util.ErrorMessage.Builder, org.spin.grpc.util.ErrorMessageOrBuilder> 
        getErrorMessageFieldBuilder() {
      if (errorMessageBuilder_ == null) {
        errorMessageBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            org.spin.grpc.util.ErrorMessage, org.spin.grpc.util.ErrorMessage.Builder, org.spin.grpc.util.ErrorMessageOrBuilder>(
                getErrorMessage(),
                getParentForChildren(),
                isClean());
        errorMessage_ = null;
      }
      return errorMessageBuilder_;
    }

    private java.lang.Object uuid_ = "";
    /**
     * <code>string uuid = 2;</code>
     */
    public java.lang.String getUuid() {
      java.lang.Object ref = uuid_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        uuid_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string uuid = 2;</code>
     */
    public com.google.protobuf.ByteString
        getUuidBytes() {
      java.lang.Object ref = uuid_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        uuid_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string uuid = 2;</code>
     */
    public Builder setUuid(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      uuid_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string uuid = 2;</code>
     */
    public Builder clearUuid() {
      
      uuid_ = getDefaultInstance().getUuid();
      onChanged();
      return this;
    }
    /**
     * <code>string uuid = 2;</code>
     */
    public Builder setUuidBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      uuid_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object condition_ = "";
    /**
     * <code>string condition = 3;</code>
     */
    public java.lang.String getCondition() {
      java.lang.Object ref = condition_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        condition_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string condition = 3;</code>
     */
    public com.google.protobuf.ByteString
        getConditionBytes() {
      java.lang.Object ref = condition_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        condition_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string condition = 3;</code>
     */
    public Builder setCondition(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      condition_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string condition = 3;</code>
     */
    public Builder clearCondition() {
      
      condition_ = getDefaultInstance().getCondition();
      onChanged();
      return this;
    }
    /**
     * <code>string condition = 3;</code>
     */
    public Builder setConditionBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      condition_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object stylesheet_ = "";
    /**
     * <code>string Stylesheet = 4;</code>
     */
    public java.lang.String getStylesheet() {
      java.lang.Object ref = stylesheet_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        stylesheet_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string Stylesheet = 4;</code>
     */
    public com.google.protobuf.ByteString
        getStylesheetBytes() {
      java.lang.Object ref = stylesheet_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        stylesheet_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string Stylesheet = 4;</code>
     */
    public Builder setStylesheet(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      stylesheet_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string Stylesheet = 4;</code>
     */
    public Builder clearStylesheet() {
      
      stylesheet_ = getDefaultInstance().getStylesheet();
      onChanged();
      return this;
    }
    /**
     * <code>string Stylesheet = 4;</code>
     */
    public Builder setStylesheetBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      stylesheet_ = value;
      onChanged();
      return this;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFieldsProto3(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:dictionary.FieldCondition)
  }

  // @@protoc_insertion_point(class_scope:dictionary.FieldCondition)
  private static final org.spin.grpc.util.FieldCondition DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new org.spin.grpc.util.FieldCondition();
  }

  public static org.spin.grpc.util.FieldCondition getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<FieldCondition>
      PARSER = new com.google.protobuf.AbstractParser<FieldCondition>() {
    public FieldCondition parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new FieldCondition(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<FieldCondition> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<FieldCondition> getParserForType() {
    return PARSER;
  }

  public org.spin.grpc.util.FieldCondition getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

