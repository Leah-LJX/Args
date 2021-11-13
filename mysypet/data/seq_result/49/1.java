package test;

public class Bench_49 {
  // û�п��Ե�
  static String reverseAndAppend(String arg0) {
    {
      StringBuffer sb1;
      String s1;
      StringBuffer sb2;
      sb1 = new StringBuffer(arg0);
      sb2 = sb1.reverse();
      s1 = sb2.toString();
      return s1;
    }
  }
}

