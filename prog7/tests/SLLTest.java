import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class SLLTest {
    private SinglyLinkedList list;

    @BeforeEach
    public void init() {
        list = new SinglyLinkedList();
        list.addFirst("3", "john");
        list.addFirst("2", "josh");
        list.addFirst("1", "james");
    }
    @Test
    public void toStringTest() {
        assertEquals("(1,james) (2,josh) (3,john) ", list.toString());
    }

    @Test
    public void insertWhenNotExisting() {
        list.insert("0", "johnny");
        assertEquals("(0,johnny) (1,james) (2,josh) (3,john) ", list.toString());
    }

    @Test
    public void insertReturnValueWhenNotExisting() {
        Node res = list.insert("0", "johnny");
        assertNull(res);
    }

    @Test
    public void insertWhenExisting() {
        list.insert("1", "ricky");
        assertEquals("(1,ricky) (2,josh) (3,john) ", list.toString());
    }

    @Test
    public void insertReturnValueWhenExisting() {
        Node res = list.insert("1", "ricky");
        assertEquals("(1,james)", res.toString());
    }

    @Test
    public void removeWhenExistingAndHead() {
        list.remove("1");
        assertEquals("(2,josh) (3,john) ", list.toString());
    }

    @Test
    public void removeWhenExistingAndNotHead() {
        list.remove("2");
        assertEquals("(1,james) (3,john) ", list.toString());
    }

    @Test
    public void removeWhenNotExisting() {
        list.remove("0");
        assertEquals("(1,james) (2,josh) (3,john) ", list.toString());
    }

    @Test
    public void removeReturnValueWhenExisting() {
        Node removed = list.remove("1");
        assertEquals("(1,james)", removed.toString());
    }

    @Test
    public void removeReturnValueWhenNotExisting() {
        Node removed = list.remove("0");
        assertNull(removed);
    }

    @Test
    public void searchWhenExisting() {
        Node search = list.search("1");
        assertEquals("(1,james)", search.toString());
    }

    @Test
    public void searchWhenNotExisting() {
        Node search = list.search("0");
        assertNull(search);
    }

    @Test
    public void searchBeforeWhenHead() {
        Node [] arr = list.searchBefore("1");
        assertEquals("null (1,james)", arr [0] + " " + arr [1]);
    }

    @Test
    public void searchBeforeWhenExisting() {
        Node [] arr = list.searchBefore("2");
        assertEquals("(1,james) (2,josh)", arr [0] + " " + arr [1]);
    }

    @Test
    public void searchBeforeWhenNotExisting() {
        Node [] arr = list.searchBefore("4");
        assertEquals("(3,john) null", arr [0] + " " + arr [1]);
    }
}
