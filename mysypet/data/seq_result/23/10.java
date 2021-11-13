package test;

import java.io.*;
import java.util.*;
import java.awt.geom.*;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.xml.transform.Source;
import javax.swing.text.Element;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.XMLStreamException;
import javax.swing.text.Document;
import java.math.BigDecimal;

public class Bench_23 {
  // rank2 ����sypet�Ĵ�����successful
  public static int getOffsetForLine(
      javax.swing.text.Document arg0, int arg1, XMLStreamWriter _xmlsw1) {
    {
      {
        Element e1;
        int i1;
        BigDecimal bd1;
        Element e2;
        try {
          e1 = arg0.getDefaultRootElement();
          e2 = e1.getElement(arg1);
          _xmlsw1.writeEndElement();
          i1 = (bd1 = new BigDecimal(arg1)).compareTo(bd1);
        } catch (XMLStreamException _e) {
        }
        return i1;
      }
    }
  }
}

