import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class CustomSetTest {
    private CustomSet<Ammunition> set;
    Ammunition sword1, sword2, armor1, shield1;

    @BeforeEach
    public void setUp() {
        set = new CustomSet<>();
        sword1 = new Sword("Великий меч", 3, 1200);
        sword2 = new Sword("Кинджал", 0.5, 300);
        armor1 = new Armor("Кольчуга", 15, 2500);
        shield1 = new Shield("Металевий шит", 2.5, 1000);
    }

    @Test
    public void testEmptyConstructor() {
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
    }

    @Test
    public void testSingleElementConstructor() {
        CustomSet<Ammunition> singleSet = new CustomSet<>(sword1);
        assertEquals(1, singleSet.size());
        assertTrue(singleSet.contains(sword1));
    }

    @Test
    public void testCollectionConstructor() {
        ArrayList<Ammunition> collectionList = new ArrayList<>();
        collectionList.add(sword1);
        collectionList.add(armor1);
        CustomSet<Ammunition> ammunitionSet = new CustomSet<>(collectionList);
        assertTrue(ammunitionSet.contains(sword1));
        assertTrue(ammunitionSet.contains(armor1));
    }

    @Test
    public void testAdd() {
        set.add(sword1);
        assertFalse(set.add(sword1));
        assertEquals(1, set.size());
        assertTrue(set.contains(sword1));
    }

    @Test
    public void testRemove() {
        set.add(sword1);
        set.add(shield1);
        assertTrue(set.remove(sword1));
        assertEquals(1, set.size());
        assertFalse(set.contains(sword1));
        assertFalse(set.remove(sword2));
        assertFalse(set.remove(null));
    }

    @Test
    public void testClear() {
        set.add(sword1);
        set.add(shield1);
        set.clear();
        assertEquals(0, set.size());
        assertTrue(set.isEmpty());
    }

    @Test
    public void testAddAll() {
        ArrayList<Ammunition> items = new ArrayList<>();
        items.add(sword1);
        items.add(armor1);
        items.add(sword1);
        assertTrue(set.addAll(items));
        assertEquals(2, set.size());
    }

    @Test
    public void testContainsAll() {
        set.add(sword1);
        set.add(armor1);
        set.add(shield1);
        assertTrue(set.containsAll(Arrays.asList(sword1, shield1)));
    }

    @Test
    public void testRemoveAll() {
        set.add(sword1);
        set.add(armor1);
        set.add(shield1);
        ArrayList<Ammunition> items = new ArrayList<>();
        items.add(sword2);
        items.add(armor1);
        items.add(shield1);

        assertTrue(set.removeAll(items));
        assertTrue(set.contains(sword1));
        assertFalse(set.contains(armor1));
        assertFalse(set.contains(shield1));
    }

    @Test
    public void testRetainAll() {
        set.add(sword1);
        set.add(armor1);
        set.add(shield1);
        assertTrue(set.retainAll(Arrays.asList(sword1, shield1)));
        assertEquals(2, set.size());
        assertTrue(set.contains(sword1));
        assertFalse(set.contains(armor1));
        assertTrue(set.contains(shield1));
    }

    @Test
    public void testIterator() {
        set.add(armor1);
        set.add(sword1);

        Iterator<Ammunition> iterator = set.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(armor1, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(sword1, iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testToArray() {
        set.add(armor1);
        set.add(sword1);

        Object[] array = set.toArray();
        assertArrayEquals(new Ammunition[]{armor1, sword1}, array);
    }
}