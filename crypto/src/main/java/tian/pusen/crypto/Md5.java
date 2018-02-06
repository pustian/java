package tian.pusen.crypto;

/**
 * 〈一句话功能简述〉<br>
 * md5，全名Message Digest Algorithm 5*
 * MD5是一种信息摘要算法，主要是通过特定的hash散列方法将文本信息转换成简短的信息摘要，压缩+加密+hash算法的结合体，
 * 是绝对不可逆的
 *
 * 对MD5算法简要的叙述可以为：MD5以512位分组来处理输入的信息，且每一分组又被划分为16个32位子分组，经过了一系列的处理后，
 *          算法的输出由四个32位分组组成，将这四个32位分组级联后将生成一个128位散列值。
 *    MD5算法中，首先需要对信息进行填充，使其位长对512求余的结果等于448。
 *          因此，信息的位长（Bits Length）将被扩展至N*512+448，N为一个非负整数，N可以是零。
 *          填充的方法如下，在信息的后面填充一个1和剩余的0，直到满足上面的条件时才停止用0对信息的填充。
 *          然后，在这个结果后面附加一个以64位二进制表示的填充前信息长度。
 *    经过这两步的处理，现在的信息的位长=N*512+448+64=(N+1）*512，即长度恰好是512的整数倍。
 *          这样做的原因是为满足后面处理中对信息长度的要求。
 *    消息L bit | 消息（0-511bit）|消息长度（64bit）
 *    MD5初始的128位值为初试链接变量，为4组8位16进制数。分别为：
 *          A=0x67452301
 *          B=0xefcdab89
 *          C=0x98badcfe
 *          D=0x98badcfe
 *    基本处理思想：初始为128个bit 信息，将128bit 初始信息与分割后的512bit 数据经过4组16次hash循环算法，得到128bit 的新信息，依次循环，直到与最后一组512 bit的数据hash算法后，就得到的整个文件的MD5值。
 *
 * MD5计算步骤
 * MD5以512位分组来处理输入的信息，且每一分组又被划分为16个32位子分组，经过了一系列的处理后，算法的输出由四个32位分组组成，将这四个32位分组级联后将生成一个128位散列值。
 *
 * 1、填充
 *      如果输入信息的长度(bit)对512求余的结果不等于448，就需要填充使得对512求余的结果等于448。
 *      填充的方法是填充一个1和n个0。填充完后，信息的长度就为N*512+448(bit)；
 * 2、记录信息长度
 *      用64位来存储填充前信息长度。这64位加在第一步结果的后面，这样信息长度就变为N*512+448+64=(N+1)*512位。
 * 3、装入标准的幻数（四个整数）
 *      标准的幻数（物理顺序）是（A=(01234567)16，B=(89ABCDEF)16，C=(FEDCBA98)16，D=(76543210)16）。
 *      如果在程序中定义应该是（A=0X67452301L，B=0XEFCDAB89L，C=0X98BADCFEL，D=0X10325476L）。
 *      有点晕哈，其实想一想就明白了。
 * 4、四轮循环运算
 *      循环的次数是分组的个数（N+1）
 *      1）将每一512字节细分成16个小组，每个小组64位（8个字节）
 *      2）先认识四个线性函数(&是与,|是或,~是非,^是异或)
 *          F(X,Y,Z)=(X&Y)|((~X)&Z)
 *          G(X,Y,Z)=(X&Z)|(Y&(~Z))
 *          H(X,Y,Z)=X^Y^Z
 *          I(X,Y,Z)=Y^(X|(~Z))
 *      3）设Mj表示消息的第j个子分组（从0到15），<<<s表示循环左移s位，则四种操作为：
 *          FF(a,b,c,d,Mj,s,ti)表示a=b+((a+F(b,c,d)+Mj+ti)<<<s)
 *          GG(a,b,c,d,Mj,s,ti)表示a=b+((a+G(b,c,d)+Mj+ti)<<<s)
 *          HH(a,b,c,d,Mj,s,ti)表示a=b+((a+H(b,c,d)+Mj+ti)<<<s)
 *          II(a,b,c,d,Mj,s,ti)表示a=b+((a+I(b,c,d)+Mj+ti)<<<s)
 *      4）四轮运算 循环哈希算法
 *      第一轮
 *          a=FF(a,b,c,d,M0,7,0xd76aa478)
 *          b=FF(d,a,b,c,M1,12,0xe8c7b756)
 *          c=FF(c,d,a,b,M2,17,0x242070db)
 *          d=FF(b,c,d,a,M3,22,0xc1bdceee)
 *          a=FF(a,b,c,d,M4,7,0xf57c0faf)
 *          b=FF(d,a,b,c,M5,12,0x4787c62a)
 *          c=FF(c,d,a,b,M6,17,0xa8304613)
 *          d=FF(b,c,d,a,M7,22,0xfd469501)
 *          a=FF(a,b,c,d,M8,7,0x698098d8)
 *          b=FF(d,a,b,c,M9,12,0x8b44f7af)
 *          c=FF(c,d,a,b,M10,17,0xffff5bb1)
 *          d=FF(b,c,d,a,M11,22,0x895cd7be)
 *          a=FF(a,b,c,d,M12,7,0x6b901122)
 *          b=FF(d,a,b,c,M13,12,0xfd987193)
 *          c=FF(c,d,a,b,M14,17,0xa679438e)
 *          d=FF(b,c,d,a,M15,22,0x49b40821)
 *      第二轮
 *          a=GG(a,b,c,d,M1,5,0xf61e2562)
 *          b=GG(d,a,b,c,M6,9,0xc040b340)
 *          c=GG(c,d,a,b,M11,14,0x265e5a51)
 *          d=GG(b,c,d,a,M0,20,0xe9b6c7aa)
 *          a=GG(a,b,c,d,M5,5,0xd62f105d)
 *          b=GG(d,a,b,c,M10,9,0x02441453)
 *          c=GG(c,d,a,b,M15,14,0xd8a1e681)
 *          d=GG(b,c,d,a,M4,20,0xe7d3fbc8)
 *          a=GG(a,b,c,d,M9,5,0x21e1cde6)
 *          b=GG(d,a,b,c,M14,9,0xc33707d6)
 *          c=GG(c,d,a,b,M3,14,0xf4d50d87)
 *          d=GG(b,c,d,a,M8,20,0x455a14ed)
 *          a=GG(a,b,c,d,M13,5,0xa9e3e905)
 *          b=GG(d,a,b,c,M2,9,0xfcefa3f8)
 *          c=GG(c,d,a,b,M7,14,0x676f02d9)
 *          d=GG(b,c,d,a,M12,20,0x8d2a4c8a)
 *      第三轮
 *          a=HH(a,b,c,d,M5,4,0xfffa3942)
 *          b=HH(d,a,b,c,M8,11,0x8771f681)
 *          c=HH(c,d,a,b,M11,16,0x6d9d6122)
 *          d=HH(b,c,d,a,M14,23,0xfde5380c)
 *          a=HH(a,b,c,d,M1,4,0xa4beea44)
 *          b=HH(d,a,b,c,M4,11,0x4bdecfa9)
 *          c=HH(c,d,a,b,M7,16,0xf6bb4b60)
 *          d=HH(b,c,d,a,M10,23,0xbebfbc70)
 *          a=HH(a,b,c,d,M13,4,0x289b7ec6)
 *          b=HH(d,a,b,c,M0,11,0xeaa127fa)
 *          c=HH(c,d,a,b,M3,16,0xd4ef3085)
 *          d=HH(b,c,d,a,M6,23,0x04881d05)
 *          a=HH(a,b,c,d,M9,4,0xd9d4d039)
 *          b=HH(d,a,b,c,M12,11,0xe6db99e5)
 *          c=HH(c,d,a,b,M15,16,0x1fa27cf8)
 *          d=HH(b,c,d,a,M2,23,0xc4ac5665)
 *      第四轮
 *          a=II(a,b,c,d,M0,6,0xf4292244)
 *          b=II(d,a,b,c,M7,10,0x432aff97)
 *          c=II(c,d,a,b,M14,15,0xab9423a7)
 *          d=II(b,c,d,a,M5,21,0xfc93a039)
 *          a=II(a,b,c,d,M12,6,0x655b59c3)
 *          b=II(d,a,b,c,M3,10,0x8f0ccc92)
 *          c=II(c,d,a,b,M10,15,0xffeff47d)
 *          d=II(b,c,d,a,M1,21,0x85845dd1)
 *          a=II(a,b,c,d,M8,6,0x6fa87e4f)
 *          b=II(d,a,b,c,M15,10,0xfe2ce6e0)
 *          c=II(c,d,a,b,M6,15,0xa3014314)
 *          d=II(b,c,d,a,M13,21,0x4e0811a1)
 *          a=II(a,b,c,d,M4,6,0xf7537e82)
 *          b=II(d,a,b,c,M11,10,0xbd3af235)
 *          c=II(c,d,a,b,M2,15,0x2ad7d2bb)
 *          d=II(b,c,d,a,M9,21,0xeb86d391)
 *      5）每轮循环后，将A，B，C，D分别加上a，b，c，d，然后进入下一循环。
 *
 * 参考：http://scenery-86.iteye.com/blog/68571
 * 参考：http://www.php.cn/java-article-350304.html
 * 参考：http://blog.csdn.net/ccy0815ccy/article/details/40709577
 * 参考：https://www.cnblogs.com/fullsail/archive/2013/02/22/2921505.html
 *  Date: 2017/12/28 12:59
 *
 * @author tianpusen
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public final class Md5 {
    // 四个链接变量
    private final int A=0x67452301;
    private final int B=0xefcdab89;
    private final int C=0x98badcfe;
    private final int D=0x10325476;
    //  ABCD的临时变量
    private int Atemp,Btemp,Ctemp,Dtemp;


    public static byte[] encode(byte[] source) {
        return null;
    }
    // 4个非线性方法
    //    F(X,Y,Z)=(X&Y)|((~X)&Z)
    //    G(X,Y,Z)=(X&Z)|(Y&(~Z))
    //    H(X,Y,Z)=X^Y^Z
    //    I(X,Y,Z)=Y^(X|(~Z))
    private long F(long x, long y, long z) {
        return (x & y) | ((~x) & z);
    }
    private long G(long x, long y, long z) {
        return (x & z) | (y & (~z));
    }
    private long H(long x, long y, long z) {
        return x ^ y ^ z;
    }
    private long I(long x, long y, long z) {
        return y ^ (x | (~z));
    }
    // 设Mj表示消息的第j个子分组（从0到15），<<<s表示循环左移s位，则四种操作为：
    //    FF(a,b,c,d,Mj,s,ti)表示a=b+((a+F(b,c,d)+Mj+ti)<<<s)
    //    GG(a,b,c,d,Mj,s,ti)表示a=b+((a+G(b,c,d)+Mj+ti)<<<s)
    //    HH(a,b,c,d,Mj,s,ti)表示a=b+((a+H(b,c,d)+Mj+ti)<<<s)
    //    II(a,b,c,d,Mj,s,ti)表示a=b+((a+I(b,c,d)+Mj+ti)<<<s)
    private long FF(long a, long b, long c, long d, long Mj, long s, long ac) {
        a += F(b, c, d) + Mj + ac;
        a = ((int) a << s) | ((int) a >>> (32 - s));
        a += b;
        return a;
    }
    private long GG(long a, long b, long c, long d, long Mj, long s, long ac) {
        a += G(b, c, d) + Mj + ac;
        a = ((int) a << s) | ((int) a >>> (32 - s));
        a += b;
        return a;
    }
    private long HH(long a, long b, long c, long d, long Mj, long s, long ac) {
        a += H(b, c, d) + Mj + ac;
        a = ((int) a << s) | ((int) a >>> (32 - s));
        a += b;
        return a;
    }
    private long II(long a, long b, long c, long d, long Mj, long s, long ac) {
        a += I(b, c, d) + Mj + ac;
        a = ((int) a << s) | ((int) a >>> (32 - s));
        a += b;
        return a;
    }

}
