package client;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

	private static Logger logger = LoggerFactory.getLogger(Main.class);
	
	
	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException
	{
		while (true)
		{
			logger.info("Connecting...");
			try (Socket socket = new Socket("generator", 3100))
			{
				logger.info("Sending command");
				OutputStreamWriter writer =new OutputStreamWriter(socket.getOutputStream());
				writer.write("get");
				writer.flush();

				logger.info("Reading answer...");
				// ???
				
				logger.info("End.");
			}
			
			Thread.sleep(5000);
		}
	}
}
