package com.codechef.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class RIGHTRI {

    private static final double THRESHOLD = .001;

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int trianglesCount = Integer.parseInt(reader.readLine());
            List<Triangle> triangles = IntStream.range(0, trianglesCount).mapToObj(testCase -> {
                try {
                    String[] coordinates = reader.readLine().split(" ");
                    return new Triangle(Integer.parseInt(coordinates[0]),
                            Integer.parseInt(coordinates[1]),
                            Integer.parseInt(coordinates[2]),
                            Integer.parseInt(coordinates[3]),
                            Integer.parseInt(coordinates[4]),
                            Integer.parseInt(coordinates[5]));
                } catch (IOException ignored) {
                }
                return null;
            }).filter(Objects::nonNull).collect(Collectors.toList());
            System.out.println(solve(triangles));
        } catch (Exception ignored) {
        }
    }

    private static long solve(List<Triangle> triangles) {
        return triangles.stream().filter(triangle -> {

            double side1 = Math.sqrt(Math.pow(triangle.x1 - triangle.x2, 2) + Math.pow(triangle.y1 - triangle.y2, 2));
            double side2 = Math.sqrt(Math.pow(triangle.x2 - triangle.x3, 2) + Math.pow(triangle.y2 - triangle.y3, 2));
            double side3 = Math.sqrt(Math.pow(triangle.x3 - triangle.x1, 2) + Math.pow(triangle.y3 - triangle.y1, 2));

            if (side1 > side2 && side1 > side3) {
                return Math.abs(Math.pow(side1, 2) - (Math.pow(side2, 2) + Math.pow(side3, 2))) < THRESHOLD;
            } else if (side2 > side3 && side2 > side1) {
                return Math.abs(Math.pow(side2, 2) - (Math.pow(side1, 2) + Math.pow(side3, 2))) < THRESHOLD;
            } else if (side3 > side2 && side3 > side1) {
                return Math.abs(Math.pow(side3, 2) - (Math.pow(side2, 2) + Math.pow(side1, 2))) < THRESHOLD;
            }
            return false;
        }).count();
    }

    static class Triangle {
        int x1;
        int y1;
        int x2;
        int y2;
        int x3;
        int y3;

        Triangle(int x1, int y1, int x2, int y2, int x3, int y3) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.x3 = x3;
            this.y3 = y3;
        }
    }
}
