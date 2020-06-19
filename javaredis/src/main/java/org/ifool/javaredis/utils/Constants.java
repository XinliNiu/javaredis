package org.ifool.javaredis.utils;

import java.util.HashMap;
import java.util.Map;

public class Constants {

	public Constants() {
		// TODO Auto-generated constructor stub
	}

	public static Map<String,Byte> operationMap = new HashMap<String, Byte>();
	static {
		operationMap.put("set", (byte) 0);
		operationMap.put("setnx", (byte) 1);
	}
	
	public final static String SUCCESS = "success";
	public final static String ERROR_CODE_SUCCESS = "0";
}
