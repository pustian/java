package tian.pusen.java.excel.entity;

import java.io.Serializable;

/**
 * Created by tianpusen on 2017/3/21.
 */
public class CodeMetaItem implements Serializable{
    private String filed;
    private String fieldType; // 需要添加对fieldType的限制 String， BigDecimal，Date， Integer/int 这几类
    private String column;
    private String columnType;
    private String columnByteSize; // decimal中18,4 这种类型
    private Boolean isNotNull;
    private Boolean isKey;

    @Override
    public String toString() {
        return "CodeMetaItem{" +
            "filed='" + filed + '\'' +
            ", fieldType='" + fieldType + '\'' +
            ", column='" + column + '\'' +
            ", columnType='" + columnType + '\'' +
            ", columnByteSize=" + columnByteSize +
            ", isNotNull=" + isNotNull +
            ", isKey=" + isKey +
            '}';
    }

    public String getFiled() {
        return filed;
    }

    public void setFiled(String filed) {
        this.filed = filed;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
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

    public String getColumnByteSize() {
        return columnByteSize;
    }

    public void setColumnByteSize(String columnByteSize) {
        this.columnByteSize = columnByteSize;
    }

    public Boolean getNotNull() {
        return isNotNull;
    }

    public void setNotNull(Boolean notNull) {
        isNotNull = notNull;
    }

    public Boolean getKey() {
        return isKey;
    }

    public void setKey(Boolean key) {
        isKey = key;
    }
}