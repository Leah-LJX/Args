import java.io.*;
import java.util.*;
import java.awt.geom.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;

public class Bench_14 {
  public static Rectangle2D translate(Rectangle2D arg0, double arg1, double arg2, Path2D _pd1) {
    {
      AffineTransform at1;
      at1 = AffineTransform.getTranslateInstance(arg1, arg1);
      _pd1.closePath();
      arg0.setFrame(arg1, arg1, arg2, arg1);
      return arg0;
    }
  }
}

