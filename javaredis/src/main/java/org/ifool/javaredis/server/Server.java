package org.ifool.javaredis.server;

public interface Server {
	
	void received(RedisChannel channel);
	void shutdown();
	
}
