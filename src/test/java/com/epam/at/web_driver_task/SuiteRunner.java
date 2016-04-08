package com.epam.at.web_driver_task;

import org.testng.TestNG;
import org.testng.xml.Parser;
import org.testng.xml.XmlSuite;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public class SuiteRunner {
    public static final String SUITE_XML = "src/test/resources/suite.xml";
    public static final String BROWSERS_XML = "src/test/resources/browsers.xml";
    private static final String HUB = "http://10.12.12.237:4444/wd/hub";

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        TestNG tng = new TestNG(true);
        Parser parser = new Parser(SUITE_XML);
        List<XmlSuite> xmlSuites = parser.parseToList();
        tng.setXmlSuites(xmlSuites);
        tng.run();
    }
}
