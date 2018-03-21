public class User{
    private Integer id;
    private String customerId;
    private String businessType;
    private String businessRole;
    private String phoneNo;
    private String userName;
    private Date createTime;
    private Date modifiedTime;
    @Override
    public String toString () {
        String ret = "["
            + "id"+id
            + "customerId"+customerId
            + "businessType"+businessType
            + "businessRole"+businessRole
            + "phoneNo"+phoneNo
            + "userName"+userName
            + "createTime"+createTime
            + "modifiedTime"+modifiedTime
            + "]";
            return ret;
    }
}
