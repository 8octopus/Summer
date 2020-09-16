package com.swingfrog.summer.protocol;

public class ProtocolConst {

	public static final String SERVER_PROTOCOL_STRING_LINE = "StringLine";
	public static final String SERVER_PROTOCOL_WEB_SOCKET = "WebSocket";
	public static final String SERVER_PROTOCOL_LENGTH_FIELD = "LengthField";
	public static final String SERVER_PROTOCOL_HTTP = "Http";
	public static final String SERVER_PROTOCOL_WEB_SOCKET_STANDARD = "WebSocket-Standard";
	public static final String SERVER_PROTOCOL_WEB_SOCKET_STANDARD_PROTOBUF = "WebSocket-Standard-Protobuf";
	public static final String SERVER_PROTOCOL_LENGTH_FIELD_PROTOBUF = "LengthField-Protobuf";

	public static final String PING = "ping";
	public static final String PONG = "pong";
	public static final String RPC = "rpc";
	public static final String RPC_SPLIT = "\t";

	public static final int PROTOBUF_HEART_BEAT_REQ_ID = 0;
	public static final int PROTOBUF_HEART_BEAT_RESP_ID = 1;
	public static final int PROTOBUF_ERROR_CODE_PUSH_ID = 2;

	public static boolean isProtobuf(String protocol) {
		return protocol.contains("Protobuf");
	}

}
