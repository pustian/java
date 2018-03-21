/**
 * 
 */
package tian.pusen.java5.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p> Title:SexCheck </p>
 * <p> Description:       </p>
 * <p> Company: Masapay   </p>
 * @author: tianpusen
 * @Date  : 2016年11月3日 下午5:54:55
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({
//	ElementType.TYPE,
	ElementType.FIELD,
//    ElementType.METHOD, 
//    ElementType.CONSTRUCTOR, 
//    ElementType.ANNOTATION_TYPE
    })
public @interface SexCheck {
//	private char value = 'F';
}
