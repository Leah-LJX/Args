import java.io.*;
import java.util.*;
import java.text.*;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class Bench_39 {
  public static String transformDateFormat(
      String date, String oldFormat, String newFormat, TimeZone _arg01) {
    {
      SimpleDateFormat sdf1;
      sdf1 = new SimpleDateFormat(oldFormat);
      sdf1.setTimeZone(_arg01);
      return newFormat;
    }
  }
}