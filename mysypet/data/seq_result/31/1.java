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
import java.io.InputStream;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;

public class Bench_31 {
  // rank2
  static Document loadXMLFromString(String xml) {
    {
      InputStream arg01;
      ThreadLocal<DocumentBuilder> tl1;
      DocumentBuilder db1;
      Document d1;
      try {
        db1 = (tl1 = new ThreadLocal()).get();
        d1 = db1.parse((arg01 = System.in));
      } catch (SAXException _e) {
      } catch (IOException _e) {
      }
      return d1;
    }
  }
}

