package ru.nemodev.project.quotes.config;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Properties;

/**
 * created by NemoDev on 19.04.2018 - 0:23
 */
public class ApplicationConfig
{
    private static final String QUOTE_BOT_TOKEN_KEY = "quote.bot.token";
    private static final String QUOTE_BOT_USERNAME_KEY = "quote.bot.username";
    private static final String QUOTE_CHANNEL_ID = "quote.channel.id";

    private final String botToken;
    private final String botUsername;
    private final String quoteChannelId;

    @Autowired
    public ApplicationConfig(Properties environment)
    {
        this.botToken = environment.getProperty(QUOTE_BOT_TOKEN_KEY);
        this.botUsername = environment.getProperty(QUOTE_BOT_USERNAME_KEY);
        this.quoteChannelId = environment.getProperty(QUOTE_CHANNEL_ID);
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
}
