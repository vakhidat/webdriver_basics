package com.epam.at.web_driver_task.factory;


import com.epam.at.web_driver_task.business_object.Mail;

import java.util.List;

public class MailFactory {
    public static Mail createMailFromSubject(String subject) {
        Mail mail = new Mail();
        mail.setMailSubject(subject);
        return mail;
    }

    public static Mail createMailFromMessage(String message) {
        Mail mail = new Mail();
        mail.setMailMessage(message);
        return mail;
    }

    public static Mail createMailFromRecipients(List<String> recipients) {
        Mail mail = new Mail();
        mail.setRecipients(recipients);
        return mail;
    }
}
