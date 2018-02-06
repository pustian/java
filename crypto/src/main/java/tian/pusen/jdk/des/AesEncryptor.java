package tian.pusen.jdk.des;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 * Date: 2018/1/23 9:56
 *
 * @author tianpusen
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 * 参考：https://stackoverflow.com/questions/15554296/simple-java-aes-encrypt-decrypt-example
 *      https://codereview.stackexchange.com/questions/85396/encrypting-a-string-using-aes-cbc-pkcs5padding
 */
public class AesEncryptor {
    public final static String CHARSET = "UTF-8";
    private final static String AES_ALGORITHM = "AES";
    public final static String CIPHER_AES_CBC_ALGORITHM = "AES/CBC/PKCS5Padding";
    public final static String CIPHER_AES_EBC_ALGORITHM = "AES/ECB/PKCS5Padding";

    private final static byte[] DEFAULT_KEY = "0123456789ABCDEF".getBytes(); // 16 bytes 长
    private static final java.util.Base64.Decoder decoder = java.util.Base64.getDecoder();
    private static final java.util.Base64.Encoder encoder = java.util.Base64.getEncoder();

    // seed 相同生成的密钥相同
    public static byte[] initSecretKey(String seed){
        //返回生成指定算法的秘密密钥的 KeyGenerator 对象
        KeyGenerator keyGenerator = null;
        try {
            keyGenerator = KeyGenerator.getInstance(AES_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        SecureRandom random = new SecureRandom();
        // 密钥的过程中指定了固定的种子，每次生成出来的密钥都是一样的。否则每次生成出来的密钥都可能是不一样的
        random.setSeed(seed.getBytes());
        keyGenerator.init(random);
        //生成一个密钥
        SecretKey secretKey = keyGenerator.generateKey();
        byte[] key = secretKey.getEncoded();
        return key;
    }

    // 每次sdk都不一样
    public static byte[] initSecretKey() {
        //返回生成指定算法的秘密密钥的 KeyGenerator 对象
        KeyGenerator keyGenerator = null;
        try {
            keyGenerator = KeyGenerator.getInstance(AES_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        // 使用随机种子则没测生成密钥不同
//        SecureRandom random = new SecureRandom();
//        keyGenerator.init(random);
//        //初始化此密钥生成器，使其具有确定的密钥大小 128, 192 or 256
        keyGenerator.init(128);
        //生成一个密钥
        SecretKey secretKey = keyGenerator.generateKey();
        byte[] key = secretKey.getEncoded();
        return key;
    }
//    public static String initSecretKeyString() {
//        byte[] key = initSecretKey();
//        return encoder.encodeToString(key);
//    }

    /**
     * 加密
     * @param source 需要加密的字符串
     * @param key 指定的密钥字符串。使用initSecretKey生成的传唤，或是常量DEFAULT_KEY
     * @param charset 字符串的编码方式，目前source，key使用相同的编码方式。null的话会指定utf-8作为编码方式
     * @param cipherDesAlgorithm CIPHER：算法/模式/填充 如果为空会采用 CIPHER_AES_ECB_ALGORITHM 模式
     *      CIPHER_AES_ECB_ALGORITHM 这个算法对于密钥值没有那么大的制约
     *      CIPHER_AES_CBC_ALGORITHM 这个算法会制约key的长度为8个字节
     * @return
     */
    public static String encrypt(String source, String key, String charset, String cipherDesAlgorithm) {
        String encrypt = null;
        byte[] encrptyByte = null;
        byte[] data = null;
        byte[] keyData = null;
        if(cipherDesAlgorithm == null ) {
            cipherDesAlgorithm = CIPHER_AES_EBC_ALGORITHM;
        }
        if(charset == null) {
            charset = CHARSET;
        }
        try {
            data = source.getBytes(charset);
            keyData = decoder.decode(key);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if(data != null && keyData != null) {
            switch(cipherDesAlgorithm) {
                case CIPHER_AES_CBC_ALGORITHM: // 可以对keyData做填充到8*
                    try {
                        encrptyByte = aesEncryptCBC(data, keyData);
                    } catch (InvalidKeyException | NoSuchAlgorithmException |NoSuchPaddingException
                            |BadPaddingException | IllegalBlockSizeException |InvalidAlgorithmParameterException e) {
                        e.printStackTrace();
                    }
                    System.out.println(CIPHER_AES_CBC_ALGORITHM);
                    break;
                case CIPHER_AES_EBC_ALGORITHM:
                    try {
                        encrptyByte = aesEncryptECB(data, keyData);
                    } catch (InvalidKeyException | NoSuchAlgorithmException |NoSuchPaddingException
                            |BadPaddingException | IllegalBlockSizeException e) {
                        e.printStackTrace();
                    }
//                    System.out.println(CIPHER_DES_ECB_ALGORITHM);
                    break;
                default:
                    System.out.println("ERROR");
                    break;
            }
            final Base64.Encoder encoder = Base64.getEncoder();
            encrypt = encoder.encodeToString(encrptyByte);
        }
        return encrypt;
    }
    /**
     * 解密
     * @param source 需要加密的字符串
     * @param key 制定的密钥字符串
     * @param charset 字符串的编码方式，目前source，key使用相同的编码方式。null的话会指定utf-8作为编码方式
     * @param cipherDesAlgorithm CIPHER：算法/模式/填充 如果为空会采用 CIPHER_AES_ECB_ALGORITHM 模式
     *      CIPHER_AES_ECB_ALGORITHM 这个算法对于密钥值没有那么大的制约
     *      CIPHER_AES_CBC_ALGORITHM 这个算法会制约key的长度为8个字节
     * @return
     */
    public static String decrypt(String source, String key, String charset, String cipherDesAlgorithm) {
        String textPlain = null;
        byte[] decrptyByte = null;
        byte[] data = null;
        byte[] keyData = null;
        if (cipherDesAlgorithm == null) {
            cipherDesAlgorithm = CIPHER_AES_EBC_ALGORITHM;
        }
        if (charset == null) {
            charset = CHARSET;
        }
        try {
            data = decoder.decode(source.getBytes(charset));
            keyData = decoder.decode(key);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (data != null && keyData != null) {
            switch (cipherDesAlgorithm) {
                case CIPHER_AES_CBC_ALGORITHM:
                    try {
                        decrptyByte = aesDecryptCBC(data, keyData);
                    } catch (NoSuchPaddingException |NoSuchAlgorithmException |InvalidKeyException
                            |BadPaddingException |IllegalBlockSizeException |InvalidAlgorithmParameterException e) {
                        e.printStackTrace();
                    }
//                    System.out.println(CIPHER_AES_CBC_ALGORITHM);
                    break;
                case CIPHER_AES_EBC_ALGORITHM:
                    try {
                        decrptyByte = aesDecryptECB(data, keyData);
                    } catch (NoSuchPaddingException| NoSuchAlgorithmException | InvalidKeyException |
                            BadPaddingException |IllegalBlockSizeException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    System.out.println("Not support:["+cipherDesAlgorithm+"]");
                    break;
            }
            try {
                textPlain = new String(decrptyByte, charset);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return textPlain;
    }
    public static byte[] aesEncryptCBC(byte[] dataSource, byte[] aesByteKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {
        long begin = System.currentTimeMillis();
        System.out.println("+++ desDecryptCBC begin DES/CBC/PKCS5Padding ");
        byte[] destination = null;
        // 创建一个DESKeySpec对象
        // 从原始密匙数据创建DESKeySpec对象
        SecretKeySpec secretKeySpec = new SecretKeySpec(aesByteKey, AES_ALGORITHM);
        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance(CIPHER_AES_CBC_ALGORITHM);
        // 初始化向量
        IvParameterSpec ivParameterSpec = new IvParameterSpec(aesByteKey);
        //用密匙初始化Cipher对象
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
        //现在，获取数据并加密 //正式执行加密操作
        destination = cipher.doFinal(dataSource);
        long end= System.currentTimeMillis();
        System.out.println("+++ desEncryptCBC end DES/CBC/PKCS5Padding elapse:"+ (end - begin));
        return destination;
    }
    public static byte[] aesDecryptCBC(byte[] dataSource, byte[] aesByteKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {
        long begin = System.currentTimeMillis();
        System.out.println("+++ desDecryptCBC begin DES/CBC/PKCS5Padding ");
        byte[] destination = null;
        // 创建一个DESKeySpec对象
        // 从原始密匙数据创建DESKeySpec对象
        SecretKeySpec secretKeySpec = new SecretKeySpec(aesByteKey, AES_ALGORITHM);
        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance(CIPHER_AES_CBC_ALGORITHM);
        // 初始化向量
        IvParameterSpec ivParameterSpec = new IvParameterSpec(aesByteKey);
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
        // 真正开始解密操作
        destination = cipher.doFinal(dataSource);
        long end= System.currentTimeMillis();
        System.out.println("+++ desDecryptCBC end DES/CBC/PKCS5Padding elapse:"+ (end - begin));
        return destination;
    }

    /**
     * AES/ECB/PKCS5Padding 模式下 加解密 aesByteKey可以为生成的16bytes，也可以为自定义的16byte长
     */
    // aes 加密
    public static byte[] aesEncryptECB(byte[] dataSource, byte[] aesByteKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        byte[] destination = null;
        // 从原始密匙数据创建DESKeySpec对象
        SecretKeySpec secretKeySpec = new SecretKeySpec(aesByteKey, AES_ALGORITHM);
        //根据指定算法AES自成密码器 Cipher.getInstance("AES") Cipher.getInstance("AES/CBC/PKCS5Padding")
        Cipher cipher = Cipher.getInstance(CIPHER_AES_EBC_ALGORITHM);
        //初始化密码器，第一个参数为加密(Encrypt_mode)或者解密解密(Decrypt_mode)操作，第二个参数为使用的KEY
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        //根据密码器的初始化方式--加密：将数据加密
        destination = cipher.doFinal(dataSource);
        return destination;
    }
    // aes 解密
    public static byte[] aesDecryptECB(byte[] dataSource, byte[] aesByteKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        byte[] destination = null;
        // 从原始密匙数据创建DESKeySpec对象
        SecretKeySpec secretKeySpec = new SecretKeySpec(aesByteKey, AES_ALGORITHM);
        //6.根据指定算法AES自成密码器
        Cipher cipher=Cipher.getInstance(CIPHER_AES_EBC_ALGORITHM);
        //7.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密(Decrypt_mode)操作，第二个参数为使用的KEY
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        destination = cipher.doFinal(dataSource);
        return destination;
    }

}
