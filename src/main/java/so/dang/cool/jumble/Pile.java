package so.dang.cool.jumble;

/**
 * A Pile is a last-in first-out stack of independently typed nodes.
 *
 * @param <TOP> The "top" item in the current Pile. This is the most-recently added item.
 * @param <UNDER> The underlying Pile.
 */
public interface Pile<TOP, UNDER extends Pile<?, ?>> {
    TOP top();
    UNDER under();
    long size();

    /**
     * Create a new Pile that has the {@code next} item on top, and the current Pile beneath it.
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

    default boolean isEmpty() {
        return size() == 0;
    }

    static Pile<Void, Empty> of() {
        return new Empty();
    }

    static <A> Pile<A, Empty> of(A a) {
        return new Thing<>(a, new Empty(), 1L);
    }

    static <A, B> Pile<A, Pile<B, Empty>> of(A a, B b) {
        return Pile.of(b).putOn(a);
    }

    static <A, B, C> Pile<A, Pile<B, Pile<C, Empty>>> of(A a, B b, C c) {
        return Pile.of(c).putOn(b).putOn(a);
    }

    static <A, B, C, D> Pile<A, Pile<B, Pile<C, Pile<D, Empty>>>> of(A a, B b, C c, D d) {
        return Pile.of(d).putOn(c).putOn(b).putOn(a);
    }

    static <A, B, C, D, E> Pile<A, Pile<B, Pile<C, Pile<D, Pile<E, Empty>>>>> of(A a, B b, C c, D d, E e) {
        return Pile.of(e).putOn(d).putOn(c).putOn(b).putOn(a);
    }

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
