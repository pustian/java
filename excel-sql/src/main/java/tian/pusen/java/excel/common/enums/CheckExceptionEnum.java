package tian.pusen.java.excel.common.enums;

/**
 * Created by tianpusen on 2017/3/23.
 */
public enum CheckExceptionEnum {
    CODE_EXCEL_TEMPLATE_UNMATCH(
        "CODE_EXCEL_TEMPLATE_UNMATCH","Excel template unmatch","Excel模板不匹配"),
    CODE_EXCEL_CLASS_NAME_NULL(
        "CODE_EXCEL_CLASS_NAME_NULL","Excel class name is null","Excel模板中类名为空"),
    CODE_EXCEL_TABLE_NAME_NULL(
        "CODE_EXCEL_TABLE_NAME_NULL","Excel table name is null","Excel模板中表名为空"),

    CODE_EXCEL_META_SIZE_UNMATCH(
        "CODE_EXCEL_META_SIZE_UNMATCH","Excel template meta size is unmatch","Excel模板中元素个数实际个数与填写个数不匹配"),

    CODE_EXCEL_META_NULL(
        "CODE_EXCEL_META_NULL","Excel template meta must be filled","Excel模板中元素必须填充值"),

    ;
    private String code;
    private String description;
    private String description_zh;
    private CheckExceptionEnum(String code, String description, String description_zh) {
        this.code = code;
        this.description = description;
        this.description_zh = description_zh;
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
