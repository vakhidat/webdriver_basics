package com.epam.at.web_driver_task.ui.service;

import com.epam.at.web_driver_task.model.entity.Mail;
import com.epam.at.web_driver_task.ui.page.Inbox;
import com.epam.at.web_driver_task.ui.page.Mailbox;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;

@Slf4j
public class InboxManager extends BaseManager {

    public InboxManager(WebDriver driver) {
        super(driver);
    }

    public boolean composeNewMailAndReturnToInbox(Mail mail) {
        Mailbox mailbox = new Inbox(driver);
        mailbox.goToComposeNewEmailPage().writeMessageAndSaveItAsDraft(mail);

        log.info("Draft recipients: {}", mail.getRecipients());
        log.info("Draft subject: {}, Draft message: {}", mail.getMailSubject(), mail.getMailMessage().substring(0, 50));

        return getCurrentUrl().endsWith(Inbox.SUFFIX);
    }

    public boolean isCurrentUrlIsMailboxPage() {
        String currentUrl = getCurrentUrl();
        log.info("Current url is {}", currentUrl);
        return currentUrl.startsWith(Mailbox.YANDEX_MAILBOX_URL_FRAGMENT);
    }

    public MainPageManager logout() {
        Mailbox mailbox = new Inbox(driver);
        mailbox.logout();
        log.info("Logout perform");
        return new MainPageManager(driver);
    }
}
