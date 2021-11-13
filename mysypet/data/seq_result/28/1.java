package test;

import java.io.*;
import java.util.*;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import java.io.*;
import java.util.*;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import java.io.File;
import org.xml.sax.SAXException;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import org.xml.sax.SAXException;
import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;

public class Bench_28 {
  // rank1 successful
  public static Document readXML(File arg0) {
    {
      DocumentBuilder db1;
      Document d1;
      DocumentBuilderFactory dbf1;
      String arg01;
      try {
        dbf1 = DocumentBuilderFactory.newInstance();
        db1 = dbf1.newDocumentBuilder();
        d1 = db1.parse(arg0);
      } catch (ParserConfigurationException _e) {
      } catch (SAXException _e) {
      } catch (IOException _e) {
      }
      return d1;
    }
  }
}

