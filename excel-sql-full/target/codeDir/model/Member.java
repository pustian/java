public class Member{
    private String memberNo;
    private String loginName;
    private String phoneNo;
    private String memberName;
    private String status;
    private Date createTime;
    private Date midifiedTime;
    @Override
    public String toString () {
        String ret = "["
            + "memberNo"+memberNo
            + "loginName"+loginName
            + "phoneNo"+phoneNo
            + "memberName"+memberName
            + "status"+status
            + "createTime"+createTime
            + "midifiedTime"+midifiedTime
            + "]";
            return ret;
    }
}
