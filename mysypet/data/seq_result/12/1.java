package test;

import java.io.*;
import java.util.*;
import java.awt.geom.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.AffineTransform;
import java.util.concurrent.atomic.AtomicReference;

public class Bench_12 {
  /*
   * û�к��ʵ�
   */
  public static Rectangle2D rotateQuadrant(Rectangle2D arg0, int arg1) {
    {
      AffineTransform at1;
      boolean b1;
      AtomicReference<Rectangle2D> ar1;
      at1 = AffineTransform.getQuadrantRotateInstance(arg1, arg1, arg1);
      b1 = (ar1 = new AtomicReference(arg0)).weakCompareAndSet(arg0, arg0);
      return arg0;
    }
  }
}

