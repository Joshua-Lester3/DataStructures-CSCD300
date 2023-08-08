import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HashChain {
    public static final int HASH_TABLE_SIZE = 5;
    public static SinglyLinkedList [] hashTable;

    public static void main(String [] args) {
        if (args.length < 1) {
            System.out.println("0 arguments given.");
        } else {
            File file = new File(args[0]);
            Scanner scanner = new Scanner("");
            hashTable = new SinglyLinkedList [HASH_TABLE_SIZE];
            for (int i = 0; i < HASH_TABLE_SIZE; i ++) {
                hashTable [i] = new SinglyLinkedList();
            }
            try {
                scanner = new Scanner(file);
            } catch (FileNotFoundException e) {
                System.out.println("No file found.");
            }
            while (scanner.hasNextLine()) {
                String [] line = scanner.nextLine().split(",");
                if (line.length < 2) {
                    throw new IllegalArgumentException("Less than two arguments given in a line.");
                }
                String id = line [0].trim();
                String name = line [1].trim();
                hashTable [h(id)].addFirst(id, name);
            }
            runMenu();
        }
    }

    public static void runMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean keepRunning = true;
        int choice;

        while (keepRunning) {
            printMenu();
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1 :
                    insertOrUpdate(scanner);
                    break;
                case 2 :
                    delete(scanner);
                    break;
                case 3 :
                    search(scanner);
                    break;
                case 4 :
                    System.out.println();
                    printAll();
                    break;
                case 5 :
                    keepRunning = false;
                    break;
                default :
                    System.out.println("Please enter a number shown.");
                    break;
            }
            System.out.println();
        }
    }

    public static void insertOrUpdate(Scanner scanner) {
        System.out.println("Input the student id: ");
        String id = scanner.nextLine().replaceAll("\n", "");
        System.out.println("Input the student name: ");
        String name = scanner.nextLine().replaceAll("\n", "");
        Node updated = hashTable [h(id)].insert(id, name);
        if (updated == null) {
            System.out.println("The new student has been added successfully.");
        } else {
            System.out.println("The student was existing and the record has been updated.");
        }
    }

    public static void delete(Scanner scanner) {
        System.out.println("Input the student id: ");
        String id = scanner.nextLine().replaceAll("\n", "");
        Node removed = hashTable [h(id)].remove(id);
        if (removed == null) {
            System.out.println("No such student.");
        } else {
            System.out.println("The student has been deleted successfully.");
        }
    }

    public static void search(Scanner scanner) {
        System.out.println("Input the student id: ");
        String id = scanner.nextLine().replaceAll("\n", "");
        Node search = hashTable [h(id)].search(id);
        if (search == null) {
            System.out.println("No such student.");
        } else {
            System.out.println("Student id:" + search.getId() + ". Student name:" + search.getName() + ".");
        }
    }

    public static void printAll() {
        for (SinglyLinkedList s : hashTable) {
            System.out.println(s);
        }
    }

    public static void printMenu() {
        System.out.print("Choose one of the following options.\n" +
                "====================================\n" +
                "1) insert/update a new student record\n" +
                "2) delete a student record\n" +
                "3) search for a student record\n" +
                "4) print all the student records\n" +
                "5) quit\n" +
                "Your choice: ");
    }

    public static int h(String id) {
        int i = Integer.parseInt(id);
        return ((7 * i) + 29) % 5;
    }
}
