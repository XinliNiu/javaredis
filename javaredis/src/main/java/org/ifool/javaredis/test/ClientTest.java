package org.ifool.javaredis.test;

import java.util.Random;

import org.ifool.javaredis.client.Client;
import org.ifool.javaredis.client.ClientFactory;
import org.ifool.javaredis.shortconnection.ShortConnectionClientFactory;

public class ClientTest {
	
	public static void main(String[] args) {
		final ClientFactory factory = new ShortConnectionClientFactory("127.0.0.1", 8888);
		final Random random = new Random();
		
		Thread[] threads = new Thread[10];
		for(int i = 0; i < 10; i++) {
			threads[i] = new Thread(new Runnable() {
				
				public void run() {
					while(true) {
					int num = random.nextInt(100000);
					String key = String.valueOf(num) + String.valueOf(num);
					String value = key;
					Client client = factory.newClient();
					client.set(key, value.getBytes());
					byte[] v = client.get(key);
					
					String value_remote = new String(v);
					if(! value_remote.equals(value)) {
						System.out.println("error");
					}
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					}
				}
			});
			threads[i].start();
		}
	}

}
