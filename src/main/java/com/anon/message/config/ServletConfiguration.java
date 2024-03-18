package com.anon.message.config;

//import com.vaadin.flow.server.VaadinServlet;
//import com.vaadin.flow.spring.SpringServlet;
//import jakarta.servlet.ServletContext;
//import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServletConfiguration {

    @Autowired
    ApplicationContext context;


//    @Bean
//    public ServletRegistrationBean<SpringServlet> vaadinServlet() {
//        System.out.println("bean definition count ==> " + context.getBeanDefinitionCount());
//        SpringServlet vaadinServlet = new SpringServlet(context, false);
//        ServletRegistrationBean<SpringServlet> registrationBean = new ServletRegistrationBean<>(vaadinServlet, "/vaadin/*");
//        registrationBean.setLoadOnStartup(1);
//        registrationBean.setAsyncSupported(true);
//        registrationBean.addInitParameter("contextConfigLocation", "com.alibou.security.config.ApplicationConfig");
//
//        // Optional: Set other init parameters if needed
//
//        return registrationBean;
//    }
}
