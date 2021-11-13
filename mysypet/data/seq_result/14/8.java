import java.io.*;
import java.util.*;
import java.awt.geom.*;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Bench_14 {
  public static Rectangle2D translate(
      Rectangle2D arg0,
      double arg1,
      double arg2,
      int _arg11,
      Graphics2D _gd1,
      int _arg21,
      int _arg01,
      int _arg03,
      int _arg31) {
    {
      Rectangle r1;
      Color arg02;
      _gd1.setBackground((arg02 = new Color(_arg01)));
      r1 = _gd1.getClipBounds();
      _gd1.clearRect(_arg03, _arg11, _arg21, _arg31);
      _gd1.dispose();
      return arg0;
    }
  }
}

