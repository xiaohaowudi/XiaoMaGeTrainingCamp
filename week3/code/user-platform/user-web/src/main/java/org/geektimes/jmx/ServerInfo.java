package org.geektimes.jmx;

public class ServerInfo implements ServerInfoMBean {

    private String name = "Jamse";
    private Integer age = 100;

    @Override
    public int getExecSqlCount() {
        System.out.println("getExecSqlCount is called!!!");
        return 100;
    }

    @Override
    public void printString(String fromJConsole) {
        System.out.println("str = " + fromJConsole);
    }

    @Override
    public String getName() {
        System.out.println("getName is called");
        return name;
    }

    @Override
    public void setName(String name) {
        System.out.println("setName is called");
        this.name = name;
    }

    @Override
    public String getAge() {
        System.out.println("getAge is called");
        return age.toString();
    }

    @Override
    public void setAge(String age) {
        System.out.println("setAge is called");
        this.age = Integer.parseInt(age);
    }

    @Override
    public void helloWorld() {
        System.out.println("Hello world");
    }


    @Override
    public void helloWorld(String str) {
        System.out.println("Hello world, " + str);
    }


    @Override
    public void getTelephone() {
        System.out.println("getTelephone is called");
    }
}
