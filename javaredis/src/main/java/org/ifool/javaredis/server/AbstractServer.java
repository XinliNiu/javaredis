package org.ifool.javaredis.server;

import java.util.concurrent.Executor;

import org.ifool.javaredis.cache.CacheInstance;
import org.ifool.javaredis.transport.Request;

public  class AbstractServer implements Server {



	public CacheInstance getCache() {
		return cache;
	}

	public void setCache(CacheInstance cache) {
		this.cache = cache;
	}

	public Executor getIOexecutor() {
		return IOexecutor;
	}

	public void setIOexecutor(Executor iOexecutor) {
		IOexecutor = iOexecutor;
	}

	public Executor getCacheExecutor() {
		return CacheExecutor;
	}

	public void setCacheExecutor(Executor cacheExecutor) {
		CacheExecutor = cacheExecutor;
	}

	private Executor IOexecutor;
	private Executor CacheExecutor;
	private CacheInstance cache;
	
	public void received(RedisChannel channel) {
			
		IOexecutor.execute(new IOtask(channel, cache, CacheExecutor));
		
	}

	public void shutdown() {
		// TODO Auto-generated method stub
		
	}

	


}
