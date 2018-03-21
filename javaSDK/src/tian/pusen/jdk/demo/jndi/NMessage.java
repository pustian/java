/**
 * 
 */
package tian.pusen.jdk.demo.jndi;

import java.io.Serializable;
import java.rmi.Remote;

/**
 * <p> Title:NMessage </p>
 * <p> Description:       </p>
 * <p> Company: Masapay   </p>
 * @author: tianpusen
 * @Date  : 2016年10月26日 上午9:13:37
 */
public final class NMessage implements Remote, Serializable {
    public String message = "";

    public NMessage(String message)
    {
        this.message = message;
    }

}
