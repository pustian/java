package tian.pusen.java.excel.pojo.code;

import tian.pusen.java.excel.pojo.Context;

import java.util.List;

/**
 * Created by tianpusen on 2017/3/21.
 */
public class CodeContext extends Context {
    private String className;
    private List<CodeMetaItem> codeMetaItemList;

    @Override
    public String toString() {
//        return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
        String string = super.toString();
        StringBuffer sb = new StringBuffer(string);
        sb.append("[className:").append(className).append("\n").append("codeMetaItemList:");
        if(codeMetaItemList != null) {
            for (CodeMetaItem codeMetaItem : codeMetaItemList) {
                sb.append(codeMetaItem.toString()+"\n");
            }
        } else {
            sb.append("null");
        }
        sb.append("]");
        return   sb.toString();
    }

    public List<CodeMetaItem> getCodeMetaItemList() {
        return codeMetaItemList;
    }

    public void setCodeMetaItemList(List<CodeMetaItem> codeMetaItemList) {
        this.codeMetaItemList = codeMetaItemList;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
