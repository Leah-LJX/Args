public static boolean test0() throws Throwable {
    java.math.BigInteger p1 = new java.math.BigInteger("10");
    java.math.BigInteger p2 = new java.math.BigInteger("5");
    return (leastCommonMultiple(p1,p2).compareTo(new java.math.BigInteger("10"))==0);
}

public static boolean test1() throws Throwable {
    java.math.BigInteger p1 = new java.math.BigInteger("5");
    java.math.BigInteger p2 = new java.math.BigInteger("3");
    return (leastCommonMultiple(p1,p2).compareTo(new java.math.BigInteger("15"))==0);
}

public static boolean test2() throws Throwable {
    java.math.BigInteger p1 = new java.math.BigInteger("15");
    java.math.BigInteger p2 = new java.math.BigInteger("20");
    return (leastCommonMultiple(p1,p2).compareTo(new java.math.BigInteger("60"))==0);
}

public static boolean test() throws Throwable {
    return test0() && test1() && test2();
}
