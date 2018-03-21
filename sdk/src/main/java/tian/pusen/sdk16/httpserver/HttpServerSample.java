/**
 * 
 */
package tian.pusen.sdk16.httpserver;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * <p>
 * Title:HttpServerSample
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company: Masapay
 * </p>
 * 
 * @author: tianpusen
 * @Date : 2016年11月8日 下午1:40:36
 */
// 运行程序后,在浏览器内输入http://localhost:8888/lj,浏览器输出
// Happy Every Day 2007!--lj
public final class HttpServerSample {
	public static void main(String[] args) {
		try {
			HttpServer httpserver = HttpServer.create(new InetSocketAddress(8888), 0);// 设置HttpServer的端口为8888
			httpserver.createContext("/lj", new MyHandler());// 用MyHandler类内处理到/lj的请求
			httpserver.setExecutor(null); // creates a default executor
			httpserver.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
