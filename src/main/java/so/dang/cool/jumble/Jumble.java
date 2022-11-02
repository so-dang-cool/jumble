package so.dang.cool.jumble;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * A {@link java.util.Map}, but each value is keyed by both the key and its own
 * {@link java.lang.Class}. This means a single key can have multiple values
 * as long as the class varies.
 * <p>
 * When an entry is added, it is added to the Jumble for its exact implementing
 * class (as returned by {@code getClass()}), as well as all superclasses and
 * interfaces.
 * <p>
 * Adding an entry overwrites any previous entries for that key and class, as
 * well as the key and superclasses/interfaces. To check if something is there
 * beforehand, be sure to {@link Jumble#get(Object, Class)}.
 */
public class Jumble {
    private final Map<JumbleKey<?, ?>, Object> things = new HashMap<>();

    public Jumble() {}

    public static <K, V> Jumble of() {
        return new Jumble();
    }

    public static <K, V> Jumble of(K key, V value) {
        Jumble jumble = new Jumble();
        jumble.put(key, value);
        return jumble;
    }

    public static <K1, V1, K2, V2> Jumble of(K1 key1, V1 value1, K2 key2, V2 value2) {
        Jumble jumble = new Jumble();
        jumble.put(key1, value1);
        jumble.put(key2, value2);
        return jumble;
    }

    public static <K1, V1, K2, V2, K3, V3> Jumble of(
            K1 key1, V1 value1,
            K2 key2, V2 value2,
            K3 key3, V3 value3
    ) {
        Jumble jumble = new Jumble();
        jumble.put(key1, value1);
        jumble.put(key2, value2);
        jumble.put(key3, value3);
        return jumble;
    }

    public static <K1, V1, K2, V2, K3, V3, K4, V4> Jumble of(
            K1 key1, V1 value1,
            K2 key2, V2 value2,
            K3 key3, V3 value3,
            K4 key4, V4 value4
    ) {
        Jumble jumble = new Jumble();
        jumble.put(key1, value1);
        jumble.put(key2, value2);
        jumble.put(key3, value3);
        jumble.put(key4, value4);
        return jumble;
    }

    public static <K1, V1, K2, V2, K3, V3, K4, V4, K5, V5> Jumble of(
            K1 key1, V1 value1,
            K2 key2, V2 value2,
            K3 key3, V3 value3,
            K4 key4, V4 value4,
            K5 key5, V5 value5
    ) {
        Jumble jumble = new Jumble();
        jumble.put(key1, value1);
        jumble.put(key2, value2);
        jumble.put(key3, value3);
        jumble.put(key4, value4);
        jumble.put(key5, value5);
        return jumble;
    }

    public static <K1, V1, K2, V2, K3, V3, K4, V4, K5, V5, K6, V6> Jumble of(
            K1 key1, V1 value1,
            K2 key2, V2 value2,
            K3 key3, V3 value3,
            K4 key4, V4 value4,
            K5 key5, V5 value5,
            K6 key6, V6 value6
    ) {
        Jumble jumble = new Jumble();
        jumble.put(key1, value1);
        jumble.put(key2, value2);
        jumble.put(key3, value3);
        jumble.put(key4, value4);
        jumble.put(key5, value5);
        jumble.put(key6, value6);
        return jumble;
    }

    public static <K1, V1, K2, V2, K3, V3, K4, V4, K5, V5, K6, V6, K7, V7> Jumble of(
            K1 key1, V1 value1,
            K2 key2, V2 value2,
            K3 key3, V3 value3,
            K4 key4, V4 value4,
            K5 key5, V5 value5,
            K6 key6, V6 value6,
            K7 key7, V7 value7
    ) {
        Jumble jumble = new Jumble();
        jumble.put(key1, value1);
        jumble.put(key2, value2);
        jumble.put(key3, value3);
        jumble.put(key4, value4);
        jumble.put(key5, value5);
        jumble.put(key6, value6);
        jumble.put(key7, value7);
        return jumble;
    }

    /**
     * Puts a value for the given key. Returns a List of previous values
     * of anything this replaces.
     *
     * @param key The key identifying the value.
     * @param value A value to store.
     * @param <K> The key type.
     * @param <V> The value type.
     */
    public <K, V> void put(K key, V value) {
        put(key, value, value.getClass());
    }

    /**
     * Puts a value for the given key. Returns an Optional of the previous
     * value.
     *
     * @param key The key identifying the value.
     * @param value A value to store.
     * @param <K> The key type.
     * @param <V> The value type.
     * @param <VSuper> The class of the value type, or one of its superclasses or interfaces.
     */
    private <K, V, VSuper> void put(K key, V value, Class<VSuper> valueClass) {
        var superClass = valueClass.getSuperclass();
        if (superClass != null && superClass != Object.class) {
            put(key, value, superClass);
        }

        Arrays.stream(valueClass.getInterfaces()).forEach(i -> put(key, value, i));

        var jk = new JumbleKey<>(key, valueClass);
        things.put(jk, value);
    }

    /**
     * Get a value for the given key and given class.
     * @param key The key identifying the value.
     * @param valueClass The class of the value to retrieve.
     * @return An optional of the queried value.
     * @param <K> The key type.
     * @param <V> The value type.
     */
    public <K, V> Optional<V> get(K key, Class<V> valueClass) {
        var jk = new JumbleKey<>(key, valueClass);
        var value = (V) things.get(jk);
        return Optional.ofNullable(value);
    }

    public boolean isEmpty() {
        return things.isEmpty();
    }

    // getOrDefault, remove, putAll, putIfAbsent, size

    private static class JumbleKey<K, VC extends Class<?>> {
        private final K key;
        private final VC valueClass;

        private JumbleKey(K key, VC valueClass) {
            this.key = key;
            this.valueClass = valueClass;
        }

        @Override
        public boolean equals(Object that) {
            if (this == that) return true;
            if (that == null || getClass() != that.getClass()) return false;
            JumbleKey<?, ?> jk = (JumbleKey<?, ?>) that;
            return key.equals(jk.key) && valueClass.equals(jk.valueClass);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, valueClass);
        }
    }
}
