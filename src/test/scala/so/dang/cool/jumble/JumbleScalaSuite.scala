package so.dang.cool.jumble

import org.scalatest.funsuite.AnyFunSuite

class JumbleScalaSuite extends AnyFunSuite {
    test("Types and values work out ok.") {
        val jumble = Jumble.of("Hello!").and(17).and(('a', 'b', 'c'))

        // The test is mostly that typing works here without casting:
        val abcs: (Char, Char, Char) = jumble.top
        val seventeen: Int = jumble.prev.top
        val greeting: String = jumble.prev.prev.top

        assertResult(('a', 'b', 'c'))(abcs)
        assertResult(17)(seventeen)
        assertResult("Hello!")(greeting)

        assert(!jumble.isEmpty)
        assertResult(3)(jumble.size)

        assert(!jumble.prev.isEmpty)
        assert(!jumble.prev.prev.isEmpty)
        assert(jumble.prev.prev.prev.isEmpty)
        assertThrows[RuntimeException](jumble.prev.prev.prev.prev)
    }

    test("Types work out in Java too.") {
        JumbleJavaTest.test()
    }
}
