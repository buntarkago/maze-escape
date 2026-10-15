import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/** Examples of tests for Mission 0. Add your own: path compression, sizes, many operations, a long chain of unions. */
class DisjointSetTest {

    @Test
    void everyCellStartsAlone() {
        DisjointSet d = new DisjointSet(5);
        assertEquals(5, d.count());
        assertFalse(d.connected(0, 1));
    }

    @Test
    void unionJoinsTwoGroups() {
        DisjointSet d = new DisjointSet(5);
        assertTrue(d.union(0, 1));
        assertTrue(d.connected(0, 1));
        assertEquals(4, d.count());
    }

    @Test
    void unionOfTheSameGroupReturnsFalse() {
        DisjointSet d = new DisjointSet(4);
        d.union(0, 1);
        d.union(1, 2);
        assertFalse(d.union(0, 2));
        assertEquals(2, d.count());
    }
}
