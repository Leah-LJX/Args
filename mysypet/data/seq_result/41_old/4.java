package test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Date;
import java.util.Locale;
import java.text.SimpleDateFormat;

public class Bench_41 {

  // rank2--successful
  static String generateDateStamp(String arg0) {
    {
      Locale arg11;
      String s1;
      SimpleDateFormat sdf1;
      Date arg01;
      sdf1 = new SimpleDateFormat(arg0, (arg11 = Locale.forLanguageTag(arg0)));
      s1 = sdf1.format((arg01 = new Date(arg0)));
      return s1;
    }
  }
}

