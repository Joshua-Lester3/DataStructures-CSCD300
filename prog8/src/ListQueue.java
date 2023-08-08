public class ListQueue {
    protected int size;
    protected Node head;
    protected Node tail;

    public ListQueue() {
        size = 0;
        head = null;
        tail = null;
    }

    public void enqueue(BST_Node b) {
        Node n = new Node(b, null);
        if (size == 0) {
            head = tail = n;
        } else {
            tail.next = n;
            tail = n;
        }
        size ++;
    }

    public BST_Node dequeue() {
        if (size == 0) {
            throw new IllegalStateException();
        }
        BST_Node res = head.elem;
        Node n = head;
        head = head.next;
        n.next = null;
        if (size == 0) tail = null;
        size --;
        return res;
    }

    private class Node {
        private BST_Node elem;
        private Node next;

        public Node(BST_Node elem, Node next) {
            this.elem = elem;
            this.next = next;
        }
    }
}
