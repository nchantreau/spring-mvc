package fr.training;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import fr.training.jsp.ExampleOne;

public class WebappInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext container) {
        // Configure ContextLoaderListener to use AnnotationConfigWebApplicationContext instead of the default XmlWebApplicationContext
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        // Register configuration class
        rootContext.register(WebappConfigJava.class); // or configure locations using setConfigLocation method
        // Bootstrap the root application context as usual using ContextLoaderListener
        container.addListener(new ContextLoaderListener(rootContext));

        // Declare a Spring MVC DispatcherServlet as usual
        ServletRegistration.Dynamic dispatcher = container.addServlet("mvcDispatcher", new DispatcherServlet(rootContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");

        // Classic JSP Servlet
        ServletRegistration.Dynamic exOne = container.addServlet("ExampleOne", ExampleOne.class);
        exOne.addMapping("/jsp/ExampleOne");

    }

}