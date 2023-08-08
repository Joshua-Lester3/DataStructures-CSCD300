import java.util.Random;

public class DoublyLinkedList {
    private class Node {
        private Node next;
        private Node prev;
        private Integer elem;

        public Node(Node next, Node prev, Integer elem) {
            this.next = next;
            this.prev = prev;
            this.elem = elem;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public DoublyLinkedList() {
        head = new Node(null, null, null);
        tail = new Node(null, null, null);
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    public void addFirst(int elem) {
        Node nn = new Node(null, null, elem);
        nn.next = head.next;
        nn.prev = head;
        head.next.prev = nn;
        head.next = nn;
        size++;
    }

    public int size() {
        return this.size;
    }

    @Override
    public String toString() {
        Node cur = head.next;
        String res = "";
        while (cur != tail) {
            res += cur.elem + "\n";
            cur = cur.next;
        }
        return res;
    }

    public void moveBefore(Node moving, Node stay) {
        moving.prev.next = moving.next;
        moving.next.prev = moving.prev;
        moving.next = stay;
        moving.prev = stay.prev;
        stay.prev.next = moving;
        stay.prev = moving;
    }

    public void movePivot() {
        if (size >= 2) {
            Random random = new Random();
            int randInt = random.nextInt(size - 1);
            Node cur = head.next;
            for (int i = 0; i < randInt; i++) {
                cur = cur.next;
            }
            moveBefore(tail.prev, cur);
            moveBefore(cur, tail);
        }
    }

    public void quickSort() {
        movePivot();
        quickSortHelper(head.next, tail.prev);
    }

    private void quickSortHelper(Node first, Node last) {
        if (first == null || last == null) {
            throw new IllegalArgumentException("null params in quickSortHelper");
        }
        if (first != last && first.prev != last) {
            Node firstPrev = first.prev;
            Node lastNext = last.next;

            Node pivot = partition(first, last);

            quickSortHelper(firstPrev.next, pivot.prev);
            quickSortHelper(pivot.next, lastNext.prev);
        }
    }

    private Node partition(Node first, Node last) {
        // save pivot. save i as first node, index as null. node cur
        // for cur does not equal last
        //  if i's element <= pivot's element save i.prev, if index != null, put i before index.
        //  set i to be iPrev variable
        //  increase i
        //  if i's element > pivot's element and index == null, set index to i
        // put pivot before index
        // return pivot
        Node firstPrev = first.prev;
        int pivot = last.elem;
        Node index = null;
        for (Node i = first; i != last; i = i.next) {
            if (i.elem <= pivot && index != null) {
                Node iPrev = i.prev;
                moveBefore(i, index);
                i = iPrev;
            } else if (i.elem > pivot && index == null) {
                index = i;
            }
        }
        if (pivot < firstPrev.next.elem) {
            moveBefore(last, firstPrev.next);
        } else if (pivot < last.prev.elem && index != null) {
            moveBefore(last, index);
        }
        return last;
    }
}