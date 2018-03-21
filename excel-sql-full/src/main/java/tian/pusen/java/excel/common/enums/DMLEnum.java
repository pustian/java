package tian.pusen.java.excel.common.enums;

/**
 * Created by tianpusen on 2017/3/21.
 */
public enum DMLEnum {
    INSERT("INSERT", "insert", "插入"),
    UPDATE("UPDATE", "update", "更新"),
//    SELECT("SELECT", "select", "查询"),
    ;
    private String code;
    private String description;
    private String description_zh;
    private DMLEnum(String code, String description, String description_zh) {
        this.code = code;
        this.description = description;
        this.description_zh = description_zh;
    }
    public static DMLEnum getByCode(String code) {
        DMLEnum ret = null;
        for(DMLEnum dmlEnum: values()) {
            if(dmlEnum.code.equals(code) ) {
                ret = dmlEnum;
                break;
            }
        }
        return ret;
    }
}
