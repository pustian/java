package tian.pusen.java.excel.service.create;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tian.pusen.java.excel.entity.CodeContext;
import tian.pusen.java.excel.entity.CodeMetaItem;
import tian.pusen.java.excel.service.util.FileUtil;
import tian.pusen.java.excel.util.StringUtil;

import java.io.IOException;
import java.util.List;

/**
 * Created by tianpusen on 2017/3/24.
 */
public abstract class AbstractCreater implements Creater{
    private static final Logger logger = LoggerFactory.getLogger(AbstractCreater.class);
    private static final String SCRIPT = "/script";
    private static final String CREATE_SCRIPT_FILE_NAME = "create_table_%s.sql";
    private static final String CREATE_TABLE_STATEMENT_START    = "CREATE TABLE %s (\n";
    private static final String CREATE_TABLE_STATEMENT_AUTO_INC ="  %s %s NOT NULL AUTO_INCREMENT, \n";
    private static final String CREATE_TABLE_STATEMENT_NOT_NULL ="  %s %s (%s) NOT NULL, \n";
    private static final String CREATE_TABLE_STATEMENT_NO_SIZE_NOT_NULL ="  %s %s NOT NULL, \n";
    private static final String CREATE_TABLE_STATEMENT_SIZE      ="  %s %s (%s), \n";
    private static final String CREATE_TABLE_STATEMENT_NO_SIZE ="  %s %s, \n";
    private static final String CREATE_TABLE_STATEMENT_KEY        ="  PRIMARY KEY (%s)\n";
    private static final String CREATE_TABLE_STATEMENT_END       =") ENGINE=INNODB DEFAULT CHARSET=UTF8;";

    private static final String SERVICE = "/service";
    private static final String SERVICE_IMPL = "/service/impl";
    private static final String MODEL = "/model";

    private static final String DDL_SCRIPT = "/ddlScript";
    @Override
    public void create(CodeContext context, String fileDirectory) {
        try{
            if(context instanceof CodeContext) {
                CodeContext codeContext = (CodeContext) context;
                // 生成script目录和代码
                createTableScript(codeContext, fileDirectory);

                // 生成entity目录和代码
                createModel(codeContext, fileDirectory);

//                // 生成service目录和代码
//                // 生成dao/mapper目录和代码

//            } else if(context instanceof SQLContext){
//                SQLContext sqlContext =(SQLContext)context;
//                // 生成ddlScript目录目录和代码
//                FileUtil.createDir(filename);
//                createDDLScript(sqlContext, filename);
            } else {
                throw new RuntimeException("context对象非CodeContext 也非 SQLContext");
            }
        }catch (IOException ioe) {
            logger.error("生成表创建数据和代码异常", ioe);
            throw new RuntimeException("生成表创建数据和代码异常");
        }
    }
    protected  abstract void createCodeDetail(CodeContext context, String fileDirectory);

    /**
     * 生成script目录和代码
     * @param codeContext
     * @param fileDirectory
     * @throws IOException
     */
    private static void createTableScript(CodeContext codeContext, String fileDirectory) throws IOException {
        String scriptFilePath = fileDirectory + SCRIPT;
        FileUtil.createDir(scriptFilePath);
        String scriptFileName = "create_table_" + codeContext.getTableName()+".sql";

        String script = generateCreateTableScript(codeContext);
        FileUtil.writeFile(script, scriptFileName, scriptFilePath);
    }

    private static String generateCreateTableScript(CodeContext codeContext) {
        StringBuffer sb = new StringBuffer();
        sb.append(String.format(CREATE_TABLE_STATEMENT_START, codeContext.getTableName() ) );

        String primaryKeyStatement = null;
        List<CodeMetaItem> codeMetaItemList = codeContext.getCodeMetaItemList();
        for (CodeMetaItem codeMetaItem : codeMetaItemList) {
            String column = codeMetaItem.getColumn();
            String columnType = codeMetaItem.getColumnType();
            String columnSize = codeMetaItem.getColumnByteSize();
            boolean isNotNull = codeMetaItem.getNotNull();
            boolean isKey = codeMetaItem.getKey();
            if(isKey) {
                sb.append(String.format(CREATE_TABLE_STATEMENT_AUTO_INC, column, columnType) );
                primaryKeyStatement = String.format(CREATE_TABLE_STATEMENT_KEY, column);
                continue;
            }

            if(StringUtil.isBlank(columnSize )) {
                if(isNotNull) {
                    sb.append(String.format(CREATE_TABLE_STATEMENT_NO_SIZE_NOT_NULL, column, columnType ) );
                } else {
                    sb.append(String.format(CREATE_TABLE_STATEMENT_NO_SIZE, column, columnType) );
                }
            }else {
                if(isNotNull) {
                    sb.append(String.format(CREATE_TABLE_STATEMENT_NOT_NULL, column, columnType, columnSize) );
                } else {
                    sb.append(String.format(CREATE_TABLE_STATEMENT_SIZE, column, columnType, columnSize) );
                }
            }
        }
        sb.append(primaryKeyStatement);
        sb.append(String.format(CREATE_TABLE_STATEMENT_END) );
        return sb.toString();
    }

