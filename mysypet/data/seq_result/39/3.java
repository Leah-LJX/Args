import java.io.*;
import java.util.*;
import java.text.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

public class Bench_39 {
  public static String transformDateFormat(String date, String oldFormat, String newFormat) {
    {
      String s1;
      Date d1;
      SimpleDateFormat sdf1;
      Date arg01;
      try {
        sdf1 = new SimpleDateFormat(oldFormat);
        s1 = sdf1.format((arg01 = new Date(date)));
        d1 = sdf1.parse(date);
      } catch (ParseException _e) {
      }
      return s1;
    }
  }
}