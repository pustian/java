
//初始化顺序
// 1，父类静态变量
// 2, 父类静态代码块
// 3, 子类静态变量
// 4, 子类静态代码块
// 5, 父类非静态变量
// 6, 父类非静态代码块
// 7, 父类构造器
// 8, 子类非静态变量
// 9, 子类非静态代码块
// 10, 子类构造器

public class OddHello{
    
    static {
        System.out.println("static block hello world");
    }

    // public static void main(String[] args) {
    //     System.out.println("");
    // }

}