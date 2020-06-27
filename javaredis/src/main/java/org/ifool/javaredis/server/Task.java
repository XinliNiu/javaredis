package org.ifool.javaredis.server;

import org.ifool.javaredis.cache.CacheInstance;
import org.ifool.javaredis.transport.Request;
import org.ifool.javaredis.transport.Response;
import org.ifool.javaredis.utils.Constants;

public class Task implements Runnable{

	private CacheInstance cache;
	private RedisChannel channel;
	private Request req;
	public Task(CacheInstance cache, RedisChannel channel, Request req) {
		this.cache = cache;
		this.channel = channel;
		this.req = req;
	}
	public void run() {
		
		byte op = req.getOp();
		if(op == Constants.OP_GET) {
			byte[] value = cache.get(req.getKey());
			Response res = null;
			if(value == null) {
				res = new Response(req.getId(),req.getOp(),  Constants.ERROR_CODE_NOTEXIST,null);
			} else {
				res = new Response(req.getId(),req.getOp(),  Constants.ERROR_CODE_SUCCESS,value);
			}
			channel.send(res.toBytes());
		}
		if(op == Constants.OP_SET) {
			cache.set(req.getKey(), req.getData(), 0);
			Response res = new Response(req.getId(), req.getOp(), Constants.ERROR_CODE_SUCCESS,null);
			channel.send(res.toBytes());
		}
		if(op == Constants.OP_DEL) {
			cache.del(req.getKey());
			Response res = new Response(req.getId(),req.getOp(),  Constants.ERROR_CODE_SUCCESS,null);
			channel.send(res.toBytes());
		}
		channel.close();
	}

}
