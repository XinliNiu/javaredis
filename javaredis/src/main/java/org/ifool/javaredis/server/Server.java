package org.ifool.javaredis.server;

public interface Server {
	public String set(String key, byte[] value);
	public byte[] get(String key);
	public boolean exists(String key);
	public long expire(String key);
	public String del(String key);
}
