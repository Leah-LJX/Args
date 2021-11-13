public static boolean test1() throws Throwable {
	java.util.Date date = new java.util.Date(1000);
    java.lang.String format = "yyyy-MM-dd HH:mm:ss";
    java.lang.String timeZone = "UTC";
    java.lang.String local = changeTimeZone(date, format, timeZone);
    if (local.equals("1970-01-01 00:00:01"))
        return true;
    else
        return false;
}

public static boolean test2() throws Throwable {
    java.util.Date date = new java.util.Date(2000);
    java.lang.String format = "yyyy-MM-dd HH:mm:ss";
    java.lang.String timeZone = "GMT+16";
    java.lang.String local = changeTimeZone(date, format, timeZone);
    if (local.equals("1970-01-01 16:00:02"))
        return true;
    else
        return false;
}

public static boolean test() throws Throwable {
    return test1() && test2();
}