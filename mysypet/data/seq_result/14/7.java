import java.io.*;
import java.util.*;
import java.awt.geom.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

public class Bench_14 {
  public static Rectangle2D translate(
      Rectangle2D arg0,
      double arg1,
      double arg2,
      ImageObserver _arg31,
      int _arg12,
      Graphics _g1,
      int _arg21,
      Image _arg02) {
    {
      boolean b1;
      AffineTransform at1;
      at1 = AffineTransform.getTranslateInstance(arg1, arg1);
      b1 = _g1.drawImage(_arg02, _arg12, _arg21, _arg31);
      return arg0;
    }
  }
}

