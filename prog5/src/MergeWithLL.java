import joshua_lester_00932346_cscd300_prog5.ArrayQueue;
import joshua_lester_00932346_cscd300_prog5.ListQueue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MergeWithLL {
    public static void main(String [] args) {
        if (args.length < 2) {
            System.out.println("Less than 2 arguments given. Program cannot run.");
        } else {
            Scanner[] scanners = new Scanner [args.length];
            File[] files = new File [args.length];
            ArrayQueue[] queues = new ArrayQueue [args.length];
            ListQueue result = new ListQueue();
            for (int i = 0; i < args.length; i++) {
                files [i] = new File(args [i]);
            }
            try {
                for (int i = 0; i < args.length; i++) {
                    scanners [i] = new Scanner(files [i]);
                }
                addFirstTen(queues, scanners, args.length);
                merge(queues, scanners, result);
                while (result.size() != 0) {
                    System.out.println(result.dequeue());
                }
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static boolean areNotAllEmpty(ArrayQueue [] queues) {
        for (ArrayQueue aq : queues) {
            if (aq.size() > 0) {
                return true;
            }
        }
        return false;
    }

    public static ArrayQueue findMin(ArrayQueue [] queues) {
        Integer min = null;
        ArrayQueue minQueue = null;
        for (int i = 0; i < queues.length; i++) {
            if (queues [i].size() > 0) {
                if (min == null) {
                    min = queues [i].front();
                    minQueue = queues [i];
                } else if (min > queues [i].front()) {
                    min = queues [i].front();
                    minQueue = queues [i];
                }
            }
        }
        return minQueue;
    }

    public static void addFirstTen(ArrayQueue [] queues, Scanner [] scanners, int length) {
        for (int i = 0; i < length; i++) {
            queues [i] = new ArrayQueue();
            queues [i].setScannerNum(i);
            for (int j = 0; j < ArrayQueue.CAPACITY && scanners [i].hasNextInt(); j++) {
                queues [i].enqueue(scanners [i].nextInt());
            }
        }
    }

    public static void merge(ArrayQueue [] queues, Scanner [] scanners, ListQueue result) {
        while(areNotAllEmpty(queues)) {
            ArrayQueue minQueue = findMin(queues);
            result.enqueue(minQueue.dequeue());
            int minScannerNum = minQueue.scannerNum;
            if (scanners [minScannerNum].hasNextInt()) {
                minQueue.enqueue(scanners [minScannerNum].nextInt());
            }
        }
    }
}
