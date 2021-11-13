package test;

import java.util.Collections;
import java.util.List;

public class Bench_49 {
  // û�п��Ե�
  static String reverseAndAppend(String arg0, List _arg01) {
    {
      AbstractStringBuilder asb1;
      StringBuffer sb1;
      String s1;
      sb1 = new StringBuffer();
      Collections.reverse(_arg01);
      asb1 = sb1.append(arg0);
      s1 = sb1.toString();
      return s1;
    }
  }
}

