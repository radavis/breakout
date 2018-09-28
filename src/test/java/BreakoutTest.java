import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BreakoutTest {

    private Breakout breakout;

    @Before
    public void setup() {
        breakout = new Breakout();
    }

    @Test
    public void sanityCheck() {
        assertTrue(true);
    }

}
