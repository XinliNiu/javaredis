package org.ifool.javaredis.server;

public interface RedisChannel {
	public void send(byte[] data);
	public byte[] read();
	public void close();
}
