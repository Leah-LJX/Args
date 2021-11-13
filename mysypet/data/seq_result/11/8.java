import java.io.*;
import java.util.*;
import java.awt.geom.*;
import java.awt.geom.AffineTransform;

public class Bench_11 {
  public static Rectangle2D shear(
      Rectangle2D arg0, double arg1, double arg2, boolean _b1, int _arg04) {
    {
      AffineTransform arg01;
      AffineTransform at1;
      if (_b1) {
        (at1 = AffineTransform.getRotateInstance(arg1, arg1, arg2)).shear(arg1, arg1);
      } else {
        at1 =
            new AffineTransform(
                (arg01 = AffineTransform.getQuadrantRotateInstance(_arg04, arg1, arg2)));
      }
      return arg0;
    }
  }
}

