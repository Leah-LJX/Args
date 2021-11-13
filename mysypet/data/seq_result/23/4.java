package test;

import java.io.*;
import java.util.*;
import java.awt.geom.*;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.xml.transform.Source;
import javax.swing.text.Element;
import javax.swing.text.Document;
import java.math.BigDecimal;

public class Bench_23 {
  // rank2 ����sypet�Ĵ�����successful
  public static int getOffsetForLine(javax.swing.text.Document arg0, int arg1) {
    {
      {
        Element e1;
        BigDecimal bd2;
        int i1;
        int i2;
        BigDecimal bd1;
        Element e2;
        e1 = arg0.getDefaultRootElement();
        e2 = e1.getElement(arg1);
        i1 = e2.getStartOffset();
        i2 = e2.getEndOffset();
        bd2 = (bd1 = new BigDecimal(arg1)).divide(bd1);
        return i2;
      }
    }
  }
}

