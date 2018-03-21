/**
 * 
 */
package tian.pusen.jdk.demo.enums;

/**
 * <p> Title:Coin </p>
 * <p> Description:       </p>
 * <p> Company: Masapay   </p>
 * @author: tianpusen
 * @Date  : 2016年10月20日 下午5:45:52
 */
public enum Coin {
    PENNY(1, "sas"), 
    NICKEL(5, "as"), 
    DIME(10, "da"), 
    QUARTER(25,"dwe"); // usual names for US coins
    // note that the above parentheses and the constructor arguments match
    private int value;
    private String desc;

    Coin(int value, String desc) { 
        this.value = value;
        this.desc  = desc;
    }

    public int getValue() {
        return value;
    }
}
