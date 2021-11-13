package test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import java.io.UnsupportedEncodingException;
import java.io.IOException;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import java.io.ByteArrayInputStream;
import org.w3c.dom.Document;

public class Bench_31 {
  // rank2
  static Document loadXMLFromString(String xml) {
    {
      ThreadLocal<DocumentBuilder> tl1;
      ByteArrayInputStream bais1;
      DocumentBuilder db1;
      Document d1;
      byte[] b1;
      try {
        db1 = (tl1 = new ThreadLocal()).get();
        b1 = xml.getBytes(xml);
        bais1 = new ByteArrayInputStream(b1);
        d1 = db1.parse(bais1);
      } catch (SAXException _e) {
      } catch (UnsupportedEncodingException _e) {
      } catch (IOException _e) {
      }
      return d1;
    }
  }
}

