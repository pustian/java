package tian.pusen.jdk.des.test;

import tian.pusen.jdk.des.AesEncryptor;
import tian.pusen.jdk.des.DESedeEncryptor;
import tian.pusen.jdk.des.DesEncryptor;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 * Date: 2018/1/25 14:20
 *
 * @author tianpusen
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class TestDesApplication {
    public static final Base64.Decoder decoder = Base64.getDecoder();
    public static final Base64.Encoder encoder = Base64.getEncoder();
    public static void main(String[] args) {
//        desEncryptorTest();

//        System.out.println(generateDesedeKey());
//        String key = "HDvqibV/vMsT1rl20+Uj1SzcNI80QOp/";
//        String key = "SnlG5m1M0A5oCN+RLFLOhUp5RuZtTNAO";
//        desedeEncryptorTest(key);

        byte[] asekey = AesEncryptor.initSecretKey("OK");
        String key = encoder.encodeToString(asekey);
        System.out.println(key);
//        aesEncryptorTest(key);
    }
        /// 三种产生密钥key的方法
    // 结果看来，key3是原始的，没有加工过；key1是经过Factory生成的，只是将其稍微加工了一下，加了一些置换操作；而key2是经过Generator通过SecureRandom生成的，所以大幅度变形。
    public static void generatorKey(byte[] keyBytes){
        try{
            //第一种，Factory
            DESKeySpec keySpec=new DESKeySpec(keyBytes);
            SecretKeyFactory keyFactory=SecretKeyFactory.getInstance("DES");
            SecretKey key1=keyFactory.generateSecret(keySpec);

            //第二种, Generator 推荐使用该方法
            KeyGenerator keyGenerator=KeyGenerator.getInstance("DES");
            keyGenerator.init(56, new SecureRandom(keyBytes));//key为8个字节，实际用了56位； 后面随机数用key作为种子seed生成
            SecretKey key2=keyGenerator.generateKey();

            //第三种， SecretKeySpec
            SecretKey key3=new SecretKeySpec(keyBytes, "DES");//SecretKeySpec类同时实现了Key和KeySpec接口

            //打印
            System.out.println("key1："+encoder.encodeToString(key1.getEncoded()));
            System.out.println("key2："+encoder.encodeToString(key2.getEncoded()));
            System.out.println("key3："+encoder.encodeToString(key3.getEncoded()));

        }catch(Exception e){
            System.out.println(e.toString());
        }
    }
    public static void desEncryptorTest(){
        String key = null;
        String textPlain = "hello des, 田圃森";
        key = "01234567";
        String encryptyText0 = DesEncryptor.encrypt(textPlain, key, null, null);
        String decrypText0  = DesEncryptor.decrypt(encryptyText0, key, null, null);
        System.out.println(encryptyText0);
        System.out.println(decrypText0);

        key = "01234567";
        String encryptyText1 = DesEncryptor.encrypt(textPlain, key, "UTF-8", DesEncryptor.CIPHER_DES_CBC_ALGORITHM);
        String decrypText1  = DesEncryptor.decrypt(encryptyText1, key, "UTF-8", DesEncryptor.CIPHER_DES_CBC_ALGORITHM);
        System.out.println(encryptyText1);
        System.out.println(decrypText1);

        key = "密钥ABCDEFG";
        String encryptyText2 = DesEncryptor.encrypt(textPlain, key, "UTF-8", DesEncryptor.CIPHER_DES_ECB_ALGORITHM);
        String decrypText2  = DesEncryptor.decrypt(encryptyText2, key, "UTF-8", DesEncryptor.CIPHER_DES_ECB_ALGORITHM);
        System.out.println(encryptyText2);
        System.out.println(decrypText2);
    }

    public static void desedeEncryptorTest(String key) {
        String textPlain = "hello desede, 田圃森123";
        System.out.println("======Desede DESede/EBC/PKCS5Padding");
        String encryptyText0 = DESedeEncryptor.encrypt(textPlain, key, null, null);
        String decrypText0  = DESedeEncryptor.decrypt(encryptyText0, key, null, null);
        System.out.println(encryptyText0);
        System.out.println(decrypText0);

        System.out.println("======Desede DESede/CBC/PKCS5Padding");
        String encryptyText1 = DESedeEncryptor.encrypt(textPlain, key, "UTF-8", DESedeEncryptor.CIPHER_DESede_CBC_ALGORITHM);
        System.out.println(encryptyText1);
        String decrypText1  = DESedeEncryptor.decrypt(encryptyText1, key, "UTF-8", DESedeEncryptor.CIPHER_DESede_CBC_ALGORITHM);
        System.out.println(decrypText1);

        System.out.println("======Desede DESede/EBC/PKCS5Padding");
        String encryptyText2 = DESedeEncryptor.encrypt(textPlain, key, "UTF-8", DESedeEncryptor.CIPHER_DESede_ECB_ALGORITHM);
        String decrypText2  = DESedeEncryptor.decrypt(encryptyText2, key, "UTF-8", DESedeEncryptor.CIPHER_DESede_ECB_ALGORITHM);
        System.out.println(encryptyText2);
        System.out.println(decrypText2);
    }

    public static String generateAesKey() {
        byte[] key = AesEncryptor.initSecretKey();
        return encoder.encodeToString(key);
    }
    public static void aesEncryptorTest(String key) {
        System.out.println("======BEGIN");
        String textPlain = "hello aes, 田圃森123";
        String encryptyText0 = AesEncryptor.encrypt(textPlain, key, null, null);
        System.out.println(encryptyText0);
        String decrypText0  = AesEncryptor.decrypt(encryptyText0, key, null, null);
        System.out.println(decrypText0);

        System.out.println("======aes AES/CBC/PKCS5Padding");
        String encryptyText1 = AesEncryptor.encrypt(textPlain, key, "UTF-8", AesEncryptor.CIPHER_AES_CBC_ALGORITHM);
        System.out.println(encryptyText1);
        String decrypText1  = AesEncryptor.decrypt(encryptyText1, key, "UTF-8", AesEncryptor.CIPHER_AES_CBC_ALGORITHM);
        System.out.println(decrypText1);

        System.out.println("======aes AES/EBC/PKCS5Padding");
        String encryptyText2 = AesEncryptor.encrypt(textPlain, key, "UTF-8", AesEncryptor.CIPHER_AES_EBC_ALGORITHM );
        String decrypText2  = AesEncryptor.decrypt(encryptyText2, key, "UTF-8", AesEncryptor.CIPHER_AES_EBC_ALGORITHM);
        System.out.println(encryptyText2);
        System.out.println(decrypText2);
//        try {
//            byte[] keydata = "1234567812345678".getBytes();
//            byte[] bytes = AesEncryptor.aesEncryptECB(textPlain.getBytes(), keydata );
//            outputString = encoder.encodeToString(bytes);
//            System.out.println(outputString);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        try {
//            byte[] keydata = "123456781234567812345678".getBytes();
//            String string = "+9wQG4cIEELOrLYQ3BMwi3bVwlaqIbkSaZPpZDnwyKc=";
//            byte[] bytes = AesEncryptor.aesDecryptECB(decoder.decode(string), keydata );
//            System.out.println(new String(bytes));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        System.out.println("======END");
    }
}
