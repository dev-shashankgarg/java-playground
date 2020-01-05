package com.codechef.utils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

class Print {
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
