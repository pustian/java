package tian.pusen.crypto;

import java.util.UUID;

/**
 * 〈一句话功能简述〉<br>
 * 基础介绍：
 * Base64是一种基于64个可打印字符来表示二进制数据的表示方法。
 * 由于2的6次方等于64，所以每6个比特为一个单元，对应某个可打印字符。三个字节有24个比特，
 * 对应于4个Base64单元，即3个字节需要用4个可打印字符来表示。它可用来作为电子邮件的传输编码。
 * 在Base64中的可打印字符包括字母A-Z、a-z、数字0-9，这样共有62个字符，此外两个可打印符号在
 * 不同的系统中而不同。
 * 使用的字符包括大小写字母各26个，加上10个数字，和加号“+”，斜杠“/”，一共64个字符，
 * 等号“=”用来作为后缀用途。
 * 算法原理：
 * 1，将原始数据以先后顺序每3个字节分成一组*。在每一组中，把3个8bit的字节（3*8=24）按高低位顺序
 * 分为每段6bit的4段（4*6=24），将每段转为十进制得到0~63之间的数，以之为索引在Base64编码表中对照
 * 得到4个密文。重复以上操作。
 * 2，若原始数据字节长度除以3余1，则在输出数据末尾加2个“=”，若原始数据长度除以3余2，则在输出数据
 * 末尾加1个“=”。
 * <p>
 * |--------|--------|--------| |--------|--------|--------| |--------|--------|--------|
 * |------|------|------|------||------|------|------|------||------|------|------|------|
 * <p>
 * |--------|--------|--------| |--------|--------|--------| |--------|--------|
 * |------|------|------|------||------|------|------|------||------|------|------|000000|
 * <p>
 * |--------|--------|--------| |--------|--------|--------| |--------|
 * |------|------|------|------||------|------|------|------||------|------|000000|000000|
 * <p>
 * Base64编码表：
 * <p>
 * <p>
 * <p>
 * Date: 2017/12/25 10:32
 *
 * @author tianpusen
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public final class Base64 {
    private static final int INT_128 = 0x80; //
    private static final byte PAD = (byte) '=';
    private static final byte[] BASE64_CHAR_MAP = {
            (byte) 'A', (byte) 'B', (byte) 'C', (byte) 'D', (byte) 'E',
            (byte) 'F', (byte) 'G', (byte) 'H', (byte) 'I', (byte) 'J',
            (byte) 'K', (byte) 'L', (byte) 'M', (byte) 'N', (byte) 'O',
            (byte) 'P', (byte) 'Q', (byte) 'R', (byte) 'S', (byte) 'T',
            (byte) 'U', (byte) 'V', (byte) 'W', (byte) 'X', (byte) 'Y',
            (byte) 'Z', (byte) 'a', (byte) 'b', (byte) 'c', (byte) 'd',
            (byte) 'e', (byte) 'f', (byte) 'g', (byte) 'h', (byte) 'i',
            (byte) 'j', (byte) 'k', (byte) 'l', (byte) 'm', (byte) 'n',
            (byte) 'o', (byte) 'p', (byte) 'q', (byte) 'r', (byte) 's',
            (byte) 't', (byte) 'u', (byte) 'v', (byte) 'w', (byte) 'x',
            (byte) 'y', (byte) 'z', (byte) '0', (byte) '1', (byte) '2',
            (byte) '3', (byte) '4', (byte) '5', (byte) '6', (byte) '7',
            (byte) '8', (byte) '9', (byte) '+', (byte) '/',
    };
    // 1， 计算导出编码后长度
    // 2， 按算法描述编码
    public static byte[] encode(byte[] source) {
        int size = 0;
        switch (source.length % 3) {
            case 1:
                size = 4 * (source.length + 2) / 3;
                break;
            case 2:
                size = 4 * (source.length + 1) / 3;
                break;
            default:
                size = 4 * source.length / 3;
        }
        byte[] desc = new byte[size];

        byte b0 = (byte) 0xf0, b1 = (byte) 0xf0, b2 = (byte) 0xf0;
        for (int si = 0, di = 0; si < source.length && di < desc.length; si += 3, di += 4) {
            switch (source.length - si) {
                case 1:
                    b0 = source[si];
                    desc[di] = BASE64_CHAR_MAP[b0 >> 2];
                    desc[di + 1] = BASE64_CHAR_MAP[(b0 & 0x03) << 4];
                    desc[di + 2] = desc[di + 3] = PAD;
                    break;
                case 2:
                    b1 = source[si + 1];
                    desc[di + 1] = BASE64_CHAR_MAP[(b0 & 0x03) << 4 | (b1 & 0xf0) >> 4];
                    desc[di + 2] = BASE64_CHAR_MAP[(b1 & 0x0f) << 2];
                    desc[di + 3] = PAD;
                    break;
                default:
                    b1 = source[si + 1];
                    b2 = source[si + 2];
                    desc[di + 1] = BASE64_CHAR_MAP[(b0 & 0x03) << 4 | (b1 & 0xf0) >> 4];
                    desc[di + 2] = BASE64_CHAR_MAP[(b1 & 0x0f) << 2 | (b2 & 0xc0) >> 6];
                    desc[di + 3] = BASE64_CHAR_MAP[b2 & 0x3f];
                    break;
            }
        }
        return desc;
    }

    // 1, 获取在64位数组中位置
    // 2, 根据算法计算编码前的值
    // 3，计算处理实际长度（尾数处理）
    public static byte[] decode(byte[] source) {
        int size = source.length / 4 * 3;
        byte[] desc = new byte[size];
        int b0 = 0, b1 = 0, b2 = 0, b3 = 0;
        for (int si = 0, di = 0; si < source.length && di < desc.length; si += 4, di += 3) {
            b0 = locateAtMap(source[si]);
            b1 = locateAtMap(source[si + 1]);
            b2 = locateAtMap(source[si + 2]);
            b3 = locateAtMap(source[si + 3]);
            desc[di] = (byte) ((b0 & 0x3f) << 2 | (b1 & 0x30) >> 4);
            desc[di + 1] = (byte) ((b1 & 0x0f) << 4 | (b2 & 0x3c) >> 2);
            desc[di + 2] = (byte) ((b2 & 0x03) << 6 | (b3 & 0x3f));
        }

        int outputSize = size;
        // 处理最后位数 最后1位为“=”  // 最后2位为“==”
        if (PAD == source[source.length - 1])
            --outputSize;
        if (PAD == source[source.length - 2])
            --outputSize;
        byte[] outputBytes = new byte[outputSize];
        System.arraycopy(desc, 0, outputBytes, 0, outputSize);
        return outputBytes;
    }

    private static int locateAtMap(byte b) {
        int ret = INT_128; //1100 0000
        if ('A' <= b && b <= 'Z') {
            ret = b - 'A';
        }
        if ('a' <= b && b <= 'z') {
            ret = b - 'a' + 26;
        }
        if ('0' <= b && b <= '9') {
            ret = b - '0' + 26 + 26;
        }
        if (b == '+') {
            ret = 52;
        }
        if (b == '/') {
            ret = 53;
        }
        return ret;
    }

    public static void main(String[] args) {
//        //        49          50          49               50          51
//        // 0001 0001 + 0001 0010 + 0001 0001        0001 0010 + 0001 0011
//        // 00 0100 + 01 0001 + 0010 00 + 01 0001    00 0100 + 10 0001 + 0011 00 + ----
        byte[] bytes0 = "12123".getBytes(); //49 50 49 50 51 // MTIxMjM=
        byte[] bytes1 = "1212".getBytes();  //49 50 49 50    // MTIxMg==
        byte[] bytes2 = "121".getBytes();   //49 50 49       // MTIx
        System.out.println(new String(bytes0) +"base64:"+new String(encode(bytes0) ));
        System.out.println(new String(bytes1) +"base64:"+new String(encode(bytes1) ));
        System.out.println(new String(bytes2) +"base64:"+new String(encode(bytes2) ));


        byte[] encoded0 = "MTIx".getBytes();    // 77 84 73 120
        byte[] encoded1 = "MTIxMjM=".getBytes();// 77 84 73 120 77 106 77 61
        byte[] encoded2 = "MTIxMg==".getBytes();// 77 84 73 120 77 103 61 61
        System.out.println(new String(encoded0) + "base64:" + new String(decode(encoded0)));
        System.out.println(new String(encoded1) + "base64:" + new String(decode(encoded1)));
        System.out.println(new String(encoded2) + "base64:" + new String(decode(encoded2)));

        byte[] bytes0Test = decode(encode(bytes0));
        byte[] bytes1Test = decode(encode(bytes1));
        byte[] bytes2Test = decode(encode(bytes2));
        System.out.println(new String(bytes0Test));
        System.out.println(new String(bytes1Test));
        System.out.println(new String(bytes2Test));
    }
}
