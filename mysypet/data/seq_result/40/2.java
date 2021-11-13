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
      d1 = new Date(time);
      sdf1 = new SimpleDateFormat(format);
      s1 = sdf1.format(d1);
      return s1;
    }
  }
}

