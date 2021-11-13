import java.io.*;
import java.util.*;
import java.awt.geom.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.geom.AffineTransform;

public class Bench_14 {
  public static Rectangle2D translate(
      Rectangle2D arg0,
      double arg1,
      double arg2,
      int _arg11,
      Graphics _g1,
      ImageObserver _arg31,
      Image _arg01,
      int _arg21) {
    {
      boolean b1;
      AffineTransform at1;
      at1 = AffineTransform.getTranslateInstance(arg1, arg1);
      b1 = _g1.drawImage(_arg01, _arg11, _arg21, _arg31);
      return arg0;
    }
  }
}

