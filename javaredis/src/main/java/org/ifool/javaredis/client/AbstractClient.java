package org.ifool.javaredis.client;

import org.ifool.javaredis.transport.Request;
import org.ifool.javaredis.transport.Response;
import org.ifool.javaredis.transport.Transport;
import org.ifool.javaredis.utils.Constants;

public abstract class AbstractClient implements Client {


	private Transport transport;

	public String set(String key, byte[] value, long expireSeconds) {
		Request req = new Request("set", key, value, expireSeconds);
		Response res = transport.send(req);
		if(res != null) {
			if(res.getErrorCode() == 0) {
				return Constants.SUCCESS;
			} else {
				return "error";
			}
		}
	}

	public String setnx(String key, byte[] value, long expireSeconds) {
		Request req = new Request("setnx", key, value, expireSeconds);
		Response res = transport.send(req);
		if(res != null) {
			if(res.getErrorCode() == 0) {
				return "
			}
		}
	}

	public String setex(String key, byte[] value, long expireSeconds) {
		Request req = new Request("setex", key, value, expireSeconds);
		Response res = transport.send(req);
		if(res != null) {
			
		}
	}

	public byte[] get(String key) {
		
	}

	public boolean exists(String key) {
		// TODO Auto-generated method stub
		return false;
	}

	public long expire(String key, long expireSeconds) {
		// TODO Auto-generated method stub
		return 0;
	}

	public String del(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	public long ttl(String key) {
		// TODO Auto-generated method stub
		return 0;
	}
	


}
