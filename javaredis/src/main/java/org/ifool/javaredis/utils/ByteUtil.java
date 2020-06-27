package org.ifool.javaredis.utils;

public class ByteUtil {

	public ByteUtil() {
	}

	/**
	 * 把long类型转换成byte[]，返回一个新的byte[]
	 * @param data
	 * @return byte[]
	 */
	public static byte[] long2bytes(long data) {	
		byte[] bytes = new byte[8];
		bytes[0] = (byte) (data & 0xff);
		bytes[1] = (byte) ((data >> 8) & 0xff);
		bytes[2] = (byte) ((data >> 16) & 0xff);
		bytes[3] = (byte) ((data >> 24) & 0xff);
		bytes[4] = (byte) ((data >> 32) & 0xff);
		bytes[5] = (byte) ((data >> 40) & 0xff);
		bytes[6] = (byte) ((data >> 48) & 0xff);
		bytes[7] = (byte) ((data >> 56) & 0xff);
		return bytes;
	}
	
	/**
	 * 把long类型转换成byte[]，传入这个byte[]，并且指定从offset开始放入long
	 * @param data
	 * @param bytes
	 * @param offset
	 * @return void
	 */
	public static void long2bytes(long data, byte[] bytes, int offset) {
		assert bytes != null && bytes.length >= offset + 8;
		bytes[offset] = (byte) (data & 0xff);
		bytes[offset+1] = (byte) ((data >> 8) & 0xff);
		bytes[offset+2] = (byte) ((data >> 16) & 0xff);
		bytes[offset+3] = (byte) ((data >> 24) & 0xff);
		bytes[offset+4] = (byte) ((data >> 32) & 0xff);
		bytes[offset+5] = (byte) ((data >> 40) & 0xff);
		bytes[offset+6] = (byte) ((data >> 48) & 0xff);
		bytes[offset+7] = (byte) ((data >> 56) & 0xff);
	}
	
	public static long bytes2long(byte[] data, int offset) {
	    long  values = 0;   
	    for (int i = 0; i < 8; i++) {    
	        values <<= 8; 
	        values|= (data[i+offset] & 0xff);   
	    }   
	    return values;  	
	}
	

		
	public static byte[] int2bytes(int integer) {
		byte[] bytes = new byte[4];
		bytes[3] = (byte) (integer >> 24);
		bytes[2] = (byte) (integer >> 16);
		bytes[1] = (byte) (integer >> 8);
		bytes[0] = (byte) integer;
		return bytes;
	}
	
	public static void int2bytes(int integer, byte[] bytes, int offset) {
		
		bytes[offset+3] = (byte) (integer >> 24);
		bytes[offset+2] = (byte) (integer >> 16);
		bytes[offset+1] = (byte) (integer >> 8);
		bytes[offset] = (byte) integer;
		
	}
	
	public static int bytes2int(byte[] bytes, int offset) {

		// 如果不与0xff进行按位与操作，转换结果将出错，有兴趣的同学可以试一下。
		int int1 = bytes[offset] & 0xff;
		int int2 = (bytes[offset + 1] & 0xff) << 8;
		int int3 = (bytes[offset + 2] & 0xff) << 16;
		int int4 = (bytes[offset + 3] & 0xff) << 24;
		return int1 | int2 | int3 | int4;

	}

	public static void main(String[] args) {
		int x = 5;
		byte[] bytes = int2bytes(x);
		int y = bytes2int(bytes,0);
		System.out.println(y);
	}
}
