package test;

public class Bench_49 {
  // û�п��Ե�
  static String reverseAndAppend(String arg0, char _arg02, int _arg01) {
    {
      StringBuffer sb3;
      StringBuffer sb1;
      String s1;
      StringBuffer sb2;
      sb1 = new StringBuffer(_arg01);
      sb2 = sb1.append(_arg02);
      sb3 = sb2.reverse();
      s1 = sb3.toString();
      return arg0;
    }
  }
}

