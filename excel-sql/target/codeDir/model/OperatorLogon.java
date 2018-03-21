public class OperatorLogon{
    private Integer id;
    private String operatorId;
    private String customerId;
    private String loginNo;
    private String loginType;
    private String passwdLoginFlag;
    private String device;
    private String version;
    private String businessType;
    private String customerRole;
    private String loginStatus;
    private String token;
    private Date createTime;
    private Date modifiedTime;
    @Override
    public String toString () {
        String ret = "["
            + "id"+id
            + "operatorId"+operatorId
            + "customerId"+customerId
            + "loginNo"+loginNo
            + "loginType"+loginType
            + "passwdLoginFlag"+passwdLoginFlag
            + "device"+device
            + "version"+version
            + "businessType"+businessType
            + "customerRole"+customerRole
            + "loginStatus"+loginStatus
            + "token"+token
            + "createTime"+createTime
            + "modifiedTime"+modifiedTime
            + "]";
            return ret;
    }
}
