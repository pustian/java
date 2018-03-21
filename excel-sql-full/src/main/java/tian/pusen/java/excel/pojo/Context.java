package tian.pusen.java.excel.pojo;

/**
 * Created by tianpusen on 2017/3/21.
 */
public class Context{
    protected String tableName;
    protected Integer metaSize;

    @Override
    public String toString(){
//        return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
        return "[tableName:" + tableName + " metaSize:"+metaSize+"]";
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
}
