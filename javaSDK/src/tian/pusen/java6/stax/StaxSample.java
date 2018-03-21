package tian.pusen.java6.stax;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/**
 * 
 * <p> Title:StaxSample </p>
 * <p> Description:       </p>
 * <p> Company: Masapay   </p>
 * @author: tianpusen
 * @Date  : 2016年11月8日 下午1:13:00
 */
// STAX是The Streaming API for XML的缩写，一种利用拉模式解析(pull-parsing)XML文档的API. 
// STAX通过提供一种基于事件迭代器(Iterator)的API让程序员去控制xml文档解析过程,程序遍历这个事件迭代器去处理每一个解析事件，解析事件可以看做是程序拉出来的，
//   也就是程序促使解析器产生一个解析事件然后处理该事件，之后又促使解析器产生下一个解析事件，如此循环直到碰到文档结束符；
//   SAX 也是基于事件处理xml 文档，但却是用推模式解析，解析器解析完整个xml 文档后，才产生解析事件，然后推给程序去处理这些事件；
// DOM 采用的方式是将整个xml文档映射到一颗内存树，这样就可以很容易地得到父节点和子结点以及兄弟节点的数据，但如果文档很大，将会严重影响性能。
public class StaxSample {
	public static void main(String[] args) throws XMLStreamException, FileNotFoundException {
//        readXMLByStAX();//用XMLEventReader 解析xml 文档
//        writeXMLByStAX();//用XMLStreamWriter 写xml文档
        
        readXMLByStAX2();
	}
	private static void readXMLByStAX2() throws FileNotFoundException, XMLStreamException {
	    String xmlDocument = "<?xml version='1.0' encoding='UTF-8' ?>"
	            + "<library>"
	                + "<book id='1'>Effective Java</book>"
	                + "<book id='2'>Java Concurrency In Practice</book>"
	                + "<notABook id='3'>This is not a book element </notABook>"
	            + "</library>";
		XMLInputFactory xmlInputFactory = XMLInputFactory.newFactory();
	    // Various flavors are possible, e.g. from an InputStream, a Source, ...
		XMLStreamReader xmlStreamReader = xmlInputFactory.createXMLStreamReader(new StringReader(xmlDocument));
	    Map<Integer, String> bookTitlesById = new HashMap<>();
		while (xmlStreamReader.hasNext()) {
			switch (xmlStreamReader.getEventType()) {
			case XMLStreamConstants.START_ELEMENT:
				System.out.println("Found start of element: " + xmlStreamReader.getLocalName());
				// Check if we are at the start of a <book> element
				if ("book".equals(xmlStreamReader.getLocalName())) {
					int bookId = Integer.parseInt(xmlStreamReader.getAttributeValue("", "id"));
					String bookTitle = xmlStreamReader.getElementText();
					bookTitlesById.put(bookId, bookTitle);
				}
				break;
			// A bunch of other things are possible : comments, processing instructions, Whitespace...
			default:
				break;
			}
		    xmlStreamReader.next();
		}
	}
	private static void readXMLByStAX() throws XMLStreamException, FileNotFoundException {
		XMLInputFactory xmlif = XMLInputFactory.newInstance();
//		XMLEventReader xmler = xmlif.createXMLEventReader(StaxSample.class.getResourceAsStream("./src/tian/pusen/java6/stax/staxtest.xml"));
		XMLEventReader xmler = xmlif.createXMLEventReader(new FileInputStream("./src/tian/pusen/java6/stax/staxtest.xml"));
		XMLEvent event;
		StringBuffer parsingResult = new StringBuffer();
		
		while (xmler.hasNext()) {
			 event = xmler.nextEvent();
			 if (event.isStartElement()) { //如果解析的是起始标记
				StartElement se = event.asStartElement();
				parsingResult.append("<");
				parsingResult.append(se.getName());
				if (se.getName().getLocalPart().equals("catalog")) {
					parsingResult.append(" id=\"");
					parsingResult.append(se.getAttributeByName(new QName("id")).getValue());
					parsingResult.append("\"");
				}
				parsingResult.append(">");
			} else if (event.isCharacters()) { //如果解析的是文本内容
				parsingResult.append(event.asCharacters().getData());
			} else if (event.isEndElement()) { //如果解析的是结束标记
				parsingResult.append("</");
				parsingResult.append(event.asEndElement().getName());
				parsingResult.append(">");
			}
		}
		System.out.println(parsingResult);
	}
	private static void writeXMLByStAX() throws XMLStreamException, FileNotFoundException {
		XMLOutputFactory xmlof = XMLOutputFactory.newInstance();
		XMLStreamWriter xmlw = xmlof.createXMLStreamWriter(new FileOutputStream("./src/tian/pusen/java6/stax/output.xml"));
		// 写入默认的XML 声明到xml文档
		xmlw.writeStartDocument();
		xmlw.writeCharacters("\n");
		// 写入注释到xml 文档
		xmlw.writeComment("testing comment");
		xmlw.writeCharacters("\n");
		// 写入一个catalogs根元素
		xmlw.writeStartElement("catalogs");
		xmlw.writeNamespace("myNS", "http://blog.csdn.net/Lj");
		xmlw.writeAttribute("owner", "Lj");
		xmlw.writeCharacters("\n");
		// 写入子元素catalog
		xmlw.writeStartElement("http://blog.csdn.net/Lj", "catalog");
		xmlw.writeAttribute("id", "007");
		xmlw.writeCharacters("Apparel");
		// 写入catalog元素的结束标签
		xmlw.writeEndElement();
		// 写入catalogs元素的结束标签
		xmlw.writeEndElement();
		// 结束XML 文档
		xmlw.writeEndDocument();
		xmlw.close();
	}
}
