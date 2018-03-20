package tian.pusen.jdk;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * 用于加密解密的一些调用
 * @author tianpusen
 *
 */
public class HashEncryptor {
	public static final String KEY_MD5 = "MD5";
	public static final String KEY_SHA = "SHA";
	public static final String KEY_SHA_1 = "SHA-1";
	public static final String KEY_SHA_256 = "SHA-256";
	public static final String KEY_SHA_384 = "SHA-384";
	
	/**
	 * Base64解密
	 * @param key
	 * @return
	 */
	public static byte[] decryptBASE64(String key) {
		return Base64.getDecoder().decode(key);
	}
	
	/**
	 * Base64加密
	 * @param src
	 * @return
	 */
	public static String encryptBASE64(byte[] src) {
		return Base64.getEncoder().encodeToString(src);
	}
	
	
	/**
	 * MD5加密
	 * @param data
	 * @return
	 * @throws NoSuchAlgorithmException 
	 */
	public static byte[] encryptMD5(byte[] data) 
			throws NoSuchAlgorithmException {
		MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);
		md5.update(data);
		return md5.digest();
	}
	private static String byte2Hex(byte[] data	) {
		int i;
        StringBuffer buf = new StringBuffer("");
        for (int offset = 0; offset < data.length; offset++) {
            i = data[offset];
            if (i < 0)
                i += 256;
            if (i < 16)
                buf.append("0");
            buf.append(Integer.toHexString(i));
//			效果同上
//			String str = Integer.toHexString(data[i] & 0xFF);
//			if (str.length() == 1)
//				buf.append("0");
//			buf.append(str);
        }
        return buf.toString();	
    }

	/**
	 * SHA加密 
	 * @param data
	 * @return
	 * @throws NoSuchAlgorithmException 
	 */
	public static byte[] encryptSHA(byte[] data) 
			throws NoSuchAlgorithmException {
		MessageDigest sha = MessageDigest.getInstance(KEY_SHA);
		sha.update(data);
		return sha.digest();
	}
	
	public static void main(String[] args) throws NoSuchAlgorithmException {
		String string ="hello, 田圃森";
		String encryptBase64 = encryptBASE64(string.getBytes());
		System.out.println("String:"+string+"\nEncryptBASE64:"+encryptBase64);
		String decryptBase64 = new String(decryptBASE64(encryptBase64));
		System.out.println("DecryptBASE64:"+decryptBase64);
		
		byte[] bytesMd5 = encryptMD5(string.getBytes() );
		String encryptMd5 = byte2Hex(bytesMd5);
		System.out.println("EncryptMD5 32:"+encryptMd5);
		
		byte[] bytesSha = encryptSHA(string.getBytes() );
		String encryptSha = byte2Hex(bytesSha);
		System.out.println("EncryptSHA:"+encryptSha);
	}
}
