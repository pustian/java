/**
 * 
 */
package tian.pusen.jdk.demo.character;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * <p> Title:CharacterEncodingSample </p>
 * <p> Description:       </p>
 * <p> Company: Masapay   </p>
 * @author: tianpusen
 * @Date  : 2016年10月19日 下午3:11:05
 */
public final class CharacterEncodingSample {
	public static void main(String[] args) throws IOException {
		byte[] bytes = getUtf8BytesFromString("Test UTF8 bytes from String：strange cyrillic symbol Ы 田圃森：");
		System.out.println(new String(bytes, StandardCharsets.UTF_8));
		String filename = "Test-CharacterEncodingSample";
		WritingUTF8TextFile(filename);
		ReadingUTF8TextFile(filename);
	}
	
	
	
	public static byte[] getUtf8BytesFromString(String string) {
        //StandardCharsets is available since Java 1.7
        //for ealier version use Charset.forName("UTF-8");
        byte[] textInUtf8 = string.getBytes(StandardCharsets.UTF_8);
        return textInUtf8;
    }
	public static void WritingUTF8TextFile(String filename) throws IOException{
		//StandardCharsets is available since Java 1.7
        //for ealier version use Charset.forName("UTF-8");
        try (BufferedWriter wr = Files.newBufferedWriter(Paths.get(filename), StandardCharsets.UTF_8)) {
            wr.write("Strange cyrillic symbol Ы 田圃森");
        }
	}
	public static void ReadingUTF8TextFile(String filename) throws IOException{
		//StandardCharsets is available since Java 1.7
        //for ealier version use Charset.forName("UTF-8");
		/* First Way. For big files */
        System.out.println("========== First Way. For big files ==========");
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filename), StandardCharsets.UTF_8)) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.print(line);
            }
        }
        
        System.out.println(); //just separating output
        
        /* Second way. For small files */
        System.out.println("========== Second way. For small files ==========");
        String s = new String(Files.readAllBytes(Paths.get(filename)), StandardCharsets.UTF_8);
        System.out.print(s);
	}
}
