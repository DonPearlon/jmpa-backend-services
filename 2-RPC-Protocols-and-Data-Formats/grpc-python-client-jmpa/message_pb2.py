# -*- coding: utf-8 -*-
# Generated by the protocol buffer compiler.  DO NOT EDIT!
# source: message.proto
"""Generated protocol buffer code."""
from google.protobuf.internal import builder as _builder
from google.protobuf import descriptor as _descriptor
from google.protobuf import descriptor_pool as _descriptor_pool
from google.protobuf import symbol_database as _symbol_database
# @@protoc_insertion_point(imports)

_sym_db = _symbol_database.Default()




DESCRIPTOR = _descriptor_pool.Default().AddSerializedFile(b'\n\rmessage.proto\x12\x1a\x63om.aterehov.stubs.message\"!\n\x0eMessageRequest\x12\x0f\n\x07message\x18\x01 \x01(\t\"\"\n\x0fMessageResponse\x12\x0f\n\x07message\x18\x01 \x01(\t2\xe4\x03\n\x0eMessageService\x12\x62\n\x07process\x12*.com.aterehov.stubs.message.MessageRequest\x1a+.com.aterehov.stubs.message.MessageResponse\x12w\n\x1aprocessServerSideStreaming\x12*.com.aterehov.stubs.message.MessageRequest\x1a+.com.aterehov.stubs.message.MessageResponse0\x01\x12w\n\x1aprocessClientSideStreaming\x12*.com.aterehov.stubs.message.MessageRequest\x1a+.com.aterehov.stubs.message.MessageResponse(\x01\x12|\n\x1dprocessBidirectionalStreaming\x12*.com.aterehov.stubs.message.MessageRequest\x1a+.com.aterehov.stubs.message.MessageResponse(\x01\x30\x01\x42\x1e\n\x1a\x63om.aterehov.stubs.messageP\x01\x62\x06proto3')

_builder.BuildMessageAndEnumDescriptors(DESCRIPTOR, globals())
_builder.BuildTopDescriptorsAndMessages(DESCRIPTOR, 'message_pb2', globals())
if _descriptor._USE_C_DESCRIPTORS == False:

  DESCRIPTOR._options = None
  DESCRIPTOR._serialized_options = b'\n\032com.aterehov.stubs.messageP\001'
  _MESSAGEREQUEST._serialized_start=45
  _MESSAGEREQUEST._serialized_end=78
  _MESSAGERESPONSE._serialized_start=80
  _MESSAGERESPONSE._serialized_end=114
  _MESSAGESERVICE._serialized_start=117
  _MESSAGESERVICE._serialized_end=601
# @@protoc_insertion_point(module_scope)