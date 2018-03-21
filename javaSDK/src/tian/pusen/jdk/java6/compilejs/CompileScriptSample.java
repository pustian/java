package tian.pusen.java6.compilejs;

import javax.script.Compilable;
import javax.script.CompiledScript;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import static java.lang.System.*;

public class CompileScriptSample {
	public static void main(String args[]) {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("javascript");
		engine.put("counter", 0); // 向javascript传递一个参数
		// 判断这个脚本引擎是否支持编译功能
		if (engine instanceof Compilable) {
			Compilable compEngine = (Compilable) engine;
			try {
				// 进行编译
				CompiledScript script = compEngine.compile(count_function);
				out.printf("Counter: %s%n", script.eval());
				out.printf("Counter: %s%n", script.eval());
				out.printf("Counter: %s%n", script.eval());

				
//				engine.put("str", "email@address.tony");			    
//			    Compilable compilable = (Compilable) engine;
//			    CompiledScript compiled = compilable.compile(check_str_function);
//			    compiled.eval();
			    
			} catch (ScriptException e) {
				err.println(e);
			}
		} else {
			err.println("这个脚本引擎不支持编译!");
		}
	}
	
	
	public final static String count_function = "" 
			+ "function count() {"
			+ "    counter = counter +1; "
			+ "    return counter; " 
			+ "}; " 
			+ "count()" ;
	
	public final static String check_str_function = ""
			+ "fucntion checkFormat() {"
			+ "    var email=/^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-](\\.[a-zA-Z0-9_-]+)+$/;"
			+ "    var ip = /^(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])){3}$/;"
			+ "    if(email.test(str) ){  println('it is an email');  return 0;}"
			+ "    else if(ip.test(str)){ println('it is an ip address'); return 1;}"
			+ "    else{ println('I don\\'t know'); return -1;}"
			+ "};";
}
