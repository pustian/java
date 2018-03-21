/**
 * 
 */
package tian.pusen.jdk.demo.referencetype;

import java.lang.ref.PhantomReference;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * <p> Title:ReferenceTypeSample </p>
 * <p> Description:       </p>
 * <p> Company: Masapay   </p>
 * @author: tianpusen
 * @Date  : 2016年10月25日 上午11:47:43
 */
public final class ReferenceTypeSample {

	public static void main(String[] args){
		Person strongRef = new Person();
		// 垃圾回收的时候内部处理标记用		
		WeakReference weakRef = new WeakReference(strongRef);
		System.out.println(weakRef.get()); // This will print the object reference address
		System.gc();
		System.out.println(weakRef.get()); // This will print 'null' if the GC cleaned up the object
		
		SoftReference softRef = new SoftReference(strongRef);
		System.out.println(softRef.get()); // This will print the reference address of the Object
		System.gc();
		System.out.println(softRef.get()); // This may or may not print the reference address of the Object
		
		PhantomReference phantomRef = new PhantomReference(strongRef, null);
	}
	
	static class Person{
		public String name;
		public char sex;
		public int age;
		Person(){}
	}

}

