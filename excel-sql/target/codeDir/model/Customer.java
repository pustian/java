public class Customer{
    private Integer id;
    private String customerId;
    private String phoneNo;
    private String credentialsType;
    private String credentialsNo;
    private String customerName;
    private String customerType;
    private String customerStatus;
    private String customerAccessRightsState;
    private Integer customerAuthenticationState;
    private Integer isDeleted;
    private String memo;
    private Date createTime;
    private Date modifiedTime;
    @Override
    public String toString () {
        String ret = "["
            + "id"+id
            + "customerId"+customerId
            + "phoneNo"+phoneNo
            + "credentialsType"+credentialsType
            + "credentialsNo"+credentialsNo
            + "customerName"+customerName
            + "customerType"+customerType
            + "customerStatus"+customerStatus
            + "customerAccessRightsState"+customerAccessRightsState
            + "customerAuthenticationState"+customerAuthenticationState
            + "isDeleted"+isDeleted
            + "memo"+memo
            + "createTime"+createTime
            + "modifiedTime"+modifiedTime
            + "]";
            return ret;
    }
}
