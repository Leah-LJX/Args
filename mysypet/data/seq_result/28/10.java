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
import org.xml.sax.InputSource;
import java.io.IOException;
import org.xml.sax.SAXException;
import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;

public class Bench_28 {
  // rank1 successful
  public static Document readXML(
      File arg0, DocumentBuilder _db1, DocumentBuilderFactory _dbf1, boolean _arg01) {
    {
      DocumentBuilder db2;
      Document d2;
      InputSource arg02;
      Document d1;
      try {
        d1 = _db1.parse(arg0);
        _dbf1.setValidating(_arg01);
        db2 = _dbf1.newDocumentBuilder();
        d2 = _db1.parse((arg02 = new InputSource()));
      } catch (ParserConfigurationException _e) {
      } catch (SAXException _e) {
      } catch (IOException _e) {
      }
      return d1;
    }
  }
}

