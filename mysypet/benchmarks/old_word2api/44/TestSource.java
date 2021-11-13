public static boolean test0() throws Throwable {
  return hex2Float("BF800000", 16) == -1.0;
}
public static boolean test1() throws Throwable{
  return hex2Float("BF700000", 16) == -0.9375;
}
public static boolean test() throws Throwable{
  return test0() && test1();
}