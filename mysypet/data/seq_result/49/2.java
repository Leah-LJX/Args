package test;

public class Bench_49 {
  // û�п��Ե�
  static String reverseAndAppend(String arg0) {
    {
      StringBuilder sb1;
      AbstractStringBuilder asb1;
      String s1;
      sb1 = new StringBuilder(arg0);
      asb1 = sb1.reverse();
      s1 = sb1.toString();
      return s1;
    }
  }
}

