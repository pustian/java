package tian.pusen.java.excel.service.create.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tian.pusen.java.excel.pojo.code.CodeContext;
import tian.pusen.java.excel.service.create.AbstractCreater;

/**
 * Created by tianpusen on 2017/3/24.
 */
public final class IbatisCodeCreater extends AbstractCreater {
    private static final Logger logger = LoggerFactory.getLogger(IbatisCodeCreater.class);
    private static final String XML = "/ibatis";
    private static final String DAO = "/dao";
    private static final String DAO_IMPL = "/dao/impl";
    private static final String MODEL = "/model";
    private static final String SERVICE = "/service";
    private static final String SERVICE_IMPL = "/service/impl";
//    /**
//     * 生成文件包含 创建表文件
//     * 创建表的script
//     * 跟表数据对应的XXXModel对象
//     * 表操作的IXXXDao XXXDaoImpl
//     *         IXXXService XXXServiceImpl
//     * 表操作相关的xml文件
//     *
//     * 测试相关文件
//     *
//     * @param context
//     */
//    @Override
//    protected void createIbatisDetail(Context context, String filename) {
//        CodeContext codeContext = null;
//        if (context instanceof CodeContext) {
//            codeContext = (CodeContext)context;
//        }else {
//            logger.error("非CodeContext的类对象");
//            throw new RuntimeException("非CodeContext的类对象");
//        }
//        logger.info("开始生成代码");
//        IBatisCodeCreaterUtil.createDir(filename);
//        logger.debug("创建生成代码目录");
//        try {
//            // 创建表的脚本
//            String scriptDir = filename + SCRIPT;
//            IBatisCodeCreaterUtil.createDir(scriptDir);
//            IBatisCodeCreaterUtil.createScript(codeContext, scriptDir);
//            logger.debug("生成创建表的脚本");
//            // 创建dao model
//            String modelDir = filename + MODEL;
//            IBatisCodeCreaterUtil.createDir(modelDir);
//            IBatisCodeCreaterUtil.createModel(codeContext, modelDir);
//            logger.debug("生成表操作的对象代码");
//            // 创建dao interface impl
//            String daoDir = filename + DAO;
//            IBatisCodeCreaterUtil.createDir(daoDir);
//            IBatisCodeCreaterUtil.createDao(codeContext, daoDir);
//            String daoImplDir = filename + DAO_IMPL;
//            IBatisCodeCreaterUtil.createDir(daoImplDir);
//            IBatisCodeCreaterUtil.createDaoImpl(codeContext, daoImplDir);
//            logger.debug("生成表操作的数据库操作dao对象和实现代码");
//            // 创建dao 对应的xml操作文件
//            String xmlDir = filename + XML;
//            IBatisCodeCreaterUtil.createDir(xmlDir);
//            IBatisCodeCreaterUtil.createXML(codeContext, xmlDir);
//            logger.debug("生成表操作的XML文件");
//
//            // 创建service interface impl
//            String serviceDir = filename + SERVICE;
//            IBatisCodeCreaterUtil.createDir(serviceDir);
//            IBatisCodeCreaterUtil.createService(codeContext, serviceDir);
//            String serviceImplDir = filename + SERVICE_IMPL;
//            IBatisCodeCreaterUtil.createDir(serviceImplDir);
//            IBatisCodeCreaterUtil.createServiceImpl(codeContext, serviceImplDir);
//            logger.debug("生成表操作的服务层service接口和实现代码");
//        }catch(IOException e) {
//            logger.error("生成代码时发生异常，需要手工删除{}", filename);
//            throw new RuntimeException("生成代码时发生异常", e);
//        }
//
//
//
//        logger.info("成功生成代码");
//    }


    @Override
    protected void createCodeDetail(CodeContext context, String filename) {

    }
}
