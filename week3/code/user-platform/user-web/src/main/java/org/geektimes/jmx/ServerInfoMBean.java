package org.geektimes.jmx;

public interface ServerInfoMBean {
    int getExecSqlCount();
    void printString(String s);

    public String getName();

    public void setName(String name);

    public String getAge();

    public void setAge(String age);

    public void helloWorld();

    public void helloWorld(String str);

    public void getTelephone();

}
