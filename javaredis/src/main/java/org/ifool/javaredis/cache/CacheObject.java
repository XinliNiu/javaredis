package org.ifool.javaredis.cache;

public class CacheObject {

	public CacheObject() {
		
	}
	
	private byte[] value;
	private	long putTime;
	private long lastAccessTime;
	private long expireTime;

}
