package test;

import java.io.*;
import java.util.*;
import java.awt.geom.*;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.xml.transform.Source;
import javax.swing.text.Element;
import javax.swing.text.BadLocationException;
import javax.swing.text.View;
import javax.swing.text.Document;

public class Bench_23 {
  // rank2 ����sypet�Ĵ�����successful
  public static int getOffsetForLine(javax.swing.text.Document arg0, int arg1, View _v1) {
    {
      {
        int i1;
        int i2;
        Element e1;
        String s1;
        Element e2;
        try {
          e1 = _v1.getElement();
          e2 = e1.getElement(arg1);
          i1 = e2.getStartOffset();
          i2 = e2.getEndOffset();
          s1 = arg0.getText(arg1, arg1);
        } catch (BadLocationException _e) {
        }
        return i2;
      }
    }
  }
}

