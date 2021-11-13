public static boolean test0() throws Throwable{		
	return reverseAndAppend("murder").equals("murderredrum");
}
public static boolean test1() throws Throwable{
	return reverseAndAppend("hello").equals("helloolleh");
}
public static boolean test() throws Throwable{
	return test0() && test1();
}