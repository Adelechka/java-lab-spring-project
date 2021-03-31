package ru.itis.javalab.config;

import lombok.SneakyThrows;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.ResourcePropertySource;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import ru.itis.javalab.security.config.SecurityConfig;
import ru.itis.javalab.security.config.SessionConfig;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.io.IOException;


public class ApplicationInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext springWebContext = new AnnotationConfigWebApplicationContext();

        try {
            PropertySource propertySource = new ResourcePropertySource("classpath:application.properties");
            springWebContext.getEnvironment().setActiveProfiles((String) propertySource.getProperty("spring.profile"));
        } catch (IOException e) {
           throw new IllegalArgumentException(e);
        }

        springWebContext.register(ApplicationConfig.class);
        springWebContext.register(SecurityConfig.class);
        servletContext.addListener(new ContextLoaderListener(springWebContext));

        ServletRegistration.Dynamic dispatcherServlet =
                servletContext.addServlet("dispatcher", new DispatcherServlet(springWebContext));
        dispatcherServlet.setLoadOnStartup(1);
        dispatcherServlet.addMapping("/");

        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);

        FilterRegistration.Dynamic filterRegistration = servletContext
                .addFilter("characterEncodingFilter", characterEncodingFilter);
        filterRegistration.addMappingForUrlPatterns(null, false, "/*");

    }
}
