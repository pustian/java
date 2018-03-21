package tian.pusen.xml.sax;

import java.util.Stack;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public final class SaxXML extends DefaultHandler {
	Stack<String> tags = new Stack<String>();
	
	public static void main(String args[]) {
		long lasting = System.currentTimeMillis();
		try {
			SAXParserFactory sf = SAXParserFactory.newInstance();
			SAXParser sp = sf.newSAXParser();
			SaxXML reader = new SaxXML();
			sp.parse(new InputSource("./src/tian/pusen/xml/data.xml"), reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("运行时间：" + (System.currentTimeMillis() - lasting) + "毫秒");
	}
	public void characters(char ch[], int start, int length) throws SAXException {
		String tag = (String) tags.peek();
		if (tag.equals("NO")) {
			System.out.print("车牌号码：" + new String(ch, start, length));
		}
		if (tag.equals("ADDR")) {
			System.out.println("地址:" + new String(ch, start, length));
		}
	}
	public void startElement(String uri,String localName,String qName,Attributes attrs) {
		tags.push(qName);
	}
}
