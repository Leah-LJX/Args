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
      BufferedReader br1;
      String s1;
      FileReader fr1;
      try {
        fr1 = new FileReader(file);
        br1 = new BufferedReader(fr1);
        while ((s1 = br1.readLine()) != null) {}
      } catch (FileNotFoundException _e) {
      } catch (IOException _e) {
      }
      return s1;
    }
  }
}

