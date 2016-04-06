package com.epam.at.web_driver_task;

import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.TestNG;
import org.testng.collections.Lists;
import org.testng.xml.Parser;
import org.testng.xml.XmlSuite;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class SuiteRunner {
    public static final String SUITE_XML = "src/test/resources/suite.xml";
    private static final String HUB = "http://10.12.12.237:4444/wd/hub";

    public static void main(String[] args)  {
        initDriver();
        testExecutor();
    }

    public static void testExecutor() {
        TestNG tng = new TestNG(true);
        tng.setXmlSuites(parseSuites(SUITE_XML));
        tng.run();
    }

    public static void initDriver() {
        DesiredCapabilities dc = new DesiredCapabilities();
		dc.setBrowserName("firefox");
        dc.setPlatform(Platform.WINDOWS);

        try {
            WebDriverFactory.setDriver(new RemoteWebDriver(new URL(HUB), dc));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public static List<XmlSuite> parseSuites(String xmlPath) {
        Parser parser = new Parser(xmlPath);
        List<XmlSuite> xmlSuites = Lists.newArrayList();
        try {
            xmlSuites = parser.parseToList();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return xmlSuites;
    }
}
