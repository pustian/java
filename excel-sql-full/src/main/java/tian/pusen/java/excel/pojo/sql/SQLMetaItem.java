package tian.pusen.java.excel.pojo.sql;

/**
 * Created by tianpusen on 2017/3/22.
 */
public class SQLMetaItem {
    private String column;
    private String columnType;
    private Boolean isUsage;
    private String value;

    @Override
    public String toString(){
        return "[column:" + column + " columnType:" + columnType
            + " isUsage:" +  isUsage + " value:" + value
            + "]";
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public Boolean getUsage() {
        return isUsage;
    }

    public void setUsage(Boolean usage) {
        isUsage = usage;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


}
