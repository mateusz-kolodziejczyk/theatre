import Theatre.Performance;
import Theatre.Show;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PerformanceTest {
    static Show show;

    @BeforeAll
    public static void before() {
        show = new Show("The Lion King",
                LocalDate.of(2019, 9, 1), LocalDate.of(2019, 10, 1),
                20, 15, 10);
        show.addPerformance(LocalDate.of(2019,10,2), "m");
        show.addPerformance(LocalDate.of(2019, 10, 2), "e");
        show.addPerformance(LocalDate.of(2019, 10, 16), "e");
    }
    @Test
    public void setNameTest(){
    }

    @AfterAll
    public static void after() {

    }
}
