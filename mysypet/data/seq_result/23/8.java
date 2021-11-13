package test;

import java.io.*;
import java.util.*;
import java.awt.geom.*;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.xml.transform.Source;
import javax.swing.text.Element;

public class Bench_23 {
  // rank2 ����sypet�Ĵ�����successful
  public static int getOffsetForLine(javax.swing.text.Document arg0, int arg1, Element _e1) {
    {
      {
        int i1;
        int i2;
        Element e2;
        int i3;
        i1 = _e1.getElementCount();
        e2 = _e1.getElement(arg1);
        i2 = e2.getStartOffset();
        i3 = e2.getEndOffset();
        return i3;
      }
    }
  }
}

