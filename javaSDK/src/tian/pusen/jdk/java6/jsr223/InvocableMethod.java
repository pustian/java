package tian.pusen.java6.jsr223;

import static java.lang.System.out;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public final class InvocableMethod {
	public final static String reverse_function = ""
			+"function reverse(name) {" 
			+"    var output =' ';" 
			+"    for (i = 0; i <= name.length; i++) {" 
			+"        output = name.charAt(i) + output" 
			+"} "
			+"return output;"
			+"}";
	
	public static void main(String args[]) throws ScriptException, NoSuchMethodException {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("javascript");
		String name="abcdefg";
		out.printf("翻转前的字符串：%s\n", name);
		engine.eval(reverse_function);
		Invocable invokeEngine = (Invocable)engine;
		Object o = invokeEngine.invokeFunction("reverse", name);
		out.printf("翻转后的字符串：%s\n", o);
	}
}
