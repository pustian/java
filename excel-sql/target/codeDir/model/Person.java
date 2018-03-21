public class Person{
    private Integer id;
    private String customerId;
    private String phoneNo;
    private String wechatNo;
    private String email;
    private String birthday;
    private String sex;
    private String maritalStatus;
    private String degree;
    private String birthProvince;
    private String birthCity;
    private String birthDistrict;
    private String birthStreet;
    private String residenceProvince;
    private String residenceCity;
    private String residenceDistrict;
    private String residenceStreet;
    private String residenceType;
    private Integer residenceYears;
    private Integer workYears;
    private String securityNo;
    private Integer monthlyIncoming;
    private String idcardImageUrl;
    private String idcardImage1;
    private String idcardImage2;
    private String employeeCardImage;
    private String businessCardImage;
    private String companyName;
    private String companyProvince;
    private String companyCity;
    private String companyDistrict;
    private String companyStreet;
    private String companyCharacter;
    private String companyIndustry;
    private String companyPhone;
    private String departmentJob;
    private String headship;
    private Integer workHours;
    private Date createTime;
    private Date modifiedTime;
    @Override
    public String toString () {
        String ret = "["
            + "id"+id
            + "customerId"+customerId
            + "phoneNo"+phoneNo
            + "wechatNo"+wechatNo
            + "email"+email
            + "birthday"+birthday
            + "sex"+sex
            + "maritalStatus"+maritalStatus
            + "degree"+degree
            + "birthProvince"+birthProvince
            + "birthCity"+birthCity
            + "birthDistrict"+birthDistrict
            + "birthStreet"+birthStreet
            + "residenceProvince"+residenceProvince
            + "residenceCity"+residenceCity
            + "residenceDistrict"+residenceDistrict
            + "residenceStreet"+residenceStreet
            + "residenceType"+residenceType
            + "residenceYears"+residenceYears
            + "workYears"+workYears
            + "securityNo"+securityNo
            + "monthlyIncoming"+monthlyIncoming
            + "idcardImageUrl"+idcardImageUrl
            + "idcardImage1"+idcardImage1
            + "idcardImage2"+idcardImage2
            + "employeeCardImage"+employeeCardImage
            + "businessCardImage"+businessCardImage
            + "companyName"+companyName
            + "companyProvince"+companyProvince
            + "companyCity"+companyCity
            + "companyDistrict"+companyDistrict
            + "companyStreet"+companyStreet
            + "companyCharacter"+companyCharacter
            + "companyIndustry"+companyIndustry
            + "companyPhone"+companyPhone
            + "departmentJob"+departmentJob
            + "headship"+headship
            + "workHours"+workHours
            + "createTime"+createTime
            + "modifiedTime"+modifiedTime
            + "]";
            return ret;
    }
}
