package org.ifool.javaredis.shortconnection;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.ifool.javaredis.server.RedisChannel;
import org.ifool.javaredis.utils.NetUtils;

public class ShortConnectionChannel implements RedisChannel{

	private Socket socket;
	public ShortConnectionChannel(Socket socket) {
		this.socket = socket;
	}
	public void send(byte[] data) {
		try {
			OutputStream os = socket.getOutputStream();
			os.write(data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public byte[] read() {
		InputStream is;
		try {
			is = socket.getInputStream();
			byte[] buffer = new byte[1024];
			int len = NetUtils.readFromSocketInputstreamUntilEnd(is, buffer, 0);
			return buffer;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public void close() {
		try {
			socket.shutdownInput();
			socket.shutdownOutput();
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
