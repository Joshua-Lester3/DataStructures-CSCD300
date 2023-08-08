/**
 * Author: Joshua Lester
 * CSCD300
 * Programming Assignment 5
 */

package joshua_lester_00932346_cscd300_prog5;

public class ListQueue {
    protected Node head;
    protected Node tail;
    protected int size;

    public ListQueue() {
        head = null;
        tail = null;
        size = 0;
    }

    public int size() {
        return this.size;
    }

    public void enqueue(int e) {
        Node nn = new Node(e, null);
        if (size == 0) {
            head = nn;
            tail = nn;
        } else {
            tail.next = nn;
            tail = nn;
        }
        size++;
    }

    public int dequeue() {
        if (size == 0) {
            throw new IllegalStateException("Size is 0 when dequeue was called");
        }
        int result = head.element;;
        if (size == 1) {
            head = tail = null;
        } else {
            head = head.next;
        }
        size--;
        return result;
    }

    public int front() {
        if (size == 0) {
            throw new IllegalStateException("Size is 0 when front was called");
        }
        return head.element;
    }

    private class Node {
        private int element;
        private Node next;

        public Node(int element, Node next) {
            this.element = element;
            this.next = next;
        }
    }
}
