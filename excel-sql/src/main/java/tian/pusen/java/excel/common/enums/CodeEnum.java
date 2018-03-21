package tian.pusen.java.excel.common.enums;

/**
 * Created by tianpusen on 2017/4/13.
 */
public enum CodeEnum {
    Mybatis("mybatis", "mybatis", "mybatis"),
    Ibatis("ibtais", "batis", "ibatis"),
    JdbcTemplate("JdbcTemplate", "JdbcTemplate", "JdbcTemplate"),

    ;
    private String code;
    private String description;
    private String description_zh;
    private CodeEnum(String code, String description, String description_zh) {
        this.code = code;
        this.description = description;
        this.description_zh = description_zh;
    }

    public static CodeEnum getByCode(String code){
        for (CodeEnum codeEnum: values()) {
            if(codeEnum.getCode().equals(code))
                return codeEnum;
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription_zh() {
        return description_zh;
    }

    public void setDescription_zh(String description_zh) {
        this.description_zh = description_zh;
    }
}
