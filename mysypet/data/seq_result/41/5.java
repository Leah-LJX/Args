import java.io.*;
import java.util.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Bench_41 {
  public static boolean compare2Dates(String date1, String date2, String format) throws Exception {

    {
      boolean b2;
      boolean b1;
      Date d1;
      d1 = new Date();
      b1 = d1.before(d1);
      b2 = d1.after(d1);
      return b1;
    }
  }
}
