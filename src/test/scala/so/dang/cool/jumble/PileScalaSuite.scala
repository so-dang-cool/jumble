package so.dang.cool.jumble

import org.scalatest.funsuite.AnyFunSuite

class PileScalaSuite extends AnyFunSuite {
    test("Types and values work out ok. (One, push two more)") {
        val pile = Pile.of("Hello!").putOn(17).putOn(List('a', 'b', 'c'))

        // The test is mostly that typing works here without casting:
        val abcs: List[Char] = pile.top
        val seventeen: Int = pile.under.top
        val greeting: String = pile.under.under.top

        assertResult(List('a', 'b', 'c'))(abcs)
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

    test("piles of 1") {
        List(
          Pile.of(1),
          Pile.of().putOn(1)
        ).foreach(p => assertResult(1)(p.top()))
    }

    test("piles of 2") {
        List(
            Pile.of("two", 1),
            Pile.of(1).putOn("two"),
            Pile.of().putOn(1).putOn("two"),
        ).foreach(p => {
            assertResult("two")(p.top())
            assertResult(1)(p.under().top())
        })
    }

    test("piles of 3") {
        List(
            Pile.of(3, "two", 1),
            Pile.of("two", 1).putOn(3),
            Pile.of(1).putOn("two").putOn(3),
            Pile.of().putOn(1).putOn("two").putOn(3),
        ).foreach(p => {
            assertResult(3)(p.top())
            assertResult("two")(p.under().top())
            assertResult(1)(p.under().under().top())
        })
    }

    test("piles of 4") {
        List(
            Pile.of("four", 3, "two", 1),
            Pile.of(3, "two", 1).putOn("four"),
            Pile.of("two", 1).putOn(3).putOn("four"),
            Pile.of(1).putOn("two").putOn(3).putOn("four"),
            Pile.of().putOn(1).putOn("two").putOn(3).putOn("four"),
        ).foreach(p => {
            assertResult("four")(p.top())
            assertResult(3)(p.under().top())
            assertResult("two")(p.under().under().top())
            assertResult(1)(p.under().under().under().top())
        })
    }

    test("piles of 5") {
        List(
            Pile.of(5, "four", 3, "two", 1),
            Pile.of("four", 3, "two", 1).putOn(5),
            Pile.of(3, "two", 1).putOn("four").putOn(5),
            Pile.of("two", 1).putOn(3).putOn("four").putOn(5),
            Pile.of(1).putOn("two").putOn(3).putOn("four").putOn(5),
            Pile.of().putOn(1).putOn("two").putOn(3).putOn("four").putOn(5),
        ).foreach(p => {
            assertResult(5)(p.top())
            assertResult("four")(p.under().top())
            assertResult(3)(p.under().under().top())
            assertResult("two")(p.under().under().under().top())
            assertResult(1)(p.under().under().under().under().top())
        })
    }
}
