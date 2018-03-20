package tian.pusen.jdk.des;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
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
 * Date: 2018/1/22 12:43
 *
 * @author tianpusen
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class DesEncryptor {
    public final static String CHARSET = "UTF-8";

    private final static String DES_ALGORITHM = "DES";
    public final static String CIPHER_DES_CBC_ALGORITHM = "DES/CBC/PKCS5Padding";
    public final static String CIPHER_DES_ECB_ALGORITHM = "DES/ECB/PKCS5Padding";


    // 因为DES使用56位密钥，以现代计算能力，24小时内即可被破解。
    // 虽然如此，在某些简单应用中，我们还是可以使用DES加密算法，
    // Base64 展示加密后byte。 key是双方约定的一个字符串。

    /**
     * 加密
     * @param source 需要加密的字符串
     * @param key 指定的密钥字符串
     * @param charset 字符串的编码方式，目前source，key使用相同的编码方式。null的话会指定utf-8作为编码方式
     * @param cipherDesAlgorithm CIPHER：算法/模式/填充 如果为空会采用 CIPHER_DES_ECB_ALGORITHM 模式
     *      CIPHER_DES_ECB_ALGORITHM 这个算法对于密钥值没有那么大的制约
     *      CIPHER_DES_CBC_ALGORITHM 这个算法会制约key的长度为8个字节
     * @return
     */
    public static String encrypt(String source, String key, String charset, String cipherDesAlgorithm) {
        String encrypt = null;
        byte[] encrptyByte = null;
        byte[] data = null;
        byte[] keyData = null;
        if(cipherDesAlgorithm == null ) {
            cipherDesAlgorithm = CIPHER_DES_ECB_ALGORITHM;
        }
        if(charset == null) {
            charset = CHARSET;
        }
        try {
            data = source.getBytes(charset);
            keyData = key.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if(data != null && keyData != null) {
            switch(cipherDesAlgorithm) {
                case CIPHER_DES_ECB_ALGORITHM:
                    try {
                        encrptyByte = desEncryptECB(data, keyData);
                    } catch (InvalidKeyException | NoSuchAlgorithmException |InvalidKeySpecException
                            |NoSuchPaddingException |BadPaddingException | IllegalBlockSizeException e) {
                        e.printStackTrace();
                    }
//                    System.out.println(CIPHER_DES_ECB_ALGORITHM);
                    break;
                case CIPHER_DES_CBC_ALGORITHM: // 可以对keyData做填充到8*
                    try {
                        encrptyByte = desEncryptCBC(data, keyData);
                    } catch (InvalidKeyException | NoSuchAlgorithmException |InvalidKeySpecException
                            |NoSuchPaddingException |BadPaddingException | IllegalBlockSizeException
                            |InvalidAlgorithmParameterException e) {
                        e.printStackTrace();
                    }
//                    System.out.println(CIPHER_DES_CBC_ALGORITHM);
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
     * @param cipherDesAlgorithm CIPHER：算法/模式/填充 如果为空会采用 CIPHER_DES_ECB_ALGORITHM 模式
     *      CIPHER_DES_ECB_ALGORITHM 这个算法对于密钥值没有那么大的制约
     *      CIPHER_DES_CBC_ALGORITHM 这个算法会制约key的长度为8个字节
     * @return
     */
    public static String decrypt(String source, String key, String charset, String cipherDesAlgorithm) {
        final Base64.Decoder decoder= Base64.getDecoder();
        String textPlain = null;
        byte[] decrptyByte = null;
        byte[] data = null;
        byte[] keyData = null;
        if(cipherDesAlgorithm == null ) {
            cipherDesAlgorithm = CIPHER_DES_ECB_ALGORITHM;
        }
        if(charset == null) {
            charset = CHARSET;
        }
        try {
            data = decoder.decode(source.getBytes(charset));
            keyData = key.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        if(data != null && keyData != null) {
            switch(cipherDesAlgorithm) {
                case CIPHER_DES_ECB_ALGORITHM:
                    try {
                        decrptyByte = desDecryptECB(data, keyData);
                    }catch (InvalidKeyException| NoSuchAlgorithmException| InvalidKeySpecException|
                        NoSuchPaddingException| BadPaddingException| IllegalBlockSizeException e) {
                        e.printStackTrace();
                    }
                    System.out.println(CIPHER_DES_ECB_ALGORITHM);
                    break;
                case CIPHER_DES_CBC_ALGORITHM:
                    try {
                        decrptyByte = desDecryptCBC(data, keyData);
                    }catch (InvalidKeyException| NoSuchAlgorithmException| InvalidKeySpecException|
                            NoSuchPaddingException| BadPaddingException| IllegalBlockSizeException|
                            InvalidAlgorithmParameterException e) {
                        e.printStackTrace();
                    }
                    System.out.println(CIPHER_DES_CBC_ALGORITHM);
                    break;
                default:
                    System.out.println("ERROR");
                    break;
            }
            try{
                textPlain = new String(decrptyByte, charset);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return textPlain;
    }

    /// ------------------ DES 加解密 DES/CBC/PKCS5Padding ------------------
    public static byte[] desEncryptCBC(byte[] dataSource, byte[] desByteKey)
            throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException,
                NoSuchPaddingException, InvalidAlgorithmParameterException,
                BadPaddingException, IllegalBlockSizeException {
        long begin = System.currentTimeMillis();
        System.out.println("+++ desEncryptCBC begin DES/CBC/PKCS5Padding ");
        byte[] destination = null;
        // 从原始密匙数据创建DESKeySpec对象
        DESKeySpec desKeySpec = new DESKeySpec(desByteKey);
        //创建一个密匙工厂，然后用它把DESKeySpec转换成
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(DES_ALGORITHM);
        SecretKey securekey = secretKeyFactory.generateSecret(desKeySpec);
        //Cipher对象实际完成加密操作
        Cipher cipher = Cipher.getInstance(CIPHER_DES_CBC_ALGORITHM);
        // 初始化向量
        IvParameterSpec ivParameterSpec = new IvParameterSpec(desByteKey);
        //用密匙初始化Cipher对象
        cipher.init(Cipher.ENCRYPT_MODE, securekey, ivParameterSpec);
        //现在，获取数据并加密 //正式执行加密操作
        destination = cipher.doFinal(dataSource);
        long end= System.currentTimeMillis();
        System.out.println("+++ desEncryptCBC end DES/CBC/PKCS5Padding elapse:"+ (end - begin));
        return destination;
    }

    // des 解密
    public static byte[] desDecryptCBC(byte[] dataSource, byte[] desByteKey)
            throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException,
            NoSuchPaddingException, InvalidAlgorithmParameterException,
            BadPaddingException, IllegalBlockSizeException {
        long begin = System.currentTimeMillis();
        System.out.println("+++ desDecryptCBC begin DES/CBC/PKCS5Padding ");
        byte[] destination = null;
        // 创建一个DESKeySpec对象
        DESKeySpec desKeySpec = new DESKeySpec(desByteKey);
        // 创建一个密匙工厂
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES_ALGORITHM);
        // 将DESKeySpec对象转换成SecretKey对象
        SecretKey securekey = keyFactory.generateSecret(desKeySpec);
        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance(CIPHER_DES_CBC_ALGORITHM);
        // 初始化向量
        IvParameterSpec ivParameterSpec = new IvParameterSpec(desByteKey);
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, ivParameterSpec);
        // 真正开始解密操作
        destination = cipher.doFinal(dataSource);
        long end= System.currentTimeMillis();
        System.out.println("+++ desDecryptCBC end DES/CBC/PKCS5Padding elapse:"+ (end - begin));
        return destination;
    }

    public static byte[] desEncryptECB(byte[] dataSource, byte[] desByteKey)
            throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException,
            NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException {
        long begin = System.currentTimeMillis();
        System.out.println("+++ desEncryptECB begin DES/ECB/PKCS5Padding ");
        byte[] destination = null;
        // 从原始密匙数据创建DESKeySpec对象
        DESKeySpec desKeySpec = new DESKeySpec(desByteKey);
        //创建一个密匙工厂，然后用它把DESKeySpec转换成
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(DES_ALGORITHM);
        SecretKey securekey = secretKeyFactory.generateSecret(desKeySpec);
        //Cipher对象实际完成加密操作
        Cipher cipher = Cipher.getInstance(CIPHER_DES_ECB_ALGORITHM);
//        // 生成一个可信任的随机数源
//        SecureRandom sr = new SecureRandom();
//        //用密匙初始化Cipher对象
//        cipher.init(Cipher.ENCRYPT_MODE, securekey,sr);
        cipher.init(Cipher.ENCRYPT_MODE, securekey);
        //现在，获取数据并加密 //正式执行加密操作
        destination = cipher.doFinal(dataSource);
        long end= System.currentTimeMillis();
        System.out.println("+++ desEncryptECB end DES/ECB/PKCS5Padding elapse:"+ (end - begin));
        return destination;
    }

    public static byte[] desDecryptECB(byte[] dataSource, byte[] desByteKey)
            throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException,
            NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException {
        long begin = System.currentTimeMillis();
        System.out.println("+++ desDecryptECB begin DES/ECB/PKCS5Padding ");
        byte[] destination = null;
        // 创建一个DESKeySpec对象
        DESKeySpec desKeySpec = new DESKeySpec(desByteKey);
        // 创建一个密匙工厂
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES_ALGORITHM);
        // 将DESKeySpec对象转换成SecretKey对象
        SecretKey securekey = keyFactory.generateSecret(desKeySpec);
        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance(CIPHER_DES_ECB_ALGORITHM);
//        // 生成一个可信任的随机数源
//        SecureRandom sr = new SecureRandom();
//        // 用密匙初始化Cipher对象
//        cipher.init(Cipher.DECRYPT_MODE, securekey,sr);
        cipher.init(Cipher.DECRYPT_MODE, securekey);
        // 真正开始解密操作
        destination = cipher.doFinal(dataSource);
        long end= System.currentTimeMillis();
        System.out.println("+++ desDecryptECB end DES/ECB/PKCS5Padding elapse:"+ (end - begin));
        return destination;
    }
}
