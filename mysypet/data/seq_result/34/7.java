package test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.TimeZone;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Bench_34 {

  static String changeTimeZone(Date date, String format, String timeZone, TimeZone _arg01) {
    {
      SimpleDateFormat sdf1;
      Date d1;
      try {
        sdf1 = new SimpleDateFormat(format);
        sdf1.setTimeZone(_arg01);
        d1 = sdf1.parse(timeZone);
      } catch (ParseException _e) {
      }
      return timeZone;
    }
  }
}

