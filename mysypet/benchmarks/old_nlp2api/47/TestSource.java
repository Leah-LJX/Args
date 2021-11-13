public static boolean test0() throws Throwable{
	return getDivision(1524, 1000, 251, 100).toString().equals("762 / 1255");
}
public static boolean test1() throws Throwable{
	return getDivision(1524, 1000, 5, 2).toString().equals("381 / 625");
}
public static boolean test() throws Throwable{
	return test0() && test1(); 
}