package com.codechef.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class TOTR {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String[] inputStr = reader.readLine().split(" ");
            int numStrings = Integer.parseInt(inputStr[0]);
            List<String> inputs = IntStream.range(0, numStrings).mapToObj(index -> {
                try {
                    return reader.readLine();
                } catch (IOException ignored) {
                }
                return null;
            }).filter(Objects::nonNull).collect(Collectors.toList());

            solve(inputStr[1], inputs);
        } catch (Exception ignored) {
        }
    }

    private static void solve(String mapping, List<String> inputs) {
        HashMap<Integer, String> dictionary = generateDictionary(mapping);
        inputs.forEach(input -> {
            for (int in : input.toCharArray()) {
                if (dictionary.get(in) != null) {
                    System.out.print(dictionary.get(in));
                } else {
                    System.out.print((char) in);
                }
            }
            System.out.println();
        });

    }

    private static HashMap<Integer, String> generateDictionary(String mapping) {
        HashMap<Integer, String> map = new HashMap<>();
        map.put((int) '_', " ");
        for (int i = 65; i < 91; i++) {
            map.put(i, Character.toString(mapping.charAt(i - 65)).toUpperCase());
        }
        for (int i = 97; i < 123; i++) {
            map.put(i, Character.toString(mapping.charAt(i - 97)));
        }
        return map;
    }
}
