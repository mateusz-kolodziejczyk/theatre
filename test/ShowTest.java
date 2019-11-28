
import Theatre.Show;
import Utilities.TheatreLinkedList;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ShowTest {
    static Show show;

    @BeforeAll
    public static void before() {
        show = new Show("The Lion King",
                LocalDate.of(2019, 9, 1), LocalDate.of(2019, 10, 1), 200,
                20, 15, 10);
    }
    @Test
    public void setNameTest(){
        show.setName("Phantom of the Opera");
        assertEquals("Phantom of the Opera", show.getName());
        show.setName("");
        assertEquals("Phantom of the Opera", show.getName());
    }

    @AfterAll
    public static void after() {

    }
}
