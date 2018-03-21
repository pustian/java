package tian.pusen.java.excel.common.enums;

/**
 * Created by tianpusen on 2017/3/23.
 */
public enum CheckExceptionEnum {
    CODE_EXCEL_TEMPLATE_UNMATCH(
        "CODE_EXCEL_TEMPLATE_UNMATCH","Code excel template unmatch","生成代码模板不匹配"),
    CODE_EXCEL_CLASS_NAME_NULL(
        "CODE_EXCEL_CLASS_NAME_NULL","Code excel class name is null","生成代码文件中类名为空"),
    CODE_EXCEL_TABLE_NAME_NULL(
        "CODE_EXCEL_TABLE_NAME_NULL","Code excel table name is null","生成代码文件中表名为空"),

    CODE_EXCEL_META_SIZE_UNMATCH(
        "CODE_EXCEL_META_SIZE_UNMATCH","Code excel meta size is unmatch","生成代码文件中元素个数不匹配"),

    CODE_EXCEL_META_NULL(
        "CODE_EXCEL_META_NULL","Code excel template meta must be filled","生成代码元素必须填充值"),

    SQL_EXCEL_TEMPLATE_UNMATCH(
        "SQL_EXCEL_TEMPLATE_UNMATCH","Sql excel template unmatch","生成SQL模板不匹配"),
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
