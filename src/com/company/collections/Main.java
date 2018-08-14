package com.company.collections;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class Main {

    /**
     * Not working. Next time
     *
     * @param args
     * @throws InterruptedException
     */
    @Deprecated
    public static void main(String[] args) throws InterruptedException {


//        List<Integer> rawDigits = new ArrayList<>();
        List<Integer> list = new Random()
                .ints(50000000, 2, 1000)
                .boxed()
                .collect(Collectors.toList());
        //random list .size() = 10,000 between 2 and 1000
        System.out.println("starting hard computations");
        long start = new Date().getTime();
        list.parallelStream()
                //0.0001
                .map(x -> Math.sqrt(x))
                //synchronized - 35-40
                .collect(Collectors.toList());

//        List<Integer> list1 = Collections.synchronizedList(new ArrayList<>());

        long end = new Date().getTime();
        System.out.println(end - start);


    }

    private static void concurrentMapExample() throws InterruptedException {
        //List
        //Set
        //Map

        List<List<Integer>> allDigitsList = new ArrayList<>();

        for (int i = 0; i < 200; i++) {
            allDigitsList.add(new ArrayList<>());
        }

        /**
         * ALL LISTS OF NUMBERS
         * List<List<Integer>>
         * [0,0,0,0,0,0]     .size() == 100,000
         * [1,1,1,1,1,1]     .size() == 100,000
         * [2,2,2,2,2,2]     .size() == 100,000
         * [3,3,3,3,3,3] ... .size() == 100,000
         * ...
         * [19,19,19,19,19]
         * [49,49,49,49,49]
         */
        int counter = 0;
        for (List<Integer> digit : allDigitsList) {
            for (int i = 0; i < 100000; i++) {
                digit.add(counter);
            }
            counter++;
        }

        for (List<Integer> digits : allDigitsList) {
            new ThreadCounter(digits).start();
        }

        Thread.sleep(3000);


        //Non-Thread-Safe
        ThreadCounter
                .getDigitsOccurrences()
                .entrySet()
//                .stream()
//                .filter(x -> x.getValue() < 100000)
                .forEach(x -> System.out.println(x));
    }
}

class ThreadCounter extends Thread {

    private static Map<Integer, Integer> digitsOccurrences = new ConcurrentHashMap<>();

    private List<Integer> threadDigits;

    public ThreadCounter(List<Integer> threadDigits) {
        this.threadDigits = threadDigits;
    }

    @Override
    public void run() {
        for (Integer digit : threadDigits) {
            if (!digitsOccurrences.containsKey(digit)) {
                digitsOccurrences.put(digit, 1);
            } else {
                int count = digitsOccurrences.get(digit);
                digitsOccurrences.put(digit, count + 1);
            }
        }
    }

    public static Map<Integer, Integer> getDigitsOccurrences() {
        return digitsOccurrences;
    }
}