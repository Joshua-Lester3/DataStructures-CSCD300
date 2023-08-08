import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BST {
    public static void main(String [] args) {
        if (args.length < 1) {
            System.out.println("No arguments given");
        } else {
            File f = new File(args [0]);
            Scanner scanner;
            BinarySearchTree bst;
            try {
                scanner = new Scanner(f);
                bst = new BinarySearchTree();
                while (scanner.hasNextInt()) {
                    bst.insert(scanner.nextInt());
                }
                InteractiveMenuBST menu = new InteractiveMenuBST(bst);
                menu.run();
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }

        }
    }
}
