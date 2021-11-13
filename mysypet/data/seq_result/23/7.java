package test;

import java.io.*;
import java.util.*;
import java.awt.geom.*;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.xml.transform.Source;
import javax.swing.text.Element;
import java.awt.Rectangle;
import javax.swing.text.View;
import javax.swing.text.Document;

public class Bench_23 {
  // rank2 ����sypet�Ĵ�����successful
  public static int getOffsetForLine(javax.swing.text.Document arg0, int arg1, View _v1) {
    {
      {
        Element e1;
        String s1;
        Element e2;
        Rectangle r1;
        e1 = arg0.getDefaultRootElement();
        e2 = e1.getElement(arg1);
        r1 = new Rectangle(arg1, arg1);
        s1 = _v1.getToolTipText(arg1, arg1, r1);
        return arg1;
      }
    }
  }
}

