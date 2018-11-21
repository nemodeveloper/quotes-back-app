package ru.nemodev.project.quotes.config.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * created by sbrf-simanov-an on 20.11.2018 - 15:35
 */
@EnableWebMvc
@Configuration
@ComponentScan(basePackages = { "ru.nemodev.project.quotes.api" })
public class WebConfig implements WebMvcConfigurer
{ }
