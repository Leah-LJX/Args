package test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.xml.sax.InputSource;
import java.io.IOException;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;

public class Bench_31 {
  // rank2
  static Document loadXMLFromString(String xml, DocumentBuilderFactory _dbf1) {
    {
      DocumentBuilder db2;
      InputStream is1;
      ThreadLocal<DocumentBuilder> tl1;
      InputSource arg01;
      DocumentBuilder db1;
      Document d1;
      try {
        db1 = (tl1 = new ThreadLocal()).get();
        db2 = _dbf1.newDocumentBuilder();
        d1 = db1.parse((arg01 = new InputSource(xml)));
        (is1 = System.in).close();
      } catch (ParserConfigurationException _e) {
      } catch (SAXException _e) {
      } catch (IOException _e) {
      }
      return d1;
    }
  }
}

