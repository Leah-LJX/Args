public static boolean test0() throws Throwable {
		if(match("<[^>]*>", "Peter <5554>").equals("<5554>"))
			return true;
		else
			return false;
	}
	public static boolean test1() throws Throwable {
		if(match("<[^>]*>", "John <5556>").equals("<5556>"))
			return true;
		else
			return false;
	}
	public static boolean test() throws Throwable{
		return test0() && test1();
	}