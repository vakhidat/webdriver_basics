package com.epam.at.web_driver_task.ui.service;

import com.epam.at.web_driver_task.model.entity.Mail;
import com.epam.at.web_driver_task.ui.page.Inbox;
import com.epam.at.web_driver_task.ui.page.Mailbox;
import com.epam.at.web_driver_task.ui.page.Main;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;

@Slf4j
public class InboxManager extends BaseManager {

    public InboxManager(WebDriver driver) {
        super(driver);
    }

    public void composeNewMailAndReturnToInbox(Mail mail) {
        Mailbox mailbox = new Inbox(driver);
        mailbox.goToComposeNewEmailPage().writeMessageAndSaveItAsDraft(mail);

        log.info("Draft recipients: {}", mail.getRecipients());
        log.info("Draft subject: {}, Draft message: {}", mail.getMailSubject(), mail.getMailMessage().substring(0, 20));
    }

    public MainPageManager logout() {
        Mailbox mailbox = new Inbox(driver);
        mailbox.logout();
        fluentWait(5, 1, (webDriver -> getCurrentUrl().equals(Main.YANDEX_URL)));
        log.info("Logout perform");
        return new MainPageManager(driver);
    }
}
