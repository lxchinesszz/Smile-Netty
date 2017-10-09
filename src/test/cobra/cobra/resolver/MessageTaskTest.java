package cobra.cobra.resolver;

import cobra.cobra.jmx.ThreadPoolMonitorProvider;
import cobra.cobra.jmx.ThreadPoolStatus;
import com.google.common.util.concurrent.*;
import org.springframework.util.StringUtils;

import javax.management.InstanceNotFoundException;
import javax.management.MBeanException;
import javax.management.MalformedObjectNameException;
import javax.management.ReflectionException;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;

/**
 * @Package: cobra.cobra.resolver
 * @Description: ${todo}
 * @author: liuxin
 * @date: 2017/9/20 下午6:05
 */
public class MessageTaskTest {

    public static void main(String[] args) {
//        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
//        ScheduledFuture<?> schedule = scheduledExecutorService.schedule(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("hello");
//            }
//        }, 2000L, TimeUnit.MILLISECONDS);


        Map<String,String> map=new LinkedHashMap<>();
        String test = map.get("test");
        System.out.println(test==null);
        System.out.println(org.apache.commons.lang.StringUtils.isWhitespace(" "));//true
        System.out.println(org.apache.commons.lang.StringUtils.isWhitespace(null));//false
    }

}
