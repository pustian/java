/**
 * 
 */
package tian.pusen.jdk.demo.encrypt;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * <p> Title:Base64 </p>
 * <p> Description:       
 * 一. Base64编码由来
 * 	为什么会有Base64编码呢？因为有些网络传送渠道并不支持所有的字节，例如传统的邮件只支持可见字符的传送，像ASCII码的控制字符就 不能通过邮件传送。
 * 	这样用途就受到了很大的限制，比如图片二进制流的每个字节不可能全部是可见字符，所以就传送不了。最好的方法就是在不改变传统协议的情 况下，做一种扩展方案来支持二进制文件的传送。
 * 	把不可打印的字符也能用可打印字符来表示，问题就解决了。Base64编码应运而生，Base64就是一种 基于64个可打印字符来表示二进制数据的表示方法。
 * 
 * 二. Base64编码原理
 * 	看一下Base64的索引表，字符选用了"A-Z、a-z、0-9、+、/" 64个可打印字符。数值代表字符的索引，这个是标准Base64协议规定的，不能更改。
 * 	64个字符用6个bit位就可以全部表示，一个字节有8个bit 位，剩下两个bit就浪费掉了，这样就不得不牺牲一部分空间了。
 * 	这里需要弄明白的就是一个Base64字符是8个bit，但是有效部分只有右边的6个 bit，左边两个永远是0。
 * 	数值 00 01 02 03 04 05 06 07 08 09 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25
 * 	字符 A  B  C  D  E  F  G  H  I  J  K  L  M  N  O  P  Q  R  S  T  U  V  W  X  Y  Z
 * 	数值 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45 46 47 48 49 50 51
 *  字符 a  b  c  d  e  f  g  h  i  j  k  l  m  n  o  p  q  r  s  t  u  v  w  x  y  z
 * 	数值 52 53 54 55 56 57 58 59 60 61 62 63
 *  字符 0  1  2  3  4  5  6  7  8  9  +  /
 * 	那么怎么用6个有效bit来表示传统字符的8个bit呢？
 * 		8和6的最小公倍数 是24，也就是说3个传统字节可以由4个Base64字符来表示，保证有效位数是一样的，这样就多了1/3的字节数来弥补Base64只有6个有效bit 的不足。
 * 	 	你也可以说用两个Base64字符也能表示一个传统字符，但是采用最小公倍数的方案其实是最减少浪费的。
 * 	结合下边的图比较容易理解。Man是三个 字符，一共24个有效bit，只好用4个Base64字符来凑齐24个有效位。
 * 		红框表示的是对应的Base64，6个有效位转化成相应的索引值再对应 Base64字符表，查出"Man"对应的Base64字符是"TWFU"。
 * 		说到这里有个原则不知道你发现了没有，要转换成Base64的最小单位就是三个字节，对一个字符串来说每次都是三个字节三个字节的转换，对应的是Base64的四个字节。这个搞清楚了其实就差不多了。
 * 		文本：		M       a        n
 *      ASCII：    		77      97       110
 *      2进制：		01001101 01100001 01101110
 *      	  		010011 010110 000101 101110
 *      索引：        		19     22     5      46
 *      Base64编码:	T      W      F      u	
 *	但是转换到最后你发现不够三个字节了怎么办呢？
 *		愿望终于实现了，我们可以用两 个Base64来表示一个字符或用三个Base64表示两个字符.
 *		像下图的A对应的第二个Base64的二进制位只有两个，把后边的四个补0就是了。
 *		所以 A对应的Base64字符就是QQ。
 *		上边已经说过了，原则是Base64字符的最小单位是四个字符一组，那这才两个字 符，后边补两个"="吧。其实不用"="也不耽误解码，之所以用"="，
 *		可能是考虑到多段编码后的Base64字符串拼起来也不会引起混淆。由此可见 Base64字符串只可能最后出现一个或两个"="，中间是不可能出现"="的。下图中字符"BC"的编码过程也是一样的。
 * 		文本：		A										B         C
 *      ASCII：    		
 *      2进制：		01000001								01000010 01000011
 *      	  		010000 010000 000000 000000             010000 100100 001100 000000
 *      索引：        		16     16                               16     40     12    
 *      Base64编码:	Q	   Q      =      =                  Q      k      M      =
 * 三. 总结
 *	说起Base64编码可能有些奇怪，因为大多数的编码都是由字符转化成二进制的过程，而从二进制转成字符的过程称为解码。而Base64的概念就恰好反了，由二进制转到字符称为编码，由字符到二进制称为解码。
 *	Base64编码主要用在传输、存储、表示二进制等领域，还可以用来加密，但是这种加密比较简单，只是一眼看上去不知道什么内容罢了，当然也可以对Base64的字符序列进行定制来进行加密。
 *  Base64编码是从二进制到字符的过程，像一些中文字符用不同的编码转为二 进制时，产生的二进制是不一样的，所以最终产生的Base64字符也不一样。
 *  例如"上网"对应utf-8格式的Base64编码是"5LiK572R"， 对应GB2312格式的Base64编码是"yc/N+A=="。
 * </p>
 * <p> Company: Masapay   </p>
 * @author: tianpusen
 * @Date  : 2016年10月20日 上午11:49:48
 */
