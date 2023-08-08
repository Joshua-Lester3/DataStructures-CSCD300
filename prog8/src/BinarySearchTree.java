public class BinarySearchTree {
    private BST_Node root;
    private int size;

    public BinarySearchTree() {
        root = null;
        size = 0;
    }

    public BST_Node getRoot() {
        return this.root;
    }

    public BST_Node search(int key) {
        BST_Node cur = root;
        while (cur != null && key != cur.getKey()) {
            if (key < cur.getKey()) {
                cur = cur.getLeft();
            } else {
                cur = cur.getRight();
            }
        }
        return cur;
    }

    public BST_Node insert(int key) {
        BST_Node inserted = new BST_Node(key);

        BST_Node cur = root;
        BST_Node curPrev = null;
        while (cur != null) {
            curPrev = cur;
            if (key == cur.getKey()) {
                return null;
            } else if (key > cur.getKey()) {
                cur = cur.getRight();
            } else {
                cur = cur.getLeft();
            }
        }

        inserted.setParent(curPrev);
        if (curPrev == null) {
            root = inserted;
        } else if (key < curPrev.getKey()) {
            curPrev.setLeft(inserted);
        } else {
            curPrev.setRight(inserted);
        }
        size ++;
        return inserted;
    }

    public BST_Node delete(int k) {
        BST_Node search = this.search(k);
        if (search != null) {
            delete(search);
            size --;
        }
        return search;
    }

    private void delete(BST_Node node) {
        if (node.getRight() == null && node.getLeft() == null) {
            this.transplant(node, null);
        } else if (node.getRight() == null) {
            this.transplant(node, node.getLeft());
        } else if (node.getLeft() == null) {
            this.transplant(node, node.getRight());
        } else {
            BST_Node successor = min(node.getRight());
            if (successor.getParent() != node) {
                transplant(successor, successor.getRight());
                successor.setRight(node.getRight());
                successor.getRight().setParent(successor);
            }
            this.transplant(node, successor);
            successor.setLeft(node.getLeft());
            successor.getLeft().setParent(successor);
        }
    }

    private void transplant(BST_Node oldSubtree, BST_Node newSubtree) {
        if (oldSubtree == root) {
            root = newSubtree;
        } else if (oldSubtree.getParent().getLeft() == oldSubtree) {
            oldSubtree.getParent().setLeft(newSubtree);
        } else { //oldSubtree.getParent.getRight() == oldSubtree
            oldSubtree.getParent().setRight(newSubtree);
        }
        if (newSubtree != null) {
            newSubtree.setParent(oldSubtree.getParent());
        }
    }

    public void InOrder_Traversal(BST_Node subtreeRoot) {
        if (subtreeRoot != null) {
            InOrder_Traversal(subtreeRoot.getLeft());
            System.out.print(subtreeRoot.getKey() + " ");
            InOrder_Traversal(subtreeRoot.getRight());
        }
    }

    public void PreOrder_Traversal(BST_Node subtreeRoot) {
        if (subtreeRoot != null) {
            System.out.print(subtreeRoot.getKey() + " ");
            PreOrder_Traversal(subtreeRoot.getLeft());
            PreOrder_Traversal(subtreeRoot.getRight());
        }
    }

    public void PostOrder_Traversal(BST_Node subtreeRoot) {
        if (subtreeRoot != null) {
            PostOrder_Traversal(subtreeRoot.getLeft());
            PostOrder_Traversal(subtreeRoot.getRight());
            System.out.print(subtreeRoot.getKey() + " ");
        }
    }

    public void LevelOrder_Traversal(BST_Node subtreeRoot) {
        if (subtreeRoot == null) {
            throw new IllegalArgumentException("Bad params in LevelOrder_Traversal");
        }
        ListQueue Q = new ListQueue();
        Q.enqueue(subtreeRoot);
        while (Q.size > 0) {
            BST_Node temp = Q.dequeue();
            System.out.print(temp.getKey() + " ");
            if (temp.getLeft() != null) {
                Q.enqueue(temp.getLeft());
            }
            if (temp.getRight() != null) {
                Q.enqueue(temp.getRight());
            }
        }
    }

    public BST_Node min(BST_Node subtreeRoot) {
        if (subtreeRoot == null) {
            throw new IllegalArgumentException("Bad params in min");
        }
        BST_Node cur = subtreeRoot;
        while (cur.getLeft() != null) {
            cur = cur.getLeft();
        }
        return cur;
    }

    public BST_Node max(BST_Node subtreeRoot) {
        if (subtreeRoot == null) {
            throw new IllegalArgumentException("Bad params in max");
        }
        BST_Node cur = subtreeRoot;
        while (cur.getRight() != null) {
            cur = cur.getRight();
        }
        return cur;
    }

    public BST_Node successor(BST_Node node) {
        if (node == null) {
            throw new IllegalArgumentException("Bad params in successor");
        }
        if (node.getRight() != null) {
            return min(node.getRight());
        }
        BST_Node child = node;
        BST_Node parent = child.getParent();
        while (parent != null && parent.getLeft() != child) {
            child = parent;
            parent = parent.getParent();
        }
        return parent;
    }

    public BST_Node predecessor(BST_Node node) {
        if (node == null) {
            throw new IllegalArgumentException("Bad params in predecessor");
        }
        if (node.getLeft() != null) {
            return max(node.getLeft());
        }
        BST_Node child = node;
        BST_Node parent = child.getParent();
        while (parent != null && parent.getRight() != child) {
            child = parent;
            parent = parent.getParent();
        }
        return parent;
    }
}
