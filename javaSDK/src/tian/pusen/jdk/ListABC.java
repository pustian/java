package tian.pusen.jdk;

import java.util.ArrayList;
import java.util.List;

public class ListABC {
	public static void main(String[] args ) {
		List list = new ArrayList();
		list.add(1);
		list.add(10L);
		list.add("hello");
		for(int i =0; i< list.size(); i++)
			System.out.println(list.get(i));
	}
}
