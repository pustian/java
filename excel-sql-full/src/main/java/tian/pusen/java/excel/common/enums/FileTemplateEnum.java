package tian.pusen.java.excel.common.enums;

/**
 * Created by tianpusen on 2017/3/24.
 */
public enum FileTemplateEnum {
    CodeExcelTemplate("CodeExcelTemplate","The excel template of code","生成开发文件"),
    SQLExcelTemplate("SQLExcelTemplate","The excel template of SQL","生成脚本"),
    ;
    private String code;
    private String description;
    private String description_zh;
    private FileTemplateEnum(String code, String description, String description_zh) {
        this.code = code;
        this.description = description;
        this.description_zh = description_zh;
    }
    public static FileTemplateEnum getByCode(String code) {
        FileTemplateEnum ret = null;
        for(FileTemplateEnum fileTemplateEnum: values()) {
            if(fileTemplateEnum.code.equals(code) ) {
                ret = fileTemplateEnum;
                break;
            }
        }
        return ret;
    }
}
