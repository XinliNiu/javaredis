package org.ifool.javaredis.cache;

public class CacheObject {

	public CacheObject(byte[] value, long expireSeconds) {
		this.value = value;
		long currentTime = System.currentTimeMillis();
		this.putTime = currentTime;
		this.lastAccessTime = currentTime;
		if(expireSeconds == 0) {
			expireTime = Long.MAX_VALUE;
		} else {
			expireTime = currentTime + expireSeconds * 1000;
		}
	}
	
	public byte[] getValue() {
		return value;
	}
	public void setValue(byte[] value) {
		this.value = value;
	}
	public long getPutTime() {
		return putTime;
	}
	public void setPutTime(long putTime) {
		this.putTime = putTime;
	}
	public long getLastAccessTime() {
		return lastAccessTime;
	}
	public void setLastAccessTime(long lastAccessTime) {
		this.lastAccessTime = lastAccessTime;
	}
	public long getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(long expireTime) {
		this.expireTime = expireTime;
	}

	private byte[] value;
	private	long putTime;
	private long lastAccessTime;
	private long expireTime;

}
