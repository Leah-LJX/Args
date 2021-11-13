public static boolean test() throws Throwable {
    java.lang.String url = "https://github.com";
    java.lang.String title = Source.getTitle(url);
    if ("The world’s leading software development platform · GitHub".equals(title))
        return true;
    else 
        return false;
}
