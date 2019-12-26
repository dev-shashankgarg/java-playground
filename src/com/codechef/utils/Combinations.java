package com.codechef.utils;

public class Combinations {

  /**
   * Utility to calculate value of nCr mod p.
   * where p is some large prime number.
   * Uses Fermat Little Theorem. Makes use of factorials (Enables pre-computation for time limited coding contests)
   */

  private static long p = 1000000007;
  private static int max = 100001;
  private static long factNumInv[] = new long[max + 1];
  private static long naturalNumInv[] = new long[max + 1];
  private static long fact[] = new long[max + 1];

  static {
    invOfNum();
    invOfFact();
    fact();
  }

  private static void fact() {
    fact[0] = 1;
    for (int i = 1; i <= max; i++) {
      fact[i] = ((fact[i - 1]) % p * i) % p;
    }
  }

  private static void invOfFact() {
    factNumInv[0] = 1;
    factNumInv[1] = 1;
    for (int i = 2; i <= max; i++) {
      factNumInv[i] = ((naturalNumInv[i]) % p * (factNumInv[i - 1]) % p) % p;
    }
  }

  private static void invOfNum() {
    naturalNumInv[0] = 1;
    naturalNumInv[1] = 1;
    for (int i = 2; i <= max; i++) {
      naturalNumInv[i] = ((naturalNumInv[(int) p % i] % p) * (p - (p / i))) % p;
    }
  }

  public long nCr(long n, long r) {
    long ans = (((fact[(int) n] % p) *
        (factNumInv[(int) r] % p)) % p * (factNumInv[(int) (n - r)] % p)) % p;
    return ans;
  }

}
