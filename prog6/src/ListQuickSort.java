import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ListQuickSort {
    public static void main(String [] args) {
        if (args.length < 1) {
            System.out.println("No arguments given. Program cannot run.");
        } else {
            File file = new File(args [0]);
            try {
                Scanner scanner = new Scanner(file);
                DoublyLinkedList list = new DoublyLinkedList();
                while (scanner.hasNextInt()) {
                    list.addFirst(scanner.nextInt());
                }
                list.quickSort();
                System.out.println(list.toString());
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}