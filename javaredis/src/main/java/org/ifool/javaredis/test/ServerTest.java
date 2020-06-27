package org.ifool.javaredis.test;

import org.ifool.javaredis.server.Server;
import org.ifool.javaredis.shortconnection.ShortConnectionServer;

public class ServerTest {
	
	public static void main(String[] args) {		
		Server server = new ShortConnectionServer(8888);
	}
}
