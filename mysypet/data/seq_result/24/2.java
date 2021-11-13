package test;

import java.io.*;
import java.util.*;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.Element;
import javax.swing.text.Document;

public class Bench_24 {
  // rank2 ���Ĳ���������

  public static javax.swing.text.Element getParagraphElement(
      javax.swing.text.Document arg0, int arg1) {
    {
      int i1;
      Element e1;
      Element e2;
      e1 = arg0.getDefaultRootElement();
      i1 = e1.getElementIndex(arg1);
      e2 = e1.getElement(arg1);
      return e2;
    }
  }
}

