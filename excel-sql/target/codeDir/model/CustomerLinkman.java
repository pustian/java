public class CustomerLinkman{
    private Integer id;
    private String customerId;
    private Integer linkmanId;
    private String relation;
    private Integer isDeleted;
    private Date createTime;
    private Date modifiedTime;
    @Override
    public String toString () {
        String ret = "["
            + "id"+id
            + "customerId"+customerId
            + "linkmanId"+linkmanId
            + "relation"+relation
            + "isDeleted"+isDeleted
            + "createTime"+createTime
            + "modifiedTime"+modifiedTime
            + "]";
            return ret;
    }
}
