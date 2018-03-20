package tian.pusen.jdk.rsa;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.List;

import static sun.security.x509.CertificateAlgorithmId.ALGORITHM;

/**
 * 〈一句话功能简述〉<br>
 *  公钥负责加密，私钥负责解密。 加密，那肯定是不希望别人知道我的消息，所以只有我才能解密
 *  私钥负责签名，公钥负责验证。 签名，那肯定是不希望有人冒充我发消息，只有我才能发布这个签名
 * Date: 2018/1/22 12:54
 *
 * @author tianpusen
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class RSAEncryptor {

    public final static String CHARSET = "UTF-8";
    private final static String RSA_ALGORITHM = "RSA";
    // 1024->128, 2048->1256, 512->53,11个字节用于保存padding信息。
    // 可以考虑按block块加解密，以达到扩展长度的目的
    private final static int RSA_KEY_SIZE = 1024; // 512, 1024, 2048
//    private static final int MAX_ENCRYPT_BLOCK = 117;
//    private static final int MAX_DECRYPT_BLOCK = 128;
    public final static String CIPHER_RSA_EBC_ALGORITHM = "RSA/ECB/PKCS1Padding";
    private static final java.util.Base64.Decoder decoder = java.util.Base64.getDecoder();
    private static final java.util.Base64.Encoder encoder = java.util.Base64.getEncoder();

    public static RSAPublicKey publicKey ;
    public static RSAPrivateKey privateKey ;

    private final static String PUBLIC_KEY_STRING = "" +
            "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCpSKko7ZhJ8jl60GLTdmpwJ9H+sHRxMNwACVmh4BCu+4Jv24pLxIQDf4r2fatd370fTxrPuIqCfPsYXPXHWl1QTg9CLmAWJDUEhlbOI+x5fD6RmOL3EY9s98wHD0Upp0Pl4tmBiLBYbaHTmIdOOUbsotXKhDq6WO1+BiTQjx4NcQIDAQAB";
    private final static String PRIVATE_KEY_STRING = "" +
            "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKlIqSjtmEnyOXrQYtN2anAn0f6wdHEw3AAJWaHgEK77gm/bikvEhAN/ivZ9q13fvR9PGs+4ioJ8+xhc9cdaXVBOD0IuYBYkNQSGVs4j7Hl8PpGY4vcRj2z3zAcPRSmnQ+Xi2YGIsFhtodOYh045Ruyi1cqEOrpY7X4GJNCPHg1xAgMBAAECgYBkjf21ixSn8BNGqZvagn+NKYoqoIQ6bExyVRuB9UfTuSBs81q90Pid31gbPODbPTjo7SM++h6EjlrUyPtRtJKyU29DOVW1jsM9mBa1t799lClhPVNAuRjnu8mdWNMJEFLhYVltq/iFEz76AzIJCwN30xkWADhl5THgMlrzmuBt8QJBAPSsRn4l5Qr9eYzJtA2KnLQ1GMZKE5sgPahK0NFUJz4lTbY54frwqKpWJVDlBldGGU/edXywfuPV0Fvom4XnBOUCQQCxHuqc658epL/YItqBxle63zmA6Z24l5HQy222/3Uxkl/vKIENHpeY60fIs8PXbO/tITAug4RvTOwacReb9AmdAkEAh8g7brJ1A75bgEhHxeQQxulYkGJ3svUXgpfSfvjmtlRZZjGFOY/o6m7YUqZm+HQMhAh+xu3j3WjBMuBJvuvQCQJAKRXE0jWNqWwJ3DbpfKTngL969T47ibAO8OHfR02mf6M2gaFhngt0mtIoypN4YCYy2UyTgpabR74OHmwRT+mhuQJBAO4dF52jCRqNZYjnsJnBtFIR/fKZUP6RCkPAOJc6BN2vjDpzKPPfoh6cbHxSPqiRAlGs/tIVHuhwpMnDzVmgwrw=";
    // 将密钥要分别写入文件中
    // 后者使用keytool命令生成RSA密钥对
    public static void initRSAKey() {
        KeyPairGenerator keyPairGen = null;
        try {
            keyPairGen = KeyPairGenerator.getInstance(RSA_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        keyPairGen.initialize(RSA_KEY_SIZE);
        KeyPair keyPair = keyPairGen.generateKeyPair();
        privateKey = (RSAPrivateKey) keyPair.getPrivate(); // 私钥
        publicKey = (RSAPublicKey) keyPair.getPublic(); // 公钥
        byte[] privateKeyBytes = privateKey.getEncoded();
        byte[] publicKeyBytes = publicKey.getEncoded();
        System.out.println("public  key:"+ encoder.encodeToString(publicKeyBytes));
        System.out.println("private key:"+ encoder.encodeToString(privateKeyBytes));
    }
    public static void loadRSAKey() {
        KeyFactory keyFactory = null;
        try {
            keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
            byte[] publicBuffer = decoder.decode(PUBLIC_KEY_STRING.getBytes(CHARSET));
            X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(publicBuffer);
            publicKey = (RSAPublicKey) keyFactory.generatePublic(pubKeySpec);

            byte[] priBuffer = decoder.decode(PRIVATE_KEY_STRING.getBytes(CHARSET));
            PKCS8EncodedKeySpec priKeySpec = new PKCS8EncodedKeySpec(priBuffer);
            privateKey = (RSAPrivateKey) keyFactory.generatePrivate(priKeySpec);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static byte[] encryptData(byte[] data) {
        byte[] destination = null;
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_RSA_EBC_ALGORITHM );
            // 编码前设定编码方式及密钥
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            // 传入编码数据并返回编码结果
            destination = cipher.doFinal(data);
//            // 分段加密处理
//            while() {
//                if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
//                    cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
//                } else {
//                    cache = cipher.doFinal(data, offSet, inputLen - offSet);
//                }
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return destination;
    }
    public static byte[] decryptData(byte[] encryptedData) {
        byte[] destination = null;
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_RSA_EBC_ALGORITHM );
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            destination = cipher.doFinal(encryptedData);
//            // 分段加密处理
//            while() {
//                if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
//                    cache = cipher.doFinal(data, offSet, MAX_DECRYPT_BLOCK);
//                } else {
//                    cache = cipher.doFinal(data, offSet, inputLen - offSet);
//                }
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return destination;
    }


}
