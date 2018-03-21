/**
 * 
 */
package tian.pusen.jdk.demo.classloader;

import java.io.File;
import java.io.FileFilter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Stack;

/**
 * <p>
 * Title:ClassLoader
 * </p>
 * <p>
 * Description:
 * 	java的类装载使用的是双亲委托模式，在装载过程中有四个相关的ClassLoader分别是，
 * 	bootstrapClassLoader->ExtClassLoader->AppClassLoader->CustomClassLoader.
 * 	上面的箭头表示左边的是右边的父对象。
 * 		加载新类时的逻辑是，先检查当前的ClassLoader是否已经加载过该类，如果没有加载过，则把请求提交给他的父对象，
 * 		直到提交到bootstrapClassLoader为止，然后该ClassLoader尝试加载要求的类，
 * 		如果在加载空间中找不到加载的类，返回给子ClassLoader在其加载空间中尝试加载。
 * 		这种双亲委托模式使得运行时的类总会被bootstrapClassLoader加载，无论你是否在classPath中提供了替换类。
 * 	另外需要注意两点：
 * 		1.classPath只影响AppClassLoader和CustomClassLoader的加载空间。bootstrapClassLoader的加载空间可以由jvm的运行参数-Xbootclasspath指定。
 * 		2.每个ClassLoader的加载空间中，不允许出现同名的类。
 * 	对于应用服务器，如JBoss、tomcat等，ClassLoader是以线程为根节点而设置的contextClassLoader，
 * 	这个classLoader缺省是被设置成AppClassLoader，用户可以设置自己的ClassLoader，该ClassLoader不再使用双亲委托模式进行装载类。
 * </p>
 * <p>
 * Company: Masapay
 * </p>
 * @author: tianpusen
 * @Date : 2016年10月19日 下午2:09:07
 */
public final class ClassLoaderSample {
	/**
	 * 
	 * @param classname
	 *            class文件所在根路径
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws MalformedURLException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws ClassNotFoundException 
	 */
	public static void loaderClass(String classname) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, MalformedURLException, ClassNotFoundException {
		// 设置class文件所在根路径
		// 例如/usr/java/classes下有一个test.App类，则/usr/java/classes即这个类的根路径，而.class文件的实际位置是/usr/java/classes/test/App.class
		File clazzPath = new File(classname);

		// 记录加载.class文件的数量
		int clazzCount = 0;

		if (clazzPath.exists() && clazzPath.isDirectory()) {
			// 获取路径长度
			int clazzPathLen = clazzPath.getAbsolutePath().length() + 1;

			Stack<File> stack = new Stack<>();
			stack.push(clazzPath);

			// 遍历类路径
			while (stack.isEmpty() == false) {
				File path = stack.pop();
				File[] classFiles = path.listFiles(new FileFilter() {
					public boolean accept(File pathname) {
						return pathname.isDirectory()
								|| pathname.getName().endsWith(".class");
					}
				});
				for (File subFile : classFiles) {
					if (subFile.isDirectory()) {
						stack.push(subFile);
					} else {
						if (clazzCount++ == 0) {
							Method method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
							boolean accessible = method.isAccessible();
							try {
								if (accessible == false) {
									method.setAccessible(true);
								}
								// 设置类加载器
								URLClassLoader classLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
								// 将当前类路径加入到类加载器中
								method.invoke(classLoader, clazzPath.toURI()
										.toURL());
							} finally {
								method.setAccessible(accessible);
							}
						}
						// 文件名称
						String className = subFile.getAbsolutePath();
						className = className.substring(clazzPathLen, className.length() - 6);
						className = className.replace(File.separatorChar, '.');
						// 加载Class类
						Class.forName(className);
						System.out.println("读取应用程序类文件[class={}]"+ className);
					}
				}
			}
		}
	}
}
