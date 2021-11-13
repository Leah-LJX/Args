import java.io.*;
import java.util.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

public class Bench_41 {
  public static boolean compare2Dates(String date1, String date2, String format, boolean _arg01)
      throws Exception {

    {
      SimpleDateFormat sdf1;
      Date d1;
      try {
        sdf1 = new SimpleDateFormat(format);
        sdf1.setLenient(_arg01);
        d1 = sdf1.parse(date2);
      } catch (ParseException _e) {
      }
      return _arg01;
    }
  }
}

