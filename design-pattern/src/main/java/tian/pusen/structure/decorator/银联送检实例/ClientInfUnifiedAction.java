//package tian.pusen.structure.decorator.银联送检实例;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.log4j.Logger;
//
//import com.jfpay.preposing.bean.CommunicationInfo;
//import com.jfpay.preposing.bean.LocalResp;
//import com.jfpay.preposing.control.RequestPoolObject;
//import com.jfpay.preposing.properties.DataDictInitialize;
//import com.jfpay.preposing.remote.SendPayService;
//import com.jfpay.preposing.reqhandle.ClientReqHandleProxy;
//import com.jfpay.preposing.reqhandle.service.IClientReqHandleService;
//import com.jfpay.preposing.support.ReturnMapCreateTool;
//import com.jfpay.preposing.support.SystemCode;
//import com.jfpay.preposing.utils.HttpUtil;
//import com.jfpay.preposing.utils.MD5;
//import com.jfpay.preposing.utils.SensitiveInfoUtils;
//import com.jfpay.preposing.utils.SensitivityInfo;
//import com.jfpay.preposing.utils.UDPClient;
//import com.jfpay.preposing.utils.UDPClientLogin;
//import com.jfpay.preposing.utils.UrlCache;
//import com.jfpay.preposing.utils.WebServiceUtil;
//import com.jfpay.preposing.utils.WebServiceUtil2;
//import com.jfpay.preposing.xml.DynamicCreateCoreXmlForJFPay;
//import com.jfpay.preposing.xml.ParseCoreReceiveXml;
//
//import cn.hnae.tuxedojms.allocate.TuxedoConst;
//import cn.hnae.tuxedojms.allocate.TxServiceHelper;
//
//public class ClientInfUnifiedAction extends HttpServlet {
//
//    /**
//     *
//     */
//    private static final long serialVersionUID = -1796194778382733548L;
//
//
//    static int blackIPTime = 1000 * 2;
//    static int clearIPTime = 2000 * 3;
//
//
//    public void doGet(HttpServletRequest request, HttpServletResponse response)// 接收get方法的请求，转到dopost方法处理
//            throws ServletException, IOException {
//        this.doPost(request, response);
//        // response.setStatus(403);
//    }
//
//    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        // 交易开始时间
//        String requestXmlWithoutDecode = request.getParameter("requestXml");
//        // 对请求的报文进行脱敏
//        log.info("发起交易step1 requestXmlWithoutDecode: " + requestXmlWithoutDecode);
//        String desensitizationXml = SensitivityInfo.checkRequestXml(requestXmlWithoutDecode);
//
//        if (requestXmlWithoutDecode.length() <= 5000) {
//            log.info("发起交易 requestXmlWithoutDecode: " + ip + desensitizationXml);
//        }
//        if (requestXmlWithoutDecode == null || requestXmlWithoutDecode.equals("")) {
//            return;
//        }
//        request.setCharacterEncoding("UTF-8");
//        response.setCharacterEncoding("UTF-8");
//
//        String msg = "";
//        String desensitizationMsg = ""; // 脱敏后的信息
//        PrintWriter out = response.getWriter();
//        String rpoKey = "";
//        Object returnMsg = null;
//
//        // 客户端请求前置map
//        HashMap<String, String> map = new HashMap<String, String>();
//        // 前置响应客户端map
//        HashMap<String, String> resultMap = new HashMap<String, String>();
//        msg = = HttpUtil.send(postUrl, postData);
//        out.write(msg);
//        out.flush();
//        out.close();
//    }
//
//    /**
//     * 获取客户端IP地址
//     *
//     * @param request
//     * @return
//     */
//    public String getIpAddr(HttpServletRequest request) {
//        /*
//         * Enumeration em=request.getHeaderNames(); while(em.hasMoreElements()){ Object o=em.nextElement();
//         * System.out.println(o.toString()+"===>"+request.getHeader(o.toString())); }
//         */
//        String ip = request.getHeader("X-Real-IP");
//
//        /*
//         * if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { ip =
//         * request.getHeader("REMOTE-HOST"); } if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//         * ip = request.getRemoteAddr(); }
//         */
//        String port = request.getHeader("X-Real-PORT");
//        return ip + ":" + port;
//    }
//
//}
