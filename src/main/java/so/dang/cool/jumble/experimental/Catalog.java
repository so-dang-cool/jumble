package so.dang.cool.jumble.experimental;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Experimental class that acts like a Map with any possible key.
 * <p>
 * Not sure if I'm adding anything here that doesn't exist with Map<Object, V> other than extra memory...?
 */
public class Catalog<V> {
    private Map<Class<? extends Object>, Map<Object, V>> items;

    public Catalog() {
        this.items = new HashMap<>();
    }

    public <K> Optional<V> get(K key) {
        return Optional.ofNullable(items.get(key.getClass())).map(i -> i.get(key));
    }

    public <K> boolean containsKey(K key) {
        Class<?> clazz = key.getClass();
        return items.containsKey(clazz) && items.get(clazz).containsKey(key);
    }

    public <K> Optional<V> put(K key, V value) {
        Class<?> clazz = key.getClass();
        Map<Object, V> classedItems = items.get(clazz);

        if (classedItems == null) {
            classedItems = new HashMap<>();
            items.put(clazz, classedItems);
        }

        Optional<V> prev = Optional.ofNullable(classedItems.get(key));

        classedItems.put(key, value);

        return prev;
    }
}
