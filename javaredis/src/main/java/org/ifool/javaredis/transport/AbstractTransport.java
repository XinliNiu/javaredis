package org.ifool.javaredis.transport;

public abstract class AbstractTransport implements Transport {

	public Response send(Request req) {
		byte[] bytes = send(req.toBytes());
		if(bytes != null) {
			try {
				Response res = new Response(bytes);
				return res;
			} catch(Exception e) {
				
			}
		}
		return null;
	}

	abstract protected byte[] send(byte[] req);
}
