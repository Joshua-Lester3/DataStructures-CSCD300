/**
 * Author: Joshua Lester
 * CSCD300
 * Programming Assignment 4
 */

package joshua_lester_00932346_cscd300_prog4;

public class SLinkedList {
    private Node top;
    private int size;

    public SLinkedList(Node top) {
        this.top = top;
        this.size = 0;
    }

    public SLinkedList() {
        this(null);
    }

    public int getSize() {
        return this.size;
    }

    public String getTop() {
        if (this.size == 0) {
            throw new IllegalStateException("Size is 0. Cannot call getTop()");
        }
        return this.top.getElement();
    }

    public void push(String elem) {
        Node newNode = new Node(elem, this.top);
        this.top = newNode;
        this.size ++;
    }

    public String pop() {
        if (this.size == 0) {
            throw new IllegalStateException("Size is 0. Cannot call pop()");
        }
        Node deleted = this.top;
        this.top = this.top.getNext();
        deleted.setNext(null);
        size --;
        return deleted.getElement();
    }
}
