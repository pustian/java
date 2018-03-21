public class Linkman{
    private Integer id;
    private String name;
    private String phone;
    private String province;
    private String city;
    private String district;
    private String address;
    private Integer isDeleted;
    private Date createTime;
    private Date modifiedTime;
    @Override
    public String toString () {
        String ret = "["
            + "id"+id
            + "name"+name
            + "phone"+phone
            + "province"+province
            + "city"+city
            + "district"+district
            + "address"+address
            + "isDeleted"+isDeleted
            + "createTime"+createTime
            + "modifiedTime"+modifiedTime
            + "]";
            return ret;
    }
}
