package com.codechef.january20b;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class ENGLISH {

    public static void main(String[] args) throws IOException {
        Scan scan = new Scan();
        Print print = new Print();

        int testCases = scan.scanInt();
        IntStream.range(0, testCases).forEach(test -> {
            try {
                int numWords = scan.scanInt();
                List<String> words = new ArrayList<>();
                for (int i = 0; i < numWords; i++) {
                    words.add(scan.scanString());
                }
                print.printLine("" + solve(words, numWords));
            } catch (IOException ignored) {
            }
        });
        print.close();
    }

    private static long solve(List<String> words, int numWords) {
        System.out.println(numWords);
        System.out.println(words.stream().sorted().collect(Collectors.toList()));

        if (words.size() <= 1) return 0;
        if (words.size() == 2) return calculate(words.get(0), words.get(1));
        int earliestAvailableWordIndex = 0;
        long answer = 0;

        for (int index = 1; index < words.size(); index++) {

            if (earliestAvailableWordIndex >= words.size()) break;

            long leftEval = calculate(words.get(earliestAvailableWordIndex), words.get(index));
            if (leftEval == 0) {
                earliestAvailableWordIndex = index;
                continue;
            }
            long rightEval = (index + 1 > words.size()) ? -1 : calculate(words.get(index + 1), words.get(index));

            if (leftEval > rightEval) {
                answer += leftEval;
                earliestAvailableWordIndex = index + 1;
                continue;
            }

            if (rightEval > leftEval) {
                answer += rightEval;
            }
        }
        return answer;
    }

    private static long calculate(String left, String right) {
        int match = 0;
        if (left.length() < right.length()) {
            for (int i = 0; i < left.length(); i++) {
                if (left.charAt(i) == right.charAt(i)) {
                    match++;
                } else {
                    break;
                }
            }
        } else {
            for (int i = 0; i < right.length(); i++) {
                if (right.charAt(i) == left.charAt(i)) {
                    match++;
                } else {
                    break;
                }
            }
        }
        return match * match;
    }


    // IO
    static class Scan {
        private byte[] buf = new byte[1024];
        private int index;
        private InputStream in;
        private int total;

        public Scan() {
            in = System.in;
        }

        public int scan() throws IOException {
            if (total < 0)
                throw new InputMismatchException();
            if (index >= total) {
                index = 0;
                total = in.read(buf);
                if (total <= 0)
                    return -1;
            }
            return buf[index++];
        }

        public int scanInt() throws IOException {
            int integer = 0;
            int n = scan();
            while (isWhiteSpace(n))
                n = scan();
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
                } else throw new InputMismatchException();
            }
            return neg * integer;
        }

        public double scanDouble() throws IOException {
            double doub = 0;
            int n = scan();
            while (isWhiteSpace(n))
                n = scan();
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
                } else throw new InputMismatchException();
            }
            if (n == '.') {
                n = scan();
                double temp = 1;
                while (!isWhiteSpace(n)) {
                    if (n >= '0' && n <= '9') {
                        temp /= 10;
                        doub += (n - '0') * temp;
                        n = scan();
                    } else throw new InputMismatchException();
                }
            }
            return doub * neg;
        }

        public String scanString() throws IOException {
            StringBuilder sb = new StringBuilder();
            int n = scan();
            while (isWhiteSpace(n))
                n = scan();
            while (!isWhiteSpace(n)) {
                sb.append((char) n);
                n = scan();
            }
            return sb.toString();
        }

        private boolean isWhiteSpace(int n) {
            if (n == ' ' || n == '\n' || n == '\r' || n == '\t' || n == -1)
                return true;
            return false;
        }
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
}
