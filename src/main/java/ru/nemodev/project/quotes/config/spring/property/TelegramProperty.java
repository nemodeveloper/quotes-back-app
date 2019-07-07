package ru.nemodev.project.quotes.config.spring.property;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;


/**
 * created by NemoDev on 19.04.2018 - 0:23
 */
@Configuration
@Profile("prod")
@PropertySource("classpath:config/telegram.properties")
public class TelegramProperty
{
    @Value("${tg.quote.bot.token}")
    private String botToken;
    @Value("${tg.quote.bot.username}")
    private String botUsername;
    @Value("${tg.quote.channel.id}")
    private String quoteChannelId;
    @Value("${tg.quote.scheduler.channel.publish.cron}")
    private String quotePublishCron;

    public String getBotToken() {
        return botToken;
    }

    public String getBotUsername() {
        return botUsername;
    }

    public String getQuoteChannelId() {
        return quoteChannelId;
    }

    public String getQuotePublishCron()
    {
        return quotePublishCron;
    }
}
