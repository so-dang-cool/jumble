package so.dang.cool.jumble

import org.scalatest.funsuite.AnyFunSuite

class PileScalaSuite extends AnyFunSuite {
    test("Types and values work out ok. (One, push two more)") {
        val pile = Pile.of("Hello!").putOn(17).putOn(('a', 'b', 'c'))

        // The test is mostly that typing works here without casting:
        val abcs: (Char, Char, Char) = pile.top
        val seventeen: Int = pile.next.top
        val greeting: String = pile.next.next.top

        assertResult(('a', 'b', 'c'))(abcs)
        assertResult(17)(seventeen)
        assertResult("Hello!")(greeting)

        assert(!pile.isEmpty)
        assertResult(3)(pile.size)

        assert(!pile.next.isEmpty)
        assertResult(2)(pile.next.size)

        assert(!pile.next.next.isEmpty)
        assertResult(1)(pile.next.next.size)

        assert(pile.next.next.next.isEmpty)
        assertResult(0)(pile.next.next.next.size)

        assertThrows[RuntimeException](pile.next.next.next.next)
    }
}
