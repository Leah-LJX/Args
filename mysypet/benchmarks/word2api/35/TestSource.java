public static boolean test1() throws Throwable {
	java.lang.String format = "yyyy-MM-dd";
	java.lang.String time = "2017-12-31";
	java.text.SimpleDateFormat sd = new java.text.SimpleDateFormat(format);
	java.util.Date date = sd.parse(time);

	int weekYear = getWeekYear(date);
    
    if(weekYear == 2018) 
        return true;
    else 
        return false;
}

public static boolean test2() throws Throwable {
	java.lang.String format = "yyyy-MM-dd";
	java.lang.String time = "2016-12-31";
	java.text.SimpleDateFormat sd = new java.text.SimpleDateFormat(format);
	java.util.Date date = sd.parse(time);

	int weekYear = getWeekYear(date);
    
    if(weekYear == 2016) 
        return true;
    else 
        return false;
}

public static boolean test() throws Throwable {
	
    return test1() && test2();
}