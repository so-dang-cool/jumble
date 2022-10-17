package so.dang.cool.jumble;

/**
 * A Jumble is a linked list of independently typed nodes.
 *
 * @param <TOP> The "top" item in the current Jumble node. This is the most-recently added item.
 * @param <PREV> The previous Jumble.
 */
public interface Jumble<TOP, PREV extends Jumble<?, ?>> {
    TOP top();
    PREV prev();
    long size();

    default <NEXT> Jumble<NEXT, Jumble<TOP, PREV>> and(NEXT next) {
        return new Thing<>(next, this, size() + 1);
    }

    default boolean isEmpty() {
        return size() == 0;
    }

    public static <A> Jumble<A, Empty> of(A a) {
        return new Thing<>(a, new Empty(), 1L);
    }

    public static <A, B> Jumble<A, Jumble<B, Empty>> of(B b, A a) {
        return Jumble.of(b).and(a);
    }

    public static <A, B, C> Jumble<A, Jumble<B, Jumble<C, Empty>>> of(A a, B b, C c) {
        return Jumble.of(c).and(b).and(a);
    }

    public static <A, B, C, D> Jumble<A, Jumble<B, Jumble<C, Jumble<D, Empty>>>> of(A a, B b, C c, D d) {
        return Jumble.of(d).and(c).and(b).and(a);
    }

    public static <A, B, C, D, E> Jumble<A, Jumble<B, Jumble<C, Jumble<D, Jumble<E, Empty>>>>> of(A a, B b, C c, D d, E e) {
        return Jumble.of(e).and(d).and(c).and(b).and(a);
    }

    class Empty implements Jumble<Void, Empty> {
        private Empty() {}

        @Override
        public Void top() {
            throw new IndexOutOfBoundsException("Jumble overflow!");
        }

        @Override
        public Empty prev() {
            throw new IndexOutOfBoundsException("Jumble overflow!");
        }

        @Override
        public long size() {
            return 0;
        }
    }

    class Thing<TOP, PREV extends Jumble<?, ?>> implements Jumble<TOP, PREV> {
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
        public PREV prev() {
            return prev;
        }

        @Override
        public long size() {
            return size;
        }
    }
}
