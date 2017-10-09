package cobra.cobra.resolver;

/**
 * @Package: cobra.cobra.resolver
 * @Description: ${todo}
 * @author: liuxin
 * @date: 2017/9/22 上午9:27
 */
public class Test {
    private static int k = 0;
    public static Test t1 = new Test("t1");
    public static Test t2 = new Test("t2");
    public static int i = print("1");
//    private int a = 0;
//    public int j = print("j");
    public static int n = 99;

    static {
        print("静态代码块");
    }

    {
        print("代码块");
    }



    public Test(String str) {
        System.out.println("构造器："+(++k) + ":" + str + " i="  + "   n=" + n);
        ++i;
        ++n;
    }

    public static int print(String str) {
        System.out.println((++k) + ":" + str + " i=" + i + "   n=" + n);
        ++n;
        return ++i;

    }

    public static void main(String[] args) {

    }

}
