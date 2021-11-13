import java.io.*;
import java.util.*;
import java.awt.geom.*;
import java.awt.geom.Rectangle2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

public class Bench_11 {
  public static Rectangle2D shear(Rectangle2D arg0, double arg1, double arg2, int _arg04) {
    {
      double d2;
      double d1;
      Shape s1;
      Rectangle2D rd1;
      AffineTransform at1;
      rd1 = arg0.getBounds2D();
      d1 = arg0.getCenterX();
      d2 = arg0.getCenterY();
      (at1 = AffineTransform.getQuadrantRotateInstance(_arg04, arg1, arg2))
          .setToTranslation(arg1, arg1);
      s1 = at1.createTransformedShape(arg0);
      return rd1;
    }
  }
}

