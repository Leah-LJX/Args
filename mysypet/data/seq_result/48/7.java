package test;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Bench_48 {
  // rank1 --successful
  static String match(String expr, String str, int _arg01) {
    {
      String s1;
      boolean b1;
      Matcher m1;
      Pattern p1;
      p1 = Pattern.compile(expr);
      m1 = p1.matcher(str);
      b1 = m1.find();
      s1 = m1.group(_arg01);
      return s1;
    }
  }
}

