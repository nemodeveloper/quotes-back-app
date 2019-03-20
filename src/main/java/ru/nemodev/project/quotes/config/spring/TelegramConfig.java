package ru.nemodev.project.quotes.config.spring;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.CronTask;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.telegram.telegrambots.ApiContextInitializer;
import ru.nemodev.project.quotes.config.TelegramProperties;
import ru.nemodev.project.quotes.telegram.bot.QuoteTelegramBot;
import ru.nemodev.project.quotes.telegram.bot.TelegramBotLoader;
import ru.nemodev.project.quotes.telegram.bot.query.handler.CallbackQueryHandler;
import ru.nemodev.project.quotes.telegram.bot.query.handler.TextMessageHandler;
import ru.nemodev.project.quotes.telegram.bot.query.parser.QueryParser;
import ru.nemodev.project.quotes.telegram.channel.QuoteChannelPublisher;

import java.io.IOException;
import java.util.Collections;

/**
 * created by sbrf-simanov-an on 21.11.2018 - 12:45
 */
@Configuration
@Profile("prod")
@EnableScheduling
public class TelegramConfig implements SchedulingConfigurer
{
    private final ServiceConfig serviceConfig;
    private final ObjectFactory<TextMessageHandler> textMessageHandler;
    private final ObjectFactory<CallbackQueryHandler> callbackQueryHandler;

    public TelegramConfig(ServiceConfig serviceConfig,
                          ObjectFactory<TextMessageHandler> textMessageHandler,
                          ObjectFactory<CallbackQueryHandler> callbackQueryHandler)
    {
        this.serviceConfig = serviceConfig;
        this.textMessageHandler = textMessageHandler;
        this.callbackQueryHandler = callbackQueryHandler;

        ApiContextInitializer.init();
    }

    @Bean
    public PropertiesFactoryBean telegramPropertiesFactory()
    {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setSingleton(true);
        propertiesFactoryBean.setLocations(
                new ClassPathResource("config/telegram.properties"));

        return propertiesFactoryBean;
    }

    @Bean
    public TelegramProperties telegramProperties()
    {
        try
        {
            return new TelegramProperties(telegramPropertiesFactory().getObject());
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Bean
    public QueryParser queryParser()
    {
        return new QueryParser();
    }

    @Bean
    @Scope("prototype")
    public TextMessageHandler textMessageHandler()
    {
        return new TextMessageHandler(
                serviceConfig.quoteServiceImpl(),
                serviceConfig.categoryServiceImpl(),
                serviceConfig.authorServiceImpl());
    }

    @Bean
    @Scope("prototype")
    public CallbackQueryHandler callbackQueryHandler()
    {
        return new CallbackQueryHandler(
                serviceConfig.quoteServiceImpl(),
                serviceConfig.categoryServiceImpl(),
                serviceConfig.authorServiceImpl());
    }

    @Bean
    public QuoteTelegramBot quoteTelegramBot()
    {
        return new QuoteTelegramBot(
                telegramProperties(),
                queryParser(),
                textMessageHandler,
                callbackQueryHandler);
    }

    @Bean(initMethod = "start", destroyMethod = "stop")
    public TelegramBotLoader telegramBotLoader()
    {
        return new TelegramBotLoader(Collections.singletonList(quoteTelegramBot()));
    }

    @Bean
    public QuoteChannelPublisher quoteChannelPublisher()
    {
        return new QuoteChannelPublisher(
                telegramProperties(),
                quoteTelegramBot(),
                serviceConfig.quoteServiceImpl()
        );
    }

    @Bean
    public CronTask quotePublishTask()
    {
        return new CronTask(() -> quoteChannelPublisher().publish(),
                new CronTrigger(telegramProperties().getQuotePublishCron()));
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar)
    {
        scheduledTaskRegistrar.addCronTask(quotePublishTask());
    }
}