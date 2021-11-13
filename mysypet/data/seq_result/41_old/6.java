package test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Bench_41 {

  // rank2--successful
  static String generateDateStamp(String arg0, long _arg01) {
    {
      String s1;
      SimpleDateFormat sdf1;
      Date d1;
      sdf1 = new SimpleDateFormat(arg0);
      d1 = new Date(_arg01);
      s1 = sdf1.format(d1);
      return s1;
    }
  }
}

