package com.codechef.january20b;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;

class CHEFPSA {

    private static BufferedInputStream in = new BufferedInputStream(System.in);

    private static final int MOD = 1000000007;
    private static final int LIMIT = 100007;

    private static long[] factorial = new long[LIMIT];

    private static long[] invFactorial = new long[LIMIT];
    private static long[] invOfNum = new long[LIMIT];

    private static long[] pow2 = new long[LIMIT];

    private static void preCompute() {
        factorial[0] = 1;
        factorial[1] = 1;

        pow2[0] = 1;
        pow2[1] = 2;

        invOfNum[0] = 1;
        invOfNum[1] = 1;

        invFactorial[0] = 1;
        invFactorial[1] = 1;

        for (int i = 2; i < LIMIT; i++) {
            factorial[i] = (i * factorial[i - 1]) % MOD;
            pow2[i] = (2 * pow2[i - 1]) % MOD;
            invOfNum[i] = ((MOD - (MOD / i)) * invOfNum[MOD % i]) % MOD;
            invFactorial[i] = (invOfNum[i] * invFactorial[i - 1]) % MOD;
        }
    }

    public static void main(String[] args) throws IOException {
        Print print = new Print();
        preCompute();
        int testCases = Integer.parseInt(read());
        IntStream.range(0, testCases).forEach(test -> {
            int arrLen = Integer.parseInt(read());
            long[] data = new long[arrLen * 2];
            for (int i = 0; i < 2 * arrLen; i++) {
                data[i] = Long.parseLong(read());
            }
            try {
                print.printLine("" + solve(data, arrLen));
            } catch (IOException ignored) {
            }
        });
        print.close();
    }

    private static long solve(long[] data, int arrLen) {
        Stats stats = optimalGenerateStats(data, arrLen);
        if (stats == null) return 0;
        if (!stats.hasInfo) return 1;

        int pairsAsserted = 0;
        int repetitions = 0;
        int noRepeatPairs = stats.distinctPairs.size();
        int halfSumPairs = (int) stats.halfValCount;

        long step1Perm = factorial[noRepeatPairs];
        pairsAsserted += noRepeatPairs;
        long step2Perm = (step1Perm * nCr(halfSumPairs + pairsAsserted, halfSumPairs)) % MOD;
        pairsAsserted += halfSumPairs;

        HashMap<Long, Long> repeatedPairs = stats.repeatedPairs;

        for (Long key : repeatedPairs.keySet()) {
            int repetition = (int) repeatedPairs.get(key).longValue();
            step2Perm = (step2Perm * nCr(pairsAsserted + repetition, repetition)) % MOD;
            pairsAsserted += repetition;
            repetitions += repetition;
        }
        return (step2Perm * pow2[noRepeatPairs + repetitions]) % MOD;
    }

    private static long nCr(int n, int r) {
        if (n == 0 || r == 0) return 1;
        return ((factorial[n] * invFactorial[n - r]) % MOD * invFactorial[r]) % MOD;
    }

    private static Stats optimalGenerateStats(long[] data, int arrLen) {
        Stats stats = new Stats();
        Map<Long, Long> valCount = new HashMap<>();
        long sum = 0;
        for (long num : data) {
            sum += num;
            Long occurrence = valCount.get(num);
            if (occurrence == null) {
                valCount.put(num, 1L);
            } else {
                valCount.put(num, occurrence + 1);
            }
        }
        long originalArrSum = sum / (arrLen + 1);
        boolean evenSum = (originalArrSum % 2 == 0);
        long halfArrSum = originalArrSum / 2;

        //checking abnormalities - (1)
        Long val = valCount.get(originalArrSum);
        if (val == null || val < 2) return null;
        if (val == 2) {
            valCount.remove(originalArrSum);
        } else {
            valCount.put(originalArrSum, val - 2);
        }
        if (valCount.isEmpty()) return stats;
        stats.hasInfo = true;

        for (Long key : valCount.keySet()) {
            if (key.equals(halfArrSum) && evenSum) {
                Long count = valCount.get(key);
                if (count % 2 == 1) return null;
                stats.halfValCount = count / 2;
            } else if (valCount.get(key) == 1) {
                if (!stats.distinctPairs.contains(originalArrSum - key)) {
                    Long revCount = valCount.get(originalArrSum - key);
                    if (revCount == null || revCount != 1) {
                        return null;
                    }
                    stats.distinctPairs.add(key);
                }
            } else {
                if (!stats.repeatedPairs.containsKey(originalArrSum - key)) {
                    Long count = valCount.get(key);
                    Long revCount = valCount.get(originalArrSum - key);
                    if (revCount == null || !revCount.equals(count)) {
                        return null;
                    }
                    stats.repeatedPairs.put(key, count);
                }
            }
        }

        return stats;
    }

    static class Stats {
        Set<Long> distinctPairs = new HashSet<>();
        long halfValCount = 0;
        HashMap<Long, Long> repeatedPairs = new HashMap<>();
        boolean hasInfo = false;
    }

    static class Print {
        private final BufferedWriter bw;

        public Print() {
            bw = new BufferedWriter(new OutputStreamWriter(System.out));
        }

        public void print(String str) throws IOException {
            bw.append(str);
        }

        public void printLine(String str) throws IOException {
            print(str);
            bw.append("\n");
        }

        public void close() throws IOException {
            bw.close();
        }
    }

    private static String read() {
        StringBuilder str = new StringBuilder();
        try {
            int a = in.read();
            while (a == 32)
                a = in.read();
            while (a != 10 && a != 32 && a != -1) {
                str.append((char) a);
                a = in.read();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str.toString();
    }

}