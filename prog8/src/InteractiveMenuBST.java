import java.util.Scanner;

public class InteractiveMenuBST {
    private BinarySearchTree bst;
    private Scanner scanner;

    public InteractiveMenuBST(BinarySearchTree bst) {
        this.bst = bst;
        scanner = new Scanner(System.in);
    }

    public void run() {
        char choice;
        boolean keepRunning;
        do {
            printMenu();
            choice = scanner.nextLine().charAt(0);
            keepRunning = runSwitch(choice);
        } while (keepRunning);
    }

    private boolean runSwitch(char choice) {
        switch (choice) {
            case '1' :
                caseSearch();
                break;
            case '2' :
                caseInsert();
                break;
            case '3' :
                caseDelete();
                break;
            case '4' :
                bst.InOrder_Traversal(bst.getRoot());
                System.out.println();
                break;
            case '5' :
                bst.PreOrder_Traversal(bst.getRoot());
                System.out.println();
                break;
            case '6' :
                bst.PostOrder_Traversal(bst.getRoot());
                System.out.println();
                break;
            case '7' :
                bst.LevelOrder_Traversal(bst.getRoot());
                System.out.println();
                break;
            case '8' :
                caseMin();
                break;
            case '9' :
                caseMax();
                break;
            case 'a' :
                caseSuccessor();
                break;
            case 'b' :
                casePredecessor();
                break;
            case 'x' :
                return false;
            default :
                System.out.println("Invalid character. Enter again");
                break;
        }
        System.out.println();
        return true;
    }

    private void casePredecessor() {
        System.out.println("Input the key: ");
        int key = Integer.parseInt(scanner.nextLine());
        BST_Node search = bst.search(key);
        if (search == null) {
            System.out.println("No such key.");
        } else {
            BST_Node predecessor = bst.predecessor(search);
            if (predecessor == null) {
                System.out.println("The given key exists but does not have a successor.");
            } else {
                System.out.println(predecessor.getKey());
            }
        }
    }

    private void caseSuccessor() {
        System.out.println("Input the key: ");
        int key = Integer.parseInt(scanner.nextLine());
        BST_Node search = bst.search(key);
        if (search == null) {
            System.out.println("No such key.");
        } else {
            BST_Node successor = bst.successor(search);
            if (successor == null) {
                System.out.println("The given key exists but does not have a successor.");
            } else {
                System.out.println(successor.getKey());
            }
        }
    }

    private void caseMax() {
        if (bst.getRoot() == null) {
            System.out.println("The tree is empty.");
        } else {
            BST_Node max = bst.max(bst.getRoot());
            System.out.println(max.getKey());
        }
    }

    private void caseMin() {
        if (bst.getRoot() == null) {
            System.out.println("The tree is empty.");
        } else {
            BST_Node min = bst.min(bst.getRoot());
            System.out.println(min.getKey());
        }
    }

    private void caseDelete() {
        System.out.println("Input the key: ");
        int key = Integer.parseInt(scanner.nextLine());
        BST_Node deleted = bst.delete(key);
        if (deleted == null) {
            System.out.println("No such key.");
        } else {
            System.out.println("The given key has been successfully deleted.");
        }
    }

    private void caseInsert() {
        System.out.println("Input the key: ");
        int key = Integer.parseInt(scanner.nextLine());
        BST_Node inserted = bst.insert(key);
        if (inserted == null) {
            System.out.println("The given key already exists.");
        } else {
            System.out.println("The given key has been inserted successfully.");
        }
    }

    private void caseSearch() {
        System.out.println("Input the key: ");
        int key = Integer.parseInt(scanner.nextLine());
        BST_Node search = bst.search(key);
        if (search != null) {
            System.out.println("The given key exists.");
        } else {
            System.out.println("The given key does not exist.");
        }
    }

    private void printMenu() {
        System.out.println("Choose one of the following options.\n" +
                "====================================\n" +
                "1) Search for a key\n" +
                "2) Insert a new key\n" +
                "3) Delete an existing key\n" +
                "4) Inorder traversal of the BST\n" +
                "5) Preorder traversal of the BST\n" +
                "6) Postorder traversal of the BST\n" +
                "7) Level-order traversal of the BST\n" +
                "8) Find the smallest key\n" +
                "9) Find the largest key\n" +
                "a) Find the successor of a given key\n" +
                "b) Find the predecessor of a given key\n" +
                "x) quit\n" +
                "Your choice: ");
    }
}
