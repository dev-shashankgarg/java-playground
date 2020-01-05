package com.codechef.january20b;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.IntStream;

class DYNAMO {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int testCases = Integer.parseInt(reader.readLine());
            IntStream.range(0, testCases).forEach(test -> {
                try {

                    int nPerf = Integer.parseInt(reader.readLine());
                    long pow10 = (long) Math.pow(10, nPerf);
                    long maxAttainable = 2 * pow10;
                    long firstGuess = Long.parseLong(reader.readLine());

                    System.out.println(firstGuess + maxAttainable);

                    long secondGuess = Long.parseLong(reader.readLine());
                    System.out.println(pow10 - secondGuess);

                    long thirdGuess = Long.parseLong(reader.readLine());
                    System.out.println(pow10 - thirdGuess);

                    int result = Integer.parseInt(reader.readLine());

                    if (result != 1) {
                        System.exit(1);
                    }
                } catch (Exception ignored) {
                }
            });
        } catch (Exception ignored) {
        }
    }
}
