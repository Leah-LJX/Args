package test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.TimeZone;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class Bench_34 {

  static String changeTimeZone(
      Date date, String format, String timeZone, TimeZone _arg01, Calendar _c1) {
    {
      String s1;
      SimpleDateFormat sdf1;
      Date d1;
      sdf1 = new SimpleDateFormat(format);
      sdf1.setTimeZone(_arg01);
      d1 = _c1.getTime();
      s1 = sdf1.format(date);
      return s1;
    }
  }
}

