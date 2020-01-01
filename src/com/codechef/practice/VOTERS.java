package com.codechef.practice;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class VOTERS {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {

            int list1Size = scanner.nextInt();
            int list2Size = scanner.nextInt();
            int list3Size = scanner.nextInt();

            HashSet<Integer> list1 = new HashSet<>();
            HashSet<Integer> list2 = new HashSet<>();
            HashSet<Integer> list3 = new HashSet<>();

            IntStream.range(0, list1Size).forEach(index -> list1.add(scanner.nextInt()));
            IntStream.range(0, list2Size).forEach(index -> list2.add(scanner.nextInt()));
            IntStream.range(0, list3Size).forEach(index -> list3.add(scanner.nextInt()));

            Map<Integer, Integer> voterMap = new HashMap<>();

            list1.forEach(val -> {
                if (!voterMap.containsKey(val)) {
                    voterMap.put(val, 1);
                } else {
                    voterMap.put(val, voterMap.get(val) + 1);
                }
            });

            list2.forEach(val -> {
                if (!voterMap.containsKey(val)) {
                    voterMap.put(val, 1);
                } else {
                    voterMap.put(val, voterMap.get(val) + 1);
                }
            });

            list3.forEach(val -> {
                if (!voterMap.containsKey(val)) {
                    voterMap.put(val, 1);
                } else {
                    voterMap.put(val, voterMap.get(val) + 1);
                }
            });

            List<Integer> eligibleVoters = voterMap.keySet().stream()
                    .filter(key -> voterMap.get(key) > 1)
                    .sorted()
                    .collect(Collectors.toList());

            System.out.println(eligibleVoters.size());
            eligibleVoters.forEach(System.out::println);

        } catch (Exception ignored) {
            System.out.println(ignored);
        }
    }
}
