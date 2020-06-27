package org.ifool.javaredis.shortconnection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.Channel;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.ifool.javaredis.cache.CacheInstance;
import org.ifool.javaredis.server.AbstractServer;
import org.ifool.javaredis.server.DirectExecutor;
import org.ifool.javaredis.server.RedisChannel;

public class ShortConnectionServer extends AbstractServer{

	private int port;
	private ServerSocket serverSocket;
	public ShortConnectionServer(int port) {
		this.port = port;
		this.setCache(new CacheInstance());
		ThreadPoolExecutor executor = new ThreadPoolExecutor(50, 100, 30, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(1000));
		//this.setCacheExecutor(new DirectExecutor());
		//this.setIOexecutor(new DirectExecutor());  //DirectExecutor方便调试
		
		this.setCacheExecutor(executor);
		this.setIOexecutor(executor);
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(true) {
			try {
				Socket socket = serverSocket.accept();
				
				RedisChannel channel = new ShortConnectionChannel(socket);
				
				received(channel);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
