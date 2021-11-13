import java.net;
import java.util.Scanner;

public static String fetchHtml(String arg0, String delimiter) throws Throwable{
    URL url = new URL(arg0);
    URLConnection connection = url.openConnection();
    InputStream is = connection.getInputStream();
    Scanner scanner = new Scanner(is);
    scanner.useDelimiter(delimiter);
    String content = scanner.next();
    return content;
}

<java.net.URL: void <init>(java.lang.String)>
<java.net.URL: java.net.URLConnection openConnection()>
<java.net.URLConnection: java.io.InputStream getInputStream()>
<java.util.Scanner: void <init>(java.io.InputStream)>
<java.util.Scanner: java.util.Scanner useDelimiter(java.lang.String)>
<java.util.Scanner: java.lang.String next()>
