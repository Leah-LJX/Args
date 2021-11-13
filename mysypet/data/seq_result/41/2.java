import java.io.*;
import java.util.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Bench_41 {
  public static boolean compare2Dates(String date1, String date2, String format) throws Exception {

    {
      String s1;
      boolean b1;
      SimpleDateFormat sdf1;
      Date d1;
      d1 = new Date();
      sdf1 = new SimpleDateFormat(format);
      s1 = sdf1.format(d1);
      b1 = date2.equals(date2);
      return b1;
    }
  }
}

