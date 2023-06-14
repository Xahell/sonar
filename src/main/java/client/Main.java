package client;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Main {

	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException
	{
		while (true)
		{
			System.out.println("Connecting...");
			Socket socket = new Socket("generator", 3100);

			System.out.println("Sending command");
			OutputStreamWriter writer =new OutputStreamWriter(socket.getOutputStream());
			writer.write("get");
			writer.flush();

			System.out.println("Reading answer...");
			// ???
			
			System.out.println("Closing...");
			socket.close();

			System.out.println("Closed.");
			
			Thread.sleep(5000);
		}
	}
}
