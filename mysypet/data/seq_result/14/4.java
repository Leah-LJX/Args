import java.io.*;
import java.util.*;
import java.awt.geom.*;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;

public class Bench_14 {
  public static Rectangle2D translate(
      Rectangle2D arg0, double arg1, double arg2, Path2D _pd1, int _arg01) {
    {
      Shape s1;
      AffineTransform at1;
      at1 = AffineTransform.getQuadrantRotateInstance(_arg01, arg1, arg2);
      s1 = _pd1.createTransformedShape(at1);
      return arg0;
    }
  }
}

