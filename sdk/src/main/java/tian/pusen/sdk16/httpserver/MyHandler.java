package tian.pusen.sdk16.httpserver;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class MyHandler implements HttpHandler {
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		  InputStream is = exchange.getRequestBody();
//          String response = "<***>Happy Every Day 2007!--ljaaew</***>";
//
          String response = "{\"amount\": \"1234567\", \"externalOrderCode\": \"EOC1234567890ABCDEF\", \"fee\": \"12\", \"sign\": \"819d82a1d9735813541045b80b315f92bb4e68bd\", \"transationType\": \"消费\", \"terminalId\": \"TerminalId\", \"tradeDate\": \"20171102\", \"tradeState\": \"1\", \"tradeTime\": \"160237\", \"payMethod\": \"1\",\"orderCode\": \"1234567890ABCDE\"}" ;
          exchange.sendResponseHeaders(200, response.length());
          OutputStream os = exchange.getResponseBody();
          os.write(response.getBytes());
          os.close();
	}

}
