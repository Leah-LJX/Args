public static boolean test0() throws Throwable{
	return !beforeSeveralMonths("2019-5-14", 2);
}

public static boolean test1() throws Throwable{
	return beforeSeveralMonths("2018-7-14", 3);
}

public static boolean test2() throws Throwable{
	return beforeSeveralMonths("2013-5-14", 20);
}

public static boolean test() throws Throwable{
	return test0() && test1() && test2();
}