package test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.TimeZone;
import java.text.SimpleDateFormat;

public class Bench_34 {

  static String changeTimeZone(Date date, String format, String timeZone) {
    {
      TimeZone tz1;
      SimpleDateFormat sdf1;
      sdf1 = new SimpleDateFormat(format);
      tz1 = TimeZone.getTimeZone(timeZone);
      sdf1.setTimeZone(tz1);
      return timeZone;
    }
  }
}

