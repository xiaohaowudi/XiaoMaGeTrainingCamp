package org.geektimes.projects.user.web.listener;

import com.sun.jdmk.comm.HtmlAdaptorServer;
import lombok.SneakyThrows;
import org.geektimes.jmx.*;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.lang.management.ManagementFactory;
import java.rmi.registry.LocateRegistry;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class JmxInitializingListener implements ServletContextListener {
    @SneakyThrows
    @Override
    public void contextInitialized(ServletContextEvent sce) {

        System.out.println("Jmx Parameter Initializing!!!");
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();

        // 注册MBean例子
        ObjectName name = new ObjectName("serverInfoBean:name=serverInfo");
        server.registerMBean(new ServerInfo(), name);

        // 注册MXBean例子
        ObjectName queueSampleName = new ObjectName("serverInfoBean:type=QueueSampler");
        Queue<String> queue = new ArrayBlockingQueue<String>(10);
        queue.add("test data 1");
        queue.add("test data 2");
        queue.add("test data 3");
        QueueSamplerMXBean queueSamplerMXBean = new QueueSampler(queue);
        server.registerMBean(queueSamplerMXBean, queueSampleName);

        // 注册读取配置信息的MXBean
        ObjectName javaeeConfigName = new ObjectName("javaeeConfig:type=JavaEEConfig");
        JavaEEConfigQueryMXBean javaEEConfigQueryMXBean = new JavaEEConfigQuery();
        server.registerMBean(javaEEConfigQueryMXBean, javaeeConfigName);


        // 启动http协议适配器，如果不需要用Web客户端访问，不需要下面的代码
//        ObjectName adapterName = new ObjectName("HelloAgent:name=htmladapter,port=8082");
//        HtmlAdaptorServer adapter = new HtmlAdaptorServer();
//        server.registerMBean(adapter, adapterName);
//        adapter.start();

        try {
            LocateRegistry.createRegistry(9998);
            JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:9998/jmxrmi");
            JMXConnectorServer jcs = JMXConnectorServerFactory.newJMXConnectorServer(url, null, server);
            System.out.println("starting JMXConnectorServer");
            jcs.start();
            System.out.println("JMXConnectorServer started!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {


    }
}
