public class BST_Node {
    private int key;
    private BST_Node parent;
    private BST_Node left;
    private BST_Node right;


    public BST_Node(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public BST_Node getParent() {
        return parent;
    }

    public void setParent(BST_Node parent) {
        this.parent = parent;
    }

    public BST_Node getLeft() {
        return left;
    }

    public void setLeft(BST_Node left) {
        this.left = left;
    }

    public BST_Node getRight() {
        return right;
    }

    public void setRight(BST_Node right) {
        this.right = right;
    }
}
