public static boolean test1() throws Throwable {
	java.lang.String str = "{ \"name\": \"Alice\", \"gender\": \"male\" }";
    java.lang.String ele = "name";
    java.lang.String result = parseJson(str, ele);
    if (result.equals("Alice"))
        return true;
    else
        return false;
}

public static boolean test2() throws Throwable {
	java.lang.String str = "{ \"name\": \"Alice\", \"gender\": \"male\" }";
    java.lang.String ele = "gender";
    java.lang.String result = parseJson(str, ele);
    if (result.equals("male"))
        return true;
    else
        return false;
}

public static boolean test() throws Throwable {
	
    return test1() && test2();
}