package tian.pusen.java.excel.service.parse.impl;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tian.pusen.java.excel.entity.CodeContext;
import tian.pusen.java.excel.service.parse.AbstractParser;
import tian.pusen.java.excel.service.util.ExcelParserUtil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * Created by tianpusen on 2017/3/20.
 */
public final class ExcelParser extends AbstractParser {
    private static final Logger logger = LoggerFactory.getLogger(ExcelParser.class);

    @Override
    public List<CodeContext> parseExcelDetail(String filename) {
        logger.info("Parse the file:"+ filename );
        List<CodeContext> contextList = null;
        try {
             FileInputStream fileInputStream;
             fileInputStream = new FileInputStream(filename);
             contextList = ExcelParserUtil.parseCodeExcel(fileInputStream);
         } catch (FileNotFoundException e) {
             logger.error("parse the filename:"+filename, e);
             throw new RuntimeException("parse the filename:"+filename, e);
         } catch (InvalidFormatException e) {
             logger.error("filename:"+filename+e.getMessage(), e);
             throw new RuntimeException("filename:"+filename+e.getMessage(), e);
         } catch (IOException e) {
             logger.error("filename:"+filename+e.getMessage(), e);
            throw new RuntimeException("filename:"+filename+e.getMessage(), e);
         }
         return contextList;
     }
}
