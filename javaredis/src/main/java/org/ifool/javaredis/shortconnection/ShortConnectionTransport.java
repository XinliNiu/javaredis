package org.ifool.javaredis.shortconnection;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import org.ifool.javaredis.transport.AbstractTransport;
import org.ifool.javaredis.transport.Request;
import org.ifool.javaredis.transport.Response;
import org.ifool.javaredis.transport.Transport;
import org.ifool.javaredis.utils.ByteUtil;
import org.ifool.javaredis.utils.NetUtils;

public class ShortConnectionTransport implements Transport {

	private String ip;
	private int port;
	
	public ShortConnectionTransport(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}
	public Response send(Request req) {
		Socket socket = null;
		try {
			socket = new Socket(ip, port);
			OutputStream os = socket.getOutputStream();
			byte[] sendBytes = req.toBytes();
			os.write(sendBytes, 0, sendBytes.length);
			os.flush();
			socket.shutdownOutput();
			InputStream is = socket.getInputStream();
			
			byte[] resHeader = new byte[14];
			NetUtils.readFromSocketInputstream(is, resHeader, 0, 14);
			long id = ByteUtil.bytes2long(resHeader, 0);
			byte op = resHeader[8];
			byte errorCode = resHeader[9];
			int datalength = ByteUtil.bytes2int(resHeader, 10);
			
			if(datalength == 0) {
				socket.shutdownInput();
				return new Response(id, op, errorCode, null);
			} else {
				byte[] data = new byte[datalength];
				NetUtils.readFromSocketInputstream(is, data, 0, datalength);
				socket.shutdownInput();
				return new Response(id, op, errorCode, data);
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
		
	}

}
