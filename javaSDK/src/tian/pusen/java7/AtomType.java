package tian.pusen.java7;

public class AtomType {
	public static void main(String[] args){
		int i = 123_456;
		int b = 0B0001_0010; //12
		int o = 017; // 8+7
		int ox= 0x12ff;
		
		System.out.printf("i:%d\n",i);
		System.out.printf("b:0X%x\n",b);
		System.out.printf("o:0X%x\n",o);
		System.out.printf("0x:0X%x\n",ox);
	}
}
