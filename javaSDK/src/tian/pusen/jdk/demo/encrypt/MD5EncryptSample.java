/**
 * 
 */
package tian.pusen.jdk.demo.encrypt;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * <p> Title:MD5EncryptSample </p>
 * <p> Description:       </p>
 * <p> Company: Masapay   </p>
 * @author: tianpusen
 * @Date  : 2016年10月20日 上午11:23:38
 */
public final class MD5EncryptSample {

	public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		// 密码：1qaz2wsx
		// 16位加密结果: e9db9c60c3e8aa94
		// 32位加密结果: 1c63129ae9db9c60c3e8aa94d3e00495
		byte[] bytes = EncryptByMD5("1qaz2wsx");
		System.out.println(Base64.encode(bytes));
	}
	public static byte[] EncryptByMD5(String plainText) throws NoSuchAlgorithmException {
        //生成实现指定摘要算法的 MessageDigest 对象。
        MessageDigest md = MessageDigest.getInstance("MD5");  
        //使用指定的字节数组更新摘要。
        md.update(plainText.getBytes());
        //通过执行诸如填充之类的最终操作完成哈希计算。
        byte bytes[] = md.digest();
        //生成具体的md5密码到buf数组
        return bytes;
	}
}
