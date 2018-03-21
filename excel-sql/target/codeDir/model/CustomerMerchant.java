public class CustomerMerchant{
    private Integer id;
    private String web;
    private String ip;
    private Date createTime;
    private Date modifiedTime;
    @Override
    public String toString () {
        String ret = "["
            + "id"+id
            + "web"+web
            + "ip"+ip
            + "createTime"+createTime
            + "modifiedTime"+modifiedTime
            + "]";
            return ret;
    }
}
