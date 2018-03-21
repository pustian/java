/**
 * 
 */
package tian.pusen.java6.jsr223;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * <p> Title:BasicScripting </p>
 * <p> Description:       </p>
 * <p> Company: Masapay   </p>
 * @author: tianpusen
 * @Date  : 2016年11月7日 下午3:35:09
 */
public final class BasicScripting {
	private ScriptEngine engine = null;
	public BasicScripting() throws ScriptException {
		ScriptEngineManager manager = new ScriptEngineManager();
		// 支持通过名称、文件扩展名、MIMEtype查找
		engine = manager.getEngineByName("JavaScript");
		// engine = manager.getEngineByExtension("js");
		// engine = manager.getEngineByExtension("application/javascript");
		// engine = manager.getEngineByMimeType("text/javascript");
		if (engine == null) {
			throw new RuntimeException("找不到JavaScript语言执行引擎。");
		}
	}
	
	public void print() throws ScriptException{
		this.engine.eval("println('Hello!');");	
	}
	
	public void complexJS() throws FileNotFoundException, ScriptException{
		this.engine.eval(new FileReader("HelloWorld.js"));	
	}

	public void jsMethod() throws ScriptException {
		String script = "function say(first,second) { print(first +' '+ second); }";
		this.engine.eval(script);
	}
	
	
	public static void main(String[] args) throws ScriptException, FileNotFoundException, NoSuchMethodException {
		BasicScripting script = new BasicScripting();
		script.print();
		// script.complexJS();
		
		script.jsMethod();
		Invocable inv = (Invocable) script.engine;
        inv.invokeFunction("say", "Hello", "Tony");

	}
	
}

