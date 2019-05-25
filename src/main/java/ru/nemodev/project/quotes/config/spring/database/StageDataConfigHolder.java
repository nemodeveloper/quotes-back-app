package ru.nemodev.project.quotes.config.spring.database;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("stage")
public class StageDataConfigHolder extends AbstractDataConfigHolder { }
