import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/** Checks the reading of the tiny example. */
class InputTest {

    @Test
    void readsTheTinyMaze() throws Exception {
        Input.Walls w = Input.readWalls("examples/tiny/walls.txt");
        assertEquals(2, w.rows());
        assertEquals(2, w.cols());
        assertEquals(4, w.count());
        assertEquals(17, w.cost()[0]);
    }

    @Test
    void readsTheTinySlides() throws Exception {
        Input.Slides s = Input.readSlides("examples/tiny/slides.txt");
        assertEquals(4, s.n());
        assertEquals(4, s.count());
        assertEquals(3, s.time()[0]);
    }
}
