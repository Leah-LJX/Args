package test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Bench_41 {

  // rank2--successful
  static String generateDateStamp(String arg0, TimeZone _arg01) {
    {
      Date arg02;
      String s1;
      SimpleDateFormat sdf1;
      sdf1 = new SimpleDateFormat(arg0);
      sdf1.setTimeZone(_arg01);
      s1 = sdf1.format((arg02 = new Date(arg0)));
      return s1;
    }
  }
}

