/**
 * 
 */
package tian.pusen.java5.annotation;

/**
 * <p> Title:AnnotationSample </p>
 * <p> Description:       </p>
 * <p> Company: Masapay   </p>
 * @author: tianpusen
 * @Date  : 2016年11月3日 下午3:55:57
 */
//其实注解就是Xml配置的一种取代方式。
// 注解可以用于类，属性或者是方法
// @Retention(RetentionPolicy.RUNTIME)//运行时可以得到（其他的就不要管那么多了，会这个就可以了，谁没事用他来最注释啥的？）
// @Target({ ElementType.FIELD,ElementType.ANNOTATION_TYPE,ElementType.METHOD })//可以注解的位置，好像还挺多的，反正也可以放很多。 
//A、Tiger中预定义的三种标准annotation
//a 、Override 指出某个method覆盖了superclass 的method当你要覆盖的方法名拼写错时编译不通过 
//b、Deprecated 指出某个method或element类型的使用是被阻止的，子类将不能覆盖该方法
//c、SupressWarnings 关闭class、method、field、variable 初始化的编译期警告，比如：List没有使用 Generic,则@SuppressWarnings("unchecked")去掉编译期警告。
//B、自定义annotation public @interface Marked{}
//C、meta-annotation//或者说annotation的annotation
//四种标准的meta-annotation全部定义在java.lang.annotaion包中:
//a, Target
//指定所定义的annotation可以用在哪些程序单元上
//如果Target没有指定，则表示该annotation可以使用在任意程序单元上
//代码
//   1. @Target({ElementType.ANNOTATION_TYPE,  
//   2.          ElementType.CONSTRUCTOR,  
//   3.          ElementType.FIELD,  
//   4.          ElementType.LOCAL_VARIABLE,  
//   5.          ElementType.METHOD,  
//   6.          ElementType.PACKAGE,  
//   7.          ElementType.PARAMETER,  
//   8.          ElementType.TYPE})  
//   9. public @interface TODO {}  
//
//b, Retention
//指出Java编译期如何对待annotation
//annotation可以被编译期丢掉，或者保留在编译过的class文件中
//在annotation被保留时，它也指定是否会在JVM加载class时读取该annotation
//代码
//   1. @Retention(RetentionPolicy.SOURCE)  // Annotation会被编译期丢弃  
//   2. public @interface TODO1 {}  
//   3. @Retention(RetentionPolicy.CLASS)   // Annotation保留在class文件中，但会被JVM忽略  
//   4. public @interface TODO2 {}  
//   5. @Retention(RetentionPolicy.RUNTIME) // Annotation保留在class文件中且会被JVM读取  
//   6. public @interface TODO3 {}  
//c, Documented
//指出被定义的annotation被视为所熟悉的程序单元的公开API之一
//被@Documented标注的annotation会在javadoc中显示，这在annotation对它标注的元素被客户端使用时有影响时起作用
//d, Inherited
//该meta-annotation应用于目标为class的annotation类型上，被此annotattion标注的class会自动继承父类的annotation
//D, Annotation的反射
//我们发现java.lang.Class有许多与Annotation的反射相关的方法，如getAnnotations、isAnnotationpresent
//我们可以利用Annotation反射来做许多事情，比如自定义Annotation来做Model对象验证
//代码
//   1. @Retention(RetentionPolicy.RUNTIME)  
//   2. @Target({ ElementType.FIELD, ElementType.METHOD })  
//   3. public @interface RejectEmpty {  
//   4.     /** hint title used in error message */  
//   5.     String value() default "";  
//   6. }  
//   7.   
//   8. @Retention(RetentionPolicy.RUNTIME)  
//   9. @Target( { ElementType.FIELD, ElementType.METHOD })  
//  10. public @interface AcceptInt {  
//  11.     int min() default Integer.MIN_VALUE;  
//  12.     int max() default Integer.MAX_VALUE;  
//  13.     String hint() default "";  
//  14. }  
//使用@RejectEmpty和@AcceptInt标注我们的Model的field，然后利用反射来做Model验证
public final class AnnotationSample {
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
