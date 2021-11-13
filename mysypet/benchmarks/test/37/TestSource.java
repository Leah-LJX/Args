public static boolean test0() throws Throwable {
	java.lang.String date = "2018-05-14";
	java.lang.String format = "yyyy-MM-dd";
	java.lang.String lastDay = getLastDayOfWeek(date, format);

    return (lastDay.equals("2018-05-20"));
}

public static boolean test1() throws Throwable {
    java.lang.String date = "2018-05-10";
	java.lang.String format = "yyyy-MM-dd";
	java.lang.String lastDay = getLastDayOfWeek(date, format);

    return (lastDay.equals("2018-05-13"));
}

public static boolean test() throws Throwable {
    return test0() && test1();
}
