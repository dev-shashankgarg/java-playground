package com.codechef.december19b;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.LongStream;

class BINXOR {

  private static final Integer MODULO = 1000000007;
  private static Map<Integer, Integer> factorialMap = new HashMap<>();

  public static void main(String[] args) {

    try (Scanner intScanner = new Scanner(System.in)) {

      long testCases = intScanner.nextLong();
      LongStream.range(0, testCases).forEach(index -> {

        int len = intScanner.nextInt();

        String num1 = intScanner.next();
        String num2 = intScanner.next();

        String[] num1Arr = num1.split("");
        String[] num2Arr = num2.split("");

        int num1Ones = (int) Arrays.stream(num1Arr).filter(a -> a.equals("1")).count();
        int num2Ones = (int) Arrays.stream(num2Arr).filter(a -> a.equals("1")).count();

        int num1Zeroes = (int) Arrays.stream(num1Arr).filter(a -> a.equals("0")).count();
        int num2Zeroes = (int) Arrays.stream(num2Arr).filter(a -> a.equals("0")).count();

        int xorResultMaxOnes = Math.min(num1Ones, num2Zeroes) + Math.min(num2Ones, num1Zeroes);
        int xorResultMinOnes =
            len - (Math.min(num1Ones, num2Ones) + Math.min(num1Zeroes, num2Zeroes));

        long answer = 0;

        //System.out.println(xorResultMaxOnes + " " + xorResultMinOnes);

        for (int index1 = xorResultMinOnes; index1 <= len; index1 += 2) {
          if (index1 <= xorResultMaxOnes) {
            answer = (answer + nCrModpLucas(len, index1, MODULO)) % MODULO;
          }
        }

        System.out.println(answer);

      });
    } catch (Exception ignored) {
    }

  }

  static int nCrModpDP(int n, int r, int p)
  {
    int[] C=new int[r+1];
    C[0] = 1;
    for (int i = 1; i <= n; i++)
    {
      for (int j = Math.min(i, r); j > 0; j--)
        C[j] = (C[j] + C[j-1])%p;
    }
    return C[r];
  }

  static int nCrModpLucas(int n, int r, int p)
  {
    if (r==0)
      return 1;

    int ni = n%p;
    int ri = r%p;

    return (nCrModpLucas(n/p, r/p, p) * nCrModpDP(ni, ri, p)) % p;
  }

}
