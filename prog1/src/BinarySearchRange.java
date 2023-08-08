/* Author: Joshua Lester
 * CSCD300
 * Programming Assignment 1
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BinarySearchRange {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("No command line arguments found");
        } else {
            // Take the first three arguments and convert them to file name, x and y
            String fileName = args[0];
            int x = Integer.parseInt(args[1]);
            int y = Integer.parseInt(args[2]);
            File file = new File(fileName);
            Scanner fileScanner;
            try {
                // Open the file, count the number of elements
                // and put them into an array
                int count = 0;
                fileScanner = new Scanner(file);
                while (fileScanner.hasNextInt()) {
                    fileScanner.nextInt();
                    count++;
                }
                fileScanner.close();
                fileScanner = new Scanner(file);
                int[] array = new int[count];
                for (int i = 0; i < count; i++) {
                    array[i] = fileScanner.nextInt();
                }
                // Print out results
                System.out.println(binarySearchRange(array, x, y));
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
            }
        }
    }

    public static String binarySearchRange(int[] array, int x, int y) {
        // Edge cases given by Professor Xu
        if (array.length == 0 || x > y) {
            return null;
        } else if (y < array[0]) {
            return null;
        } else if (x > array[array.length - 1]) {
            return null;
        } else if (x <= array[0] && y >= array[array.length - 1]) {
            return "A[0.." + (array.length - 1) + "]";
        } else {
            // The main algorithms:
            int s = findS(array, x);
            int t = findT(array, y);
            if (s > t || t < 0 || s < 0) {
                return null;
            }
            return "A[" + s + ".." + t + "]";
        }
    }

    // Helper method for binarySearchRange() method.
    // Finds the lowest, leftmost index that is greater than or equal to x
    public static int findS(int[] array, int x) {
        if (array[0] >= x) {
            return 0;
        }
        int low = 0;
        int high = array.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (array[mid] >= x) {
                if (array[mid - 1] < x) {
                    return mid;
                } else {
                    high = mid - 1;
                }
            } else { // array[mid] < x
                low = mid + 1;
            }
        }
        return -1;
    }

    // Helper method for binarySearchRange() method.
    // Finds the lowest, rightmost index that is less than or equal to y.
    public static int findT(int[] array, int y) {
        if (array[array.length - 1] <= y) {
            return array.length - 1;
        }
        int low = 0;
        int high = array.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (array[mid] <= y) {
                if (array[mid + 1] > y) {
                    return mid;
                } else {
                    low = mid + 1;
                }
            } else { // array[mid] > y
                high = mid - 1;
            }
        }
        return -1;
    }
}
