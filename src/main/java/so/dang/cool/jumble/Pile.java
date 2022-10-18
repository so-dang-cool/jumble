package so.dang.cool.jumble;

/**
 * A Pile is a last-in first-out stack of independently typed nodes.
 *
 * @param <TOP> The "top" item in the current Pile. This is the most-recently added item.
 * @param <PREV> The underlying Pile.
 */
public interface Pile<TOP, PREV extends Pile<?, ?>> {
    TOP top();
    PREV next();
    long size();

    /**
     * Create a new Pile that has the {@param next} item on top, and the current Pile beneath it.
     * <p>
     * The original Pile is never mutated.
     *
     * @param next The item to add on top.
     * @return A new Pile with the {@param next} item on top.
     * @param <NEXT> The type of the {@param next} item.
     */
    default <NEXT> Pile<NEXT, Pile<TOP, PREV>> add(NEXT next) {
        return new Thing<>(next, this, size() + 1);
    }

    default boolean isEmpty() {
        return size() == 0;
    }

    static <A> Pile<A, Empty> of(A a) {
        return new Thing<>(a, new Empty(), 1L);
    }

    static <A, B> Pile<A, Pile<B, Empty>> of(B b, A a) {
        return Pile.of(b).add(a);
    }

    static <A, B, C> Pile<A, Pile<B, Pile<C, Empty>>> of(A a, B b, C c) {
        return Pile.of(c).add(b).add(a);
    }

    static <A, B, C, D> Pile<A, Pile<B, Pile<C, Pile<D, Empty>>>> of(A a, B b, C c, D d) {
        return Pile.of(d).add(c).add(b).add(a);
    }

    static <A, B, C, D, E> Pile<A, Pile<B, Pile<C, Pile<D, Pile<E, Empty>>>>> of(A a, B b, C c, D d, E e) {
        return Pile.of(e).add(d).add(c).add(b).add(a);
    }

    class Empty implements Pile<Void, Empty> {
        private Empty() {}

        @Override
        public Void top() {
            throw new IndexOutOfBoundsException("Pile overflow!");
        }

        @Override
        public Empty next() {
            throw new IndexOutOfBoundsException("Pile overflow!");
        }

        @Override
        public long size() {
            return 0;
        }
    }

    class Thing<TOP, PREV extends Pile<?, ?>> implements Pile<TOP, PREV> {
        private final PREV prev;
        private final TOP top;
        private final long size;

        private Thing(TOP top, PREV prev, long size) {
            this.prev = prev;
            this.top = top;
            this.size = size;
        }

        @Override
        public TOP top() {
            return top;
        }

        @Override
        public PREV next() {
            return prev;
        }

        @Override
        public long size() {
            return size;
        }
    }
}
