package test;

import java.io.*;
import java.util.*;
import java.awt.geom.*;
import java.util.Comparator;
import java.awt.geom.AffineTransform;
import javax.swing.AbstractButton;
import java.util.PriorityQueue;

public class Bench_12 {
  /*
   * û�к��ʵ�
   */
  public static Rectangle2D rotateQuadrant(
      Rectangle2D arg0, int arg1, AbstractButton _ab1, boolean _arg01) {
    {
      AffineTransform at1;
      at1 = AffineTransform.getQuadrantRotateInstance(arg1, arg1, arg1);
      _ab1.setSelected(_arg01);
      return arg0;
    }
  }
}

