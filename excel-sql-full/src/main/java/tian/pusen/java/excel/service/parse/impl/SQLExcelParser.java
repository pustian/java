package tian.pusen.java.excel.service.parse.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tian.pusen.java.excel.pojo.Context;
import tian.pusen.java.excel.service.parse.AbstractParser;

import java.util.List;

/**
 * Created by tianpusen on 2017/3/24.
 */
public final class SQLExcelParser extends AbstractParser{
    private static final Logger logger = LoggerFactory.getLogger(CodeExcelParser.class);

    @Override
    public List<? extends Context> parseExcelDetail(String filename) {
        logger.info("Parse the file " + filename);
        throw new RuntimeException("No implements");
    }
}
