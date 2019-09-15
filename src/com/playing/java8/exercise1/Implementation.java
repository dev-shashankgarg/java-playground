package com.playing.java8.exercise1;

import com.playing.java8.exercise1.helper.Trader;
import com.playing.java8.exercise1.helper.Transaction;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Implementation {

  /**
   * 1 Find all transactions in the year 2011 and sort them by value (small to high).
   * 2 What are all the unique cities where the traders work?
   * 3 Find all traders from Cambridge and sort them by name.
   * 4 Return a string of all traders’ names sorted alphabetically.
   * 5 Are any traders based in Milan?
   * 6 Print the values of all transactions from the traders living in Cambridge.
   * 7 What’s the highest value of all the transactions?
   * 8 Find the transaction with the smallest value.
   */
  public static void main(String[] args) {

    Trader raoul = new Trader("Raoul", "Cambridge");
    Trader mario = new Trader("Mario", "Milan");
    Trader alan = new Trader("Alan", "Cambridge");
    Trader brian = new Trader("Brian", "Cambridge");
    List<Transaction> transactions = Arrays.asList(
        new Transaction(brian, 2011, 300),
        new Transaction(raoul, 2012, 1000),
        new Transaction(raoul, 2011, 400),
        new Transaction(mario, 2012, 710),
        new Transaction(mario, 2012, 700),
        new Transaction(alan, 2012, 950)
    );

    solution1(transactions);
    System.out.println("-------------------");
    solution2(transactions);
    System.out.println("-------------------");
    solution3(transactions);
    System.out.println("-------------------");
    solution4(transactions);
    System.out.println("-------------------");
    solution5(transactions);
    System.out.println("-------------------");
    solution6(transactions);
    System.out.println("-------------------");
    solution7(transactions);
    System.out.println("-------------------");
    solution8(transactions);
    System.out.println("-------------------");


  }

  private static void solution1(List<Transaction> transactions) {

    transactions.stream().filter(t -> t.getYear() == 2011)
        .sorted(Comparator.comparing(Transaction::getValue)).forEach(System.out::println);

  }

  private static void solution2(List<Transaction> transactions) {

    transactions.stream().map(transaction -> transaction.getTrader().getCity()).distinct()
        .forEach(System.out::println);

  }

  private static void solution3(List<Transaction> transactions) {

    transactions.stream().map(Transaction::getTrader)
        .filter(trader -> trader.getCity().equals("Cambridge"))
        .sorted(Comparator.comparing(Trader::getName)).distinct().forEach(System.out::println);
  }

  private static void solution4(List<Transaction> transactions) {

    Optional<String> traderName = transactions.stream().map(Transaction::getTrader)
        .map(Trader::getName)
        .distinct()
        .sorted(String::compareTo).reduce((a, b) -> a + "," + b);

    System.out.println(traderName.orElse(""));

  }

  private static void solution5(List<Transaction> transactions) {

    Integer tradersInMilan = transactions.stream().map(Transaction::getTrader).distinct()
        .filter(trader -> trader.getCity().equals("Milan")).map(trader -> 1)
        .reduce(0, Integer::sum);

    System.out.println("Are there any traders in Milan : " + (tradersInMilan));

    boolean tradersInMilan2 = transactions.stream()
        .anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));

    System.out.println("(2)Are there any traders in Milan : " + (tradersInMilan2));

  }

  private static void solution6(List<Transaction> transactions) {

    transactions.stream()
        .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
        .map(Transaction::getValue)
        .forEach(System.out::println);

  }

  private static void solution7(List<Transaction> transactions) {

    Optional<Integer> maxTransactionValue = transactions.stream()
        .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
        .map(Transaction::getValue).reduce(Integer::max);

    System.out.println("Max Transaction Value: " + maxTransactionValue.orElse(0));

  }

  private static void solution8(List<Transaction> transactions) {

    Optional<Transaction> transactionWithLeastValue = transactions.stream()
        .reduce((a, b) -> a.getValue() < b.getValue() ? a : b);

    System.out.println("Transaction with least value: " + transactionWithLeastValue.orElse(null));

    Optional<Transaction> transactionWithLeastValue2 = transactions.stream()
        .min(Comparator.comparing(Transaction::getValue));

  }

}
