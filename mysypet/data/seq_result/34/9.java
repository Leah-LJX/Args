package test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.TimeZone;
import java.text.DateFormat;
import java.util.Date;

public class Bench_34 {

  static String changeTimeZone(
      Date date, String format, String timeZone, TimeZone _arg01, DateFormat _df1) {
    {
      String s1;
      _df1.setTimeZone(_arg01);
      s1 = _df1.format(date);
      return s1;
    }
  }
}

