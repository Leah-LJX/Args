import java.io.*;
import java.util.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class Bench_14 {
  public static Rectangle2D translate(
      Rectangle2D arg0,
      double arg1,
      double arg2,
      int _arg11,
      int _arg05,
      int _arg01,
      int _arg02,
      int _arg13,
      ImageObserver _arg22,
      int _arg21) {
    {
      AffineTransform arg12;
      boolean b1;
      int i1;
      Graphics2D gd1;
      BufferedImage bi1;
      bi1 = new BufferedImage(_arg01, _arg11, _arg21);
      gd1 = bi1.createGraphics();
      b1 =
          gd1.drawImage(
              bi1, (arg12 = AffineTransform.getQuadrantRotateInstance(_arg05, arg1, arg2)), _arg22);
      i1 = bi1.getRGB(_arg02, _arg13);
      bi1.setRGB(i1, i1, i1);
      return arg0;
    }
  }
}

