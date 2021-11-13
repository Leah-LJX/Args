public static boolean test() throws Throwable {
    java.lang.String fileName = "benchmarks/word2api/38/test.txt";
    java.io.File file = new java.io.File(fileName);
	return readFile(fileName).equals("Fafa");
}
