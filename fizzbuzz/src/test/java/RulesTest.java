import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RulesTest {

    Rules rules;

    @Before
    public void setUp(){
        rules = new Rules();
    }

    @Test
    public void testFizzValidNumber() {
        assertTrue(rules.isFizz(3));
    }

    @Test
    public void testFizzInvalidNumber() {
        assertFalse(rules.isFizz(2));
    }

    @Test
    public void testBuzzInvalidNumber() {
        assertFalse(rules.isBuzz(2));
    }

    @Test
    public void testBuzzValidNumber() {
        assertTrue(rules.isBuzz(5));
    }

    @Test
    public void testFizzBuzzValidNumber() {
        assertTrue(rules.isFizzBuzz(15));
    }

    @Test
    public void testFizzBuzzInvalidNumber() {
        assertFalse(rules.isFizzBuzz(2));
    }
}
