package so.dang.cool.jumble;

import org.scalatestplus.testng.TestNGSuite;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Stack;
import java.util.Vector;
import java.util.stream.Stream;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

public class JumbleJavaSuite extends TestNGSuite {
    @Test
    public void jumbleOfThings() {
        Jumble jumble = Jumble.of(
                "one", 1,
                "two", 2,
                "two", "two",
                "three", 3,
                "three", "three",
                "three", List.of(1, 2, 3)
        );

        assertFalse(jumble.isEmpty());
        assertEquals(jumble.get("one", Integer.class), Optional.of(1));
        assertEquals(jumble.get("two", Integer.class), Optional.of(2));
        assertEquals(jumble.get("two", String.class), Optional.of("two"));
        assertEquals(jumble.get("three", Integer.class), Optional.of(3));
        assertEquals(jumble.get("three", String.class), Optional.of("three"));
        assertEquals(jumble.get("three", List.class), Optional.of(List.of(1, 2, 3)));
    }

    @Test
    public void jumbleDoesThingsWithSuperclasses() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        Stream.of(1, 2, 3).forEachOrdered(arrayList::add);

        LinkedList<Integer> linkedList = new LinkedList<>();
        Stream.of(4, 5, 6).forEachOrdered(linkedList::add);

        Stack<Integer> stack = new Stack<>();
        Stream.of(7, 8, 9).forEachOrdered(stack::push);

        Vector<Integer> vector = new Vector<>();
        Stream.of(10, 11, 12).forEachOrdered(vector::add);

        Jumble jumble = Jumble.of(
                "nums", List.of(0, 0, 0), // For the List interface, I am overwritten.
                "nums", arrayList,
                "nums", linkedList,
                "nums", stack,
                "nums", vector
        );

        assertEquals(jumble.get("nums", ArrayList.class), Optional.of(List.of(1, 2, 3)));
        assertEquals(jumble.get("nums", LinkedList.class), Optional.of(List.of(4, 5, 6)));
        assertEquals(jumble.get("nums", Stack.class), Optional.of(List.of(7, 8, 9)));
        assertEquals(jumble.get("nums", Vector.class), Optional.of(List.of(10, 11, 12)));

        // Because vector is added last:
        assertEquals(jumble.get("nums", List.class), Optional.of(List.of(10, 11, 12)));
    }
}
