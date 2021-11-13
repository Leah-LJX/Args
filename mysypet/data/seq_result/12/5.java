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
  public static Rectangle2D rotateQuadrant(Rectangle2D arg0, int arg1, Path2D _pd1) {
    {
      AffineTransform at1;
      long l2;
      Long l1;
      Shape s1;
      at1 = AffineTransform.getQuadrantRotateInstance(arg1, arg1, arg1);
      s1 = _pd1.createTransformedShape(at1);
      l2 = (l1 = new Long(arg1)).longValue();
      return arg0;
    }
  }
}

