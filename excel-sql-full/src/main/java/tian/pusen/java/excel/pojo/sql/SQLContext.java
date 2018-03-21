package tian.pusen.java.excel.pojo.sql;

import tian.pusen.java.excel.common.enums.DMLEnum;
import tian.pusen.java.excel.pojo.Context;

import java.util.List;

/**
 * Created by tianpusen on 2017/3/21.
 */
public class SQLContext extends Context {
    // String, BigDecimal, Date, DateTime
    private List<? extends Object[]> recordList;
    private Integer size;
    private DMLEnum dmlEnum;
    @Override
    public String toString(){
        String string = super.toString();
        return "[tableName:" + tableName + " metaSize:"+metaSize+"]";
    }

}
