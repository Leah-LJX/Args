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
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.ErrorHandler;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;

public class Bench_31 {
  // rank2
  static Document loadXMLFromString(String xml, DocumentBuilderFactory _dbf1, ErrorHandler _arg01) {
    {
      DocumentBuilder db2;
      ThreadLocal<DocumentBuilder> tl1;
      DocumentBuilder db1;
      Document d1;
      InputSource arg02;
      try {
        db1 = (tl1 = new ThreadLocal()).get();
        db2 = _dbf1.newDocumentBuilder();
        db1.setErrorHandler(_arg01);
        tl1.set(db1);
        d1 = db2.parse((arg02 = new InputSource(xml)));
      } catch (ParserConfigurationException _e) {
      } catch (SAXException _e) {
      } catch (IOException _e) {
      }
      return d1;
    }
  }
}

