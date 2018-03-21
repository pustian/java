///**
// * Copyright (C), 2016-2017, 上海友米互联网科技有限公司
// */
//package tian.pusen.structure.decorator.银联送检实例;
//
//import java.io.UnsupportedEncodingException;
//import java.net.URLEncoder;
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletRequestWrapper;
//
//import org.apache.log4j.Logger;
//
//import antlr.StringUtils;
//
///**
// * 〈一句话功能简述〉<br>
// * 〈功能详细描述〉
// *
// * Date: 2017年11月24日 下午7:11:23
// *
// * @author tianpusen
// * @see [相关类/方法]（可选）
// * @since [产品/模块版本] （可选）
// */
//public class ParameterRequestWrapper extends HttpServletRequestWrapper {
//    static Logger logger = Logger.getLogger(ParameterRequestWrapper.class);
//    private Map<String, String[]> paramsMap = new HashMap<String, String[]>();
//
////    private final static String APPLICATION = "BankCardBalance"; // "BankCardBalance"
////    private final static String ENCRYPT_KEY_BEGIN = "<cardInfo>"; // "cardInfo"
////    private final static String ENCRYPT_KEY_END = "</cardInfo>";
//    static final String SIGN_KEY = "53e01b9927e1563fe5c5c0bc093e8669";
//    static final String[] APPLICATION_LIST = new String[]{"BankCardBalance", "JFPalCardPay"};
//    static Map<String, String[]> applicationMap ;
//    static {
//        applicationMap = new HashMap<String, String[]>();
//        applicationMap.put("BankCardBalance",new String[]{"<cardInfo>", "</cardInfo>"});
//        applicationMap.put("JFPalCardPay",new String[]{"<cardInfo>", "</cardInfo>"});
//    }
//
//    @SuppressWarnings("unchecked")
//    public ParameterRequestWrapper(HttpServletRequest request) {
//        super(request);
//        logger.info("Sub request");
//        Map<String, String[]> requestParamMap =  request.getParameterMap();
//        String[] values = requestParamMap.get("requestXml");
//        // 测试代码
//        logger.info("Before reset parameterMap-->requestXml value");
//        for(int i=0; i< values.length; i++) {
//            logger.info("测试代码："+i+"|"+values[i]);
//        }
////        resetParameterRequestXml(values[0]);
////        for(String application: APPLICATION_LIST) {
//        boolean boolDecyrptFlag = false;
//        for(int i=0; i<APPLICATION_LIST.length; i++) {
//            logger.info("需要解密该接口："+values[0].contains(APPLICATION_LIST[i]));
//            if(values[0].contains(APPLICATION_LIST[i])) {
//                String[] decryptValues = requestXmlStrings(values[0], applicationMap.get(APPLICATION_LIST[i]));
//                String[] replacedSignArr = new String[]{replacedSign(decryptValues[0], SIGN_KEY), };
//                paramsMap.put("requestXml", replacedSignArr);
//                // 测试代码
//                logger.info("After reset parameterMap-->requestXml value");
//                for(int j=0; j< replacedSignArr.length; j++) {
//                    logger.info("测试代码："+i+"|"+replacedSignArr[j]);
//                }
//                boolDecyrptFlag = true;
//            }
//        }
//        // 不需要解密的接口
//        if(boolDecyrptFlag == false) {
//            paramsMap.put("requestXml", values);
//        }
//    }
//    // 获取parameter方法
//    @Override
//    public String[] getParameterValues(String name) {
//        return paramsMap.get(name);
//    }
//
//    @Override
//    public String getParameter(String name) {
//        String[]values = paramsMap.get(name);
//        if(values == null || values.length == 0) {
//            return null;
//        }
//        return values[0];
//    }
////    // 重置请求
////    public void resetParameterRequestXml(String requestXml) {// 增加参数
////        String[] strings = requestXmlStrings(requestXml);
////        synchronized (this) {
////            paramsMap.put("requestXml", strings);
////        }
////        logger.info("重置requestXml");
////    }
//
//    private static String[] requestXmlStrings(String requestXml, String[] string) {
//        logger.info("requestXml 解密前为" + requestXml);
//        // 获取
//        logger.info("requestXml 包含  application 需要做解密");
//        for(int i=0; i < string.length; i+=2) {
//            int cardInfoStart = requestXml.indexOf(string[i]);
//            int cardInfoEnd = requestXml.indexOf(string[i+1]);
//            cardInfoStart += string[i+1].length();
//            String cardInfoValue = requestXml.substring(cardInfoStart-1, cardInfoEnd);
//            String[] stringArr = cardInfoValue.split("@");
//            String cardEncrptValue = cardInfoValue;
//            String cardSuffix = "";
//            if(stringArr.length > 1) {
//                cardEncrptValue = stringArr[0];
//                cardSuffix = stringArr[1];
//            }
//            String decryptCardInfoValue = null;
//            logger.info("encypt string:" + cardEncrptValue +  "\n suffix:"+ cardSuffix);
//            try {
//                byte[] testBytes = Base64Utils.decode(cardEncrptValue, RSAUtil.UTF8_CHARACTER);
//                byte[] decryptCardEncryptBytes = RSAUtil.decryptData(testBytes, RSAUtil.privateKey);
//                decryptCardInfoValue = new String(decryptCardEncryptBytes, RSAUtil.UTF8_CHARACTER) + cardSuffix;
//                requestXml = requestXml.replace(cardInfoValue, decryptCardInfoValue);
//                logger.info("cardInfoValue:" + cardInfoValue + " decryptCardInfoValue:" + decryptCardInfoValue);
//                logger.info("requestXml 解密后为" + requestXml);
//            } catch (UnsupportedEncodingException e) {
//                logger.error("处理报文处理异常", e);
//            }
//        }
//        return new String[] {requestXml};
//    }
//
//    private static String replacedSign(String requestXml,String key) {
//        String signOrign = requestXml.substring(requestXml.indexOf("<sign>"), requestXml.indexOf("</sign>")+7);
//        String signKey = "<sign>"+key+"</sign>";
//        String replacedRequestXml = requestXml.replace(signOrign,signKey);
//        String urlEncuderrequestXml = null;
//        try {
//            urlEncuderrequestXml = URLEncoder.encode(replacedRequestXml,"UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            logger.error("Md5异常", e);
//        }
//
//        String signDesc = "<sign>"+MD5.md5(urlEncuderrequestXml).toUpperCase()+"</sign>";
//        String replacedSign = replacedRequestXml.replace(signKey, signDesc);
//        logger.info("Replaced sign:"+replacedSign);
//        return replacedSign;
//    }
//
//    public static void main(String[] args) {
//        String xml = "<JFPay application=\"JFPalCardPay.Req\" version=\"3.5.9\" osType=\"iOS10.1.1\" clientType=\"04\" appUser=\"jfpal2\" userIP=\"172.16.1.2\" mobileSerialNum=\"IIKDCZIUYDBWRJUENKAICVZLALTEIPDCSUCUOKOSQ\" phone=\"15601819126\" token=\"C3A699178C2FC1178697A0A5AED2F46C\"><productId>0000000000</productId><transDate>20171130</transDate><transTime>194944</transTime><mobileNo>15601819126</mobileNo><merchantId>0001000001</merchantId><partnerId></partnerId>"
////                    +"<cardInfo>TFSDpmkUNg164S9WbNBOcLjuZe7d2OmLqxi2t5v67R6+MINb71eJCB3SBF1FmSODxxnl8KaYVrLBXXvVr3i8+yTX0pGp1AEBYu6LVoBVSnBEoZLix3At09jmr1I7vsoiYiqgmJWtxFTH1d/AOXvzw9Tu4ldnb1jcOmqGfAiTgrCtCWkGZVR6AGxdkufPZ7WqwltVbgiOSVqmKw9FnYoWtc03LsnTVLrG/PsP1Hn2IXTS8cjG+w0IUspYI+dzW8+RKfyN5S0+Cfg85lc5hMKsqjGNamxwuwZr++bTPsmX4v/lNnww5+s7Yvk7p31Jf53pnrAcBlPpT72LdWFXOBKrgISZuSUhMvYiMmdecU0E2JfltueV05xmQTyTklvlENjXT1V5vh6EcLn/oQPlS1vEPgUPV3OCtClxZ43HEZXGdbUGvrk9nTaS0zB4M9oINW+myMUouhel0O1xzK6LspRartucmVpkaTuiSnW3tkFctLjL4Z6mKTzVbYvl0ZPEVUCrOKg01rb/ysALT6cJ3ZTtsO3+douYq6EjTPOGTkBoA7GLdpq2BnRFpSvfByk/J8WPztvIubj6VPcfss/xBxES6HjNn+j/Uk0XoLLFbbxRffplEdYOFHr4W0FQ22IKtiRSVJfLs9IJJbh4wGj9Xr2OYz4uMLnc67h1apGY1leqvk0=&0D252BD419E1A01010C3BA294CA120E33CD01F170D998E815121A8BB62EB1728F519E71593D11B58E6CDDEB95A3DBF5CAF6272EE9BAD060D3257FFF148BA9CCF73D92DD700CBDF87EF4241651E5A90525CAB5EE7C1216BF6ED15D39F33DED20EDA61B0B2669521607FAC63BBF4DA5AE5453D77BADEAEC738F58BE3157469F359175ADC6F839843B90D1CB1E7AB479486DC1D054C61AC1AAC33108AE88F8413A8E4ACAB9372D08A88327361A48BC7F2C22326229E718FB641F00F33E45AF8E89EE7E7063F6B708F548A3412F9B604FC4FE5DF5974AB7608AC45C4E553595049E82B4D4646750521A0536850E7E73D8A8DAC405DA9F44772CA22BC3FBB01BCE19EB9B4002EC1A2313B06CACA38EF38EF7314C1EA23F0C</cardInfo>"
//                    +"<cardInfo>TFSDpmkUNg164S9WbNBOcLjuZe7d2OmLqxi2t5v67R6+MINb71eJCB3SBF1FmSODxxnl8KaYVrLBXXvVr3i8+yTX0pGp1AEBYu6LVoBVSnBEoZLix3At09jmr1I7vsoiYiqgmJWtxFTH1d/AOXvzw9Tu4ldnb1jcOmqGfAiTgrCtCWkGZVR6AGxdkufPZ7WqwltVbgiOSVqmKw9FnYoWtc03LsnTVLrG/PsP1Hn2IXTS8cjG+w0IUspYI+dzW8+RKfyN5S0+Cfg85lc5hMKsqjGNamxwuwZr++bTPsmX4v/lNnww5+s7Yvk7p31Jf53pnrAcBlPpT72LdWFXOBKrgISZuSUhMvYiMmdecU0E2JfltueV05xmQTyTklvlENjXT1V5vh6EcLn/oQPlS1vEPgUPV3OCtClxZ43HEZXGdbUGvrk9nTaS0zB4M9oINW+myMUouhel0O1xzK6LspRartucmVpkaTuiSnW3tkFctLjL4Z6mKTzVbYvl0ZPEVUCrOKg01rb/ysALT6cJ3ZTtsO3+douYq6EjTPOGTkBoA7GLdpq2BnRFpSvfByk/J8WPztvIubj6VPcfss/xBxES6HjNn+j/Uk0XoLLFbbxRffplEdYOFHr4W0FQ22IKtiRSVJfLs9IJJbh4wGj9Xr2OYz4uMLnc67h1apGY1leqvk0=</cardInfo>"
//                    + "<orderAmt>5000</orderAmt><parameter>BY</parameter><orderId>2017113000912450</orderId><cardPassword>0B8D525DB127318363234A97DFFDA2AF0777352A3CB3D94029A430C5458CBF3634B90AA38EF9397746F2ACD1DB74A32772C9C2BFA8386B14E45D9D4020338CEE08C527267C69F654F6DDBDC7657E875BD2D7BB71BABC1C2B95A35E450EC4DFD6EFF1E0A8ACFED46077EFEE5415FFB89DF5C8732B8F2708E4F5CF02B2B56B553F</cardPassword><transLogNo>000035</transLogNo><sign>613F93AB50DEE62EFAAD52C6887A81E2</sign></JFPay>";
//        String[] strings = requestXmlStrings(xml, applicationMap.get("BankCardBalance"));
//        for(String str: strings) {
//            System.out.println(str);
//        }
//    }
//}
