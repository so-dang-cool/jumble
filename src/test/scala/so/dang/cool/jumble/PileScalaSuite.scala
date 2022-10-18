package so.dang.cool.jumble

import org.scalatest.funsuite.AnyFunSuite

class PileScalaSuite extends AnyFunSuite {
    test("Types and values work out ok. (One, push two more)") {
        val pile = Pile.of("Hello!").putOn(17).putOn(('a', 'b', 'c'))

        // The test is mostly that typing works here without casting:
        val abcs: (Char, Char, Char) = pile.top
        val seventeen: Int = pile.under.top
        val greeting: String = pile.under.under.top

        assertResult(('a', 'b', 'c'))(abcs)
        assertResult(17)(seventeen)
        assertResult("Hello!")(greeting)

        assert(!pile.isEmpty)
        assertResult(3)(pile.size)

        assert(!pile.under.isEmpty)
        assertResult(2)(pile.under.size)

        assert(!pile.under.under.isEmpty)
        assertResult(1)(pile.under.under.size)

        assert(pile.under.under.under.isEmpty)
        assertResult(0)(pile.under.under.under.size)

        assertThrows[RuntimeException](pile.under.under.under.under)
    }
}
