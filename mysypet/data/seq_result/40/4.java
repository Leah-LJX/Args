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
      Date d1;
      d1 = new Date(time);
      s1 = _df1.format(d1);
      return s1;
    }
  }
}

