package so.dang.cool.jumble;

/**
 * A Pile is a stack of independently typed nodes.
 * <p>
 * It is safe to share a Pile across threads as long as the items inside the
 * Pile are also safe to share. A Pile is an immutable and persistent data
 * structure. The items in a Pile cannot be reassigned, and two Piles that
 * fork off the the same ancestor share references to the ancestor rather than
 * duplicate memory allocation.
 * <p>
 * The basic interface is:
 * <ul>
 *   <li>The most recently added item is accessible with {@link Pile#top()}</li>
 *   <li>All other items are accessible as a Pile with {@link Pile#under()}</li>
 *   <li>
 *     Add an item with {@link Pile#putOn()}. It becomes the new "top" and the
 *     previous pile beomes the new "under."
 *   </li>
 *   <li>
 *     Calling either {@link Pile#top()} or {@link Pile#under()} on an empty
 *     Pile will result in an {@link IndexOutOfBoundsException}.
 *   </li>
 * </ul>
 *
 * @param <TOP> The "top" item in the current Pile. This is the most-recently added item.
 * @param <UNDER> The underlying Pile.
 */
public interface Pile<TOP, UNDER extends Pile<?, ?>> {
    /**
     * The Pile's topmost item.
     * <p>
     * Throws {@link IndexOutOfBoundsException} when called on an empty Pile.
     * 
     * @return The Pile's topmost item.
     */
    TOP top();

    /**
     * The Pile under this Pile's topmost item.
     * <p>
     * Throws {@link IndexOutOfBoundsException} when called on an empty Pile.
     * 
     * @return The Pile under this Pile.
     */
    UNDER under();

    /**
     * The number of items in the Pile.
     * @return The number of items in the Pile.
     */
    long size();

    /**
     * Create a new Pile that has the {@code next} item on top, and the current Pile under it.
     * <p>
     * The original Pile is never mutated.
     *
     * @param next The item to add on top.
     * @return A new Pile with the {@code next} item on top.
     * @param <NEXT> The type of the {@code next} item.
     */
    default <NEXT> Pile<NEXT, Pile<TOP, UNDER>> putOn(NEXT next) {
        return new Thing<>(next, this, size() + 1);
    }

    /**
     * The Pile's emptiness. Returns true when there are no items, and false otherwise.
     * @return Returns true when there are no items, and false otherwise.
     */
    default boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Create a new empty Pile.
     * @return A new empty Pile.
     */
    static Pile<Void, Empty> of() {
        return new Empty();
    }

    /**
     * Create a new Pile of a single item.
     * @return A new Pile of a single item.
     */
    static <A> Pile<A, Empty> of(A a) {
        return new Thing<>(a, new Empty(), 1L);
    }

    /**
     * Create a new Pile of two items. The first parameter will be the top.
     * @return A new Pile of two items.
     */
    static <A, B> Pile<A, Pile<B, Empty>> of(A a, B b) {
        return Pile.of(b).putOn(a);
    }

    /**
     * Create a new Pile of three items. The first parameter will be the top,
     * the second second, and the last last.
     * @return A new Pile of three items.
     */
    static <A, B, C> Pile<A, Pile<B, Pile<C, Empty>>> of(A a, B b, C c) {
        return Pile.of(c).putOn(b).putOn(a);
    }

    /**
     * Create a new Pile of four items. The first parameter will be the top,
     * the second second, and so on, with the last as last.
     * @return A new Pile of four items.
     */
    static <A, B, C, D> Pile<A, Pile<B, Pile<C, Pile<D, Empty>>>> of(A a, B b, C c, D d) {
        return Pile.of(d).putOn(c).putOn(b).putOn(a);
    }

    /**
     * Create a new Pile of five items. The first parameter will be the top,
     * the second second, and so on, with the last as last.
     * @return A new Pile of five items.
     */
    static <A, B, C, D, E> Pile<A, Pile<B, Pile<C, Pile<D, Pile<E, Empty>>>>> of(A a, B b, C c, D d, E e) {
        return Pile.of(e).putOn(d).putOn(c).putOn(b).putOn(a);
    }

    /**
     * A Pile of nothing. This type is at the "bottom" of all Piles.
     * <p>
     * Calling either {@link Pile#top()} or {@link Pile#under()} on an empty
     * Pile will result in an {@link IndexOutOfBoundsException}.
     */
    class Empty implements Pile<Void, Empty> {
        private Empty() {}

        @Override
        public Void top() {
            throw new IndexOutOfBoundsException("Pile overflow!");
        }

        @Override
        public Empty under() {
            throw new IndexOutOfBoundsException("Pile overflow!");
        }

        @Override
        public long size() {
            return 0;
        }
    }

    /**
     * One Thing on top of a Pile. The Pile immediately under could be another
     * Thing, or it could be {@link Empty}.
     */
    class Thing<TOP, UNDER extends Pile<?, ?>> implements Pile<TOP, UNDER> {
        private final TOP top;
        private final UNDER under;
        private final long size;

        private Thing(TOP top, UNDER under, long size) {
            this.top = top;
            this.under = under;
            this.size = size;
        }

        @Override
        public TOP top() {
            return top;
        }

        @Override
        public UNDER under() {
            return under;
        }

        @Override
        public long size() {
            return size;
        }
    }
}
