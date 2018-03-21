package tian.pusen.jdk.info;

import java.util.Properties;

/**
 * Created by tianpusen on 2017/3/16.
 */
public class JDKProperties {
    public static void main(String[] args) {
        Properties properties = System.getProperties();
        properties.list(System.out);
    }
}
