package org.ifool.javaredis.client;

import org.ifool.javaredis.transport.Request;
import org.ifool.javaredis.transport.Response;
import org.ifool.javaredis.transport.Transport;
import org.ifool.javaredis.utils.Constants;

public abstract class AbstractClient implements Client {


	private Transport transport;

	public Transport getTransport() {
		return transport;
	}



	public void setTransport(Transport transport) {
		this.transport = transport;
	}



	public byte set(String key, byte[] value) {
		Request req = new Request("set", key, value);
		Response res = transport.send(req);
		if(res != null) {
			byte errorCode = res.getErrorCode();
			return errorCode;
		}
		return Constants.ERROR_CODE_UNKNOWN;
	}



	public byte[] get(String key) {
		Request req = new Request("get", key, null);
		Response res = transport.send(req);
		if(res != null) {
			byte errorCode = res.getErrorCode();
			if(errorCode == 0) {
				return res.getData();
			}
		}
		return null;
		
	}




	public byte del(String key) {
		Request req = new Request("get", key, null);
		Response res = transport.send(req);
		if(res != null) {
			byte errorCode = res.getErrorCode();
			return errorCode;
		}
		return Constants.ERROR_CODE_UNKNOWN;
	}

	


}
