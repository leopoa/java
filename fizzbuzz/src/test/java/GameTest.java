import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameTest {

    Game game;

    @Before
    public void setUp(){
        game = new Game();
    }

    @Test
    public void testGame() {
        assertEquals(100, game.play(1,100).size());
    }

    @Test
    public void testPrintFizz() {
        assertEquals("Fizz", game.play(1,100).get(2));
    }

    @Test
    public void testPrintBuzz() {
        assertEquals("Buzz", game.play(1,100).get(4));
    }

    @Test
    public void testPrintFizzBuzz() {
        assertEquals("FizzBuzz", game.play(1,100).get(14));
    }

    @Test
    public void testPrintAnotherNumber() {
        assertEquals("1", game.play(1,100).get(0));
    }

}


