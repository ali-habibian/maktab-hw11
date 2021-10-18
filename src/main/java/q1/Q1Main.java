package q1;

import java.util.ArrayList;
import java.util.Scanner;

public class Q1Main {

    private static boolean isEvenAdded = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Object monitorObject = new Object();

        ArrayList<Integer> evenNumbers = new ArrayList<>();
        ArrayList<Integer> oddNumbers = new ArrayList<>();
        ArrayList<Integer> allNumbers = new ArrayList<>();

        System.out.println("Enter dest number: ");
        int number = scanner.nextInt();

        for (int i = 0; i <= number; i++) {
            if (i % 2 == 0)
                evenNumbers.add(i);
            else
                oddNumbers.add(i);
        }

        Thread evenThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (Integer evenNumber : evenNumbers) {
                    synchronized (monitorObject) {
                        try {
                            while (isEvenAdded) {
                                monitorObject.wait();
                            }
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            e.printStackTrace();
                        }
                        allNumbers.add(evenNumber);
                        isEvenAdded = true;
                        monitorObject.notifyAll();
                    }
                }
            }
        });

        evenThread.start();
        Thread oddThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (Integer oddNumber : oddNumbers) {
                    synchronized (monitorObject) {
                        try {
                            while (!isEvenAdded) {
                                monitorObject.wait();
                            }
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            e.printStackTrace();
                        }
                        allNumbers.add(oddNumber);
                        isEvenAdded = false;
                        monitorObject.notifyAll();
                    }
                }
            }
        });
        oddThread.start();

        try {
            evenThread.join();
            oddThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Even numbers: " + evenNumbers);
        System.out.println("Odd numbers: " + oddNumbers);
        System.out.println("All numbers: " + allNumbers);
    }
}
