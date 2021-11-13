package test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.DateFormat;
import java.util.Date;

public class Bench_40 {
  static String convertTime(long time, String format, DateFormat _df1) {
    {
      String s1;
      String s2;
      Date d1;
      d1 = new Date();
      s1 = _df1.format(d1);
      s2 = Long.toString(time);
      return s2;
    }
  }
}

