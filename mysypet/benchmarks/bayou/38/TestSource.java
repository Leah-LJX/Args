public static boolean test() throws Throwable {
    java.lang.String fileName = "benchmarks/bayou/38/test.txt";
    java.io.File file = new java.io.File(fileName);
	return readFile(file).equals("Fafa");
}
