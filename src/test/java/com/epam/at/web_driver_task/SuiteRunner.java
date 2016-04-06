package com.epam.at.web_driver_task;

import org.testng.TestNG;
import org.testng.xml.Parser;
import org.testng.xml.XmlSuite;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public class SuiteRunner {
    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        TestNG tng = new TestNG(true);
        Parser parser = new Parser("src/test/resources/suite.xml");
        List<XmlSuite> suites = parser.parseToList();
        tng.setXmlSuites(suites);
        tng.run();
    }
}
