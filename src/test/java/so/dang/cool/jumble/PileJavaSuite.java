package so.dang.cool.jumble;

import org.scalatestplus.testng.TestNGSuite;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class PileJavaSuite extends TestNGSuite {
    @Test
    public void jumbleOfThreeIsOk() {
        var pile = Pile.of(
                List.of('a', 'b', 'c'),
                17,
                "Hello!"
        );

        // The test is that typing works here without casting. (I.e. compiling is the test)
        List<Character> abcs = pile.top();
        int seventeen = pile.next().top();
        String greeting = pile.next().next().top();

        assertEquals(abcs, List.of('a', 'b', 'c'));
        assertEquals(seventeen, 17);
        assertEquals(greeting, "Hello!");
    }
}
