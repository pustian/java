public class OperatorDetail{
    private Integer id;
    private String operatorId;
    private String customerId;
    private String loginPasswd;
    private String paymentPasswd;
    private String lastLoginPasswd;
    private String lastPaymentPasswd;
    private String loginSalt;
    private String paymentSalt;
    private Date createTime;
    private Date modifiedTime;
    @Override
    public String toString () {
        String ret = "["
            + "id"+id
            + "operatorId"+operatorId
            + "customerId"+customerId
            + "loginPasswd"+loginPasswd
            + "paymentPasswd"+paymentPasswd
            + "lastLoginPasswd"+lastLoginPasswd
            + "lastPaymentPasswd"+lastPaymentPasswd
            + "loginSalt"+loginSalt
            + "paymentSalt"+paymentSalt
            + "createTime"+createTime
            + "modifiedTime"+modifiedTime
            + "]";
            return ret;
    }
}
