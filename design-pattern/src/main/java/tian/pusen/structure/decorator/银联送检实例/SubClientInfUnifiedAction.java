///**
// * Copyright (C), 2016-2017, 上海友米互联网科技有限公司
// */
//package tian.pusen.structure.decorator.银联送检实例;
//
//import java.io.IOException;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletRequestWrapper;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.log4j.Logger;
//import org.python.modules.synchronize;
//
//import com.jfpay.preposing.control.RequestTimeOutControl;
//import com.jfpay.preposing.utils.ParameterRequestWrapper;
//import com.jfpay.preposing.utils.RSAUtil;
//
///**
// * 〈一句话功能简述〉<br>
// * 〈功能详细描述〉
// *
// * Date: 2017年11月22日 下午1:18:59
// *
// * @author tianpusen
// * @see [相关类/方法]（可选）
// * @since [产品/模块版本] （可选）
// */
//public class SubClientInfUnifiedAction extends ClientInfUnifiedAction {
//    private static final long serialVersionUID = 1L;
//    static Logger log = Logger.getLogger(SubClientInfUnifiedAction.class);
//
//    @Override
//    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setCharacterEncoding("UTF-8");
//        response.setCharacterEncoding("UTF-8");
//        String requestXmlWithoutDecode = request.getParameter("requestXml");
//        String decyptXML = requestXmlWithoutDecode;
////        try{
////            log.info("解密前requesXML："+ requestXmlWithoutDecode);
////            decyptXML = RSAUtil.decryptData(requestXmlWithoutDecode.getBytes(RSAUtil.UTF8_CHARACTER));
////            log.info("解密后requesXML："+ decyptXML);
////        }catch(Exception e) {
////            log.error("解密异常", e);
////        }
//        log.info("子类方法调用开始:" + decyptXML);
//        ParameterRequestWrapper newRequest= new ParameterRequestWrapper(request);
////        newRequest.addParameter("requestXml", decyptXML);
////        log.info("new request");
//        super.doPost(newRequest, response);
//        log.info("子类方法调用结束");
//    }
//}
