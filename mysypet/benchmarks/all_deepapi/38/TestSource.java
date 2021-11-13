public static boolean test() throws Throwable {
    java.lang.String fileName = "benchmarks/google/38/test.txt";
    
	return readFile(fileName).equals("Fafa");
}
