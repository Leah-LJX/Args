public static boolean test0() throws Throwable{
    java.awt.geom.Point2D p1 = new java.awt.geom.Point2D.Double(1,5);
    java.awt.geom.Point2D p2 = new java.awt.geom.Point2D.Double(4,2);
    java.awt.geom.Point2D p3 = new java.awt.geom.Point2D.Double(-2,8);
    return (distance(p1,p2,p3) == 0.0);
}

public static boolean test1() throws Throwable{
    java.awt.geom.Point2D p1 = new java.awt.geom.Point2D.Double(-1,6);
    java.awt.geom.Point2D p2 = new java.awt.geom.Point2D.Double(3,11);
    java.awt.geom.Point2D p3 = new java.awt.geom.Point2D.Double(-9,-4);
    return (distance(p1,p2,p3) == 0.0);
}

public static boolean test2() throws Throwable{
    java.awt.geom.Point2D p1 = new java.awt.geom.Point2D.Double(0,2);
    java.awt.geom.Point2D p2 = new java.awt.geom.Point2D.Double(0,10);
    java.awt.geom.Point2D p3 = new java.awt.geom.Point2D.Double(1,12);
    return (distance(p1,p2,p3) == 1.0);
}

public static boolean test() throws Throwable{
	return test0() && test1() && test2();
}
