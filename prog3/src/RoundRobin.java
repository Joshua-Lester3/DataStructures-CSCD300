/** Author: Joshua Lester
 * CSCD300
 * Programming Assignment 3
 * Round Robin
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RoundRobin {
    /** Main method that calls methods to sort circular doubly linked
     * list (named LinkedList) and run the Round Robin algorithm
     *
     * @param args
     */
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Insufficient arguments given");
        } else {
            String fileName = args[0];
            int cpuServiceTime = Integer.parseInt(args[1]);
            File file = new File(fileName);
            try {
                Scanner scanner = new Scanner(file);
                LinkedList list = new LinkedList();
                sortIntoList(scanner, list);
                int[] result = runRoundRobin(list, cpuServiceTime);
                for (int i = 0; i < result.length; i++) {
                    System.out.print(result[i]);
                    if (i != result.length - 1) {
                        System.out.print(",");
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /** Goes through circular doubly linked list and subtracts service time
     * until service needs are less than or equal to 0. Returns int [] of
     * order of when each pid was completed
     * @param list
     * @param cpuServiceTime
     * @return result
     */
    private static int [] runRoundRobin(LinkedList list, int cpuServiceTime) {
        int [] result = new int [list.getSize()];
        Node current = list.getCursor();
        int resultCount = 0;
        while (list.getSize() != 0) {
            current.setCpuTimeNeeds(current.getCpuTimeNeeds() - cpuServiceTime);
            if (current.getCpuTimeNeeds() <= 0) {
                result [resultCount] = current.getPid();
                resultCount++;
                Node temp = current.getNext();
                list.remove(current);
                current = temp;
            } else if (list.getSize() != 0) {
                current = current.getNext();
            }
        }
        return result;
    }

    /** Scans in pid and cpu time needs and sorts them into circular doubly linked list
     * @param scanner
     * @param list
     */
    private static void sortIntoList(Scanner scanner, LinkedList list) {
        // Runs through each line of input from the text file
        while (scanner.hasNext()) {
            String [] input = scanner.nextLine().split(",");
            if (input.length == 1) {
                throw new IllegalStateException("Only one input given on this line. Two are required");
            }
            int pid = Integer.parseInt(input [0]);
            int cpuTimeNeeds = Integer.parseInt(input [1].strip());
            Node newNode = new Node(pid, cpuTimeNeeds);
            // Check where to add new node
            if (list.getSize() == 0) {
                list.addAfterCursor(newNode);
            } else if (list.getSize() == 1) {
                if (newNode.getPid() < list.getCursor().getPid()) {
                    list.addBefore(newNode, list.getCursor());
                } else {
                    list.addAfterCursor(newNode);
                }
            } else {
                if (list.getCursor().getPid() > newNode.getPid()) {
                    list.addBefore(newNode, list.getCursor());
                } else if (list.getCursor().getPrev().getPid() < newNode.getPid()) {
                    list.addAfter(newNode, list.getCursor().getPrev());
                } else {
                    Node current = list.getCursor();
                    while (newNode.getPid() > current.getPid()) {
                        current = current.getNext();
                    }
                    list.addBefore(newNode, current);
                }
            }
        }
    }
}

class Node {
    private int pid;
    private int cpuTimeNeeds;
    private Node next;
    private Node prev;

    public Node(int pid, int cpuTimeNeeds) {
        this.pid = pid;
        this.cpuTimeNeeds = cpuTimeNeeds;
    }

    public Node(int pid, int cpuTimeNeeds, Node next, Node prev) {
        this(pid, cpuTimeNeeds);
        this.next = next;
        this.prev = prev;
    }
    
    // Getters
    public Node getNext() {
        return this.next;
    }
    
    public Node getPrev() {
        return this.prev;
    }
    
    public int getPid() {
        return this.pid;
    }
    
    public int getCpuTimeNeeds() {
        return this.cpuTimeNeeds;
    }
    
    // Setters
    public void setNext(Node j) {
        this.next = j;
    }
    
    public void setPrev(Node j) {
        this.prev = j;
    }
    
    public void setPid(int pid) {
        this.pid = pid;
    }
    
    public void setCpuTimeNeeds(int cpuTimeNeeds) {
        this.cpuTimeNeeds = cpuTimeNeeds;
    }
}

class LinkedList {
    private Node cursor;
    private int size;

    public LinkedList() {
        this.cursor = null;
        this.size = 0;
    }
    
    // Getters
    public int getSize() {
        return this.size;
    }
    
    public Node getCursor() {
        return this.cursor;
    }

    /** Adds passed in node after cursor
     *
     * @param j
     */
    public void addAfterCursor(Node j) {
        if (this.getSize() == 0) {
            this.cursor = j;
            this.cursor.setNext(this.cursor);
            this.cursor.setPrev(this.cursor);
        } else {
            this.cursor.getNext().setPrev(j);
            j.setNext(this.cursor.getNext());
            this.cursor.setNext(j);
            j.setPrev(this.cursor);
        }
        this.size++;
    }

    /** Adds added node after existing node
     *
     * @param added
     * @param existing
     */
    public void addAfter(Node added, Node existing) {
        added.setNext(existing.getNext());
        added.setPrev(existing);
        added.getNext().setPrev(added);
        existing.setNext(added);
        this.size++;
    }

    /** Adds added node before existing node
     *
     * @param added
     * @param existing
     */
    public void addBefore(Node added, Node existing) {
        added.setNext(existing);
        added.setPrev(existing.getPrev());
        existing.getPrev().setNext(added);
        existing.setPrev(added);
        if (existing == this.cursor) {
            this.cursor = added;
        }
        this.size++;
    }

    public void remove(Node j) {
        if (this.getSize() == 0) {
            throw new IllegalStateException("Tried removing when.getSize() is 0");
        } else if (this.getSize() == 1) {
            this.cursor = null;
        } else if (this.getSize() > 1) {
            j.getNext().setPrev(j.getPrev());
            j.getPrev().setNext(j.getNext());
            if (j == cursor) {
                this.advance();
            }
            j.setNext(null);
            j.setPrev(null);
        }
        this.size--;
    }

    public void advance() {
        if (this.getSize() == 0) {
            throw new IllegalArgumentException("Tried to advance when list was empty");
        }
        if (this.getSize() > 1) {
            this.cursor = this.cursor.getNext();
        }
    }
}
