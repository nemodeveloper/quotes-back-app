package ru.nemodev.project.quotes.telegram.bot.query.handler;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Chat;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import ru.nemodev.project.quotes.entity.Quote;
import ru.nemodev.project.quotes.service.author.AuthorService;
import ru.nemodev.project.quotes.service.category.CategoryService;
import ru.nemodev.project.quotes.service.quote.QuoteService;
import ru.nemodev.project.quotes.telegram.bot.query.info.MessageQueryInfo;
import ru.nemodev.project.quotes.telegram.bot.query.info.QueryType;
import ru.nemodev.project.quotes.utils.QuoteUtils;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * created by NemoDev on 14.03.2018 - 23:28
 */
public class TextMessageHandler extends AbstractQueryHandler<MessageQueryInfo, SendMessage>
{
    private static final Logger LOGGER = LoggerFactory.getLogger(TextMessageHandler.class);

    private static final String START_MESSAGE_TEXT = "Поиск цитат";

    private static final ReplyKeyboardMarkup simpleKeyboard = buildSimpleKeyboard();
    private static ReplyKeyboardMarkup buildSimpleKeyboard()
    {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        List<KeyboardRow> keyboardRows = new LinkedList<>();
        replyKeyboardMarkup.setKeyboard(keyboardRows);

        KeyboardRow randomQuoteRow = new KeyboardRow();
        randomQuoteRow.add("Случайная");
        keyboardRows.add(randomQuoteRow);

        return replyKeyboardMarkup;
    }

    private static final InlineKeyboardMarkup inlineKeyboard = buildInlineKeyboard();
    private static InlineKeyboardMarkup buildInlineKeyboard()
    {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> buttonGrid = new LinkedList<>();
        inlineKeyboardMarkup.setKeyboard(buttonGrid);

        List<InlineKeyboardButton> randomQuoteOption = new LinkedList<>();
        buttonGrid.add(randomQuoteOption);

        InlineKeyboardButton randomQuoteButton = new InlineKeyboardButton("Случайная");
        randomQuoteButton.setCallbackData("random");
        randomQuoteOption.add(randomQuoteButton);

        return inlineKeyboardMarkup;
    }

    public TextMessageHandler(QuoteService quoteService, CategoryService categoryService, AuthorService authorService)
    {
        super(quoteService, categoryService, authorService);
    }

    @Override
    public SendMessage handle()
    {
        List<Quote> quotes;
        QueryType queryType = queryInfo.getQueryType();

        if (QueryType.RANDOM == queryType)
            quotes = quoteService.getRandom(1L);
        else if (QueryType.START == queryType)
            return getKeyboard(START_MESSAGE_TEXT);
        else
            quotes = Collections.emptyList();

        if (CollectionUtils.isEmpty(quotes))
            return buildBaseSendMessage(QUOTE_NOT_FOUND);

        return buildBaseSendMessage(QuoteUtils.getQuoteTextForShare(quotes.get(0)));
    }

    private SendMessage getKeyboard(String text)
    {
        final Chat chat = queryInfo.getUpdate().getChat();

        SendMessage sendMessage = buildBaseSendMessage(text);
        sendMessage.enableMarkdown(true);

        if (chat.isUserChat())
            sendMessage.setReplyMarkup(simpleKeyboard);
        else if (chat.isGroupChat() || chat.isSuperGroupChat())
            sendMessage.setReplyMarkup(inlineKeyboard);
        else
        {
            LOGGER.warn("Бот получил сообщение на обработку, но не сумел построить ответ для заданного типа чата!");
            return null;
        }

        return sendMessage;
    }
}
