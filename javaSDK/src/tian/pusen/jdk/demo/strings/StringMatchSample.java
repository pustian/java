package tian.pusen.jdk.demo.strings;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringMatchSample {
    static Pattern p=Pattern.compile("[0-9]+");
    public static void main(String[] args) {
        String settle = "99.98840";
        Matcher m=p.matcher(settle);
        while(m.find()){
            System.out.println("string:"+m.group()+"\tstart:"+m.start()+"\tend:"+m.end());
        }
    }

}
