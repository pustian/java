///**
// * Copyright (C), 2016-2017, 上海友米互联网科技有限公司
// */
//package tian.pusen.structure.decorator.银联送检实例;
//
//import java.security.KeyFactory;
//import java.security.NoSuchAlgorithmException;
//import java.security.PrivateKey;
//import java.security.PublicKey;
//import java.security.interfaces.RSAPrivateKey;
//import java.security.interfaces.RSAPublicKey;
//import java.security.spec.InvalidKeySpecException;
//import java.security.spec.PKCS8EncodedKeySpec;
//import java.security.spec.X509EncodedKeySpec;
//
//import javax.crypto.Cipher;
//
///**
// * 〈一句话功能简述〉<br>
// * 〈功能详细描述〉
// *
// * Date: 2017年11月23日 下午5:16:09
// *
// * @author tianpusen
// * @see [相关类/方法]（可选）
// * @since [产品/模块版本] （可选）
// */
//public class RSAUtil {
////    private final static String GBK_CHARACTER = "gbk";
//    public final static String UTF8_CHARACTER = "utf-8";
//    private final static String ALGORITHM = "RSA";
//    private final static String PRIVATE_KEY_STRING = "" +
//            "MIIJQwIBADANBgkqhkiG9w0BAQEFAASCCS0wggkpAgEAAoICAQDGcXFN7IkIE6+i\n"+
//            "O7V3aYNtdXFTOF1TLLZygMm8Et5AGCcJ32zbmsg27+KDPr3GfVE4+aDW1pkmBxix\n"+
//            "DbtSJzjtHD9wPLVxeAS2cMi+EBulg2yqvQi4jb4vt5bvInMQROBgobHUJR3Es2LS\n"+
//            "yl+20VDOGJwnLYD6NOq505LKU6wKfHAxNPTAkvt3KhfE0N6Ph5c8ge2dC057rN2M\n"+
//            "/FB8KsPkoZo3hp9puz55ozu045aMvo5/3kgv0MTj1K6K2ftGFl8y9VSpMeL4Qu8L\n"+
//            "wym6OVrQTvCWM4+zqset053ZxDEX6PFpcByhMqJ0LE6AMdHYsNJh3FWVQPdrTTQ+\n"+
//            "eUoXU7Kki9X8uLH0eeQjKmbTgx5Cn6NpeTxR5LAqhwUH9OsQgTu31KE+gVkp/Jm9\n"+
//            "3pXfhbbfuFlG8YuG3p576STEQr7x+AzBhd+oqHCzEhT/vaB1QgCDpVdhIwhhQTu/\n"+
//            "Tv7bdiEIr7OeOMQMVSRr/lPC9dHYGmcsCQEyxsTdsQv8KTR+M0okF89h5XTaAxi8\n"+
//            "mbLzgvZPocuDrs7u4LJgnzQLZnSZaxqWwyd8T2KZOF94e0S1lsy/cUBLfsAdALtu\n"+
//            "Bv1TRU1/+TQDVX14TPGHxHFKSgdLPKqqbQSxKpFCgN6CCYRq8vRHp0wnnuN60aqA\n"+
//            "qcQo8LkRny+KY/Ki0L/Enc6RXgBKSwIDAQABAoICAQCEDJUS/2UoJMFouUbF0UaN\n"+
//            "nertL8KaSFSX2S9koUGXcPr5TN2JPHQianpWT3fFDzrvKN1oWKDMd0hs+PA/eK4e\n"+
//            "yf9E1oD+opiwNli+cMWU2+GBtAyLknaOFiKvukLK/qo4YyCMRK6MZ7pI/jW2145n\n"+
//            "tmSihVsgI/pV8X3qeMcwqyGU/OIpCyWeZDow07Rl2ho/P044hH+TOLKs0HFWTrbP\n"+
//            "+uE+Naz6lIi8NGzUr+VMfrkgSW/LsaZy3OHQr8DPIK/0nt/ZPsMD5LZhI1su+l99\n"+
//            "4imiKWlkay44c2rEsBLjEDUkrl++vIzxtVH/aCd1hCRazl181iBOHK+5KsiswRtL\n"+
//            "yZjdbTKfSRRzM/UAW6AudlLNQdQ36GTY0U3QhKR6R25AKZhhten8fKXWAb0lwHit\n"+
//            "Ew2hbCbURcSrWdKTctNrssK7B9dfH+a7X8PdXX/Uj1J3svcs6Nylt3oxlO5TbgeK\n"+
//            "P8q7LzZnz01U0xqrtrlOo+SR6e8pgmBnanW+08EkNxCEN039RsNh3sxwaipZFrWN\n"+
//            "Mr4HGpGFA6CUwW7JLf2kPDM3DrAuOhd0/LiVQT54uM3X4/achAlZeFuKTTytZQrH\n"+
//            "b0uAgIYofXr771t3pj3RDZSPrwgrzq5aP0LPZQWIEgFHRRGJ6oDEiYZpmImlBdSd\n"+
//            "zfT2oyYdI/+NdcXXZdsfQQKCAQEA+9VHkUvpMDmhoKJmcBjnU+lZqEEhfjQfs0ln\n"+
//            "9RxtnnwQC0/GzrX011r31oc5OvfR6phRH0YQ3hbhLLYcPg6lopDjq7ewtPZUP1le\n"+
//            "DJsXhHjPYAL5Orw9FHKCPepuTidxHs0JJ/1MI0VutLC8cSsyw0EXqsKiX47gV7BY\n"+
//            "nqQjRPTZAjsUEV5wnxMKpbw6Wl51B4zhGilPLWtcZxKaq10Mp+mszXuY7da+sWkK\n"+
//            "eMjQhWa7Ns/idjEEzKfLdFAH5d5fXJvkhDTVaLFFwPN10b0eIDr08oBb7f6ySzbt\n"+
//            "M1mjlBsazjFItab63eCzDqDifYuqdAgLY8wgTIjZKZVMvuFU2wKCAQEAyboDNDIE\n"+
//            "+SDWCxk/saGD+TIoNZCEYEH3j2Zg+VUVDNjCQxAx4zKdU4krNoTg8AmJsLcte+hD\n"+
//            "UK0SxG2LliW/D3AdnG8xKpylHDWAmVVgeBdXoWSbwL8cpgqvaXBUDZOo+CDnKqqa\n"+
//            "W2zrLmzrt+RVc5a05FxXT+scRK0fH7nnnYT/tjjsxBTbtMhnKbT7NDlkF8oiv1Td\n"+
//            "J5qbJ3M+eiyiY6aUsINX2C9nNjDlrk6Eq+w0nJkNhlW+1kbIwo91Uq7XArH5V4gZ\n"+
//            "Xcp+OnCywMo+s6fDE43ifg9Gw4UMh/5wxp0ylkwUsZgsuQKYxL0TzgbigpesIVAO\n"+
//            "uFCBXBbooBejUQKCAQEAqSHpyfSQrVnXaloHsHuZSWrsUytDAk6hh7jiP+WS3aD2\n"+
//            "6ZHvtL7RrJ0k3Ii+8BKmggdAXF+jTnVRT8UTHL6bkZ37ukVJQZA6rr0nhBHZbpfw\n"+
//            "tsvodQEdiJELVegPox+sQoJ0Bs6AhyPHoUUdmmfn1OjvqzjC8qizlVETg6A7K8HB\n"+
//            "yVTCOpQR0VRpjzvmePD9ak+8kuRxj9COI4+EH1kD+fXfn7XF1N6n/eDNLqe4HBAN\n"+
//            "5lciAj0TuxtqcjiZxVFe/ISaqgwN1B1S+q7IT0/TXtQZ1JBOrX2kYw2FqzoluX+m\n"+
//            "thq1eYKm88NInqeA1c6qcPlCJQlgG+bA6sOh4oTVhwKCAQB3QAUg1+jktSqTSQvE\n"+
//            "ICz6JVJrx9DZbmXAWc891bQp8gF0z6AOJR+oAFCRXB/1Bk5lLwQuWXFhwc50XCXG\n"+
//            "Tu4RXD/E+F5iSFO6x04MVtI+a94bxHPmj5OJ/IsRk5u8ouuiMyE0scGqBktJGI6I\n"+
//            "GO0C9bZdwNDEp8IAPy9E/AsVrUfVGvWsPIBc8JM7rodJqhunjJDZ9nr+LIFH4vKu\n"+
//            "GrCspA8GAb7uR6eF+4XrcvMvBuVbJYc/QHUeCp4nldLPXBgyua8itYv5S/ku5uxk\n"+
//            "16oVXjkN2DRqPPGiJ4bu5dUZNFhxTOx2x/S4MfbJZtTMWHmSpcLVtpPdFYpDPC+s\n"+
//            "ZAzhAoIBACCSF1WTJXU70Ks6LzSKsuQc7jw5BaHHvPuiD6e/pyqWMyGU6ivR5ONu\n"+
//            "/iPFTPx5E7wJ86nECqQminRihsicf93unT5Dv9fwh+gUVvKSJmK7q9ddv6PBhiWy\n"+
//            "3HHQs0/CCKFaJD7ig3PATrZ99sV0MuBuS+H3Oki/6dXySHOxN+S/69ESaqaf4e7k\n"+
//            "fL4+B7/g8kOGlG2dciZm6EXMdwRJqfGrnOMCAIfB9kgdGpDIV9BbU0M00YA1IlDs\n"+
//            "3V1uQlB7162tb4ebpX/DGebF5+yEcY1iDSro/psjeZkUQiEpUpm2LEORlkx4lAne\n"+
//            "tmZv5e78Nwi62j83UyWBzlKdeG47exo=";
////            "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAKcTDRAUFPx6pOh7\n" +
////            "bldS5n44KkCY5pJJHzHVqy2o2G+ZOqhMv12BSFdCcHdt3Qp7e+sV7jCu6GDmH22y\n" +
////            "6rcQ4RjIa5D6tznWQIfMbMkLzts1WWYkDZFbWAEDScyHHW5A5YGzGiCG5hJS/5wY\n" +
////            "LxioWr7MlbMp/njpfVaC+kfA2/TfAgMBAAECgYBz+829s7M98wY4PjfDd1F7nhH2\n" +
////            "9i5ar1hwiBxlfimCj6Ug4OaYSv+lVAIvZp1kgYTtFjRcp4+6dQasaOB4ZcddBd8w\n" +
////            "v3SA2uGQG5fHBf42PTmyo6Fj5b5QWgOU3jiJnMbGnPTa4M/7sbLXjHkin7vqv/gT\n" +
////            "H5cHNd58SxzEviQwQQJBANAuZQKHrP8IW7x7ps6W96ojqr1butRYWwCQtFcOXkZP\n" +
////            "+HCmaBoj5/rBksAMsw/BAQ2ZAa91RaXNVvgvD2E0GI0CQQDNc3exNm+KGF/vfJ7O\n" +
////            "23MIN30GhSQMRoNB21tuDxu+LACHNyGXxtJUvUlnho9o+6J9eXUSC0zZsLTO8MN0\n" +
////            "FlYbAkEAxaxYA1f6/0rbeJRc5mtZDXfChv0T4M9o0ZeVddhR0ifbOnP4A2I2wtaN\n" +
////            "Vsh4OixEqLmxbP5IvM/GlfBxhRXnfQI/SqKn2LjtzD0OnPGov3z5TQLbaIL1lYAI\n" +
////            "LuSBUnoqmetiRbzBd2HDBwW8pN89OeYwIWT+Io/4WxzWklbkqkdzAkBfHnyclGRF\n" +
////            "ecweyZ47ZUGpYfgROBCl3GJxWqvCN8mx19p5ERnH1Sbe8dAf4zPdBaFDM7t+dcjh\n" +
////            "Lq1qNWKv2JK8";
//    private final static String PUBLIC_KEY_STRING = "" +
//            "MIICIjANBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEAxnFxTeyJCBOvoju1d2mD\n"+
//            "bXVxUzhdUyy2coDJvBLeQBgnCd9s25rINu/igz69xn1ROPmg1taZJgcYsQ27Uic4\n"+
//            "7Rw/cDy1cXgEtnDIvhAbpYNsqr0IuI2+L7eW7yJzEETgYKGx1CUdxLNi0spfttFQ\n"+
//            "zhicJy2A+jTqudOSylOsCnxwMTT0wJL7dyoXxNDej4eXPIHtnQtOe6zdjPxQfCrD\n"+
//            "5KGaN4afabs+eaM7tOOWjL6Of95IL9DE49Suitn7RhZfMvVUqTHi+ELvC8Mpujla\n"+
//            "0E7wljOPs6rHrdOd2cQxF+jxaXAcoTKidCxOgDHR2LDSYdxVlUD3a000PnlKF1Oy\n"+
//            "pIvV/Lix9HnkIypm04MeQp+jaXk8UeSwKocFB/TrEIE7t9ShPoFZKfyZvd6V34W2\n"+
//            "37hZRvGLht6ee+kkxEK+8fgMwYXfqKhwsxIU/72gdUIAg6VXYSMIYUE7v07+23Yh\n"+
//            "CK+znjjEDFUka/5TwvXR2BpnLAkBMsbE3bEL/Ck0fjNKJBfPYeV02gMYvJmy84L2\n"+
//            "T6HLg67O7uCyYJ80C2Z0mWsalsMnfE9imThfeHtEtZbMv3FAS37AHQC7bgb9U0VN\n"+
//            "f/k0A1V9eEzxh8RxSkoHSzyqqm0EsSqRQoDeggmEavL0R6dMJ57jetGqgKnEKPC5\n"+
//            "EZ8vimPyotC/xJ3OkV4ASksCAwEAAQ==\n"
//            ;
//    public static RSAPublicKey publicKey ;
//    public static RSAPrivateKey privateKey ;
//    static{
//      try {
//        publicKey = loadPublicKey(PUBLIC_KEY_STRING);
//        privateKey = loadPrivateKey(PRIVATE_KEY_STRING);
//    } catch (Exception e) {
//        // TODO Auto-generated catch block
//        e.printStackTrace();
//    }
//
//    }
//    /**
//     * 从字符串中加载公钥
//     *
//     * @param publicKeyStr
//     *            公钥数据字符串
//     * @throws Exception
//     *             加载公钥时产生的异常
//     */
//    public static RSAPublicKey loadPublicKey(String publicKeyStr) throws Exception {
//        try {
//             byte[] buffer = Base64Utils.decode(publicKeyStr, UTF8_CHARACTER);
//            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
//            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
//            RSAPublicKey rsaPublicKey = (RSAPublicKey) keyFactory.generatePublic(keySpec);
//            return rsaPublicKey;
//        } catch (NoSuchAlgorithmException e) {
//            throw new Exception("无此算法");
//        } catch (InvalidKeySpecException e) {
//            throw new Exception("公钥非法");
//        } catch (NullPointerException e) {
//            throw new Exception("公钥数据为空");
//        }
//    }
//    /**
//     * 从字符串中加载私钥<br>
//     * 加载时使用的是PKCS8EncodedKeySpec（PKCS#8编码的Key指令）。
//     *
//     * @param privateKeyStr
//     * @return
//     * @throws Exception
//     */
//    public static RSAPrivateKey loadPrivateKey(String privateKeyStr) throws Exception {
//        try {
//            byte[] buffer = Base64Utils.decode(privateKeyStr, UTF8_CHARACTER);
//            // X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
//            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
//            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
//            return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
//        } catch (NoSuchAlgorithmException e) {
//            throw new Exception("无此算法");
//        } catch (InvalidKeySpecException e) {
//            throw new Exception("私钥非法");
//        } catch (NullPointerException e) {
//            throw new Exception("私钥数据为空");
//        }
//    }
//    public static byte[] encryptData(byte[] data, PublicKey publicKey) {
//        try {
//            Cipher cipher = Cipher.getInstance(ALGORITHM );
//            // 编码前设定编码方式及密钥
//            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
//            // 传入编码数据并返回编码结果
//            return cipher.doFinal(data);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//    public static byte[] decryptData(byte[] encryptedData, PrivateKey privateKey) {
//        try {
//            Cipher cipher = Cipher.getInstance(ALGORITHM );
//            cipher.init(Cipher.DECRYPT_MODE, privateKey);
//            return cipher.doFinal(encryptedData);
//        } catch (Exception e) {
//            return null;
//        }
//    }
//    public static String decryptData(byte[] encryptedData) throws Exception {
//        RSAPrivateKey privateKey = loadPrivateKey(PRIVATE_KEY_STRING);
//        byte[] decryptBytes = decryptData(encryptedData, privateKey);
//        return new String(decryptBytes, UTF8_CHARACTER);
//    }
//
//    public static void main(String[] args) throws Exception {
//
//        RSAPublicKey publicKey = loadPublicKey(PUBLIC_KEY_STRING);
//        RSAPrivateKey privateKey = loadPrivateKey(PRIVATE_KEY_STRING);
//        String data = "123luochao罗超超";
////        String data = "MF0000530018000412137A76781518DCA3A8C5592CDA366F12D4F63025C442AB6D9EAA8F96ABF206710200020032900471020002003290043632313730303234333030303338353336313232323132722AFA53406E6F3B";
////        String data = "IC00015E001800041210F900890A55C3872E916089B662E98B7AF3218205FF105EC491670077F206710100020053287371010002005328733632323537363837313130343332323231393130F575CFC165902519010C93C737C1789A4D09A4639B8ACA4D7EA6B25917EF474E81EF60C88C217A34EB4DDAE58ADA84DD5CC2E61FD2D48174F02DD0C4C75CE70ED8A85BA25388BCABC37ABA68CF4243FD4E1027555A65C3461DAE4B070AD6F6D08648D8D579B3D6E72D1813F1338B53768D6B010E2834195698475B043C095914AB50A3E6C27E1BE80BC43816E5B464E7B5FD954616B36C339CF8E15F5B8983621D1B19604375E8EC76EFAAE9139BAC1025F4097D6DD42EFE1D2EFA231443CACFBCBA2176FB7A0B8A950AEFD25D9EF5091E206039A6986416257523E03E3C86B48FFAC1DAFC1F83EDEA50D0562B8ACBD0A74BC429AD44592EC697433E5E8B677FB2609DEBE0491DF138663ACEB2F62D13E553EA22146B";
//        byte[] encryptBytes = encryptData(data.getBytes(UTF8_CHARACTER), publicKey);
//        byte[] decryptBytes = decryptData(encryptBytes, privateKey);
//        System.out.println("data:" + data);
//        System.out.println("encryptData:" + Base64Utils.encode(encryptBytes));
//        System.out.println("decryptData:" + new String(decryptBytes, UTF8_CHARACTER));
////        String testString = "QKaVE8dragN85m9C9CM02P3w3kN1PgMTQRJf5H396MBbNxkFbc99ZjSqnOlCtyk0XOlHCrBTi0UB1jYPYV74bc/ArdeHG0/7oPkEWywgfpyUEmHEnwSEdJMt0pmScOWNk1dUT2OmT2YRnu1cMHyeXxT6ROJsRgjZoiWCSOUcAxLDTe3MTYWFLPD+ElQCbxyCrw058E6QpBJtivsZjAqomPUUmC2IMF/fYM5ulCr8zuzk5x2FBB1nNzYUQQDMq/5Py/WtklgrWKwdZ/Krn0pHeBAsZFq9c2RKABMGlaG8nvkE9a80rP5/8nl/u57Di4izzJ05g2JSdowo1rTq/D6I59IwsDqdVY8RbIcMM7KXTDe3LWGY/x+GC8tjTka2UwavWCIdXbdKHI3Uw1ik3w/CEnStTs8g6LOO9cqmvxEoySxI4XfL4mrCRq4hmkpWDuY1ZRF1pHLIX2m49jjU6Ku8rQlPn6C//Q1wFswzSeDvM9V+SC2EKQWemArl/FUcFSvHQSrHrpHkHMgUOWCc5NuA/SyHzHTedmcqssrU2Q0RmhKsmTp5uD13wN+nxyeecVvX/ll0kz0DyUTdwt2JQGYTwQQuMglvY8hc7pSO3rnwBSE7ztABhKRbj4oY0oFJfn2mnvnsBu7n5a4qZhdsK+GPc08AE2lkjAFhtgJGsdC0aDU=";
////        String testString = "UUgTSxCsPyIpdEdk0p0vZSxbgmNpsIx/r1wFMItbFC0U99P2HjLw8e+S04mn+9cPvpMBYCxZJ2s0h+mp+kZ8VW0p0Epw3NPAR7p/TcZzF7p2vRiUH25fDrMCfY6160MqsgJIChvvcL6ztZOlcLhzd+FOG61k44F/pQUUC9o6bwOlH3AGcwg4zCDomaQeQHVOOgLitVxQaPQzHtzI/0Ipzh3cu+I4WEyhPemvlMFZZyHrWgc+14avsGTufctR7d82znZWVFCd95hVG6/m4pE+kI7AUN3818YEs3p6xJE47BaTCfNO5EysDhqXd7UAQVyWQhcs/Eowz4d6bNbQ9zK4CafZ2wwcxu1qNVhPAbxVEUANwZ3b5oVRWKYQLp3fw/DXDRMP+YMF/j08w+rUxUL86je1IAUp51+nccwXXi+5pULlDLdXJ5UY6IWMTc/EQcAkxAocmlYiKaR1cAqs6EpR/s0pHGv6uALMSMAt3SFoejzOcIW8tj4MtT9eHiplMhwfRQ2mvBF8UG5GNmYcQoQBp8Y0ObODWpHBpwAoUAvXhXm+IeNxe0E5JIaVFRdTyiT/rhkDv/DWIkQibNc6kiXdFCXSkZV/YQ+nZtPuKaNMxbqIhCqMODTw0xKtQxoTQAKGLG5i6pWrQlPNJPDas75rlC4KTM9zTZQ2ZK/R7OhKfudNvGuM9RLs7hZ20RCDnGMlHxQHB3bgQUAqrDmI9ev7P1sBCUaf2P8X3Cjp0kyfgYnnErqON3TwNo/lWKzAAeQNRAQ1ZYDB4PDLp7ExKAgZAadXBNX0ZlO2RFayZJm4IBgZyeUwUdEzIQCtA68N+1DEZbpV4NfYH5J9RO6z5XxQfnrcsOjb/I7dntfRDwSkIjVKiMBrbtp04RYy5RvhX9OWGP+SgdKs2B/wImfNPQhW4NYUsgHsAHCNJncfz3XW8tZbPiTGsYR3m+BB5QuMKSNaBQDJdUfI2hrdmnqoDEplHmiZdfZkPihMAjcXSL5FYpXfs05f8bn4yaRBJAF/L0/Q0iHt+TE8E7GcuXyidyTlEWljiL20sA3rsuaaHxQwWCgvrpqhHf/Euy899aa/7Ma5PZy7IqGmBWWnXGMGrEXf3yLfKj/1QrCrscMUlQmALN+1Vy2Arjj6yOFb+Z91S+4V3oBscY19P0SZMfU0kYaCuThrQE9IxkPpkZ+6v+sSpNtN8Rz1x9I089aEBVk8VPW3GNzjq8L9+Aqtpt3X+dYWuOIXu6p76Ctr9lOWRsUvTua4qelsoEIuRYPnGaz6OWbWuQQYpRfE/sMIOtGVLpEDgAdUCagd0ossTEMP8AHCsTrIv7A1OJaZSHOcAnbe7eH6sTpJzP+sNTdHNRyKb+g==";
////        String testString = "DzfMRXua+eb1TBcOR0gWTloVXH0z+Ou+SAAsDPu4kAA+sexBFoUk3G2IS8PhNA0NA3uQPGjTRsSCO5EbGveld07pE0z6oVWpawUs55wKqZob2h+l3eKjn2MVoawYQE0cMgPDFyy8cT+FPvmtoSmOKx0kL41y6LZH9aCzidCtU4DRRT1d6/MQzHAWG7ioPHbDCu/pyKOA5kcGDVi6H+q/1xXtzQw5V3YI6rJJSDKKVCMZA1bb7cgLZeirhMqk6QCkvxX3CZLi1IBog8vYVXY5OTJuP4OVYzmdkY3+BCAoA2LU/RR7+/03duMPzEhJTp+kGovzxow82Ylw5KkjQ30k6KMwQlsAdg37SX9aapFhMQwjtqvR05EnjtoPS0F0Oa+MRVG52WoaaHI6ERluoNcSIaFr7JGYOAhT37cvw6tL4UzqTNrx1ECd3VAkqNPXix0aaFK1f4/3GJldpqR0hlnIXYZcTTD8hCbShzDuxegE+sHWGmNXA0p38pG9Y4yQE8iaUSRMkEtVvy2zuydfSDk+KtduaFvpBsqua3fAmhYJDVCvXPN1E6Hmr7DSMv+w85nD4IZRcNnhM9uu0HFF1jaQxCw5jM/Leuu8Ul3/YE27yaAW2DtxhcPhvwdaqa4lLsIyjYLxJyACtcRJR4lj+liEynSE2ze4pBUrExCVCnJ8Txs=";
//        String testString = "TFSDpmkUNg164S9WbNBOcLjuZe7d2OmLqxi2t5v67R6+MINb71eJCB3SBF1FmSODxxnl8KaYVrLBXXvVr3i8+yTX0pGp1AEBYu6LVoBVSnBEoZLix3At09jmr1I7vsoiYiqgmJWtxFTH1d/AOXvzw9Tu4ldnb1jcOmqGfAiTgrCtCWkGZVR6AGxdkufPZ7WqwltVbgiOSVqmKw9FnYoWtc03LsnTVLrG/PsP1Hn2IXTS8cjG+w0IUspYI+dzW8+RKfyN5S0+Cfg85lc5hMKsqjGNamxwuwZr++bTPsmX4v/lNnww5+s7Yvk7p31Jf53pnrAcBlPpT72LdWFXOBKrgISZuSUhMvYiMmdecU0E2JfltueV05xmQTyTklvlENjXT1V5vh6EcLn/oQPlS1vEPgUPV3OCtClxZ43HEZXGdbUGvrk9nTaS0zB4M9oINW+myMUouhel0O1xzK6LspRartucmVpkaTuiSnW3tkFctLjL4Z6mKTzVbYvl0ZPEVUCrOKg01rb/ysALT6cJ3ZTtsO3+douYq6EjTPOGTkBoA7GLdpq2BnRFpSvfByk/J8WPztvIubj6VPcfss/xBxES6HjNn+j/Uk0XoLLFbbxRffplEdYOFHr4W0FQ22IKtiRSVJfLs9IJJbh4wGj9Xr2OYz4uMLnc67h1apGY1leqvk0=";
//        byte[] testBytes = Base64Utils.decode(testString, UTF8_CHARACTER);
//        decryptBytes = decryptData(testBytes, privateKey);
//        System.out.println("=== decryptData:" + new String(decryptBytes, UTF8_CHARACTER));
//    }
//}
