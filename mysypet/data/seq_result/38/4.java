import java.io.*;
import java.util.*;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

public class Bench_38 {
  public static String readFile(File file) {
    {
      String s1;
      BufferedReader br1;
      FileReader fr1;
      String arg01;
      try {
        fr1 = new FileReader((arg01 = String.valueOf(file)));
        br1 = new BufferedReader(fr1);
        s1 = br1.readLine();
      } catch (FileNotFoundException _e) {
      } catch (IOException _e) {
      }
      return arg01;
    }
  }
}

