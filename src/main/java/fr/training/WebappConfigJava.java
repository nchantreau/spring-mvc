package fr.training;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceResourceBundle;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import fr.training.spring.controller.OldStyleController;

/**
 * Configuration can be done with XML file
 */
@EnableWebMvc
@Configuration
@ComponentScan({ "fr.training.spring, fr.training.jstl.controllers" })
public class WebappConfigJava implements WebMvcConfigurer {


    public WebappConfigJava() {
        super();
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        // Content strategy using path extension
        Map<String, MediaType> mediaTypes = new HashMap<>();
        mediaTypes.put("json", MediaType.APPLICATION_JSON);
        mediaTypes.put("xml", MediaType.APPLICATION_XML);

        configurer.favorPathExtension(false)
                .favorParameter(true)
                .parameterName("mediaType")
                .ignoreAcceptHeader(true)
                .defaultContentType(MediaType.APPLICATION_JSON)
                .useJaf(false)
                .mediaTypes(mediaTypes);
    }

    @Override
    public void addViewControllers(final ViewControllerRegistry registry) {
        // Add specific view controller
        registry.addViewController("/sample");
    }

    @Bean
    public ViewResolver viewResolver() {
        final InternalResourceViewResolver bean = new InternalResourceViewResolver();
        // To resolve the view’s name “welcome” in the following way:
        // prefix + view name + suffix = /WEB-INF/view/welcome.jsp
        bean.setViewClass(JstlView.class);
        bean.setPrefix("/WEB-INF/view/");
        bean.setSuffix(".jsp");

        return bean;
    }

    // Old style controller (< Spring 2.5)
    @Bean("/oldStyleCtrl*.html")
    public OldStyleController oldStyleController() {
        return new OldStyleController();
    }

    // Mapping by SimpleUrlHandlerMapping (using properties)
    @Bean
    public SimpleUrlHandlerMapping sampleServletMapping() {
        SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
        mapping.setOrder(2);

        // Mapping can use AntPathMatcher or not
        Properties urlProperties = new Properties();
        // Mapping to the controller WelcomeController called welcomeController by default
        urlProperties.put("/welcome*", "welcomeController");
        // Mapping to the controller HelloController called myHelloController
        urlProperties.put("/hello*", "myHelloController");

        mapping.setMappings(urlProperties);

        return mapping;
    }

    @Bean
    public MessageSource messageSource() {
        final ResourceBundleMessageSource ms = new ResourceBundleMessageSource();
        ms.setBasenames("messages");
        return ms;
    }

    @Bean
    public ResourceBundle getBeanResourceBundle() {
        final Locale locale = Locale.getDefault();
        return new MessageSourceResourceBundle(messageSource(), locale);
    }
}