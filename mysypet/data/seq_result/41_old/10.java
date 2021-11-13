package test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.text.SimpleDateFormat;

public class Bench_41 {

  // rank2--successful
  static String generateDateStamp(String arg0, TimeZone _arg01) {
    {
      SimpleDateFormat sdf1;
      sdf1 = new SimpleDateFormat(arg0);
      sdf1.setTimeZone(_arg01);
      return arg0;
    }
  }
}

