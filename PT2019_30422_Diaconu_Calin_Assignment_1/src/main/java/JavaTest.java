import junit.framework.*;

public class JavaTest extends TestCase {
    private int value1, value2;

    protected void setUp() {
        value1 = 3;
        value2 = 3;
    }

    public void testAdd() {
        double result = value1 + value2;
        assertEquals(6, result);
    }
}
