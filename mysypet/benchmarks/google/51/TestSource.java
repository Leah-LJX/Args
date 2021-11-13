public static boolean test0() throws Throwable{
	java.net.URL url= new java.net.URL("http://www.baidu.com");
	return fetchHtml(url,"\n").equals("<!DOCTYPE html>\r");
}
public static boolean test1() throws Throwable{
	java.net.URL url= new java.net.URL("http://www.taobao.com");
	return fetchHtml(url,"\n").equals("<!DOCTYPE HTML PUBLIC \"-//IETF//DTD HTML 2.0//EN\">\r");
}
public static boolean test() throws Throwable{
	return test0() && test1();
}
