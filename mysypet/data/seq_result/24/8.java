package test;

import java.io.*;
import java.util.*;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.Element;
import javax.swing.text.View;

public class Bench_24 {
  // rank2 ���Ĳ���������

  public static javax.swing.text.Element getParagraphElement(
      javax.swing.text.Document arg0, int arg1, View _v1) {
    {
      Element e1;
      Element e2;
      e1 = _v1.getElement();
      e2 = e1.getElement(arg1);
      return e2;
    }
  }
}

