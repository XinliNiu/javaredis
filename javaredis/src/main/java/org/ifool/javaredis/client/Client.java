package org.ifool.javaredis.client;

public interface Client {
	public String set(String key, byte[] value, long expireSeconds);
	public String setnx(String key, byte[] value, long expireSeconds);
	public String setex(String key, byte[] value, long expireSeconds);
	public byte[] get(String key);
	public boolean exists(String key);
	public long expire(String key,long expireSeconds);
	public String del(String key);
	public long ttl(String key);
}
