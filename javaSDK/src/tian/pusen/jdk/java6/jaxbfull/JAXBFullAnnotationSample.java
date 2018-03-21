/**
 * 
 */
package tian.pusen.java6.jaxbfull;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * <p> Title:JAXBFullAnnotationSample </p>
 * <p> Description:       </p>
 * <p> Company: Masapay   </p>
 * @author: tianpusen
 * @Date  : 2016年11月8日 上午11:31:09
 */
public class JAXBFullAnnotationSample {
    public static void main(String[] args) throws JAXBException, IOException{
        Set<Order> orders =new HashSet<Order>();
        Address address1 =new Address("China","ShangHai", "ShangHai", "Huang","200000");
        Customer customer1 =new Customer("Jim","male", "13699990000", address1);
        Order order1 =new Order("Mart","LH59900", new Date(), new BigDecimal(60),1);
        order1.setCustomer(customer1);
        orders.add(order1);
        Address address2 =new Address("China","JiangSu", "NanJing", "ZhongYangLu","210000");
        Customer customer2 =new Customer("David","male", "13699991000", address2);
        Order order2 =new Order("Mart","LH59800", new Date(), new BigDecimal(80),1);
        order2.setCustomer(customer2);
        orders.add(order2);
         
        Shop shop =new Shop("CHMart","100000", "EveryThing", 
        		new Address("China","ZheJiang", "HangZhou", "XiHuRoad","310000"));
        shop.setOrders(orders);
        
        String filename = "./src/tian/pusen/java6/jaxbfull/shop.xml";
        System.out.println("写入文件"+filename);
        FileWriter writer =null;
        JAXBContext context = JAXBContext.newInstance(Shop.class);
        try{
            Marshaller marshal = context.createMarshaller();
            marshal.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
            marshal.marshal(shop, System.out); // Console output
            
            writer =new FileWriter(filename);
            marshal.marshal(shop, writer);
        }catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("读入文件:"+filename);
        Unmarshaller unmarshal = context.createUnmarshaller();
        FileReader reader =new FileReader(filename) ;
        Shop shop1 = (Shop)unmarshal.unmarshal(reader);
        Set<Order> orders1 = shop1.getOrders();
        for(Order order : orders1){
            System.out.println("***************************");
            System.out.println(order.getOrderNumber());
            System.out.println(order.getCustomer().getName());
            System.out.println("***************************");
        }
    }
}

//// 同时使用了@XmlType（propOrder={}）和@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)，
// 但是生成的xml只按照propOrder定义的顺序生成元素
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name ="shop", propOrder = {"name", "number","describer", "address","orders"})
@XmlRootElement(name ="CHMart")
class Shop {
    @XmlAttribute
    private String name;
    // @XmlElement
    private String number;
    @XmlElement
    private String describer;
    @XmlElementWrapper(name ="orders")
    @XmlElement(name ="order")
    private Set<Order> orders;
    @XmlElement
    private Address address;
    public Shop() { }
    public Shop(String name, String number, String describer, Address address) {
        this.name = name;
        this.number = number;
        this.describer = describer;
        this.address = address;
    }
	public final String getName() {
		return name;
	}
	public final void setName(String name) {
		this.name = name;
	}
	public final String getNumber() {
		return number;
	}
	public final void setNumber(String number) {
		this.number = number;
	}
	public final String getDescriber() {
		return describer;
	}
	public final void setDescriber(String describer) {
		this.describer = describer;
	}
	public final Set<Order> getOrders() {
		return orders;
	}
	public final void setOrders(Set<Order> orders) {
		this.orders = orders;
	}
	public final Address getAddress() {
		return address;
	}
	public final void setAddress(Address address) {
		this.address = address;
	}
} 

//// @XmlAccessorType(XmlAccessType.FIELD)，所以此处注释掉了@XmlElement，xml中依然会生成这些元素
@XmlType(name="order",propOrder={"shopName","orderNumber","price","amount","purDate","customer"})
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
class Order {
	// @XmlElement
    private String shopName;
    @XmlAttribute
    private String orderNumber;
	// @XmlElement
    @XmlJavaTypeAdapter(value=DateAdapter.class)
    private Date purDate;
	// @XmlElement
    private BigDecimal price;
	// @XmlElement
    private int amount;
	// @XmlElement
    private Customer customer;
 
    public Order() {}
    public Order(String shopName, String orderNumber, Date purDate, BigDecimal price,int amount) {
        this.shopName = shopName;
        this.orderNumber = orderNumber;
        this.purDate = purDate;
        this.price = price;
        this.amount = amount;
    }
	public final String getShopName() {
		return shopName;
	}
	public final void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public final String getOrderNumber() {
		return orderNumber;
	}
	public final void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public final Date getPurDate() {
		return purDate;
	}
	public final void setPurDate(Date purDate) {
		this.purDate = purDate;
	}
	public final BigDecimal getPrice() {
		return price;
	}
	public final void setPrice(BigDecimal price) {
		this.price = price;
	}
	public final int getAmount() {
		return amount;
	}
	public final void setAmount(int amount) {
		this.amount = amount;
	}
	public final Customer getCustomer() {
		return customer;
	}
	public final void setCustomer(Customer customer) {
		this.customer = customer;
	}
}

@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
class Customer {
    @XmlAttribute
    private String name;
    private String gender;
    private String phoneNo;
    private Address address;
    private Set<Order> orders;
    public Customer() {}
    public Customer(String name, String gender, String phoneNo, Address address) {
        this.name = name;
        this.gender = gender;
        this.phoneNo = phoneNo;
        this.address = address;
    }
	public final String getName() {
		return name;
	}
	public final void setName(String name) {
		this.name = name;
	}
	public final String getGender() {
		return gender;
	}
	public final void setGender(String gender) {
		this.gender = gender;
	}
	public final String getPhoneNo() {
		return phoneNo;
	}
	public final void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public final Address getAddress() {
		return address;
	}
	public final void setAddress(Address address) {
		this.address = address;
	}
	public final Set<Order> getOrders() {
		return orders;
	}
	public final void setOrders(Set<Order> orders) {
		this.orders = orders;
	}
}
@XmlType(propOrder={"state","province","city","street","zip"})
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement
class Address {
    @XmlAttribute
    private String state;
    @XmlElement
    private String province;
    @XmlElement
    private String city;
    @XmlElement
    private String street;
    @XmlElement
    private String zip;
    public Address() {}
    public Address(String state, String province, String city, String street, String zip) {
        this.state = state;
        this.province = province;
        this.city = city;
        this.street = street;
        this.zip = zip;
    }
	public final String getState() {
		return state;
	}
	public final void setState(String state) {
		this.state = state;
	}
	public final String getProvince() {
		return province;
	}
	public final void setProvince(String province) {
		this.province = province;
	}
	public final String getCity() {
		return city;
	}
	public final void setCity(String city) {
		this.city = city;
	}
	public final String getStreet() {
		return street;
	}
	public final void setStreet(String street) {
		this.street = street;
	}
	public final String getZip() {
		return zip;
	}
	public final void setZip(String zip) {
		this.zip = zip;
	}
}

class DateAdapter extends XmlAdapter<String, Date> {
    private String pattern = "yyyy-MM-dd HH:mm:ss";
    @Override
    public Date unmarshal(String dateStr) throws Exception {
    	SimpleDateFormat fmt =new SimpleDateFormat(pattern);
    	return fmt.parse(dateStr);
    }
    @Override
    public String marshal(Date date) throws Exception {
    	SimpleDateFormat fmt =new SimpleDateFormat(pattern);
        return fmt.format(date);
    }
}