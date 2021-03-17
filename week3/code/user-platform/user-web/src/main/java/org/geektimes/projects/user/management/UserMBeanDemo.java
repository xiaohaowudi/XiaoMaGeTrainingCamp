package org.geektimes.projects.user.management;

import org.geektimes.projects.user.domain.User;

import javax.management.*;
import java.lang.management.ManagementFactory;

public class UserMBeanDemo {

    public static void main(String[] args) throws MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException, InterruptedException {
        System.out.println("Jmx Parameter Initializing!!!");
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("package org.geektimes.projects.user.management:type=User");

        User user = new User();
        server.registerMBean(new UserManagement(user), name);

        while (true) {
            Thread.sleep(1000);
            System.out.println(user);

        }


    }

}
