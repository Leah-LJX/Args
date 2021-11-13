import java.io.*;
import java.util.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class Bench_10 {
  public static Rectangle2D scale(
      Rectangle2D arg0,
      double arg1,
      double arg2,
      int _arg11,
      ImageObserver _arg22,
      int _arg21,
      int _arg01) {
    {
      boolean b1;
      Graphics2D gd1;
      BufferedImage bi1;
      AffineTransform at1;
      bi1 = new BufferedImage(_arg01, _arg11, _arg21);
      gd1 = bi1.createGraphics();
      at1 = AffineTransform.getScaleInstance(arg1, arg1);
      b1 = gd1.drawImage(bi1, at1, _arg22);
      return arg0;
    }
  }
}

