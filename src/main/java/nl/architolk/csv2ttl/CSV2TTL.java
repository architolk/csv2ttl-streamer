package nl.architolk.csv2ttl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class CSV2TTL {

  static final int KADASTRALE_GEMEENTE_CODE = 2;

  private static header() {
    return null;
  }

  private static boolean filter(String line) {
    return line.contains(",\"LSE00\",");
  }

  private static String process(String line) {
    String[] items = line.split(",");
    return items[KADASTRALE_GEMEENTE_CODE];
  }

  public static void main(String[] args) {

    System.out.println("== CSV2TTL ==");
    System.out.println("Input: " + args[0]);
    System.out.println("Output: " + args[1]);

    try(BufferedReader inputReader = new BufferedReader(new FileReader(args[0]))) {
      try(PrintWriter outputWriter = new PrintWriter(new BufferedWriter(new FileWriter(args[1])))) {
        String header = header();
        if (header != null) {
          outputWriter.println(header);
        }
        String line = inputReader.readLine();

        while (line != null) {
            if (filter(line)) {
              outputWriter.println(process(line));
            }
            line = inputReader.readLine();
        }
      }
    } catch (Exception e) {
      System.out.println("Error: " + e.getMessage());
    }
  }
}
