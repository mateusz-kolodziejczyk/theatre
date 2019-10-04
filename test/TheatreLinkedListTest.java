import Utilities.TheatreLinkedList;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TheatreLinkedListTest {
    static TheatreLinkedList<String> testLinkedList;

    @BeforeAll
    public static void before() {
        testLinkedList = new TheatreLinkedList<>();
    }

    @Test
    public void addFrontTest() {
        testLinkedList.addFront("doing");
        testLinkedList.addFront("you");
        testLinkedList.addFront("are");
        testLinkedList.addFront("how");
    }

    @Test
    public void getTest() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            testLinkedList.get(0);
        });
        testLinkedList.addFront("doing");
        testLinkedList.addFront("you");
        testLinkedList.addFront("are");
        testLinkedList.addFront("how");
        assertEquals("how", testLinkedList.get(0));
        assertEquals("doing", testLinkedList.get(3));
    }

    @AfterAll
    public static void after() {

    }
}
