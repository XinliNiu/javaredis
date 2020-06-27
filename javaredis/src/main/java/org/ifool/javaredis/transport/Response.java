package org.ifool.javaredis.transport;

import org.ifool.javaredis.utils.ByteUtil;
import org.ifool.javaredis.utils.Constants;

/**
 * 
 * 
 * @author xlniu
 *
 *
 *  | 8 byte id| 1 byte op | 1 byte errorcode | 4 byte data length | data 
 */
public class Response {

	
	public byte[] toBytes() {
		int len = 8 + 1 + 1 + 4;
		if( data != null && data.length > 0) {
			len += data.length;
		}
		
		byte[] bytes = new byte[len];
		
		int offset = 0;
		
		ByteUtil.long2bytes(id, bytes, offset);
		offset += 8;
		
		bytes[offset] = op;
		offset += 1;
		
		bytes[offset] = errorCode;
		offset += 1;
		
		if(data != null && data.length > 0) {
			ByteUtil.int2bytes(data.length, bytes, offset);
			offset += 4;
			System.arraycopy(data, 0, bytes, offset, data.length);
		} else {
			ByteUtil.int2bytes(0, bytes, offset);
			offset += 4;
		}
		
		return bytes;
	}
	
	public Response(byte[] bytes) {
		int offset = 0;
		this.id = ByteUtil.bytes2long(bytes, offset);
		offset += 8;
		this.op = bytes[offset++];
		this.errorCode = bytes[offset++];
		if(errorCode == Constants.ERROR_CODE_SUCCESS) {
			int dataLength = ByteUtil.bytes2int(bytes, offset);
			if(dataLength > 0) {
				byte[] buf = new byte[dataLength];
				System.arraycopy(buf, 0, bytes, offset, dataLength);
				this.data = buf;
			}
		}
	}
	
	public Response(long id, byte op, byte errorCode, byte[] data) {
		this.id = id;
		this.errorCode = errorCode;
		this.data = data;
		this.op = op;
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
