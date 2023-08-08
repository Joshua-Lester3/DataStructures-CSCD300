/**
 * Author: Joshua Lester
 * CSCD300
 * Programming Assignment 5
 */

package joshua_lester_00932346_cscd300_prog5;

public class ArrayQueue {
    public static final int CAPACITY = 10;
    protected int head;
    protected int tail;
    protected int size;
    protected int [] Q;
    protected int scannerNum;

    public ArrayQueue() {
        head = tail = -1;
        size = 0;
        Q = new int [CAPACITY];
    }

    public void enqueue(int elem) {
        if (size == CAPACITY) {
            throw new IllegalStateException("AQ has reached capacity");
        }
        if (size == 0) {
            head = tail = 0;
        } else {
            tail = (tail + 1) % CAPACITY;
        }
        Q[tail] = elem;
        size++;
    }

    public int dequeue() {
        if (size == 0) {
            throw new IllegalStateException("Size is 0 when dequeue was called");
        }
        int result = Q[head];
        if (size == 1) {
            head = tail = -1;
        } else {
            head = (head + 1) % CAPACITY;
        }
        size--;
        return result;
    }

    public int front() {
        if (size == 0) {
            throw new IllegalStateException("Size is 0 when front was called");
        }
        return Q[head];
    }

    public int size() {
        return size;
    }

    public void setScannerNum(int i) {
        this.scannerNum = i;
    }
}
