package ru.nemodev.project.quotes;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import ru.nemodev.project.quotes.config.spring.ApplicationConfig;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
@EnableAdminServer
@ComponentScan(basePackageClasses = {ApplicationConfig.class})
public class Application
{
    public static void main(String[] args)
    {
        SpringApplication.run(Application.class, args);
    }
}

