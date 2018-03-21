import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tian.pusen.java.excel.pojo.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by tianpusen on 2017/3/21.
 */
public class ExcelDemo {
    private static final Logger logger = LoggerFactory.getLogger(ExcelDemo.class);

    public static void main(String[] args) {
        Workbook workbook = null;
        String excelFileName = "C:/workspace/idea/mybatis-plus/excel-sql/Code模板.xlsx";
        try {
            FileInputStream fileInputStream = new FileInputStream(excelFileName);
            workbook = WorkbookFactory.create(fileInputStream);
        } catch (FileNotFoundException e) {
            logger.error("Read the template error!"+e.getMessage(), e);
        } catch (InvalidFormatException e) {
            logger.error("Read the template error!"+e.getMessage(), e);
        } catch (IOException e) {
            logger.error("Read the template error!"+e.getMessage(), e);
        }

        int i = workbook.getNumberOfSheets();
        List<Context> contextList = new ArrayList<Context>(i);

        Iterator<Sheet> sheetIter = workbook.sheetIterator();
        while(sheetIter.hasNext()) {
            Sheet sheet = sheetIter.next();
            Context context = parseSheet(sheet);
            logger.info("parse sheet {}",sheet.getSheetName()+" context:"+context);
            if(context != null) {
                contextList.add(context);
            }
        }
    }

    public static Context parseSheet(Sheet sheet) {
        Context context = null;
        Row basicInfoRow = sheet.getRow(0);
        Cell cell0 = basicInfoRow.getCell(0);
        Cell cell1 = basicInfoRow.getCell(1);
        Cell cell2 = basicInfoRow.getCell(2);
        Cell cell3 = basicInfoRow.getCell(3);
        Cell cell4 = basicInfoRow.getCell(4);
        Cell cell5 = basicInfoRow.getCell(5);
        logger.debug("row cell [00] {} ,[01] {} [02] {}, [03] {}, [04]{}, [05] {}",
            cell0.getStringCellValue(), cell1.getStringCellValue(),
            cell2.getStringCellValue(), cell3.getStringCellValue(),
            cell4.getStringCellValue(), cell5.getStringCellValue());


        return context;
    }
}
