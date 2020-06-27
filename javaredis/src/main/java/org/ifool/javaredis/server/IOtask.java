package org.ifool.javaredis.server;

import java.util.concurrent.Executor;

import org.ifool.javaredis.cache.CacheInstance;
import org.ifool.javaredis.transport.Request;

public class IOtask implements Runnable {

	private RedisChannel channel;
	private Executor cacheExecutor;
	private CacheInstance cache;
	public IOtask(RedisChannel channel, CacheInstance cache, Executor cacheExecutor) {
		this.channel = channel;
		this.cache = cache;
		this.cacheExecutor = cacheExecutor;
	}
	public void run() {
		byte[] bytes = channel.read();
		Request req = new Request(bytes);
		cacheExecutor.execute(new CacheTask(cache, req, channel));
	}

}
