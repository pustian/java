package tian.pusen.xml.dom;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public final class DomXML {
	public static void main(String arge[]){
		long start =System.currentTimeMillis();
		try{
			File f=new File("./src/tian/pusen/xml/data.xml");
			DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
			DocumentBuilder builder=factory.newDocumentBuilder();
			Document doc = builder.parse(f);
			
			NodeList nl = doc.getElementsByTagName("VALUE");
			for (int i=0; i < nl.getLength(); i++){
				String carNo = doc.getElementsByTagName("NO").item(i).getFirstChild().getNodeValue();
				System.out.print("车牌号码:" + carNo);
				String address = doc.getElementsByTagName("ADDR").item(i).getFirstChild().getNodeValue();
				System.out.println("车主地址:" + address);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		long end =System.currentTimeMillis();
		System.out.println("共花费"+(end-start)+"毫秒");
	} 
}
