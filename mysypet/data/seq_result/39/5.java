import java.io.*;
import java.util.*;
import java.text.*;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Date;
import java.text.ParseException;

public class Bench_39 {
  public static String transformDateFormat(String date, String oldFormat, String newFormat) {
    {
      SimpleDateFormat sdf1;
      Date d1;
      Locale arg11;
      try {
        sdf1 = new SimpleDateFormat(oldFormat, (arg11 = Locale.forLanguageTag(newFormat)));
        d1 = sdf1.parse(date);
      } catch (ParseException _e) {
      }
      return newFormat;
    }
  }
}