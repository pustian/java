public class Operator{
    private Integer id;
    private String customerId;
    private String operatorName;
    private Integer errorLoginTime;
    private String businessType;
    private String customerRole;
    private String operatorStatus;
    private String operatorType;
    private Date lockTime;
    private Integer isDeleted;
    private Date createTime;
    private Date modifiedTime;
    @Override
    public String toString () {
        String ret = "["
            + "id"+id
            + "customerId"+customerId
            + "operatorName"+operatorName
            + "errorLoginTime"+errorLoginTime
            + "businessType"+businessType
            + "customerRole"+customerRole
            + "operatorStatus"+operatorStatus
            + "operatorType"+operatorType
            + "lockTime"+lockTime
            + "isDeleted"+isDeleted
            + "createTime"+createTime
            + "modifiedTime"+modifiedTime
            + "]";
            return ret;
    }
}
