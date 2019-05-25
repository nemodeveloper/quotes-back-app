package ru.nemodev.project.quotes.config.spring.database;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * created by sbrf-simanov-an on 21.11.2018 - 18:11
 */
@Configuration
@Profile("prod")
public class ProdDataConfigHolder extends AbstractDataConfigHolder { }
