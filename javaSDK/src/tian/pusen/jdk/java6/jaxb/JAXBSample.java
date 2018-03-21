package tian.pusen.java6.jaxb;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
//JSR 222
// JAXB是Java Architecture for XML Binding的缩写，可以将一个Java对象转变成为XML格式，反之亦然。
// 我们把对象与关系数据库之间的映射称为ORM, 其实也可以把对象与XML之间的映射称为OXM(Object XML Mapping). 
// JDK6中自带的这个JAXB版本是2.0, 比起1.0(JSR 31)来，JAXB2(JSR 222)用JDK5的新特性Annotation来标识要作绑定的类和属性等，
//	这就极大简化了开发的工作量。实际上，在Java EE 5.0中，EJB和Web Services也通过Annotation 来简化开发工作。
//	另外,JAXB在底层是用STAX(JSR173)来处理XML文档。
public class JAXBSample {
	public static void main(String[] args) throws JAXBException, IOException {
		Address address = new Address("China", "Beijing", "Beijing",
				"ShangDi West", "100080");
		Person p = new Person(Calendar.getInstance(), "JAXB2", address,
				Gender.MALE, "SW");

		// 下面代码演示将对象转变为xml
		System.out.println("Reader the xml file from object");
		JAXBContext context = JAXBContext.newInstance(Person.class);
		Marshaller m = context.createMarshaller();
		FileWriter fw = new FileWriter("./src/tian/pusen/java6/jaxb/person.xml");
		m.marshal(p, fw);
		
		System.out.println("Reader from the xml file");
		// 下面代码演示将上面生成的xml转换为对象
		FileReader fr = new FileReader("./src/tian/pusen/java6/jaxb/person.xml");
		Unmarshaller um = context.createUnmarshaller();
		Person p2 = (Person) um.unmarshal(fr);
		System.out.println("Country:" + p2.getAddress().getCountry());
	}
}

@XmlRootElement
//表示person是一个根元素
class Person {
	@XmlElement
	Calendar birthDay; //birthday将作为person的子元素
	@XmlAttribute
	String name; //name将作为person的的一个属性
	@XmlElement
	Address address; //address将作为person的子元素
	@XmlElement
	Gender gender; //gender将作为person的子元素
	@XmlElement
	String job; //job将作为person的子元素
	public Person() {}
	
	public Person(Calendar birthDay, String name, Address address, Gender gender, String job) {
		this.birthDay = birthDay;
		this.name = name;
		this.address = address;
		this.gender = gender;
		this.job = job;
	}
	public Address getAddress() {
		return address;    
	}
}


enum Gender {
	MALE(true), FEMALE(false);
	private final boolean value;
	Gender(boolean _value) {
		value = _value;
	}
	public final boolean getValue(){
		return value;
	}
}

class Address {
	@XmlAttribute
	String country;
	@XmlElement
	String state;
	@XmlElement
	String city;
	@XmlElement
	String street;
	String zipcode; //由于没有添加@XmlElement,所以该元素不会出现在输出的xml中
	
	public Address() {}
	public Address(String country, String state, String city, String street, String zipcode) {
		this.country = country;
		this.state = state;
		this.city = city;
		this.street = street;
		this.zipcode = zipcode;
	}
	public String getCountry() {
		return country;
	}
}