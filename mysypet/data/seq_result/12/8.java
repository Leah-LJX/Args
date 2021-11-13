package test;

import java.io.*;
import java.util.*;
import java.awt.geom.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.Shape;

public class Bench_12 {
  /*
   * û�к��ʵ�
   */
  public static Rectangle2D rotateQuadrant(
      Rectangle2D arg0, int arg1, Path2D _pd1, boolean _arg01) {
    {
      AffineTransform at1;
      boolean b2;
      Boolean b1;
      Shape s1;
      at1 = AffineTransform.getQuadrantRotateInstance(arg1, arg1, arg1);
      s1 = _pd1.createTransformedShape(at1);
      b2 = (b1 = Boolean.valueOf(_arg01)).booleanValue();
      return arg0;
    }
  }
}

