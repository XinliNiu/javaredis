package org.ifool.javaredis.transport;

public interface Transport {
	public Response send(Request req);
}
