import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class QSTests {
    private DoublyLinkedList list;

    @BeforeEach
    public void init() {
        list = new DoublyLinkedList();
    }

    @Test
    public void pivotFurthestLeft() {
        list.addFirst(55);
        list.addFirst(26);
        list.addFirst(37);
        list.addFirst(19);
        list.addFirst(38);
        list.quickSort();
        assertEquals("19\n26\n37\n38\n55\n", list.toString());
    }

    @Test
    public void pivotFurthestRight() {
        list.addFirst(11);
        list.addFirst(26);
        list.addFirst(37);
        list.addFirst(19);
        list.addFirst(38);
        list.addFirst(55);
        list.quickSort();
        assertEquals("11\n19\n26\n37\n38\n55\n", list.toString());
    }

    @Test
    public void listSize0() {
        list.quickSort();
        assertEquals("", list.toString());
    }

    @Test
    public void listSize1() {
        list.addFirst(55);
        list.quickSort();
        assertEquals("55\n", list.toString());
    }

    @Test
    public void listSize2() {
        list.addFirst(55);
        list.addFirst(13);
        list.quickSort();
        assertEquals("13\n55\n", list.toString());
    }
}
