import java.io.*;
import java.util.*;
import java.text.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Bench_39 {
  public static String transformDateFormat(String date, String oldFormat, String newFormat) {
    {
      String s1;
      SimpleDateFormat sdf1;
      Date d1;
      sdf1 = new SimpleDateFormat(oldFormat);
      d1 = new Date();
      s1 = sdf1.format(d1);
      return s1;
    }
  }
}
