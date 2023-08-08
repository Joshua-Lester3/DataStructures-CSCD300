public class Node implements Cloneable {
    private String id;
    private String name;
    private Node next;

    public Node(Node next, String id, String name) {
        this.id = id;
        this.name = name;
        this.next = next;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Node res = (Node) super.clone();
        if (res.getNext() != null) {
            res.setNext((Node) res.getNext().clone());
        }
        return res;
    }

    @Override
    public String toString() {
        return "(" + this.id + "," + this.name + ")";
    }
}
