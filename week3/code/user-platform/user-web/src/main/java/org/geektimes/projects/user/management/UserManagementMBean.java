package org.geektimes.projects.user.management;

// MBean 接口描述
public interface UserManagementMBean {

    // MBeanAttribute 属性列表
    public Long getId();

    public String getName();

    public String getPassword();

    public String getEmail();

    public String getPhoneNumber();

    public void setId(Long id);

    public void setName(String name);

    public void setPassword(String password);

    public void setEmail(String email);

    public void setPhoneNumber(String phoneNumber);

    // MBeanAttribute 操作列表
    public String toString();
}
