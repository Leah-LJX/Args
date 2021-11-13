import java.io.*;
import java.util.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.DateFormat;

public class Bench_41 {
  public static boolean compare2Dates(String date1, String date2, String format, DateFormat _df1)
      throws Exception {

    {
      String s1;
      boolean b1;
      Date d1;
      if ((b1 = (d1 = new Date(date2)).before(d1))) {
        s1 = _df1.format(d1);
      } else {
      }
      return b1;
    }
  }
}

