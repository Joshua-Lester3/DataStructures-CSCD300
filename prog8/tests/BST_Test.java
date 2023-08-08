import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.io.*;
import java.lang.reflect.Field;

public class BST_Test {
    private BinarySearchTree bst;
    private final PrintStream ORIGINALOUT = System.out;
    private ByteArrayOutputStream testOut;

    @BeforeEach
    public void init() {
        this.bst = new BinarySearchTree();
        this.testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(this.testOut));
    }

    @AfterEach
    public void cleanUp() {
        System.setOut(this.ORIGINALOUT);
    }

    private void setBst() {
        this.bst.insert(5);
        this.bst.insert(2);
        this.bst.insert(7);
        this.bst.insert(1);
        this.bst.insert(6);
        this.bst.insert(0);
        this.bst.insert(4);
    }

    @Test
    public void insertOne() {
        this.bst.insert(5);
        this.bst.InOrder_Traversal(this.bst.getRoot());
        assertEquals("5 ", testOut.toString());
    }

    @Test
    public void insertThree() {
        this.bst.insert(5);
        this.bst.insert(2);
        this.bst.insert(7);
        this.bst.InOrder_Traversal(this.bst.getRoot());
        assertEquals("2 5 7 ", this.testOut.toString());
    }

    @Test
    public void insertSeven() {
        this.setBst();
        this.bst.InOrder_Traversal(this.bst.getRoot());
        assertEquals("0 1 2 4 5 6 7 ", this.testOut.toString());
    }

    @Test
    public void searchWhenExists() {
        this.setBst();
        BST_Node node = bst.search(4);
        assertNotNull(node);
        if (node != null) {
            assertEquals(4, node.getKey());
        }
    }

    @Test
    public void searchWhenDoesntExist() {
        this.setBst();
        BST_Node node = bst.search(3);
        assertNull(node);
    }

    @Test
    public void preOrderTraversal() {
        this.setBst();
        this.bst.PreOrder_Traversal(this.bst.getRoot());
        assertEquals("5 2 1 0 4 7 6 ", testOut.toString());
    }

    @Test
    public void deleteRoot() {
        this.setBst();
        BST_Node deleted = this.bst.delete(5);
        assertEquals(5, deleted.getKey());
    }
}
