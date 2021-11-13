package test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Bench_40 {
  static String convertTime(long time, String format) {
    {
      String s1;
      SimpleDateFormat sdf1;
      Date d1;
      sdf1 = new SimpleDateFormat(format);
      d1 = new Date(time);
      s1 = sdf1.format(d1);
      return s1;
    }
  }
}

