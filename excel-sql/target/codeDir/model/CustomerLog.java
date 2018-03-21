public class CustomerLog{
    private Integer id;
    private String customerId;
    private String businessType;
    private String businessRole;
    private Integer userId;
    private String updateContent;
    private Date createTime;
    @Override
    public String toString () {
        String ret = "["
            + "id"+id
            + "customerId"+customerId
            + "businessType"+businessType
            + "businessRole"+businessRole
            + "userId"+userId
            + "updateContent"+updateContent
            + "createTime"+createTime
            + "]";
            return ret;
    }
}
