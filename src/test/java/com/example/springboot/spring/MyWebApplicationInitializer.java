package com.example.springboot.spring;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

/**
 * @description: 实现java config第一步 实现spring的接口
 * @author: yewubin
 * @date: 2021/1/12 15:04
 * @version: v1.0
 */
    public class MyWebApplicationInitializer implements WebApplicationInitializer {

    /**
     * 传统的spring mvc 需要有一个web.xml 配置文件
     *
     * <web-app>
     *
     *     <listener>
     *         <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
     *     </listener>
     *
     *     <context-param>
     *         <param-name>contextConfigLocation</param-name>
     *         <param-value>/WEB-INF/app-context.xml</param-value>
     *     </context-param>
     *
     *     <servlet>
     *         <servlet-name>app</servlet-name>
     *         <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
     *         <init-param>
     *             <param-name>contextConfigLocation</param-name>
     *             <param-value></param-value>
     *         </init-param>
     *         <load-on-startup>1</load-on-startup>
     *     </servlet>
     *
     *     <servlet-mapping>
     *         <servlet-name>app</servlet-name>
     *         <url-pattern>/app/*</url-pattern>
     *     </servlet-mapping>
     *
     * </web-app>
     */

    /**
     * 使用JavaConfig的方式：
     * web容器会在启动的时候去掉用 onStartup() ServletContext web上下文对象
     * servlet 3.0版本以后，提出一个新规范SPI
     * spring 的项目中如果有某些方法需要在启动的时候被web容器（Tomcat）调用的话
     * 首先，在项目的根目录的META-INF/services/javax.servlet.ServletContainerInitializer目录下建立一个
     * org.springframework.web.SpringServletContainerInitializer
     *
     * @param servletContext
     */
    @Override
    public void onStartup(ServletContext servletContext) {
        // 用Java注解方式去初始化spring上下文环境
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        // spring 容器上下文对象
        // ClassPathXmlApplicationContext
        context.register(AppConfig.class);

        DispatcherServlet dispatcherServlet = new DispatcherServlet(context);

        ServletRegistration.Dynamic registration = servletContext.addServlet("app", dispatcherServlet);

        registration.setLoadOnStartup(1);
        registration.addMapping("/app/*");
    }
}
