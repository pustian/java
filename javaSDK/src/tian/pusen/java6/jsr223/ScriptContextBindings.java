/**
 * 
 */
package tian.pusen.java6.jsr223;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * <p> Title:ScriptContextBindings </p>
 * <p> Description:       </p>
 * <p> Company: Masapay   </p>
 * @author: tianpusen
 * @Date  : 2016年11月7日 下午3:58:02
 */
public class ScriptContextBindings {
    public static void main(String[] args) throws ScriptException {
        ScriptContextBindings scb = new ScriptContextBindings();
        scb.scriptContextBindings();
//        scb.useScriptContextValues();
//        scb.attributeInBindings();
    }
	public void scriptContextBindings() throws ScriptException {
		ScriptEngineManager manager = new ScriptEngineManager();
		// 属于ScriptEngineManager的全局上下文,每个ScriptEngineManager拥有一个Bindings
		Bindings globalBindings = manager.getBindings(); 
		globalBindings.put("name", "yangYong");
		
		// 每个引擎都共有一个ScriptEngineManager的Bindings
		ScriptEngine engine = manager.getEngineByName("js");
		Bindings globalBindings02 = engine.getBindings(ScriptContext.GLOBAL_SCOPE); 
		System.out.println("name=" + globalBindings02.get("name"));
		System.out.println(globalBindings == globalBindings02);
		
		// 每个引擎的scriptContext中都有个默认属于自己的Bindings
		// 每个引擎都有自己独立的scriptContext上下文
//		ScriptContext context = engine.getContext(); 
		Bindings engineDefaultBindings = engine.getBindings(ScriptContext.ENGINE_SCOPE);
		engineDefaultBindings.put("gender", "男");
		engine.eval("name2 = name;println(name2);", engineDefaultBindings);
		engineDefaultBindings.put("name", "zhangSan");
		engine.eval("name2 = name;println(name2)", engineDefaultBindings);

		// 每个引擎的scriptContext中也可以新建多个属于自己的Bindings
		Bindings engineCreateBindings = engine.createBindings(); 
		engineCreateBindings.put("gender", "女");
		System.out.println(engineDefaultBindings == engineCreateBindings);
		System.out.println(engine.getBindings(ScriptContext.ENGINE_SCOPE));
		System.out.println(engineCreateBindings.get("gender"));
		// System.out.println(engineDefaultBindings.get("gender"));
		engine.eval("name2 = name;println(name2)", engineCreateBindings);
    }
//    public void useScriptContextValues() throws ScriptException {
//    	ScriptEngineManager manager = new ScriptEngineManager();
//		ScriptEngine engine = manager.getEngineByName("javaScript");
//        ScriptContext context = engine.getContext();
//        Bindings bindings = engine.getBindings(ScriptContext.ENGINE_SCOPE);
//        bindings.put("name", "Alex");
//        engine.eval("println(name);");
//    }
//    public void attributeInBindings() throws ScriptException {
//    	ScriptEngineManager manager = new ScriptEngineManager();
//    	Bindings globalBindings = manager.getBindings(); 
//		ScriptEngine engine = manager.getEngineByName("javaScript");
//        ScriptContext context = engine.getContext();
//        context.setAttribute("name", "Alex", ScriptContext.GLOBAL_SCOPE);
//        engine.eval("println(name);");
//    }

}

