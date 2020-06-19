package org.ifool.javaredis.transport;

import java.util.concurrent.atomic.AtomicLong;

import org.ifool.javaredis.utils.Constants;

public class Request {

	public static AtomicLong idGenerator = new AtomicLong(0);
	
	//id主要供远程调用
	public Request(String op, String key, byte[] data, long expireSeconds) {
		this.id = idGenerator.getAndIncrement();
		this.op = Constants.operationMap.get(op);
		this.key = key;
		this.data = data;
		this.setExpireSeconds(expireSeconds);
	}
	//本地的可以固定一个id，减少性能损耗
	public Request(long id, String op, String key, byte[] data, long expireSeconds) {
		this.id = id;
		this.op = Constants.operationMap.get(op);
		this.key = key;
		this.data = data;
		this.setExpireSeconds(expireSeconds);
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
	public long getExpireSeconds() {
		return expireSeconds;
	}
	public void setExpireSeconds(long expireSeconds) {
		this.expireSeconds = expireSeconds;
	}
	private long id;
	private byte op;
	private String key;
	private byte[] data;
	private long expireSeconds;
}
