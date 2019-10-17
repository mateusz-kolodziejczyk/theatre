import Theatre.Show;
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
         testLinkedList.addFront("doing.");
        testLinkedList.addFront("you ");
        testLinkedList.addFront("are ");
        testLinkedList.addFront("how ");
    }

    @Test
    public void addFrontTest() {
        testLinkedList.removeAll();
        testLinkedList.addFront("doing.");
        testLinkedList.addFront("you ");
        testLinkedList.addFront("are ");
        testLinkedList.addFront("how ");
    }

    @Test
    public void getTest() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            testLinkedList.get(-1);
        });
        assertThrows(IndexOutOfBoundsException.class, () -> {
            testLinkedList.get(4);
        });
        assertEquals("how ", testLinkedList.get(0));
        assertEquals("doing.", testLinkedList.get(3));
    }

    @Test
    public void lengthTest(){
        testLinkedList.removeAll();
        testLinkedList.addFront("doing.");
        testLinkedList.addFront("you ");
        testLinkedList.addFront("are ");
        testLinkedList.addFront("how ");
        assertEquals(4, testLinkedList.size());
        testLinkedList.addFront("hey ");
        assertEquals(5, testLinkedList.size());
    }

    @Test
    public void removeAllTest(){
        assertEquals(4, testLinkedList.size());
        testLinkedList.removeAll();
        assertEquals(0, testLinkedList.size());
    }
    @Test
    public void removeIndexTest(){
        testLinkedList.removeAll();
        testLinkedList.addFront("doing.");
        testLinkedList.addFront("you ");
        testLinkedList.addFront("are ");
        testLinkedList.addFront("how ");
        assertThrows(IndexOutOfBoundsException.class, () ->{
            testLinkedList.removeIndex(4);
        });
        testLinkedList.removeIndex(2);
        assertEquals(3, testLinkedList.size());
        assertEquals("doing.", testLinkedList.get(2));
    }
    @Test
    public void removeItemTest(){
      testLinkedList.addFront("heyyyu");
      assertEquals(true, testLinkedList.removeItem("heyyyu"));
      assertEquals(false, testLinkedList.removeItem("oh no"));
    }
    @Test
    public void iterationTest(){
        testLinkedList.removeAll();
        testLinkedList.addFront("doing.");
        testLinkedList.addFront("you ");
        testLinkedList.addFront("are ");
        testLinkedList.addFront("how ");
        var messageString = new StringBuilder();
        for (String s:testLinkedList) {
           messageString.append(s) ;
        }
        assertEquals("how are you doing.", messageString.toString());

    }

    @Test
    public void generalityTest(){
        var stringList = new TheatreLinkedList<String>();
        var showList = new TheatreLinkedList<Show>();
        var showListList = new TheatreLinkedList<TheatreLinkedList<Show>>();
    }

    @AfterAll
    public static void after() {

    }
}
