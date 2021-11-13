package test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import java.io.IOException;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;

public class Bench_31 {
  // rank2
  static Document loadXMLFromString(String xml, byte[] _arg01) {
    {
      DocumentBuilderFactory dbf1;
      ByteArrayInputStream bais1;
      DocumentBuilder db1;
      Document d1;
      try {
        bais1 = new ByteArrayInputStream(_arg01);
        dbf1 = DocumentBuilderFactory.newInstance();
        db1 = dbf1.newDocumentBuilder();
        d1 = db1.parse(bais1);
      } catch (ParserConfigurationException _e) {
      } catch (SAXException _e) {
      } catch (IOException _e) {
      }
      return d1;
    }
  }
}

