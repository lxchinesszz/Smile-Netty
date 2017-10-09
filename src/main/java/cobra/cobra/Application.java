package cobra.cobra;

import cobra.cobra.event.EmailEvent;
import cobra.cobra.jmx.ThreadPoolMonitorProvider;
import cobra.cobra.jmx.ThreadPoolStatus;
import cobra.cobra.resolver.MessageTask;
import com.google.common.util.concurrent.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.management.InstanceNotFoundException;
import javax.management.MBeanException;
import javax.management.MalformedObjectNameException;
import javax.management.ReflectionException;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Package: cobra.cobra
 * @Description: ${todo}
 * @author: liuxin
 * @date: 2017/9/20 下午7:04
 */
@SpringBootApplication
public class Application {
    private static final Timer timer = new Timer("ThreadPoolMonitor", true);
    private static long monitorDelay = 100L;
    private static long monitorPeriod = 300L;

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(Application.class, args);
        EmailEvent emailEvent=new EmailEvent("hello","lxchinesszz@163.com","this is a email text");
        applicationContext.publishEvent(emailEvent);
    }

    public static void test() {

        ThreadPoolExecutor executor = (ThreadPoolExecutor) getExecutorWithJmx(130);
        /**
         * 注册监听线程
         */
        ListeningExecutorService threadPoolExecutor = MoreExecutors.listeningDecorator(executor);
        /**
         * 返回异步结果
         */
        ListenableFuture<Boolean> listenableFuture = threadPoolExecutor.submit(new MessageTask(1));
        /**
         * 添加监听处理异步结果
         */
        Futures.addCallback(listenableFuture, new FutureCallback<Boolean>() {
            public void onSuccess(Boolean result) {
                System.out.println("处理成功");
            }

            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        }, threadPoolExecutor);


    }


    /**
     * 做了一个定时任务，
     * 每秒刷新一次，状态，给JMX调用
     *
     * @param threads
     * @return
     */
    public static Executor getExecutorWithJmx(int threads) {
        /**
         * 定义连接池
         */
        final ThreadPoolExecutor executor = new ThreadPoolExecutor(threads, 400, 0, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(),
                new ThreadPoolExecutor.AbortPolicy());
        timer.scheduleAtFixedRate(new TimerTask() {

            public void run() {
                ThreadPoolStatus status = new ThreadPoolStatus();
                status.setPoolSize(executor.getPoolSize());
                status.setActiveCount(executor.getActiveCount());
                status.setCorePoolSize(executor.getCorePoolSize());
                status.setMaximumPoolSize(executor.getMaximumPoolSize());
                status.setLargestPoolSize(executor.getLargestPoolSize());
                status.setTaskCount(executor.getTaskCount());
                status.setCompletedTaskCount(executor.getCompletedTaskCount());

                try {
                    ThreadPoolMonitorProvider.monitor(status);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (MalformedObjectNameException e) {
                    e.printStackTrace();
                } catch (ReflectionException e) {
                    e.printStackTrace();
                } catch (MBeanException e) {
                    e.printStackTrace();
                } catch (InstanceNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }, monitorDelay, monitorDelay);
        return executor;
    }
}

