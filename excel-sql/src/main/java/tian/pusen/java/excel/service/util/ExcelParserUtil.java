package tian.pusen.java.excel.service.util;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tian.pusen.java.excel.common.enums.CheckExceptionEnum;
import tian.pusen.java.excel.common.exception.CheckException;
import tian.pusen.java.excel.entity.CodeContext;
import tian.pusen.java.excel.entity.CodeMetaItem;
import tian.pusen.java.excel.util.StringUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Created by tianpusen on 2017/3/21.
 */
public final class ExcelParserUtil {
    private static final Logger logger = LoggerFactory.getLogger(ExcelParserUtil.class);
    // 首行基本信息
    private static final int BASIC_INFO_ROW = 0;
    private static final int BASIC_INFO_TABLE_NAME_COL = 1;
    private static final int BASIC_INFO_CLASS_NAME_COL= 3;
    private static final int BASIC_INFO_META_SIZE_COL = 5;
    //    private static final int BASIC_INFO_DATABASE_TYPE_COL = 7;
    // 从第2行开始具体数据库表和对象的信息
    private static final int META_ITEM_START_ROW = 2;
    //    private static final int META_ITEM_CONDITION_COL = 7;
//    private static final int META_ITEM_FIELD_NAME_COL = 0;
//    private static final int META_ITEM_FIELD_TYPE_COL = 1;
    // 具体每列存储内容
    private static final int META_ITEM_COLUMN_NAME_COL = 0;
    private static final int META_ITEM_COLUMN_TYPE_COL = 1;
    private static final int META_ITEM_COLUMN_SIZE_COL = 2;
    private static final int META_ITEM_KEY_COL = 3;
    private static final int META_ITEM_NOT_NULL_COL = 4;

    /**
     * 多个sheet页都是符合模板格式的数据库，需要做转换
     * @param inputStream
     * @return 多个需要转换
     * @throws IOException
     * @throws InvalidFormatException
     */
    public static List<CodeContext> parseCodeExcel(InputStream inputStream) throws IOException, InvalidFormatException {
        Workbook workbook = WorkbookFactory.create(inputStream);
        int i = workbook.getNumberOfSheets();
        List<CodeContext> contextList = new ArrayList<CodeContext>(i);
        // 可以对多张sheet页处理
        Iterator<Sheet> sheetIter = workbook.sheetIterator();
        while(sheetIter.hasNext()) {
            Sheet sheet = sheetIter.next();
            logger.info("Parse sheet:{}",sheet.getSheetName());
            // 检查sheet页格式是否按模板要求填写的
            checkCodeSheetTemplate(sheet);
            CodeContext context = parseCodeSheet(sheet);
            logger.info("The context:{}", context);
            if(context != null) {
                contextList.add(context);
            }
        }
        if(contextList.size() <= 0) {
            throw new RuntimeException("删除不施用的sheet页，并按照模板正确输入信息");
        }
        return  contextList;
    }

    /**
     * 检查生成代码类型模板是否符合规范，否则会抛出检查异常
     * 1，命名格式（目前只检查了为空）
     * 2,元数据实际和预填写个数
     * @param sheet
     */
    public static void checkCodeSheetTemplate(Sheet sheet) {
        CodeContext context = new CodeContext();
        // 首行基本信息
        Row basicInfoRow = sheet.getRow(BASIC_INFO_ROW);
        Cell tableNameCell = basicInfoRow.getCell(BASIC_INFO_TABLE_NAME_COL);
        Cell classNameCell = basicInfoRow.getCell(BASIC_INFO_CLASS_NAME_COL);
        if(tableNameCell == null || StringUtil.isBlank(tableNameCell.getStringCellValue()) ) {
            throw new CheckException(CheckExceptionEnum.CODE_EXCEL_TABLE_NAME_NULL);
        }
        if(classNameCell == null || StringUtil.isBlank(classNameCell.getStringCellValue()) ) {
            throw new CheckException(CheckExceptionEnum.CODE_EXCEL_CLASS_NAME_NULL);
        }

        // 解析元数据行 从2行开始 base0
        int metaStartRow = META_ITEM_START_ROW; // 包含改行
        int endRow = sheet.getLastRowNum();
        int actualSize = 0;
        List<CodeMetaItem> codeMetaItemList = new ArrayList<CodeMetaItem>(endRow);
        for(int i=metaStartRow ; i <= endRow ; i++) {
            logger.debug("Check the value at row num {}, sheet:{} ", i, sheet.getSheetName());
            Row row = sheet.getRow(i);
            if(row != null ) {
                checkCodeRowTemplate(row);
                actualSize++;
            } else {
                logger.warn("The value is null at row num {}, sheet:{} ", i, sheet.getSheetName());
            }
        }
        Cell metaSizeCell = basicInfoRow.getCell(BASIC_INFO_META_SIZE_COL);

        if(metaSizeCell == null || actualSize != Integer.parseInt(metaSizeCell.getStringCellValue() ) ) {
            throw  new CheckException(CheckExceptionEnum.CODE_EXCEL_META_SIZE_UNMATCH);
        }
    }

    /**
     * 检查元素信息，目前检查比较简单后续需要增强检查
     * @param row
     */
    public static void checkCodeRowTemplate(Row row) {
        Cell columnCell = row.getCell(META_ITEM_COLUMN_NAME_COL);
        Cell columnTypeCell = row.getCell(META_ITEM_COLUMN_TYPE_COL);

        if( columnCell == null || StringUtil.isBlank(columnCell.getStringCellValue())
            || columnTypeCell == null || StringUtil.isBlank(columnTypeCell.getStringCellValue())) {
                throw new CheckException(CheckExceptionEnum.CODE_EXCEL_META_NULL);
        }
    }

