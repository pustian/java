/**
 * 
 */
package tian.pusen.jdk.demo.classloader;

import java.io.File;
import java.io.FilenameFilter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * <p>
 * Title:JarLoader
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
 * 
 * @author: tianpusen
 * @Date : 2016年10月19日 下午1:58:50
 */
public final class JarLoaderSample {
	/**
	 * 
	 * @param filename jar文件所在路径
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws MalformedURLException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static void loaderJarZip(String filename)
			throws NoSuchMethodException, SecurityException,
			MalformedURLException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		// 系统类库路径
		File libPath = new File(filename);

		// 获取所有的.jar和.zip文件
		File[] jarFiles = libPath.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.endsWith(".jar") || name.endsWith(".zip");
			}
		});

		if (jarFiles != null) {
			// 从URLClassLoader类中获取类所在文件夹的方法
			// 对于jar文件，可以理解为一个存放class文件的文件夹
			Method method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
			boolean accessible = method.isAccessible(); // 获取方法的访问权限
			try {
				if (accessible == false) {
					method.setAccessible(true); // 设置方法的访问权限
				}
				// 获取系统类加载器
				URLClassLoader classLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
				for (File file : jarFiles) {
					URL url = file.toURI().toURL();
					method.invoke(classLoader, url);
					System.out.println("读取jar文件[name={}]" + file.getName());
				}
			} finally {
				method.setAccessible(accessible);
			}
		}
	}
}
