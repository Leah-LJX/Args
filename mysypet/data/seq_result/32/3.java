package test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.HashMap;

public class Bench_32 {

  static String repalceWithReg(String INPUT, String REGEX, String REPLACE) {
    {
      String s1;
      Matcher m1;
      HashMap<String, Object> hm1;
      Pattern p1;
      (hm1 = new HashMap()).clear();
      m1 = (p1 = Pattern.compile(INPUT)).matcher(INPUT);
      s1 = m1.replaceAll(INPUT);
      return REPLACE;
    }
  }
}

