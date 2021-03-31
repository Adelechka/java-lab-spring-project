package ru.itis.javalab.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import freemarker.template.TemplateExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassRelativeResourceLoader;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.ui.freemarker.SpringTemplateLoader;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@EnableWebMvc
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "ru.itis.javalab.repositories")
@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = "ru.itis.javalab")
@EnableJdbcHttpSession
public class ApplicationConfig implements WebMvcConfigurer {

    @Autowired
    private Environment environment;

    @Bean
    public DataSource dataSource() {
        return new HikariDataSource(hikariConfig());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ExecutorService executorService() {
        return Executors.newCachedThreadPool();
    }

    @Bean
    public HikariConfig hikariConfig() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(environment.getProperty("db.url"));
        hikariConfig.setUsername(environment.getProperty("db.username"));
        hikariConfig.setPassword(environment.getProperty("db.password"));
        hikariConfig.setMaximumPoolSize(Integer.parseInt(environment.getProperty("db.hikari.max-pool-size")));
        hikariConfig.setDriverClassName(environment.getProperty("db.driver.classname"));
        return hikariConfig;
    }

    @Bean
    public FreeMarkerViewResolver freeMarkerViewResolver() {
        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
        resolver.setPrefix("");
        resolver.setSuffix(".ftlh");
        resolver.setContentType("text/html;charset=UTF-8");
        return resolver;
    }

    @Bean
    public freemarker.template.Configuration configuration() {
        freemarker.template.Configuration configuration = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_30);
        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateLoader(
                new SpringTemplateLoader(new ClassRelativeResourceLoader(this.getClass()),
                        "/"));
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        return configuration;
    }

    @Bean
    public FreeMarkerConfigurer freeMarkerConfig() {
        FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
        configurer.setTemplateLoaderPath("/WEB-INF/ftl/");
        return configurer;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setDatabase(Database.POSTGRESQL);
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource());
        entityManagerFactory.setPackagesToScan("ru.itis.javalab.models");
        entityManagerFactory.setJpaVendorAdapter(hibernateJpaVendorAdapter);
        entityManagerFactory.setJpaProperties(additionalProperties());
        return entityManagerFactory;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

    private Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL95Dialect");
        properties.setProperty("hibernate.show_sql", "true");

        properties.setProperty("hibernate.hbm2ddl.import_files_sql_extractor", "org.hibernate.tool.hbm2ddl.MultipleLinesSqlCommandExtractor");
        properties.setProperty("hibernate.connection.charSet", "UTF8");
        properties.setProperty("hibernate.connection.characterEncoding", "UTF8");
        properties.setProperty("hibernate.hbm2ddl.import_files", "schema.sql");
        properties.setProperty("connection.autocommit", "true");

        return properties;
    }

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setDefaultEncoding("UTF-8");

        mailSender.setHost(environment.getProperty("spring.mail.host"));
        mailSender.setPort(Integer.parseInt(environment.getProperty("spring.mail.port")));

        mailSender.setUsername(environment.getProperty("spring.mail.username"));
        mailSender.setPassword(environment.getProperty("spring.mail.password"));


        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", environment.getProperty("spring.mail.properties.mail.smtp.ssl.trust"));
        props.put("mail.smtp.auth", environment.getProperty("spring.mail.properties.mail.smtp.allow8bitmime"));
        props.put("mail.smtp.starttls.enable", environment.getProperty("spring.mail.properties.mail.smtp.starttls.enable"));
        props.put("mail.debug", environment.getProperty("spring.mail.properties.mail.debug"));

        return mailSender;
    }

}
