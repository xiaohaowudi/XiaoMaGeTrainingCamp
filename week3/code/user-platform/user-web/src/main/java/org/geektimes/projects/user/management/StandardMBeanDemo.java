package org.geektimes.projects.user.management;

import org.geektimes.projects.user.domain.User;

import javax.management.MBeanInfo;
import javax.management.StandardMBean;

public class StandardMBeanDemo {

    public static void main(String[] args) throws Exception {
        // 生成一个动态MBean
        StandardMBean bean = new StandardMBean(new UserManagement(new User()), UserManagementMBean.class);
        MBeanInfo info  = bean.getMBeanInfo();
        System.out.println(info);
    }

}
