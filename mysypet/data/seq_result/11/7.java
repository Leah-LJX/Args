import java.io.*;
import java.util.*;
import java.awt.geom.*;
import java.util.Arrays;
import java.awt.Graphics2D;

public class Bench_11 {
  public static Rectangle2D shear(
      Rectangle2D arg0, double arg1, double arg2, double[] _arg01, Graphics2D _gd1) {
    {
      Arrays.fill(_arg01, arg1);
      _gd1.shear(arg1, arg1);
      return arg0;
    }
  }
}

