/**
 * 
 */
package tian.pusen.xml.dom;

import java.io.File;
import java.util.Iterator;

// dom4j-1.6.1.jar
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * <p> Title:Dom4jXML </p>
 * <p> Description:       </p>
 * <p> Company: Masapay   </p>
 * @author: tianpusen
 * @Date  : 2016年11月8日 下午3:36:41
 */
public final class Dom4jXML {
	public static void main(String arge[]) {
		long start = System.currentTimeMillis();
		try {
			File f = new File("./src/tian/pusen/xml/data.xml");
			SAXReader reader = new SAXReader();
			Document doc = reader.read(f);
			Element root = doc.getRootElement();
			Element foo;
			for (Iterator i = root.elementIterator("VALUE"); i.hasNext() ; ){
				foo = (Element) i.next();
				System.out.print("车牌号码:" + foo.elementText("NO"));
				System.out.println("车主地址:" + foo.elementText("ADDR"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		long end =System.currentTimeMillis();
		System.out.println("共花费"+(end-start)+"毫秒");
	}
}
