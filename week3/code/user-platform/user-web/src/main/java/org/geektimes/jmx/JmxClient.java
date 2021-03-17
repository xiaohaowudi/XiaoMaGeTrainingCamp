package org.geektimes.jmx;

import javax.management.*;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;
import java.util.Arrays;

public class JmxClient {
    public static void main(String[] args) throws IOException, MalformedObjectNameException, AttributeNotFoundException, MBeanException, ReflectionException, InstanceNotFoundException {

        JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:9998/jmxrmi");
        JMXConnector jmxc = JMXConnectorFactory.connect(url, null);

        MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();

        ObjectName mbeanName = new ObjectName("serverInfoBean:name=serverInfo");
        String[] domains = mbsc.getDomains();
        System.out.println("doimains:");
        Arrays.stream(domains).forEach(System.out::println);

        System.out.println("bean count = " + mbsc.getMBeanCount());

        String name = (String)mbsc.getAttribute(mbeanName, "Name");
        String age = (String)mbsc.getAttribute(mbeanName, "Age");
        System.out.println("name = " + name + ", age = " + age);


        // 用代理进行RPC调用
        ServerInfoMBean proxy = MBeanServerInvocationHandler.newProxyInstance(mbsc, mbeanName, ServerInfoMBean.class, false);

        proxy.setName("Bond");
        proxy.setAge("123");

        proxy.helloWorld();
        proxy.helloWorld("this is a test");
        proxy.getTelephone();

        System.out.println(proxy.getName());
        System.out.println(proxy.getAge());

        mbsc.invoke(mbeanName, "helloWorld", new String[] {"this is a test"}, new String[] {"java.lang.String"});

    }


}
