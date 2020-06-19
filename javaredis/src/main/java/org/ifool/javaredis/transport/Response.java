package org.ifool.javaredis.transport;

public class Response {

	
	public Response(long id, byte errorCode, byte[] data) {
		this.id = id;
		this.errorCode = errorCode;
		this.data = data;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public byte getOp() {
		return op;
	}
	public void setOp(byte op) {
		this.op = op;
	}
	public byte[] getData() {
		return data;
	}
	public void setData(byte[] data) {
		this.data = data;
	}

	public byte getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(byte errorCode) {
		this.errorCode = errorCode;
	}

	private long id;
	private byte errorCode;
	private byte op;
	private byte[] data;
}
