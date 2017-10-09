package cobra.cobra.jmx;

import org.springframework.jmx.support.ConnectorServerFactoryBean;
import org.springframework.jmx.support.MBeanServerConnectionFactoryBean;
import org.springframework.jmx.support.MBeanServerFactoryBean;
import org.springframework.remoting.rmi.RmiRegistryFactoryBean;
import java.io.IOException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.EnableMBeanExport;
import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import javax.management.MBeanException;
import javax.management.InstanceNotFoundException;

/**
 * @author tangjie<https://github.com/tang-jie>
 * @filename:ThreadPoolMonitorProvider.java
 * @description:ThreadPoolMonitorProvider功能模块
 * @blogs http://www.cnblogs.com/jietang/
 * @since 2016/10/13
 */

@Configuration
@EnableMBeanExport
public class ThreadPoolMonitorProvider {
    public static final String DELIMITER = ":";
    public static final String JMX_POOL_SIZE_METHOD = "setPoolSize";
    public static final String JMX_ACTIVE_COUNT_METHOD = "setActiveCount";
    public static final String JMX_CORE_POOL_SIZE_METHOD = "setCorePoolSize";
    public static final String JMX_MAXIMUM_POOL_SIZE_METHOD = "setMaximumPoolSize";
    public static final String JMX_LARGEST_POOL_SIZE_METHOD = "setLargestPoolSize";
    public static final String JMX_TASK_COUNT_METHOD = "setTaskCount";
    public static final String JMX_COMPLETED_TASK_COUNT_METHOD = "setCompletedTaskCount";
    public static String url;

    @Bean
    public ThreadPoolStatus threadPoolStatus() {
        return new ThreadPoolStatus();
    }

    @Bean
    public MBeanServerFactoryBean mbeanServer() {
        return new MBeanServerFactoryBean();
    }

    @Bean
    public RmiRegistryFactoryBean registry() {
        return new RmiRegistryFactoryBean();
    }

    @Bean
    @DependsOn("registry")
    public ConnectorServerFactoryBean connectorServer() throws MalformedObjectNameException {
        String ipAddr = "127.0.0.1";
        url = "service:jmx:rmi://" + ipAddr + "/jndi/rmi://" + ipAddr + ":1099/nettyrpcstatus";
        System.out.println("NettyRPC JMX MonitorURL : [" + url + "]");
        ConnectorServerFactoryBean connectorServerFactoryBean = new ConnectorServerFactoryBean();
        connectorServerFactoryBean.setObjectName("connector:name=rmi");
        connectorServerFactoryBean.setServiceUrl(url);
        return connectorServerFactoryBean;
    }

    /**
     * 通过jmx调用
     * @param status
     * @throws IOException
     * @throws MalformedObjectNameException
     * @throws ReflectionException
     * @throws MBeanException
     * @throws InstanceNotFoundException
     */
    public static void monitor(ThreadPoolStatus status) throws IOException, MalformedObjectNameException, ReflectionException, MBeanException, InstanceNotFoundException {
        MBeanServerConnectionFactoryBean mBeanServerConnectionFactoryBean = new MBeanServerConnectionFactoryBean();
        mBeanServerConnectionFactoryBean.setServiceUrl(url);
        mBeanServerConnectionFactoryBean.afterPropertiesSet();
        MBeanServerConnection connection = mBeanServerConnectionFactoryBean.getObject();
        ObjectName objectName = new ObjectName("cobra.cobra.jmx:name=threadPoolStatus,type=ThreadPoolStatus");
        connection.invoke(objectName, JMX_POOL_SIZE_METHOD, new Object[]{status.getPoolSize()}, new String[]{int.class.getName()});
        connection.invoke(objectName, JMX_ACTIVE_COUNT_METHOD, new Object[]{status.getActiveCount()}, new String[]{int.class.getName()});
        connection.invoke(objectName, JMX_CORE_POOL_SIZE_METHOD, new Object[]{status.getCorePoolSize()}, new String[]{int.class.getName()});
        connection.invoke(objectName, JMX_MAXIMUM_POOL_SIZE_METHOD, new Object[]{status.getMaximumPoolSize()}, new String[]{int.class.getName()});
        connection.invoke(objectName, JMX_LARGEST_POOL_SIZE_METHOD, new Object[]{status.getLargestPoolSize()}, new String[]{int.class.getName()});
        connection.invoke(objectName, JMX_TASK_COUNT_METHOD, new Object[]{status.getTaskCount()}, new String[]{long.class.getName()});
        connection.invoke(objectName, JMX_COMPLETED_TASK_COUNT_METHOD, new Object[]{status.getCompletedTaskCount()}, new String[]{long.class.getName()});
    }
}