    /**
     * 解析生成代码的模板
     * @param sheet
     * @return
     */
    public static CodeContext parseCodeSheet(Sheet sheet) {
        CodeContext context = new CodeContext();
        // 首行基本信息
        Row basicInfoRow = sheet.getRow(BASIC_INFO_ROW);
        Cell tableNameCell = basicInfoRow.getCell(BASIC_INFO_TABLE_NAME_COL);
        Cell classNameCell = basicInfoRow.getCell(BASIC_INFO_CLASS_NAME_COL);
        Cell metaSizeCell = basicInfoRow.getCell(BASIC_INFO_META_SIZE_COL);
        context.setClassName(classNameCell.getStringCellValue());
        context.setTableName(tableNameCell.getStringCellValue());
        int metaSize = Integer.parseInt(metaSizeCell.getStringCellValue() );
        context.setMetaSize(metaSize );
        List<CodeMetaItem> codeMetaItemList = new ArrayList<CodeMetaItem>();
        for (int i = META_ITEM_START_ROW, endRow = metaSize + META_ITEM_START_ROW; i< endRow; i++) {
            logger.debug("Parse the value at row num {}, sheet:{} ", i, sheet.getSheetName());
            CodeMetaItem codeMetaItem = parseMateRow(sheet.getRow(i) );
            codeMetaItemList.add(codeMetaItem);
        }
        context.setCodeMetaItemList(codeMetaItemList);
        return context;
    }

    /**
     * 解析每一个生成代码模板的具体属性的配置
     * @param row
     * @return
     */
    private static CodeMetaItem parseMateRow(Row row) {
        Cell columnCell = row.getCell(META_ITEM_COLUMN_NAME_COL);
        Cell columnTypeCell = row.getCell(META_ITEM_COLUMN_TYPE_COL);
        Cell columnSizeCell = row.getCell(META_ITEM_COLUMN_SIZE_COL);
        Cell isKeyCell = row.getCell(META_ITEM_KEY_COL);
        Cell isNotNullCell = row.getCell(META_ITEM_NOT_NULL_COL);

        String column = columnCell.getStringCellValue();
        String columnType = columnTypeCell.getStringCellValue();
        String columnByteSize = columnSizeCell.getStringCellValue();
//        Integer columnByteSize = null;
//        if(columnSizeCell != null && columnType.toLowerCase().contains("char")) {
//            columnByteSize = Integer.parseInt(columnSizeCell.getStringCellValue());
//        }
        boolean key = isKeyCell != null && StringUtil.isNotBlank(isKeyCell.getStringCellValue());
        boolean notNull = isNotNullCell != null && StringUtil.isNotBlank(isNotNullCell.getStringCellValue());

        CodeMetaItem codeMetaItem = new CodeMetaItem();
        codeMetaItem.setColumn(column);
        codeMetaItem.setColumnType(columnType);
        codeMetaItem.setColumnByteSize(columnByteSize);
        codeMetaItem.setNotNull(notNull);
        codeMetaItem.setKey(key);
        // 生成filed name和type
        String field = column2Filed(column);
        String fieldType = columnType2fileType(columnType);
        codeMetaItem.setFiled(field);
        codeMetaItem.setFieldType(fieldType);

        return codeMetaItem ;
    }

    /**
     * 大小写转换转为标准驼峰命名方案
     * @param column
     * @return
     */
    public static String column2Filed(String column) {
        StringTokenizer tokenizer = new StringTokenizer(column, "_");
        StringBuffer sb = new StringBuffer(tokenizer.nextToken());
        while( tokenizer.hasMoreElements() ){
            String string = tokenizer.nextToken();
            sb.append(Character.toUpperCase(string.charAt(0)));
            sb.append(string.substring(1) );
        }
        return sb.toString();
    }

    /**
     * 直接转换
     * @param columnType
     * @return
     */
    public static String columnType2fileType(String columnType) {
        Map<String, String> mysqlMap = new HashMap<String, String>();

        mysqlMap.put("TINYINT", "Integer");
        mysqlMap.put("SMALLINT", "Integer");
        mysqlMap.put("MEDIUMINT", "Integer");
        mysqlMap.put("INT", "Integer");
        mysqlMap.put("INTEGER", "Integer");

        mysqlMap.put("BIGINT", "Long");
        mysqlMap.put("FLOAT", "Float");
        mysqlMap.put("DOUBLE", "Double");
        mysqlMap.put("DECIMAL", "BigDecimal");

        mysqlMap.put("CHAR", "String");
        mysqlMap.put("VARCHAR", "String");
        mysqlMap.put("TINYTEXT", "String");
        mysqlMap.put("TEXT", "String");
        mysqlMap.put("MEDIUMTEXT", "String");
        mysqlMap.put("LONGTEXT", "String");

        mysqlMap.put("TINYBLOB", "byte[]" );
        mysqlMap.put("BLOB", "byte[]" );
        mysqlMap.put("MEDIUMBLOB", "byte[]" );
        mysqlMap.put("LOGNGBLOB", "byte[]" );

        mysqlMap.put("VARBINARY", "String" );
        mysqlMap.put("BINARY", "String" );

        mysqlMap.put("DATE" , "Date");
        mysqlMap.put("TIME" , "Date");
        mysqlMap.put("YEAR" , "Date");
        mysqlMap.put("DATETIME" , "Date");
        mysqlMap.put("TIMESTAMP", "Timestamp");
        return mysqlMap.get(columnType.toUpperCase());
    }
}
