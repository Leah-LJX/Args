
import java.io.*;
import java.util.*;
import java.awt.geom.*;
import java.awt.geom.Rectangle2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

public class Bench_14 {
  public static Rectangle2D translate(Rectangle2D arg0, double arg1, double arg2) {
    {
      Shape s1;
      boolean b1;
      AffineTransform at1;
      at1 = AffineTransform.getTranslateInstance(arg1, arg1);
      s1 = at1.createTransformedShape(arg0);
      b1 = arg0.intersects(arg0);
      return arg0;
    }
  }
}

