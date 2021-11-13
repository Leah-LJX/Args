public static boolean test0() throws Throwable {		
	return (dayOfWeek("2015/11/10", "yyyy/MM/dd") == "星期二");	
}
	
public static boolean test1() throws Throwable {
	return (dayOfWeek("2015/11/11", "yyyy/MM/dd") == "星期三");					
}
	

public static boolean test() throws Throwable {
		
    return test0() && test1();

} 
