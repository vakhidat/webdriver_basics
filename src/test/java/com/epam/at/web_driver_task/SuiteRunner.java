package com.epam.at.web_driver_task;

import org.testng.TestNG;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.xml.Parser;
import org.testng.xml.XmlSuite;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public class SuiteRunner {
    private static final String YANDEX_URL = "https://www.yandex.kz/";

    @BeforeSuite(alwaysRun = true)
    public static void startFirefox() {
        WebDriverFactory.firefoxInstance().get(YANDEX_URL);
    }

    @AfterSuite(alwaysRun = true)
    public static void quitFirefox() {
        WebDriverFactory.firefoxInstance().close();
    }

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        TestNG tng = new TestNG(true);
        final Parser parser = new Parser("src/test/resources/suite.xml");
        final List<XmlSuite> suites = parser.parseToList();
        tng.setXmlSuites(suites);
        tng.run();
    }
}