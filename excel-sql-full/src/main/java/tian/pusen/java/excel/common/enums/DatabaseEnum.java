package tian.pusen.java.excel.common.enums;

/**
 * Created by tianpusen on 2017/3/21.
 */
public enum DatabaseEnum {
    MYSQL("MYSQL", "MySQL", "MySQL"),
    ORACLE("ORACLE", "Oracle", "Oracle"),
    DB2("DB2", "DB2", "DB2"),
    ;
    private String code;
    private String description;
    private String description_zh;
    private DatabaseEnum(String code, String description, String description_zh) {
        this.code = code;
        this.description = description;
        this.description_zh = description_zh;
    }
}
