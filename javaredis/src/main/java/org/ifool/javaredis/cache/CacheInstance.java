package org.ifool.javaredis.cache;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CacheInstance {

	public CacheInstance() {
		int cpus = Runtime.getRuntime().availableProcessors();
		caches = new ConcurrentHashMap[cpus];		
	}

	public ConcurrentHashMap<String,CacheObject>[] caches;
	
	
	
}