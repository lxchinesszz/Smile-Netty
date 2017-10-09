package cobra.cobra.resolver;


import java.util.concurrent.Callable;

/**
 * @Package: cobra.cobra.resolver
 * @Description: 异步任务
 * @author: liuxin
 * @date: 2017/9/20 下午5:36
 */
public class MessageTask implements Callable<Boolean> {
    private volatile int flag;

    public MessageTask(int flag) {
        this.flag = flag;
    }

    public Boolean call() throws Exception {
        Thread.sleep(2000L);
        if (flag % 2 == 0) {
            return true;
        }
        return false;
    }
}
