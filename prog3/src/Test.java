import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        Node newNode = new Node(1, 6);
        list.addAfterCursor(newNode);
        list.addAfterCursor(new Node(0, 2));
        list.addAfterCursor(new Node(-1, 1));
        list.addAfter(new Node(2, 4), newNode);
        list.addBefore(new Node(5, 3), newNode);
        list.remove(newNode);
    }
}
