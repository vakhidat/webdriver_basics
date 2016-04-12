package com.epam.at.web_driver_task.ui.service;

import com.epam.at.web_driver_task.model.entity.Mail;
import com.epam.at.web_driver_task.ui.page.DraftFolder;
import com.epam.at.web_driver_task.util.ReportUtil;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;

import java.util.List;

@Slf4j
public class DraftManager extends BaseManager {
    public static final String SCREENSHOT_PREFIX = "draft";
    private DraftFolder draftFolder;

    public DraftManager(WebDriver driver) {
        super(driver);
    }

    public boolean draftPresentInFolder() {
        draftFolder = goToDraftFolder();
        if (draftFolder.getDraftFirstInList() == null) {
            ReportUtil.highlightElementAndTakeScreenshot(driver, draftFolder.getDraftFirstInList(), SCREENSHOT_PREFIX);
            return false;
        }
        ReportUtil.highlightElement(driver, draftFolder.getDraftFirstInList());
        log.info("Draft is present in folder");
        return true;
    }

    public boolean draftDisappearedFromFolder() {
        draftFolder = goToDraftFolder();
        ReportUtil.highlightElementAndTakeScreenshot(driver, draftFolder.getEmptyFolderDiv(), SCREENSHOT_PREFIX);
        log.info("Draft folder is empty");
        return draftFolder.isDraftFolderEmpty();
    }

    public boolean checkFullDraftContent(Mail mail) {
        draftFolder = goToDraftFolder();
        boolean recipientsMatch = draftRecipientsMatchExpected(mail.getRecipients(), draftFolder);
        boolean subjectMatch = draftSubjectMatchExpected(mail.getMailSubject(), draftFolder);
        boolean messageMatch = draftMessageMatchExpected(mail.getMailMessage(), draftFolder);
        log.info("Full content was checked");
        return recipientsMatch && subjectMatch && messageMatch;
    }

    public boolean draftRecipientsMatchExpected(List<String> recipients, DraftFolder draftFolder) {
        ReportUtil.highlightElement(driver, draftFolder.getDraftRecipientMail());
        boolean allRecipientsMatch = recipients
                .stream()
                .allMatch(recipient -> draftFolder.getDraftRecipientMailText().contains(recipient));
        log.info("Draft expected recipients: {}", recipients);
        log.info("Draft actual recipients: {}", draftFolder.getDraftRecipientMailText());
        return allRecipientsMatch;
    }

    public boolean draftSubjectMatchExpected(String subject, DraftFolder draftFolder) {
        ReportUtil.highlightElement(driver, draftFolder.getDraftRecipientSubject());
        boolean subjectMatch = draftFolder.getDraftRecipientSubjectText().equals(subject);
        log.info("Draft expected subject: {}", subject);
        log.info("Draft actual subject: {}", draftFolder.getDraftRecipientSubjectText());
        return subjectMatch;
    }

    public boolean draftMessageMatchExpected(String message, DraftFolder draftFolder) {
        ReportUtil.highlightElement(driver, draftFolder.getDraftRecipientMessage());
        boolean messageMatch = draftFolder.getDraftRecipientMessageText().equals(message);
        log.info("Draft expected message: {}", message);
        log.info("Draft actual message: {}", draftFolder.getDraftRecipientMessageText());
        return messageMatch;
    }
}
