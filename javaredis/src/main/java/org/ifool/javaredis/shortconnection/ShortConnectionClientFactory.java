package org.ifool.javaredis.shortconnection;

import java.util.Map;

import org.ifool.javaredis.client.AbstractClient;
import org.ifool.javaredis.client.Client;
import org.ifool.javaredis.client.ClientFactory;

public class ShortConnectionClientFactory implements ClientFactory {

	private String ip;
	private int port;
	private ShortConnectionTransport transport;
	private Client client;
	public ShortConnectionClientFactory(String ip, int port) {
		this.ip = ip;
		this.port = port;
		transport = new ShortConnectionTransport(ip, port);
		ShortConnectionClient c = new ShortConnectionClient();
		c.setTransport(transport);
		client = c;
	}
	public Client newClient() {
		return client;
	}
	public void setParams(Map<String, Object> params) {
		
	}

}
