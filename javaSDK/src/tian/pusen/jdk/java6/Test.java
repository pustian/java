package tian.pusen.java6;

public final class Test {
	static boolean foo(char c){
		System.out.print(c);
		return true;
	}
	public static void main(String[] args){
		int i=0;
		for(foo('A'); foo('B')&&(i<2); foo('C')) {
			i++;
			foo('D');
		}
	}
}
