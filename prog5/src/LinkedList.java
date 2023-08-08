import joshua_lester_00932346_cscd300_prog5.ArrayQueue;

public class LinkedList {
    protected Node head;
    protected Node tail;
    protected int size;

    public LinkedList() {
        head = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(ArrayQueue aq) {
        if (aq == null) {
            throw new IllegalArgumentException("Null argument in addFirst");
        }
        Node nn = new Node(aq, null, null);
        nn.next = head;
        if (size != 0) {
            head.prev = nn;
        }
        head = nn;
        size++;
    }

    public void addLast(ArrayQueue aq) {
        if (aq == null) {
            throw new IllegalArgumentException("Null argument in addLast");
        }
        if (size == 0) {
            addFirst(aq);
        } else {
            Node nn = new Node(aq, null, null);
            tail.next = nn;
            nn.prev = tail;
            tail = nn;
            size++;
        }

    }

    public void removeFirst() {
        if (size == 0) {
            throw new IllegalStateException("Size is 0 when removeFirst was called");
        }
        if (size > 1) {
            head.next.prev = null;
        }
        head = head.next;
        size --;
    }

    public void removeLast() {
        if (size == 0) {
            throw new IllegalStateException("Size is 0 when removeLast was called");
        }
    }

    public void remove(Node nn) {

    }

    private class Node {
        private ArrayQueue element;
        private Node next;
        private Node prev;

        public Node(ArrayQueue element, Node next, Node prev) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }
    }
}
