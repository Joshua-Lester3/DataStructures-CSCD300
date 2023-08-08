import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class DLLTests {
    private DoublyLinkedList list;

    @BeforeEach
    public void init() {
        list = new DoublyLinkedList();
    }

    @Test
    public void addFirstWhenEmpty() {
        list.addFirst(5);
        assertEquals("5\n", list.toString());
    }

    @Test
    public void addFirstWhenSize1() {
        list.addFirst(5);
        list.addFirst(6);
        assertEquals("6\n5\n", list.toString());
    }

    @Test
    public void randomizeWithSize2() {
        list.addFirst(5);
        list.addFirst(6);
        list.movePivot();
        assertEquals("5\n6\n", list.toString());
    }
}
