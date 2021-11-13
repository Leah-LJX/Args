public static boolean test() throws Throwable {    
    long date = 15565555;
	java.lang.String format = "yyyy MM dd HH:mm:ss";
	java.lang.String result = convertTime(date, format);
    return (result.equals("1970 01 01 12:19:25"));    
}