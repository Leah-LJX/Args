public static boolean test() throws Throwable {
  
    return test1() && test2();
}

public static boolean test1() throws Throwable {

	java.lang.String format = "yyyy.MM.dd";
	java.lang.String date1 = "2019.11.25";
	java.lang.String date2 = "2019.12.11";
	boolean before = compare2Dates(date1, date2, format);
  
    return before;
}

public static boolean test2() throws Throwable {

	java.lang.String format = "yyyy.MM.dd";
	java.lang.String date1 = "2019.12.25";
	java.lang.String date2 = "2019.11.11";
	boolean before = compare2Dates(date1, date2, format);
  
    return !before;
}
