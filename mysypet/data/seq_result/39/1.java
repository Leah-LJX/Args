import java.io.*;
import java.util.*;
import java.text.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

public class Bench_39 {
  public static String transformDateFormat(String date, String oldFormat, String newFormat) {
    {
      SimpleDateFormat sdf1;
      Date d1;
      try {
        sdf1 = new SimpleDateFormat(oldFormat);
        d1 = sdf1.parse(date);
      } catch (ParseException _e) {
      }
      return newFormat;
    }
  }
}