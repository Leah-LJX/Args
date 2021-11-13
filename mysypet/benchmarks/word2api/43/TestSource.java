public static boolean test() throws Throwable{
	if (readInteger("benchmarks/test/43/file.txt") == 12345)
		return true;
	else
		return false;
}