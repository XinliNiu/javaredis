package org.ifool.javaredis.transport;

import java.util.concurrent.atomic.AtomicLong;

import org.ifool.javaredis.utils.ByteUtil;
import org.ifool.javaredis.utils.Constants;

/**
 * 
 * @author xlniu
 * 
 *
 * <p> 报文格式：
 * 
 * 
 * <p> | 8字节id   |1字节opcode|4字节key长度|4字节value长度 |key | value(可能没有)
 * 
 * 
 * <p> | -------- |  -  | ---- |  ----???----- | ---- | ----????----
 */

public class Request {

	public static AtomicLong idGenerator = new AtomicLong(0);
	
	public byte[] toBytes() {
		int len = 8 + 1 + 4 + 4;
		int keyLen = key.getBytes().length;
		len = len + keyLen;
		
		int valueLen = 0;
		if(data != null) {
			valueLen = data.length;
			len = len + valueLen;
		}
		
		byte[] message = new byte[len];
		ByteUtil.long2bytes(id, message, 0);
		message[8] = op;
		ByteUtil.int2bytes(keyLen, message, 9);
		ByteUtil.int2bytes(valueLen, message,13);
		System.arraycopy(key.getBytes(), 0, message, 17 , keyLen);
		if(valueLen != 0) {
			System.arraycopy(data, 0, message, 17+keyLen, valueLen);
		}
		return message;
	}
	
	public Request(byte[] message) {
		if(message.length < 14) {
			throw new RuntimeException();
		}
		int currentPointer = 0;
		this.id = ByteUtil.bytes2long(message, currentPointer);
		currentPointer += 8;
		this.op = message[currentPointer++];
		
		int keyLen = ByteUtil.bytes2int(message, currentPointer);
		currentPointer += 4;
		int valueLen = ByteUtil.bytes2int(message, currentPointer);
		currentPointer += 4;
		this.key = new String(message, currentPointer, keyLen);
		currentPointer += keyLen;
		
		if(valueLen != 0) {
			this.data = new byte[valueLen];
			System.arraycopy(message, currentPointer, this.data, 0, valueLen);
		}
		
	}
	
	//id主要供远程调用
	public Request(String op, String key, byte[] data) {
		this.id = idGenerator.getAndIncrement();
		this.op = Constants.operationMap.get(op);
		this.key = key;
		this.data = data;
	}
	//本地的可以固定一个id，减少性能损耗
	public Request(long id, String op, String key, byte[] data) {
		this.id = id;
		this.op = Constants.operationMap.get(op);
		this.key = key;
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
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public byte[] getData() {
		return data;
	}
	public void setData(byte[] data) {
		this.data = data;
	}

	private long id;
	private byte op;
	private String key;
	private byte[] data;
}
