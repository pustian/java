package tian.pusen.java6.httpserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;


public class MyHandler implements HttpHandler {
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		  InputStream is = exchange.getRequestBody();
          String response = "<***>Happy Every Day 2007!--lj</***>";
          exchange.sendResponseHeaders(200, response.length());
          OutputStream os = exchange.getResponseBody();
          os.write(response.getBytes());
          os.close();
	}

}
