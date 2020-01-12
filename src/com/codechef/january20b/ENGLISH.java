package com.codechef.january20b;

import static java.util.stream.Collectors.toList;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class ENGLISH {

  public static void main(String[] args) {
    Print print = new Print();
    Scan scan = new Scan();

    int testCases = scan.scanInt();
    IntStream.range(0, testCases).forEach(test -> {
      int numWords = scan.scanInt();
      List<String> words = IntStream.range(0, numWords).mapToObj(index -> scan.scanString())
          .collect(toList());
      print.printLine("" + solve(numWords, words));
    });
    print.close();
  }

  private static long solve(int numWords, List<String> words) {
    return createPairs(words, 0);
  }

  private static long createPairs(List<String> words, int currentLevel) {

    long answer = 0L;
    List<String> toBeProcessedWords = words.stream()
        .filter(word -> word.length() > currentLevel).collect(toList());

    long terminatedWords = words.size() - toBeProcessedWords.size();

    Map<String, List<String>> wordLevelGrouping = toBeProcessedWords.stream()
        .collect(Collectors.groupingBy(word -> word.charAt(currentLevel) + Character
            .toString(word.charAt(word.length() - currentLevel - 1))));

    long readyToPairWords = wordLevelGrouping.keySet().stream()
        .filter(key -> wordLevelGrouping.get(key).size() < 2).count();

    long discards = 0;
    for (String key : wordLevelGrouping.keySet()) {
      List<String> wordSet = wordLevelGrouping.get(key);
      if (wordSet.size() >= 2) {
        discards += wordSet.size() % 2;
        answer += createPairs(wordLevelGrouping.get(key), currentLevel + 1);
      }
    }

    readyToPairWords += terminatedWords;
    readyToPairWords += discards;
    long readyToPairWordPairs = readyToPairWords / 2;

    answer += (Math.pow(currentLevel, 2) * readyToPairWordPairs);

    //System.out.println(words + ">>" + answer);

    return answer;
  }

  static class Scan {

    private byte[] buf = new byte[1024];
    private int index;
    private InputStream in;
    private int total;

    public Scan() {
      in = System.in;
    }

    public int scan() {
      if (total < 0) {
        throw new InputMismatchException();
      }
      if (index >= total) {
        index = 0;
        try {
          total = in.read(buf);
        } catch (IOException ignored) {
        }
        if (total <= 0) {
          return -1;
        }
      }
      return buf[index++];
    }

    public int scanInt() {
      int integer = 0;
      int n = scan();
      while (isWhiteSpace(n)) {
        n = scan();
      }
      int neg = 1;
      if (n == '-') {
        neg = -1;
        n = scan();
      }
      while (!isWhiteSpace(n)) {
        if (n >= '0' && n <= '9') {
          integer *= 10;
          integer += n - '0';
          n = scan();
        } else {
          throw new InputMismatchException();
        }
      }
      return neg * integer;
    }

    public double scanDouble() {
      double doub = 0;
      int n = scan();
      while (isWhiteSpace(n)) {
        n = scan();
      }
      int neg = 1;
      if (n == '-') {
        neg = -1;
        n = scan();
      }
      while (!isWhiteSpace(n) && n != '.') {
        if (n >= '0' && n <= '9') {
          doub *= 10;
          doub += n - '0';
          n = scan();
        } else {
          throw new InputMismatchException();
        }
      }
      if (n == '.') {
        n = scan();
        double temp = 1;
        while (!isWhiteSpace(n)) {
          if (n >= '0' && n <= '9') {
            temp /= 10;
            doub += (n - '0') * temp;
            n = scan();
          } else {
            throw new InputMismatchException();
          }
        }
      }
      return doub * neg;
    }

    public String scanString() {
      StringBuilder sb = new StringBuilder();
      int n = scan();
      while (isWhiteSpace(n)) {
        n = scan();
      }
      while (!isWhiteSpace(n)) {
        sb.append((char) n);
        n = scan();
      }
      return sb.toString();
    }

    private boolean isWhiteSpace(int n) {
      if (n == ' ' || n == '\n' || n == '\r' || n == '\t' || n == -1) {
        return true;
      }
      return false;
    }
  }

  static class Print {

    private final BufferedWriter bw;

    public Print() {
      bw = new BufferedWriter(new OutputStreamWriter(System.out));
    }

    public void print(String str) {
      try {
        bw.append(str);
      } catch (IOException ignored) {
      }
    }

    public void printLine(String str) {
      print(str);
      try {
        bw.append("\n");
      } catch (IOException ignored) {
      }
    }

    public void close() {
      try {
        bw.close();
      } catch (IOException ignored) {
      }
    }
  }

}
