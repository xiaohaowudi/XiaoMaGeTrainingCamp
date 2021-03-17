package org.geektimes.projects.user.web.listener;

import org.geektimes.context.ComponentContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.logging.Logger;

@Deprecated
public class TestingListener implements ServletContextListener {

    private Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("TestingListener running");
        printConfData(sce);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }


    private void printConfData(ServletContextEvent sce) {
        // 打印JNDI context.xml中的配置项
        ComponentContext context = ComponentContext.getInstance();
        Integer maxValue = context.getComponent("maxValue");
        logger.info("JNDI maxValue = " + maxValue);

        // 打印web.xml中的 context-param 配置
        ServletContext servletContext = sce.getServletContext();
        String val = servletContext.getInitParameter("application.name");
        logger.info("ServletContext application.name = " + val);
    }

}