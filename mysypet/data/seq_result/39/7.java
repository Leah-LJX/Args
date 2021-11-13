import java.io.*;
import java.util.*;
import java.text.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

public class Bench_39 {
  public static String transformDateFormat(
      String date, String oldFormat, String newFormat, boolean _arg01) {
    {
      SimpleDateFormat sdf1;
      Date d1;
      try {
        sdf1 = new SimpleDateFormat(oldFormat);
        sdf1.setLenient(_arg01);
        d1 = sdf1.parse(date);
      } catch (ParseException _e) {
      }
      return newFormat;
    }
  }
}