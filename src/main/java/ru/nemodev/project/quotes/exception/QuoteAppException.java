package ru.nemodev.project.quotes.exception;

/**
 * created by NemoDev on 19.04.2018 - 23:34
 */
public class QuoteAppException extends RuntimeException
{
    public QuoteAppException() {
    }

    public QuoteAppException(String message) {
        super(message);
    }

    public QuoteAppException(String message, Throwable cause) {
        super(message, cause);
    }

    public QuoteAppException(Throwable cause) {
        super(cause);
    }

    public QuoteAppException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
