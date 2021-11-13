package test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.TimeZone;
import java.text.DateFormat;

public class Bench_34 {

  static String changeTimeZone(Date date, String format, String timeZone, DateFormat _df1) {
    {
      TimeZone tz1;
      tz1 = TimeZone.getTimeZone(timeZone);
      _df1.setTimeZone(tz1);
      return format;
    }
  }
}

