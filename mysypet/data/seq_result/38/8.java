import java.io.*;
import java.util.*;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

public class Bench_38 {
  public static String readFile(File file) {
    {
      FileReader fr1;
      String arg01;
      try {
        fr1 = new FileReader((arg01 = String.valueOf(file)));
      } catch (FileNotFoundException _e) {
      }
      return arg01;
    }
  }
}

