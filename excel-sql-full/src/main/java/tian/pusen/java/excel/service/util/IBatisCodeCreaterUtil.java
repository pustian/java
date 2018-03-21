//package tian.pusen.java.excel.service.util;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import tian.pusen.java.excel.pojo.code.CodeContext;
//
//import java.io.IOException;
//
///**
// * Created by tianpusen on 2017/3/24.
// */
//public class IBatisCodeCreaterUtil {
//    private static final Logger logger = LoggerFactory.getLogger(IBatisCodeCreaterUtil.class);
//
//    public static final String FOUR_SPACES= "    ";
//    public static final char ONE_SPACE = ' ';
//    public static final char BRACKET_START = '(';
//    public static final char BRACKET_END = ')';
//    public static final char BRACE_START = '{';
//    public static final char BRACE_END = '}';
//    public static final char COMMA=  ',';
//    public static final char SEMICOLON = ';';
//    public static final char NEW_LINE_CHAR=  '\n';
//
//
//    public static final String CLASS_PREFIX = "public classs ";
//    public static final String INTERFACE_PREFIX = "public interface ";
//
//
//    private static final String INSERT_METHOD = "void insert({})";
//    private static final String UPDATE_METHOD = "void update({})";
//    private static final String query_METHOD = "{} queryByID(String keyID)";
//    private static final String queryList_METHOD = "List<{}> queryByID({})";
//
//
//    public static void createDao(CodeContext codeContext, String filename) throws IOException {
//        String daoInterfaceCode = generateDaoCode(codeContext);
//        String daoInterfaceCodeName = "I"+codeContext.getClassName()+"Dao.java";
//        FileUtil.writeFile(daoInterfaceCode, daoInterfaceCodeName, filename );
//    }
//    private static String generateDaoCode(CodeContext codeContext) {
//        StringBuffer sb = new StringBuffer();
//        sb.append("public interface I").append(codeContext.getClassName()).append("Dao {\n");
//
//        sb.append("}\n");
//        return  sb.toString();
//    }
//    public static void createDaoImpl(CodeContext codeContext, String filename) throws IOException {
//        String daoImplCode = generateDaoImplCode(codeContext);
//        String daoImplCodeName = codeContext.getClassName()+"DaoImpl.java";
//        FileUtil.writeFile(daoImplCode, daoImplCodeName, filename );
//    }
//    private static String generateDaoImplCode(CodeContext codeContext) {
//        StringBuffer sb = new StringBuffer();
//        sb.append("public class ").append(codeContext.getClassName()).append("DaoImpl implements ");
//        sb.append("I").append(codeContext.getClassName()).append("Dao {\n");
//
//        sb.append("}\n");
//        return  sb.toString();
//    }
//    public static void createXML(CodeContext codeContext, String filename) throws IOException {
//        String xmlCode = generateXMLCode(codeContext);
//        String xmlCodeName = codeContext.getClassName()+"-SQLMap.xml";
//        FileUtil.writeFile(xmlCode, xmlCodeName, filename );
//    }
//    private static String generateXMLCode(CodeContext codeContext) {
//        String classname = codeContext.getClassName();
//        StringBuffer sb = new StringBuffer();
//        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>").append('\n');
//        sb.append("<!DOCTYPE sqlMapConfig").append('\n');
//        sb.append("    PUBLIC \"-//ibatis.apache.org//DTO SQL Map Config 2.0//EN\"").append('\n');
//        sb.append("    http://ibatis.apache.org/dtd/sql-map-config-2.dtd\">)").append('\n');
//        sb.append("<sqlMap namespace=\""+ classname +"\">").append('\n');
//
//        sb.append("    <resultMap id=\"" + classname + "\" class=\"" + classname + "\"> ").append('\n');
//        sb.append("        <result property=\"XXX\" column=\"XXXXX\" ");
//            sb.append(  "jdbcType=\"VARCHAR\" javaType=\"java.lang.String\" >");
//        sb.append("    </resultMap>");
//        sb.append("......");
//        sb.append("</sqlMap>").append('\n');
//        return  sb.toString();
//    }
////    public static void createService(CodeContext codeContext, String filename) throws IOException {
////        String serviceInterfaceCode = generateServiceCode(codeContext);
////        String serviceInterfaceCodeName = codeContext.getClassName()+"Service.java";
////        FileUtil.writeFile(serviceInterfaceCode, serviceInterfaceCodeName, filename );
////    }
////    public static String generateServiceCode(CodeContext codeContext) {
////        StringBuffer sb = new StringBuffer();
////        sb.append("public interface I").append(codeContext.getClassName()).append("Service {\n");
////
////        sb.append("}\n");
////        return sb.toString();
////    }
////    public static void createServiceImpl(CodeContext codeContext, String filename) throws IOException {
////        String serviceImplCode = generateServiceImplCode(codeContext);
////        String serviceImplCodeName = codeContext.getClassName()+"ServiceImpl.java";
////        FileUtil.writeFile(serviceImplCode, serviceImplCodeName, filename );
////    }
////    public static String generateServiceImplCode(CodeContext codeContext) {
////        StringBuffer sb = new StringBuffer();
////        sb.append("public class ").append(codeContext.getClassName()).append("ServiceImpl implements ");
////        sb.append("I").append(codeContext.getClassName()).append("Service {\n");
////        sb.append("}\n");
////        return sb.toString();
////    }
//}
