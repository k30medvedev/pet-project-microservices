package org.app.soft.messageservice.service.exceptions;

public class MailRequestCannotBeNullException extends RuntimeException {
    public MailRequestCannotBeNullException() {
        super("MailRequest cannot be null");
    }
}
