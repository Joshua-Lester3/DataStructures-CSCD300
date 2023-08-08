/* Author: Joshua Lester
 * Class: CSCD300
 * Programming Assignment 2
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BestTrading {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("No arguments given.");
        } else {
            String fileName = args[0];
            File f = new File(fileName);
            Scanner fileScanner;
            try {
                fileScanner = new Scanner(f);
                int count = 0;
                while (fileScanner.hasNextInt()) {
                    count++;
                    fileScanner.nextInt();
                }
                fileScanner.close();
                fileScanner = new Scanner(f);
                int[] array = new int[count];
                for (int i = 0; i < count; i++) {
                    array[i] = fileScanner.nextInt();
                }
                int[] arrayOfResult = bestTrading(array, 0, array.length - 1);
                String result = "A[" + arrayOfResult[0] + "," +
                        arrayOfResult[1] + "," + arrayOfResult[2] + "]";
                System.out.println(result);
            } catch (FileNotFoundException e) {
                System.out.println("File not found.");
            }
        }
    }

    // Recursive best trading method to find the best buy day and sell day to maximize profits
    public static int[] bestTrading(int[] array, int low, int high) {
        if (low > high) {
            throw new IllegalArgumentException("Bad params in bestTrading");
        }
        if (low == high) {
            int[] res = {low, high, 0};
            return res;
        }
        int mid = (low + high) / 2;
        int[] left = bestTrading(array, low, mid);
        int[] right = bestTrading(array, mid + 1, high);
        int[] across = bestTradingAcross(array, low, high);

        // Assign variables to 3rd element in arrays - the profit
        int leftProfit = left[2];
        int rightProfit = right[2];
        int acrossProfit = across[2];
        if (leftProfit >= rightProfit && leftProfit >= acrossProfit) {
            return left;
        } else if (rightProfit >= leftProfit && rightProfit >= acrossProfit) {
            return right;
        } else { // acrossProfit >= leftProfit && acrossProfit >= rightProfit
            return across;
        }
    }

    public static int[] bestTradingAcross(int[] array, int low, int high) {
        if (low >= high) {
            throw new IllegalArgumentException("Bad params in bestTradingAcross");
        }
        int mid = (low + high) / 2;

        int lowest = array[low];
        int lowestIndex = low;
        for (int i = low + 1; i <= mid; i++) {
            if (lowest > array[i]) {
                lowestIndex = i;
                lowest = array[lowestIndex];
            }
        }

        int highest = array[mid + 1];
        int highestIndex = mid + 1;
        for (int i = mid + 2; i <= high; i++) {
            if (highest < array[i]) {
                highestIndex = i;
                highest = array[highestIndex];
            }
        }
        int[] res = {lowestIndex, highestIndex, array[highestIndex] - array[lowestIndex]};
        return res;
    }
}
