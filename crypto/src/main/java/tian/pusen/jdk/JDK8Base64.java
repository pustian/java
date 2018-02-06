package tian.pusen.jdk;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 * Date: 2018/1/26 14:22
 *
 * @author tianpusen
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class JDK8Base64 {
    public static final Base64.Decoder decoder = Base64.getDecoder();
    public static final Base64.Encoder encoder = Base64.getEncoder();

    public static void main(String[] args) throws UnsupportedEncodingException {
        final String text = "ABC字串文字xyz";
        final byte[] textByte = text.getBytes("UTF-8");
        //编码
        final String encodedText = encoder.encodeToString(textByte);
        System.out.println(encodedText);
        //解码
        final byte[] textBase64Byte = decoder.decode(encodedText);
        System.out.println(new String(textBase64Byte, "UTF-8"));
    }
}
