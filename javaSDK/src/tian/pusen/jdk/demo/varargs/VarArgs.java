/**
 * 
 */
package javaABC.demo.varargs;

/**
 * <p> Title:VarArgs </p>
 * <p> Description:       </p>
 * <p> Company: Masapay   </p>
 * @author: tianpusen
 * @Date  : 2016年10月26日 上午10:00:41
 */
//Rules for varargs :
//Varargs must be the last argument.
//There can be only one Varargs in the method.
public final class VarArgs {
	// this method will print the entire contents of the parameter passed in
    void printVarArgArray(int... x) {
        for (int i = 0; i < x.length; i++) {
            System.out.print(x[i] + ",");
        }
    }
    static void doSomething(String... strings){
    	if(strings != null) {
            for (String s : strings){
                System.out.print(s+",");
            }
    	}
    }
    
    public static void main(String args[]) {
        VarArgs obj = new VarArgs();
        //Using an array:
        int[] testArray = new int[]{10, 20};
        obj.printVarArgArray(testArray); 
       
        System.out.println(" ");
        //Using a sequence of arguments
        obj.printVarArgArray(5, 6, 5, 8, 6, 31);
        
//        System.out.println("String[] strings = {values}");
        String[] strings = {"Spring","Summer","Autumn","Winner"};
        doSomething(strings);
        
//        System.out.println("String[] strings2 = null");
        String[] strings2 = null;
        doSomething(strings2);
        
//        System.out.println("String[] strings3 = {}");
        String[] strings3 = {};
        doSomething(strings3);
    }
}
