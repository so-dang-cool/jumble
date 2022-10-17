package so.dang.cool.jumble;

import java.util.List;

public class JumbleJavaTest {
    public static void test() {
        var jumble = Jumble.of("Hello!").and(17).and(List.of('a', 'b', 'c'));

        // The test is that typing works here without casting. (I.e. compiling is the test)
        List<Character> _abcs = jumble.top();
        Integer _seventeen = jumble.prev().top();
        String _greeting = jumble.prev().prev().top();
    }
}
