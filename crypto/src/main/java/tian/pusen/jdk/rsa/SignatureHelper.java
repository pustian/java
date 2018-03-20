package tian.pusen.jdk.rsa;

import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class SignatureHelper {
    private static PrivateKey privateKey;
    private static PublicKey publicKey;
    public final static String CHARSET = "UTF-8";
    private static final String ALGORITHM = "SHA1withRSA";
    // 1024->128, 2048->1256, 512->53,11个字节用于保存padding信息。
    // 可以考虑按block块加解密，以达到扩展长度的目的
    private final static int RSA_KEY_SIZE = 1024; // 512, 1024, 2048
    //    private static final int MAX_ENCRYPT_BLOCK = 117;
//    private static final int MAX_DECRYPT_BLOCK = 128;
    private static final java.util.Base64.Decoder decoder = java.util.Base64.getDecoder();
    private static final java.util.Base64.Encoder encoder = java.util.Base64.getEncoder();

    private final static String PUBLIC_KEY_STRING = "" +
            "";
    private final static String PRIVATE_KEY_STRING = "" +
            "";
    // 将密钥要分别写入文件中
    // 后者使用keytool命令生成RSA密钥对
    public static void initRSAKey() {
        KeyPairGenerator keyPairGen = null;
        try {
            keyPairGen = KeyPairGenerator.getInstance(ALGORITHM);
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
            keyFactory = KeyFactory.getInstance(ALGORITHM);
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
    /**
     * 使用SHA1withRSA算法
     * @param data
     * @return
     */
    public static byte[] sign(byte[] data)
            throws NoSuchAlgorithmException, InvalidKeyException,
            SignatureException, UnsupportedEncodingException {
        Signature sign = Signature.getInstance(ALGORITHM);
        sign.initSign(privateKey);
        sign.update(data);

        return sign.sign();
    }
    /**
     * 使用SHA1withRSA算法
     * @param content
     * @param signature
     * @return
     */
    public static boolean verify(String content, byte[] signature)
            throws NoSuchAlgorithmException, InvalidKeyException,
            SignatureException, UnsupportedEncodingException {
        Signature sign = Signature.getInstance(ALGORITHM);
        sign.initVerify(publicKey);
        sign.update(content.getBytes("UTF-8"));

        return sign.verify(signature);
    }
}
