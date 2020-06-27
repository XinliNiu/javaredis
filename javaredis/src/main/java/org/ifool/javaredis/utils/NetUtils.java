package org.ifool.javaredis.utils;

import java.io.IOException;
import java.io.InputStream;

public class NetUtils {
	
	public static void readFromSocketInputstream(InputStream is, byte[] buffer, int offset, int len) throws IOException {
		int totalRead = 0;
		int readBytes = is.read(buffer, offset+totalRead, len-totalRead);
		while(totalRead < len && readBytes > 0 ) {
			totalRead += readBytes;
			readBytes = is.read(buffer, offset+totalRead, len-totalRead);
		}
	}
	public static int readFromSocketInputstreamUntilEnd(InputStream is, byte[] buffer, int offset) throws IOException {
		int totalRead = 0;
		int readBytes = is.read(buffer, offset, 1024);
		while(readBytes > 0 ) {
			totalRead += readBytes;
			readBytes = is.read(buffer, offset+totalRead, 1024-totalRead);
		}
		return totalRead;
	}

}
