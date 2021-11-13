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
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;

public class Bench_28 {
  // rank1 successful
  public static Document readXML(File arg0, DocumentBuilder _db1) {
    {
      Document d1;
      String arg01;
      try {
        d1 = _db1.parse(arg0);
      } catch (SAXException _e) {
      } catch (IOException _e) {
      }
      return d1;
    }
  }
}

