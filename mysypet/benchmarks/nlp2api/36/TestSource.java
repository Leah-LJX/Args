public static boolean test1() throws Throwable {
	java.lang.String jsonLine = "{\"data1\": \"Hello world\", \"data2\": \"Hello2 world\"}";;
	java.lang.String element = "data1";
    java.lang.String str = parseJson(jsonLine, element);

    if (str.equals("Hello world"))
        return true;
    else
        return false;
}

public static boolean test2() throws Throwable {
	java.lang.String jsonLine = "{\"data1\": \"Hello world\", \"data2\": \"Hello2 world\"}";;
	java.lang.String element = "data2";
    java.lang.String str = parseJson(jsonLine, element);

    if (str.equals("Hello2 world"))
        return true;
    else
        return false;
}

public static boolean test() throws Throwable {
	
    return test1() && test2();
}