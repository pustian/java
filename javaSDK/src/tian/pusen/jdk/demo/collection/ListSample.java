/**
 * 
 */
package tian.pusen.jdk.demo.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * <p> Title:ListSample </p>
 * <p> Description:       </p>
 * <p> Company: Masapay   </p>
 * @author: tianpusen
 * @Date  : 2016年10月26日 上午11:33:46
 */
public final class ListSample {

	public static void main(String[] args) {
	    List<Integer> numbersA = new ArrayList<>();
	    List<Integer> numbersB = new ArrayList<>();
	    numbersA.addAll(Arrays.asList(new Integer[] { 1, 3, 4, 7, 5, 2 }));
	    numbersB.addAll(Arrays.asList(new Integer[] { 13, 32, 533, 3, 4, 2 }));

	    System.out.println("A: " + numbersA);
	    System.out.println("B: " + numbersB);
	    List<Integer> numbersC = new ArrayList<>();
	    numbersC.addAll(numbersA);
	    numbersC.retainAll(numbersB);
	    System.out.println("List A : " + numbersA);
	    System.out.println("List B : " + numbersB);
	    System.out.println("Common elements between A and B: " + numbersC);
	    
	    System.out.println("numbersB.removeAll(numbersA);");
	    numbersB.removeAll(numbersA);
	    System.out.println("List A : " + numbersA);
	    System.out.println("List B : " + numbersB);

	    System.out.println("\n\n list sort");
	    List<MyBook> bookList = new ArrayList<MyBook>();
	    bookList.add(new MyBook(110, "The Bible and I", "Peter J."));
	    bookList.add(new MyBook(33, "1984", "W. Smith"));
	    bookList.add(new MyBook(2, "Davinci Code", "Adam Bronw"));
	    bookList.add(new MyBook(3, "The Bible and I", "Peter J."));
	    bookList.add(new MyBook(1111, "Eine Scheibe muss ich", "Tommy Jaud"));

	    System.out.println("Unsorted list: " + bookList);
	    Collections.sort(bookList, new Comparator<MyBook>() {
	        @Override
	        public int compare(MyBook o1, MyBook o2) {
	        	return o1.getName().compareToIgnoreCase(o2.getName());
	        }
	    });
	    System.out.println("Sorted list by name: " + bookList);
	}
}
class MyBook { 
    private int isbn;
    private String name;
    private String author;

    /**
     * @param isbn
     * @param name
     * @param author
     */
    public MyBook(int isbn, String name, String author) {
    this.isbn = isbn;
    this.name = name;
    this.author = author;
    }

    // @Override
    // public int compareTo(MyBook o) {
    // // TODO Auto-generated method stub
    // return 0;
    // }
    @Override
    public String toString() {
    	return "MyBook [isbn=" + isbn + ", name=" + name + ", author=" + author + "]\n";
//    	return ReflectionToStringBuilder.toString(this, ToStringStyle.DEFAULT_STYLE);
    }

    public int getIsbn() {
    return isbn;
    }

    public String getName() {
    return name;
    }

    public String getAuthor() {
    return author;
    }
}
