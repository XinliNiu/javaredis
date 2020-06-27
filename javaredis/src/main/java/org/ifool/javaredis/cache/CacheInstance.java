package org.ifool.javaredis.cache;

import java.util.concurrent.ConcurrentHashMap;

import org.ifool.javaredis.utils.Constants;

public class CacheInstance  {

	public CacheInstance() {
		cache = new ConcurrentHashMap<String,CacheObject>(1024);	
	}

	private ConcurrentHashMap<String,CacheObject> cache;

	public byte set(String key, byte[] value, long expireSeconds) {
		CacheObject co = new CacheObject(value, expireSeconds);	
		cache.put(key, co);
		return Constants.ERROR_CODE_SUCCESS;
	}



	public byte[] get(String key) {
		CacheObject co = cache.get(key);
		if(co != null) {
			return co.getValue();
		} else {
			return null;
		}
	}



	public byte del(String key) {
		CacheObject co = cache.get(key);
		if(co == null) {
			return Constants.ERROR_CODE_NOTEXIST;
		} else {
			return Constants.ERROR_CODE_SUCCESS;
		}
	}

}