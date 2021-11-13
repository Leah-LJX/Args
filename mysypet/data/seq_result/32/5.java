package test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Bench_32 {

  static String repalceWithReg(String INPUT, String REGEX, String REPLACE, int _arg11) {
    {
      String s1;
      Matcher m1;
      String s2;
      Pattern p1;
      s1 = INPUT.replaceAll(INPUT, INPUT);
      p1 = Pattern.compile(INPUT, _arg11);
      m1 = p1.matcher(INPUT);
      s2 = m1.replaceAll(INPUT);
      return s2;
    }
  }
}

