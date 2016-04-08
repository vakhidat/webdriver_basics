package com.epam.at.web_driver_task.model.entity;

import java.util.List;

public class Mail {
    private List<String> recipients;
    private String mailSubject;
    private String mailMessage;

    public Mail(List<String> recipientMailbox, String mailSubject, String mailMessage) {
        this.recipients = recipientMailbox;
        this.mailSubject = mailSubject;
        this.mailMessage = mailMessage;
    }

    public Mail() {
    }

    public List<String> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<String> recipients) {
        this.recipients = recipients;
    }

    public String getMailSubject() {
        return mailSubject;
    }

    public void setMailSubject(String mailSubject) {
        this.mailSubject = mailSubject;
    }

    public String getMailMessage() {
        return mailMessage;
    }

    public void setMailMessage(String mailMessage) {
        this.mailMessage = mailMessage;
    }
}
