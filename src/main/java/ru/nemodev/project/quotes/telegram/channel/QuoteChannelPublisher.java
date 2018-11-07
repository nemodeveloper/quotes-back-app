package ru.nemodev.project.quotes.telegram.channel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import ru.nemodev.project.quotes.config.ApplicationConfig;
import ru.nemodev.project.quotes.service.quote.QuoteService;
import ru.nemodev.project.quotes.telegram.bot.QuoteTelegramBot;
import ru.nemodev.project.quotes.utils.QuoteUtils;


/**
 * created by NemoDev on 18.04.2018 - 23:58
 */
public class QuoteChannelPublisher
{
    private static final Logger LOGGER = LoggerFactory.getLogger(QuoteChannelPublisher.class);

    private final ApplicationConfig applicationConfig;
    private final QuoteTelegramBot quoteTelegramBot;
    private final QuoteService quoteService;

    @Autowired
    public QuoteChannelPublisher(ApplicationConfig applicationConfig,
                                 QuoteTelegramBot quoteTelegramBot,
                                 QuoteService quoteService)
    {
        this.applicationConfig = applicationConfig;
        this.quoteTelegramBot = quoteTelegramBot;
        this.quoteService = quoteService;
    }

    public void publish()
    {
        LOGGER.info("Отправка цитаты в канал телеграма по расписанию...");

        try
        {
            String text = QuoteUtils.getQuoteTextForShare(quoteService.getRandom(1L).get(0));
            quoteTelegramBot.sendBotMessage(buildSendMessage(text));
            LOGGER.info("Отправка цитаты в канал телеграма по расписанию завершена!");
        }
        catch (Exception e)
        {
            LOGGER.error("Ошибка отправки цитаты в канал телеграм по расписанию!", e);
        }
    }

    private SendMessage buildSendMessage(String text)
    {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(applicationConfig.getQuoteChannelId());
        sendMessage.enableMarkdown(true);
        sendMessage.setText(text);

        return sendMessage;
    }
}
