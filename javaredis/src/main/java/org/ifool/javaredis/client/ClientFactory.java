package org.ifool.javaredis.client;

import java.util.Map;

public interface ClientFactory {
	public Client newClient();
	public void setParams(Map<String,Object> params);
}
