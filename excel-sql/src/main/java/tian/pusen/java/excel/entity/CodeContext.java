package tian.pusen.java.excel.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tianpusen on 2017/3/21.
 */
public class CodeContext  implements Serializable {
    protected String tableName;
    protected Integer metaSize;
    private String className;
    private List<CodeMetaItem> codeMetaItemList;

//    @Override
//    public String toString() {
//        return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
//    }
    @Override
    public String toString() {
        return "CodeContext{" +
            "tableName='" + tableName + '\'' +
            ", metaSize=" + metaSize +
            ", className='" + className + '\'' +
            ", codeMetaItemList=" + codeMetaItemList +
            '}';
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Integer getMetaSize() {
        return metaSize;
    }

    public void setMetaSize(Integer metaSize) {
        this.metaSize = metaSize;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<CodeMetaItem> getCodeMetaItemList() {
        return codeMetaItemList;
    }

    public void setCodeMetaItemList(List<CodeMetaItem> codeMetaItemList) {
        this.codeMetaItemList = codeMetaItemList;
    }
}
