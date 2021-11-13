package test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.TimeZone;
import java.util.Locale;
import java.text.SimpleDateFormat;

public class Bench_34 {

  static String changeTimeZone(Date date, String format, String timeZone, TimeZone _arg01) {
    {
      Locale arg11;
      SimpleDateFormat sdf1;
      sdf1 = new SimpleDateFormat(format, (arg11 = Locale.forLanguageTag(format)));
      sdf1.setTimeZone(_arg01);
      return format;
    }
  }
}

