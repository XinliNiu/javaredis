package org.ifool.javaredis.utils;

import java.util.Arrays;
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
		operationMap.put("setex", (byte) 2);
		operationMap.put("get", (byte) 3);
		operationMap.put("exists", (byte) 4);
		operationMap.put("expire", (byte) 5);
		operationMap.put("del", (byte) 6);
		operationMap.put("ttl", (byte) 7);
	}
	public final static byte OP_SET = 0;
	public final static byte OP_SETNX = 1;
	public final static byte OP_SETEX = 2;
	public final static byte OP_GET = 3;
	public final static byte OP_EXISTS = 4;
	public final static byte OP_EXPIRE = 5;
	public final static byte OP_DEL = 6;
	public final static byte OP_TTL = 7;
	
	public static Map<Byte,String> errorCodeMapping = new HashMap<Byte, String>();
	static {
		errorCodeMapping.put((byte) 0, "success");
	}
	
	public final static byte ERROR_CODE_SUCCESS = 0;
	public final static byte ERROR_CODE_EXIST = 1;
	public final static byte ERROR_CODE_NOTEXIST = 2;
	public final static byte ERROR_CODE_UNKNOWN = 3;
	
	public static void main(String[] args) {
		int[] a = {3,2,1,8,9,6,7};
		Arrays.sort(a,2,5);
		for (int i = 0; i < a.length; i++) {
			System.out.println(a[i]);
		}
	}
} 
