public class SinglyLinkedList {
    private Node head;
    private int size;

    public SinglyLinkedList() {
        head = null;
        size = 0;
    }

    public void addFirst(String id, String name) {
        Node n = new Node(head, id, name);
        head = n;
        size ++;
    }

    @Override
    public String toString() {
        String res = "";
        Node cur = head;
        while (cur != null) {
            res += cur.toString() + " ";
            cur = cur.getNext();
        }
        return res;
    }

    public Node insert(String id, String name) {
        Node found = search(id);
        if (found == null) {
            addFirst(id, name);
            return null;
        } else {
            Node old = null;
            try {
                old = (Node) found.clone();
            } catch (CloneNotSupportedException e) {
                System.out.println(e.getMessage() + "\nNode returned is null.");
            }
            found.setName(name);
            return old;
        }
    }

    public Node remove(String id) {
        Node [] found = searchBefore(id);
        Node before = found [0];
        Node remove = found [1];
        if (remove == null) {
            return null;
        } else if (remove == head) {
            head = head.getNext();
            remove.setNext(null);
            size --;
        } else {
            before.setNext(remove.getNext());
            remove.setNext(null);
            size --;
        }
        return remove;
    }

    public Node search(String id) {
        Node cur = head;
        while (cur != null && !(cur.getId().equals(id))) {
            cur = cur.getNext();
        }
        return cur;
    }

    public Node [] searchBefore(String id) {
        Node cur = head;
        Node prev = null;
        while (cur != null && !(cur.getId().equals(id))) {
            if (prev != null) {
                prev = prev.getNext();
            } else {
                prev = head;
            }
            cur = cur.getNext();
        }
        Node [] res = {prev, cur};
        return res;
    }
}
