package org.geektimes.projects.user.logging;


import java.io.IOException;
import java.io.InputStream;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

// 代码方式修改日志级别，Java Logging 框架中的LogManager会读取logging.properties中的config选项，
// 发现需要加载一个UserWebLoggingConfiguration的实例
public class UserWebLoggingConfiguration {

    public UserWebLoggingConfiguration() throws Exception {
        System.out.println("UserWebLoggingConfiguration");

        Logger logger = Logger.getLogger("org.geektimes");

        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setEncoding("UTF-8");
        consoleHandler.setLevel(Level.WARNING);
        logger.addHandler(consoleHandler);
    }

    public static void main(String[] args) throws IOException {

        Logger logger = Logger.getLogger("org.geektimes");

        ClassLoader cl = Thread.currentThread().getContextClassLoader();

        try (InputStream in = cl.getResourceAsStream("META-INF/logging.properties")) {
            LogManager logManager = LogManager.getLogManager();
            logManager.readConfiguration(in);
        }

        logger.info("Hello, world info");       // 这里不会打印到console，因为上面配置了consoleHandler级别是warning
        logger.warning("Hello, world warning");
    }

}
