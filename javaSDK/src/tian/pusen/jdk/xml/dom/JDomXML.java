/**
 * 
 */
package tian.pusen.xml.dom;

import java.io.File;
import java.util.List;



// jdom-1.0.jar
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

/**
 * <p> Title:JDomXML </p>
 * <p> Description:       </p>
 * <p> Company: Masapay   </p>
 * @author: tianpusen
 * @Date  : 2016年11月8日 下午3:33:52
 */
public final class JDomXML {
	public static void main(String arge[]) {
		long start = System.currentTimeMillis();
		try {
			SAXBuilder builder = new SAXBuilder();
			Document doc = builder.build(new File("./src/tian/pusen/xml/data.xml"));
			Element foo = doc.getRootElement();
			List allChildren = foo.getChildren();
			for(int i=0; i < allChildren.size(); i++) {
				System.out.print("车牌号码:" + ((Element)allChildren.get(i)).getChild("NO").getText());
				System.out.println("车主地址:" + ((Element)allChildren.get(i)).getChild("ADDR").getText());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		long end =System.currentTimeMillis();
		System.out.println("共花费"+(end-start)+"毫秒");
	}
}
