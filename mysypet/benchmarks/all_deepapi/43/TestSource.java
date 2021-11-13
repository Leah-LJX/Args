public static boolean test() throws Throwable{
	if (readInteger("benchmarks/google/43/file.txt") == 12345)
		return true;
	else
		return false;
}
