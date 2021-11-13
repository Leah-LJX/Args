public static boolean test0() throws Throwable{
    return beforeSeveralMonths("2019-10-1", 1);
}

public static boolean test1() throws Throwable{
    return !beforeSeveralMonths("2019-10-2", 3);
}

public static boolean test2() throws Throwable{
    return !beforeSeveralMonths("2019-8-14", 5);
}

public static boolean test() throws Throwable{
    return test0() && test1() && test2();
}
