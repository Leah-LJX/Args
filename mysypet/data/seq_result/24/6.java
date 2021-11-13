package test;

import java.io.*;
import java.util.*;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.Element;
import javax.swing.text.AttributeSet;
import javax.swing.text.Document;

public class Bench_24 {
  // rank2 ���Ĳ���������

  public static javax.swing.text.Element getParagraphElement(
      javax.swing.text.Document arg0, int arg1) {
    {
      AttributeSet as1;
      Element e1;
      Element e2;
      e1 = arg0.getDefaultRootElement();
      e2 = e1.getElement(arg1);
      as1 = e2.getAttributes();
      return e2;
    }
  }
}

