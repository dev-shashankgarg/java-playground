package com.codechef.december19b;

import java.util.Scanner;
import java.util.stream.LongStream;

class BINADD {

  public static void main(String[] args) {

    try (Scanner intScanner = new Scanner(System.in)) {

      long testCases = intScanner.nextLong();

      LongStream.range(0, testCases).forEach(index -> {

        String num1String = intScanner.next();
        String num2String = intScanner.next();

        if (num1String.length() < num2String.length()) {
          num1String = generateZeroString(num2String.length() - num1String.length()) + num1String;
        } else {
          num2String = generateZeroString(num1String.length() - num2String.length()) + num2String;
        }

        int arrLen = num1String.length();
        String allZero = generateZeroString(num1String.length());

//        int[] num1 = new int[2 + arrLen];
//        int[] num2 = new int[2 + arrLen];
        int[] num3 = new int[2 + arrLen];

        int[] carry = new int[2 + arrLen];

        int count = 0;
        while (!hasAllZeroes(num2String, allZero)) {
          count++;
          Update operate = operate(carry, num3, num1String, num2String);
          num1String = operate.getA();
          num2String = operate.getB();
        }

        System.out.println(count);

      });

    } catch (Exception ignored) {
    }

  }

  private static String generateZeroString(int i) {
    return new String(new char[i]).replace("\0", "0");
  }

  private static Update operate(int[] carry, int[] num3, String num1, String num2) {
    for (int i = 0; i < num1.length(); i++) {
      num3[i] = (num1.charAt(i) - '0') ^ (num2.charAt(i) - '0');
      num1 = num1.substring(0, i) + (char) num3[i] + 48 + num1.substring(i + 1);
      if (i < num1.length() - 1) {
        carry[i] = (num1.charAt(i + 1) - '0') & (num2.charAt(i + 1) - '0');
      } else {
        carry[i] = 0;
      }
      num2 = num2.substring(0, i) + (char) carry[i]+ 48 + num2.substring(i + 1);
    }
    return new Update(num1, num2);
  }

  private static boolean hasAllZeroes(String num2String, String global) {
    return num2String.equals(global);
  }

  private static void initializeArray(int[] num, String numString) {
    int zeroValued = num.length - numString.length();
    for (int i = 0; i < zeroValued; i++) {
      num[i] = 0;
    }
    for (int i = zeroValued; i < zeroValued + numString.length(); i++) {
      num[i] = Integer.parseInt(Character.toString(numString.charAt(i - zeroValued)));
    }

  }

  static class Update {

    String a;
    String b;

    public Update(String a, String b) {
      this.a = a;
      this.b = b;
    }

    public String getA() {
      return a;
    }

    public void setA(String a) {
      this.a = a;
    }

    public String getB() {
      return b;
    }

    public void setB(String b) {
      this.b = b;
    }
  }

}
