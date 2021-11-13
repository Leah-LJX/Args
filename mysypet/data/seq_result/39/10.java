import java.io.*;
import java.util.*;
import java.text.*;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import java.util.Date;

public class Bench_39 {
  public static String transformDateFormat(
      String date, String oldFormat, String newFormat, TimeZone _arg01) {
    {
      Date arg02;
      String s1;
      SimpleDateFormat sdf1;
      sdf1 = new SimpleDateFormat(oldFormat);
      sdf1.setTimeZone(_arg01);
      s1 = sdf1.format((arg02 = new Date(date)));
      return s1;
    }
  }
}