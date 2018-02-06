package tian.pusen.jdk.rsa.test;

import tian.pusen.jdk.rsa.RSAEncryptor;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 * Date: 2018/1/30 15:47
 *
 * @author tianpusen
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class TestRSAApplication {
    private static final java.util.Base64.Decoder decoder = java.util.Base64.getDecoder();
    private static final java.util.Base64.Encoder encoder = java.util.Base64.getEncoder();

    public static void main(String[] args) {
//        RSAEncryptor.initRSAKey();
        testEncrpt();
    }
    public static void testEncrpt(){
        final String hello = "hello，welcome to 即付";
        byte[] bytes = RSAEncryptor.encryptData(hello.getBytes());
        String encrypt = encoder.encodeToString(bytes);

        byte[] deBytes = RSAEncryptor.decryptData(bytes);
        String textPlain = new String(deBytes);
        System.out.println(encrypt);
        System.out.println(textPlain);
    }
}