public final class Base64 {
	public static char[] BASE64_CHAR = {
		'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
		'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
		'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/',
	};
	public static String BASE64_String = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
	public static char BASE64_PAD = '=';

	public static void main(String[] args) throws UnsupportedEncodingException {
//		System.out.println("new byte[0]\t" + encode(new byte[0]));
//		System.out.println("A\t" + encode("A".getBytes()) + "\t\tDecode:\t" +new String(decode(encode("A".getBytes())), "utf-8"));
//		System.out.println("BC\t" + encode("BC".getBytes()) + "\t\tDecode:\t" +new String(decode(encode("BC".getBytes())), "utf-8"));
//		System.out.println("Man\t" + encode("Man".getBytes()) + "\t\tDecode:\t" +new String(decode(encode("Man".getBytes())), "utf-8"));
//		System.out.println("ManBC\t" + encode("ManBC".getBytes()) + "\t\tDecode:\t" +new String(decode(encode("ManBC".getBytes())), "utf-8"));
		byte[] bs= {(byte) 0x9a, (byte) 0xea, (byte) 0xdc};
		String str = encode(bs);
		byte[] debs= decode(str);
		System.out.println(new String(bs)+"encode:"+str);
//		String string = "Idea love code,you know why?";
//		byte[] strBytes = string.getBytes() ;
//		byte[] decodeBytes = decode(encode(string.getBytes()));
//		//SWRlYSBsb3ZlIGNvZGUseW91IGtub3cgd2h5Pw==
//		System.out.println(string+"]\t"+ string.length()+"\t"+encode(string.getBytes()) +"\t\tDecode:\t[" +new String(decodeBytes, "utf-8")+"]");
	}
	/**
	 * 字节转换为可读的字符串
	 * @param bytes
	 * @return
	 */
	public static String encode(byte[] bytes) {
		String base64String = null;
		if(bytes.length > 0 ) {
			StringBuffer sb = new StringBuffer();
			int leaveOffNo = bytes.length%3;
			int loopTime = bytes.length /3;
			byte byte0=0x00, byte1=0x00, byte2 = 0x00;
			
			if(loopTime > 0 ) {
				for(int i=0; i < loopTime*3; i+=3) {
					byte0 = bytes[i];
					byte1 = bytes[i+1];
					byte2 = bytes[i+2];
//					System.out.println("=============================");
//					System.out.print("\nbyte0:\t"); BitOutput.outputBit(byte0);
//					System.out.print("\nbyte1:\t"); BitOutput.outputBit(byte1);
//					System.out.print("\nbyte2:\t"); BitOutput.outputBit(byte2);
//					System.out.println("-----------------------------");
//					System.out.print("\nbyte0>>>2:\t"); BitOutput.outputBit(byte0 >>> 2 & 0x3f);
//					System.out.print("\nbyte0>>>2:\t");BitOutput.outputBit((byte0 & 0x03) <<4 | (byte1 & 0xf0) >>>4);
//					System.out.print("\nbyte0>>>2:\t");BitOutput.outputBit((byte1 & 0x0f) <<2 | (byte2 & 0xc0) >>>6);
//					System.out.print("\nbyte2 & 0x3f:\t");BitOutput.outputBit(byte2 & 0x3f);
//					System.out.println();
					sb.append( BASE64_CHAR[(byte0 >>> 2) & 0x3f]);
					sb.append( BASE64_CHAR[((byte0 & 0x03) <<4 | (byte1 & 0xf0) >>>4) & 0x3f] ); 
					sb.append( BASE64_CHAR[((byte1 & 0x0f) <<2 | (byte2 & 0xc0) >>>6) & 0x3f]);
					sb.append( BASE64_CHAR[byte2 & 0x3f]);
				}
			}
			// if(leaveOffNo == 0 ) 无需再处理
			byte0=0x00; byte1=0x00; // byte2 = 0x00;
			if(leaveOffNo == 1 ) {
				byte0 = bytes[bytes.length-1];
				sb.append( BASE64_CHAR[(byte0 >>> 2) & 0x3f]);
				sb.append( BASE64_CHAR[((byte0 & 0x03) <<4 | (byte1 & 0xf0) >>>4) & 0x3f] );  
				sb.append( BASE64_PAD);
				sb.append( BASE64_PAD);
			}
			byte0=0x00; byte1=0x00; byte2 = 0x00;
			if(leaveOffNo == 2 ) {
				byte0 = bytes[bytes.length-2];
				byte1 = bytes[bytes.length-1];
				sb.append( BASE64_CHAR[(byte0 >>> 2) & 0x3f]);
				sb.append( BASE64_CHAR[((byte0 & 0x03) <<4 | (byte1 & 0xf0) >>>4) & 0x3f] ); 
				sb.append( BASE64_CHAR[((byte1 & 0x0f) <<2 | (byte2 & 0xc0) >>>6) & 0x3f]);
				sb.append( BASE64_PAD);
			}
			base64String = sb.toString();
		}
		return base64String;
	}
	/**
	 * 将base64 encode的字串转为字节
	 * @param base64String
	 * @return
	 */
	public static byte[] decode(String base64String) {
		byte[] bytes = null;
		if(base64String != null && base64String.length()  > 0 
				&& base64String.length() %4  == 0 
				&& base64String.matches("[A-Z|a-z|0-9|+|/|=]+")) {
			int stringLen = base64String.length();
			int size = stringLen / 4 *3;
			if(base64String.charAt(stringLen-1) == BASE64_PAD) size--;
			if(base64String.charAt(stringLen-2) == BASE64_PAD) size--;
			
			bytes = new byte[size];
			for(int i=0, j=0; i<stringLen; i+=4, j+=3) {
				char ch0 = base64String.charAt(i);
				char ch1 = base64String.charAt(i+1);
				char ch2 = base64String.charAt(i+2);
				char ch3 = base64String.charAt(i+3);
				int indexCh0 = -1;
				int indexCh1 = -1;
				int indexCh2 = -1;
				int indexCh3 = -1;
				for(int k = 0; k<BASE64_CHAR.length; k++) {
					if(BASE64_CHAR[k] == ch0) indexCh0 = k;
					if(BASE64_CHAR[k] == ch1) indexCh1 = k;
					if(BASE64_CHAR[k] == ch2) indexCh2 = k;
					if(BASE64_CHAR[k] == ch3) indexCh3 = k;
				}
				if(indexCh3 == -1) {
					if(indexCh2 == -1) {
						bytes[j] = (byte) ((indexCh0 << 2) |( indexCh1 >>> 4));
						// bytes[j+1] = (byte) ((indexCh1 &0xf)<<4 |(0 & 0xff) >>>2) ;
						// bytes[j+2] = (byte) ((0 &0x3)<<6 | 0);
					} else{
						bytes[j] = (byte) ((indexCh0 << 2) |( indexCh1 >>> 4));
						bytes[j+1] = (byte) ((indexCh1 &0xf)<<4 |(indexCh2 & 0xff) >>>2) ;
						// bytes[j+2] = (byte) ((indexCh2 &0x3)<<6 | 0);						
					}
				} else {
					bytes[j] = (byte) ((indexCh0 << 2) |( indexCh1 >>> 4));
					bytes[j+1] = (byte) ((indexCh1 &0xf)<<4 |(indexCh2 & 0xff) >>>2) ;
					bytes[j+2] = (byte) ((indexCh2 &0x3)<<6 | indexCh3);
				}
			}
//			// 去掉0
//			while(bytes[size-1]==0 && size>=0) {
//				size--;
//			}
//			retBytes = Arrays.copyOf(bytes, size);
		} else {
			System.out.println(base64String);
			throw new RuntimeException("The string is not encoded by Base64");
		}
		
		
		return bytes ;
	}

}

