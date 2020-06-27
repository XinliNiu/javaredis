package org.ifool.javaredis.client;

public interface Client {
	public byte set(String key, byte[] value);
	public byte[] get(String key);
	public byte del(String key);
}
