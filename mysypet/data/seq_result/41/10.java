import java.io.*;
import java.util.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Bench_41 {
  public static boolean compare2Dates(String date1, String date2, String format, long _arg01)
      throws Exception {

    {
      boolean b2;
      Date d2;
      boolean b1;
      Date d1;
      if ((b1 = (d1 = new Date(date2)).before(d1))) {
        d2 = new Date(_arg01);
        b2 = d2.after(d1);
      } else {
      }
      return b1;
    }
  }
}

