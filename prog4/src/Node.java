/**
 * Author: Joshua Lester
 * CSCD300
 * Programming Assignment 4
 */

package joshua_lester_00932346_cscd300_prog4;

public class Node {
    private String element;
    private Node next;

    public Node(String element, Node next) {
        this.element = element;
        this.next = next;
    }

    // Getters
    public String getElement() {
        return this.element;
    }

    public Node getNext() {
        return this.next;
    }

    // Setters
    public void setElement(String element) {
        this.element = element;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
