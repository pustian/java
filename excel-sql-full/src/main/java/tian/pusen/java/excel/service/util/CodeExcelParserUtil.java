package tian.pusen.java.excel.service.util;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tian.pusen.java.excel.common.enums.CheckExceptionEnum;
import tian.pusen.java.excel.common.exception.CheckException;
import tian.pusen.java.excel.pojo.code.CodeContext;
import tian.pusen.java.excel.pojo.code.CodeMetaItem;
import tian.pusen.java.excel.util.StringUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by tianpusen on 2017/3/21.
 */
public final class CodeExcelParserUtil {
    private static final Logger logger = LoggerFactory.getLogger(CodeExcelParserUtil.class);
    // 首行基本信息
    private static final int BASIC_INFO_ROW = 0;
    private static final int BASIC_INFO_CLASS_NAME_COL= 1;
    private static final int BASIC_INFO_TABLE_NAME_COL = 3;
    private static final int BASIC_INFO_META_SIZE_COL = 5;
//    private static final int BASIC_INFO_DATABASE_TYPE_COL = 7;
    // 从第2行开始具体数据库表和对象的信息
    private static final int META_ITEM_START_ROW = 2;
    private static final int META_ITEM_FIELD_NAME_COL = 0;
    private static final int META_ITEM_FIELD_TYPE_COL = 1;
    private static final int META_ITEM_COLUMN_NAME_COL = 2;
    private static final int META_ITEM_COLUMN_TYPE_COL = 3;
    private static final int META_ITEM_COLUMN_SIZE_COL = 4;
    private static final int META_ITEM_KEY_COL = 5;
    private static final int META_ITEM_NOT_NULL_COL = 6;
    private static final int META_ITEM_CONDITION_COL = 7;

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
        Cell classNameCell = basicInfoRow.getCell(BASIC_INFO_CLASS_NAME_COL);
        Cell tableNameCell = basicInfoRow.getCell(BASIC_INFO_TABLE_NAME_COL);
        if(classNameCell == null || StringUtil.isBlank(classNameCell.getStringCellValue()) ) {
            throw new CheckException(CheckExceptionEnum.CODE_EXCEL_CLASS_NAME_NULL);
        }
        if(tableNameCell == null || StringUtil.isBlank(tableNameCell.getStringCellValue()) ) {
            throw new CheckException(CheckExceptionEnum.CODE_EXCEL_TABLE_NAME_NULL);
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
        Cell fieldCell = row.getCell(META_ITEM_FIELD_NAME_COL);
        Cell fieldTypeCell = row.getCell(META_ITEM_FIELD_TYPE_COL);
        Cell columnCell = row.getCell(META_ITEM_COLUMN_NAME_COL);
        Cell columnTypeCell = row.getCell(META_ITEM_COLUMN_TYPE_COL);

        if(fieldCell == null || StringUtil.isBlank(fieldCell.getStringCellValue())
            || fieldTypeCell == null || StringUtil.isBlank(fieldTypeCell.getStringCellValue())
            || columnCell == null || StringUtil.isBlank(columnCell.getStringCellValue())
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
        Cell classNameCell = basicInfoRow.getCell(BASIC_INFO_CLASS_NAME_COL);
        Cell tableNameCell = basicInfoRow.getCell(BASIC_INFO_TABLE_NAME_COL);
        Cell metaSizeCell = basicInfoRow.getCell(BASIC_INFO_META_SIZE_COL);
        context.setClassName(classNameCell.getStringCellValue());
        context.setTableName(tableNameCell.getStringCellValue());
        int metaSize = Integer.parseInt(metaSizeCell.getStringCellValue() );
        context.setMetaSize(metaSize );
        List<CodeMetaItem> codeMetaItemList = new ArrayList<CodeMetaItem>();
        for (int i = META_ITEM_START_ROW, endRow = metaSize + META_ITEM_START_ROW; i< endRow; i++) {
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
        Cell fieldCell = row.getCell(META_ITEM_FIELD_NAME_COL);
        Cell fieldTypeCell = row.getCell(META_ITEM_FIELD_TYPE_COL);
        Cell columnCell = row.getCell(META_ITEM_COLUMN_NAME_COL);
        Cell columnTypeCell = row.getCell(META_ITEM_COLUMN_TYPE_COL);
        Cell columnSizeCell = row.getCell(META_ITEM_COLUMN_SIZE_COL);
        Cell isKeyCell = row.getCell(META_ITEM_KEY_COL);
        Cell isNotNullCell = row.getCell(META_ITEM_NOT_NULL_COL);
        Cell isConditionCell = row.getCell(META_ITEM_CONDITION_COL);

        String field = fieldCell.getStringCellValue();
        String fieldType = fieldTypeCell.getStringCellValue();
        String column = columnCell.getStringCellValue();
        String columnType = columnTypeCell.getStringCellValue();
        Integer columnByteSize = null;
        if(columnSizeCell != null && columnType.toLowerCase().contains("char")) {
            columnByteSize = Integer.parseInt(columnSizeCell.getStringCellValue());
        }
        boolean key = isKeyCell != null && StringUtil.isNotBlank(isKeyCell.getStringCellValue());
        boolean notNull = isNotNullCell != null && StringUtil.isNotBlank(isNotNullCell.getStringCellValue());
        boolean condition = isConditionCell != null && StringUtil.isNotBlank(isConditionCell.getStringCellValue());

        CodeMetaItem codeMetaItem = new CodeMetaItem();
        codeMetaItem.setFiled(field);
        codeMetaItem.setFieldType(fieldType);
        codeMetaItem.setColumn(column);
        codeMetaItem.setColumnType(columnType);
        codeMetaItem.setColumnByteSize(columnByteSize);
        codeMetaItem.setNotNull(notNull);
        codeMetaItem.setKey(key);
        codeMetaItem.setCondition(condition);
        return codeMetaItem ;
    }
}
