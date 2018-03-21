/**
 * 
 */
package tian.pusen.jdk.demo.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p> Title:ArraySimple </p>
 * <p> Description:       </p>
 * <p> Company: Masapay   </p>
 * @author: tianpusen
 * @Date  : 2016年10月18日 上午10:07:38
 */
public final class ArraySample {
	public static void main(String[] args) {
//		basicArray();
//		excepteArray();
//		array2List();
//		array2ListExcept();
//		collection2Array();


	}
    
	static int min(int a, int b, int c) {
    	return (a < b ? a : b) < c ? (a < b ? a : b) : c;
    }
	
	static void basicArray(){
		int[]   numbers1 = new int[3];                 // Array for 3 int values, default value is 0
		int[]   numbers2 = { 1, 2, 3 };                // Array literal of 3 int values
		int[]   numbers3 = new int[] { 1, 2, 3 };      // Array of 3 int values initialized
		int[][] numbers4 = { { 1, 2 }, { 3, 4, 5 } };  // Jagged array literal  Jagged--锯齿状的; 参差不齐的; 粗糙的
		// need to be assigned
		int[][] numbers5 = new int[5][];               // Jagged array, one dimension 5 long
		int[][] numbers6 = new int[5][4];              // Multidimensional array: 5x4
		int size = 42;
		int[] array = new int[size];
		
		{
			System.out.print("int[]   numbers1 = new int[3];\n\t");
			for(int i=0; i<numbers1.length; i++) 
				System.out.print("index:["+i+"] value:["+numbers1[i]+"]\t");
			System.out.println();
			System.out.print("int[]   numbers2 = { 1, 2, 3 };\n\t");
			for(int i=0; i<numbers2.length; i++) 
				System.out.print("index:["+i+"] value:["+numbers2[i]+"]\t");
			System.out.println();
			System.out.print("int[]   numbers3 = new int[] { 1, 2, 3 };\n\t");
			for(int i=0; i<numbers3.length; i++) 
				System.out.print("index:["+i+"] value:["+numbers3[i]+"]\t");
			System.out.println();
			System.out.println();

			System.out.print("int[][] numbers4 = { { 1, 2 }, { 3, 4, 5 } };\n\t");
			for(int i=0; i<numbers4.length; i++) {
				System.out.print("index:["+i+"] value:["+numbers4[i]+"]\t");
			}
			System.out.println();
			System.out.println("int[][] numbers4 = { { 1, 2 }, { 3, 4, 5 } };");
			for(int i=0; i<numbers4.length; i++) {
				System.out.print("\t");
				for(int j=0; j<numbers4[i].length; j++) {
					System.out.print("index:["+i+"]["+j+"] value:["+numbers4[i][j]+"]\t");
				}
				System.out.println("\t");
			}
			System.out.println();
			
		}
		////
		System.out.println("Arrays.fill(numbers1, 1, 3, 99);");
		Arrays.fill(numbers1, 1, 3, 99); 
		// 输出
		System.out.print("int[]   numbers1 = new int[3];\n\t");
		for(int i=0; i<numbers1.length; i++) 
			System.out.print("index:["+i+"] value:["+numbers1[i]+"]\t");
		System.out.println();
	}
	static void excepteArray01(){
		int[] array = new int[3];
		Arrays.fill(array, 1, 4, 99);
	}
	static void excepteArray02(){	
		int[] array = new int[-1]; 
	}
	static void array2List(){
		String[] stringArray = {"foo", "bar", "baz"};
		List<String> stringList = Arrays.asList(stringArray); // Arrays$ArrayList
		// Using Arrays.asList()
		List<String> stringListArrays = new ArrayList<>(Arrays.asList(stringArray));

		// Using ArrayList.addAll()
		ArrayList<String> listAdd = new ArrayList<>();
		listAdd.addAll(Arrays.asList(stringArray));

		// Using Collections.addAll()
		ArrayList<String> listCollection = new ArrayList<>();
		Collections.addAll(listCollection, stringArray);
	}
	static void array2ListExcept(){
		String[] stringArray = {"foo", "bar", "baz"};
		List<String> stringList = Arrays.asList(stringArray);
		stringList.add("another");
	}
	


    /**
     * Object[] toArray()
     * <T> T[] toArray(T[] a)
     */
	static void collection2Array(){
		Set<String> set = new HashSet<String>();
		set.add("red");
		set.add("blue");
		// although set is a Set<String>, toArray() returns an Object[] not a String[]
		Object[] objectArray = set.toArray();
		
		// The array does not need to be created up front with the correct size.
		// Only the array type matters. (If the size is wrong, a new array will
		// be created with the same type.)
		String[] stringArray = set.toArray(new String[0]);  
		// If you supply an array of the same size as collection or bigger, it
		// will be populated with collection values and returned (new array
		// won't be allocated)
		String[] stringArray2 = set.toArray(new String[set.size()]);
	}
}
