package cobra.cobra.resolver;

/**
 * @Package: cobra.cobra.resolver
 * @Description: ${todo}
 * @author: liuxin
 * @date: 2017/9/22 下午3:20
 */
public class test2 {
    public static void main(String[] args) throws Exception {
//
        /**
         * n:99
         静态代码块
         */
//        Class<?> aClass = Class.forName("cobra.cobra.resolver.Test");

        /**
         *a:0
         n:99
         代码块
         1: i=   n=99
         */
        new Test("");

        /**
         * 当运行main方法时候
         * 1.加载到内存
         * 2.在加载字节码时候实例化，执行，非静态成员变量->非静态代码块->非静态构造
         */
    }
}