    /**
     * 生成XXXmodel
     * @param codeContext
     * @param fileDirectory
     * @throws IOException
     */
    private static void createModel(CodeContext codeContext, String fileDirectory) throws IOException {
        String modelFilePath = fileDirectory + MODEL;
        FileUtil.createDir(modelFilePath);

        String modelCodeName = codeContext.getClassName()+".java";
        String modelCode = generateModelCode(codeContext);
        FileUtil.writeFile(modelCode, modelCodeName, modelFilePath );
    }
    private static String generateModelCode(CodeContext codeContext) {
        StringBuffer sb = new StringBuffer();
        sb.append("public class "+ codeContext.getClassName() + "{\n");
        List<CodeMetaItem> codeMetaItemList = codeContext.getCodeMetaItemList();

        StringBuffer sbToString = new StringBuffer();
        sbToString.append("    @Override\n");
        sbToString.append("    public String toString () {\n");
        sbToString.append("        String ret = \"[\"\n");
        for (CodeMetaItem codeMetaItem : codeMetaItemList) {
            String fieldType = codeMetaItem.getFieldType();
            String fieldName = codeMetaItem.getFiled();
            sb.append("    private "+fieldType+" "+fieldName+";\n");
            sbToString.append("            + \"" + fieldName + "\"+" + fieldName + "\n");
        }
        sbToString.append("            + \"]\";\n");
        sbToString.append("            return ret;\n");
        sbToString.append("    }\n");
        sb.append(sbToString+"}\n");
        return sb.toString();
    }

//    /**
//     * 生成service目录和代码
//     * @param codeContext
//     * @param filename
//     * @throws IOException
//     */
//    private static void createService(CodeContext codeContext, String filename) throws IOException {
//        String serviceInterfaceCode = generateServiceCode(codeContext);
//        FileUtil.createDir(filename);
//        String serviceInterfaceCodeName = codeContext.getClassName()+"Service.java";
//        FileUtil.writeFile(serviceInterfaceCode, serviceInterfaceCodeName, filename );
//    }
//    private static String generateServiceCode(CodeContext codeContext) {
//        StringBuffer sb = new StringBuffer();
//        sb.append("public interface I").append(codeContext.getClassName()).append("Service {\n");
//
//        sb.append("}\n");
//        return sb.toString();
//    }
//
//    /**
//     * 生成serviceImpl目录和代码
//     * @param codeContext
//     * @param filename
//     * @throws IOException
//     */
//    private static void createServiceImpl(CodeContext codeContext, String filename) throws IOException {
//        String serviceImplCode = generateServiceImplCode(codeContext);
//        FileUtil.createDir(filename);
//        String serviceImplCodeName = codeContext.getClassName()+"ServiceImpl.java";
//        FileUtil.writeFile(serviceImplCode, serviceImplCodeName, filename );
//    }
//    private static String generateServiceImplCode(CodeContext codeContext) {
//        StringBuffer sb = new StringBuffer();
//        sb.append("public class ").append(codeContext.getClassName()).append("ServiceImpl implements ");
//        sb.append("I").append(codeContext.getClassName()).append("Service {\n");
//        sb.append("}\n");
//        return sb.toString();
//    }
//
//    /**
//     * 生成ddlScript目录和代码
//     * @param sqlContext
//     * @param filename
//     * @throws IOException
//     */
//    private static void createDDLScript(SQLContext sqlContext, String filename) throws IOException {
//        String script = generateDDLTableScript(sqlContext);
//        FileUtil.createDir(filename);
//        String scriptFileName = "ddl_" + sqlContext.getTableName()+".sql";
//        FileUtil.writeFile(script, scriptFileName, filename);
//    }
//    private static String generateDDLTableScript(SQLContext sqlContext) {
//        return "";
//    }
}
