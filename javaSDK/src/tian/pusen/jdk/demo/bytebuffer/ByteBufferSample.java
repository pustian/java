/**
 * 
 */
package tian.pusen.jdk.demo.bytebuffer;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * <p> Title:ByteBufferSample </p>
 * <p> Description:       </p>
 * <p> Company: Masapay   </p>
 * @author: tianpusen
 * @Date  : 2016年10月25日 下午5:31:27
 */
public final class ByteBufferSample {
	public static void main(String[] args) {
		Charset charset = Charset.forName("UTF-8");
		String example01 = "example";
		ByteBuffer bb01 = ByteBuffer.wrap(example01.getBytes(charset));
		int size01 = bb01.remaining();
		System.out.println("Size of ByteBuffer : " + size01);
		
		
		String example02 = "example汉";
		ByteBuffer bb02 = ByteBuffer.wrap(example02.getBytes(charset));
		int size02 = bb02.remaining();
		System.out.println("Size of ByteBuffer : " + size02);
		

//		final byte[] bytes = {0x04,0x03,0x4b,0x50};
//		final ByteBuffer bb = ByteBuffer.wrap(bytes);
//		System.out.println("Original: " + Arrays.toString(bb.array()));
//		final ByteBuffer bb2 = ((ByteBuffer) bb.position(1).limit(2)).slice();
//		final byte[] dst = new byte[bb2.remaining()];
//		bb.get(dst);
//		System.out.println("Slice: " + Arrays.toString(dst));
	}
}
