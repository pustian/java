package tian.pusen.jdk.des;

import javax.crypto.*;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
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
 */
public class DESedeEncryptor {
    public final static String CHARSET = "UTF-8";
    public final static String DESede_ALGORITHM = "DESede";
    public final static String CIPHER_DESede_CBC_ALGORITHM = "DESede/CBC/PKCS5Padding";
    public final static String CIPHER_DESede_ECB_ALGORITHM = "DESede/ECB/PKCS5Padding";
    // IvParameterSpec(BYTES_8_LEN)
    private final static byte[] BYTES_8_LEN = {(byte)0, (byte)1, (byte)2,(byte)3,
            (byte)4, (byte)5, (byte)6,(byte)7};
    private static final Base64.Decoder decoder = Base64.getDecoder();
    private static final Base64.Encoder encoder = Base64.getEncoder();

    /// ------------------ DESEDE 生成密钥 ------------------
    public static byte[] initSecretKey() {
        //返回生成指定算法的秘密密钥的 KeyGenerator 对象
        KeyGenerator keyGenerator = null;
        try {
            keyGenerator = KeyGenerator.getInstance(DESede_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        //初始化此密钥生成器，使其具有确定的密钥大小112/168
        keyGenerator.init(112);
        //生成一个密钥
        SecretKey secretKey = keyGenerator.generateKey();
        byte[] key = secretKey.getEncoded();
        return key;
    }
    public static String initSecretKeyString() {
        byte[] key = initSecretKey();
        return encoder.encodeToString(key);
    }

    /**
     * 加密
     * @param source             需要加密的字符串
     * @param key                指定的密钥，initSecretKey生成的byte流,Base64转换后的值；或是initSecretKeyString生成的字符流
     * @param charset            字符串的编码方式，目前source，key使用相同的编码方式。null的话会指定utf-8作为编码方式
     * @param cipherDesAlgorithm CIPHER：算法/模式/填充 如果为空会采用 CIPHER_DES_CBC_ALGORITHM 模式
     *                           CIPHER_DES_ECB_ALGORITHM 这个算法对于密钥值没有那么大的制约
     *                           CIPHER_DES_CBC_ALGORITHM 这个算法会制约key的长度为8个字节
     * @return
     */
    public static String encrypt(String source, String key, String charset, String cipherDesAlgorithm) {
        String encrypt = null;
        byte[] encrptyByte = null;
        byte[] data = null;
        byte[] keyData = null;
        if (cipherDesAlgorithm == null) {
            cipherDesAlgorithm = CIPHER_DESede_ECB_ALGORITHM;
        }
        if (charset == null) {
            charset = CHARSET;
        }
        try {
            data = source.getBytes(charset);
            keyData = decoder.decode(key);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (data != null && keyData != null) {
            switch (cipherDesAlgorithm) {
                case CIPHER_DESede_CBC_ALGORITHM:
                    try {
                        encrptyByte = desedeEncryptCBC(data, keyData);
                    } catch (NoSuchPaddingException e) {
                        e.printStackTrace();
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    } catch (InvalidKeyException e) {
                        e.printStackTrace();
                    } catch (BadPaddingException e) {
                        e.printStackTrace();
                    } catch (IllegalBlockSizeException e) {
                        e.printStackTrace();
                    } catch (InvalidAlgorithmParameterException e) {
                        e.printStackTrace();
                    }
                    break;
                case CIPHER_DESede_ECB_ALGORITHM:
                    try {
                        encrptyByte = desedeEncryptECB(data, keyData);
                    } catch (NoSuchPaddingException e) {
                        e.printStackTrace();
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    } catch (InvalidKeyException e) {
                        e.printStackTrace();
                    } catch (BadPaddingException e) {
                        e.printStackTrace();
                    } catch (IllegalBlockSizeException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    System.out.println("Not support:["+cipherDesAlgorithm+"]");
                    break;
            }
            final Base64.Encoder encoder = Base64.getEncoder();
            encrypt = encoder.encodeToString(encrptyByte);
        }
        return encrypt;
    }
    /**
     * 解密
     * @param source             需要加密的字符串
     * @param key                指定的密钥，initSecretKey生成的byte流,Base64转换后的值；或是initSecretKeyString生成的字符流
     * @param charset            字符串的编码方式，目前source，key使用相同的编码方式。null的话会指定utf-8作为编码方式
     * @param cipherDesAlgorithm CIPHER：算法/模式/填充 如果为空会采用 CIPHER_DES_CBC_ALGORITHM 模式
     *                           CIPHER_DES_ECB_ALGORITHM 这个算法对于密钥值没有那么大的制约
     *                           CIPHER_DES_CBC_ALGORITHM 这个算法会制约key的长度为8个字节
     * @return
     */
    public static String decrypt(String source, String key, String charset, String cipherDesAlgorithm) {
        String textPlain = null;
        byte[] decrptyByte = null;
        byte[] data = null;
        byte[] keyData = null;
        if (cipherDesAlgorithm == null) {
            cipherDesAlgorithm = CIPHER_DESede_ECB_ALGORITHM;
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
                case CIPHER_DESede_CBC_ALGORITHM:
                    try {
                        decrptyByte = desedeDecryptCBC(data, keyData);
                    } catch (NoSuchPaddingException e) {
                        e.printStackTrace();
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    } catch (InvalidKeyException e) {
                        e.printStackTrace();
                    } catch (BadPaddingException e) {
                        e.printStackTrace();
                    } catch (IllegalBlockSizeException e) {
                        e.printStackTrace();
                    } catch (InvalidAlgorithmParameterException e) {
                        e.printStackTrace();
                    }
                    break;
                case CIPHER_DESede_ECB_ALGORITHM:
                    try {
                        decrptyByte = desedeDecryptECB(data, keyData);
                    } catch (NoSuchPaddingException e) {
                        e.printStackTrace();
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    } catch (InvalidKeyException e) {
                        e.printStackTrace();
                    } catch (BadPaddingException e) {
                        e.printStackTrace();
                    } catch (IllegalBlockSizeException e) {
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

    // 转换密钥 2机制密钥转换为 secretKey
    private static Key secretKey(byte[] key) {
        SecretKey secretKey = null;
        try {
            //实例化DES密钥规则
            DESedeKeySpec deSedeKeySpec = new DESedeKeySpec(key);
            //实例化密钥工厂
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(DESede_ALGORITHM);
            //生成密钥
            secretKey = secretKeyFactory.generateSecret(deSedeKeySpec);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return secretKey;
    }

    // desede 加密
    public static byte[] desedeEncryptECB(byte[] dataSource, byte[] desedeByteKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        long begin = System.currentTimeMillis();
        System.out.println("+++ desedeEncryptECB begin DESede/ECB/PKCS5Padding ");
        byte[] destination = null;
        SecretKey secretKey = (SecretKey) secretKey(desedeByteKey);
        //Cipher对象实际完成加密操作
        Cipher cipher = Cipher.getInstance(CIPHER_DESede_ECB_ALGORITHM);
        //用密匙初始化Cipher对象
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        //现在，获取数据并加密 //正式执行加密操作
        destination = cipher.doFinal(dataSource);
        long end= System.currentTimeMillis();
        System.out.println("+++ desedeEncryptECB end DESede/ECB/PKCS5Padding elapse:"+ (end - begin));
        return destination;
    }
    // des 解密
    public static byte[] desedeDecryptECB(byte[] dataSource, byte[] desedeByteKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        long begin = System.currentTimeMillis();
        System.out.println("+++ desedeDecryptECB begin DESede/ECB/PKCS5Padding ");
        byte[] destination = null;
        SecretKey secretKey = (SecretKey) secretKey(desedeByteKey);
        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance(CIPHER_DESede_ECB_ALGORITHM);
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        // 真正开始解密操作
        destination = cipher.doFinal(dataSource);
        long end= System.currentTimeMillis();
        System.out.println("+++ desedeDecryptECB end DESede/ECB/PKCS5Padding elapse:"+ (end - begin));
        return destination;
    }
    // desede 加密
    public static byte[] desedeEncryptCBC(byte[] dataSource, byte[] desedeByteKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {
        long begin = System.currentTimeMillis();
        System.out.println("+++ desedeEncryptCBC begin DESede/CBC/PKCS5Padding ");
        byte[] destination = null;
        SecretKey secretKey = (SecretKey) secretKey(desedeByteKey);
        //Cipher对象实际完成加密操作
        Cipher cipher = Cipher.getInstance(CIPHER_DESede_CBC_ALGORITHM);
        // 初始化向量
        IvParameterSpec ivParameterSpec = new IvParameterSpec(BYTES_8_LEN);
        //用密匙初始化Cipher对象
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);
        //现在，获取数据并加密 //正式执行加密操作
        destination = cipher.doFinal(dataSource);
        long end= System.currentTimeMillis();
        System.out.println("+++ desedeEncryptCBC end DESede/CBC/PKCS5Padding elapse:"+ (end - begin));
        return destination;
    }
    // des 解密
    public static byte[] desedeDecryptCBC(byte[] dataSource, byte[] desedeByteKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {
        long begin = System.currentTimeMillis();
        System.out.println("+++ desedeDecryptCBC begin DESede/CBC/PKCS5Padding ");
        byte[] destination = null;
        SecretKey secretKey = (SecretKey) secretKey(desedeByteKey);
        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance(CIPHER_DESede_CBC_ALGORITHM);
        // 初始化向量
        IvParameterSpec ivParameterSpec = new IvParameterSpec(BYTES_8_LEN);
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
        // 真正开始解密操作
        destination = cipher.doFinal(dataSource);
        long end= System.currentTimeMillis();
        System.out.println("+++ desedeDecryptCBC end DESede/CBC/PKCS5Padding elapse:"+ (end - begin));
        return destination;
    }
}
