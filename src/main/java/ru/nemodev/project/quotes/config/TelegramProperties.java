package ru.nemodev.project.quotes.config;


import java.util.Properties;

/**
 * created by NemoDev on 19.04.2018 - 0:23
 */
public class TelegramProperties
{
    private static final String QUOTE_BOT_TOKEN_KEY = "quote.bot.token";
    private static final String QUOTE_BOT_USERNAME_KEY = "quote.bot.username";
    private static final String QUOTE_CHANNEL_ID = "quote.channel.id";
    private static final String QUOTE_PUBLISH_CRON = "quote.scheduler.channel.publish.cron";

    private final String botToken;
    private final String botUsername;
    private final String quoteChannelId;
    private final String quotePublishCron;

    public TelegramProperties(Properties environment)
    {
        this.botToken = environment.getProperty(QUOTE_BOT_TOKEN_KEY);
        this.botUsername = environment.getProperty(QUOTE_BOT_USERNAME_KEY);
        this.quoteChannelId = environment.getProperty(QUOTE_CHANNEL_ID);
        this.quotePublishCron = environment.getProperty(QUOTE_PUBLISH_CRON);
    }

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
