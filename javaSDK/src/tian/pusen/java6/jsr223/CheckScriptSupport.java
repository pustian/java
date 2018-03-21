/**
 * 
 */
package tian.pusen.java6.jsr223;

import java.util.List;

import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;

/**
 * <p> Title:CheckScriptSupport </p>
 * <p> Description:       </p>
 * <p> Company: Masapay   </p>
 * @author: tianpusen
 * @Date  : 2016年11月7日 下午3:46:00
 */
public final class CheckScriptSupport {
	public static void main(String[] args) {
		ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
		List<ScriptEngineFactory> engineFactories = scriptEngineManager.getEngineFactories();
		if (engineFactories.size() == 0) {
			System.out.println("本JVM尚不支持任何脚本引擎");
			return;
		}
		System.out.println("本JVM支持的脚本引擎有:");
		for (ScriptEngineFactory engineFactory : engineFactories) {
			System.out.println("引擎名称:" + engineFactory.getEngineName());
			System.out.println("\t可被ScriptEngineManager识别的名称:" + engineFactory.getNames());
			System.out.println("\t该引擎支持的脚本语言名称:" + engineFactory.getLanguageName());
			System.out.println("\t是否线程安全:" + engineFactory.getParameter("THREADING"));
		}
	}
}
