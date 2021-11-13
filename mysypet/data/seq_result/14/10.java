import java.io.*;
import java.util.*;
import java.awt.geom.*;
import java.awt.geom.Rectangle2D;
import java.awt.Image;
import java.awt.Color;
import java.awt.Shape;
import java.awt.image.ImageObserver;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class Bench_14 {
  public static Rectangle2D translate(
      Rectangle2D arg0,
      double arg1,
      double arg2,
      int _arg11,
      Graphics2D _gd1,
      ImageObserver _arg101,
      int _arg41,
      int _arg21,
      int _arg71,
      int _arg51,
      int _arg81,
      int _arg31,
      Image _arg01,
      int _arg61) {
    {
      Rectangle2D rd1;
      boolean b1;
      Shape s1;
      Color arg91;
      AffineTransform at1;
      at1 = _gd1.getTransform();
      s1 = at1.createTransformedShape(arg0);
      rd1 = arg0.getBounds2D();
      b1 =
          _gd1.drawImage(
              _arg01,
              _arg11,
              _arg21,
              _arg31,
              _arg41,
              _arg51,
              _arg61,
              _arg71,
              _arg81,
              (arg91 = Color.getColor(new String())),
              _arg101);
      return rd1;
    }
  }
}

