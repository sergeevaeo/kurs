import org.junit.Test;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

import static org.junit.Assert.*;


public class TreapTests {


    @Test
    public void size() {
        Treap<Integer> treap = new Treap<>();
        treap.add(10, 1);
        treap.add(4, 6);
        treap.add(2, 2);
        treap.add(9, 76);
        assertEquals(treap.size(), 4);
    }

    @Test
    public void clear() {
        Treap<Integer> treap = new Treap<>();
        treap.add(10);
        treap.add(4);
        treap.add(2);
        treap.add(9);
        treap.clear();
        assertEquals(treap.size(), 0);
    }

    @Test
    public void isEmpty() {
        Treap<Integer> treap = new Treap<>();
        treap.add(10);
        treap.add(4);
        treap.add(2);
        treap.add(9);
        treap.clear();
        assertTrue(treap.isEmpty());
    }

    @Test
    public void contains() {
        Treap<Integer> treap = new Treap<>();
        treap.add(10);
        treap.add(4);
        treap.add(2);
        treap.add(9);
        assertTrue(treap.contains(10));
        assertTrue(treap.contains(2));
        assertTrue(treap.contains(9));
        assertTrue(treap.contains(4));
    }
    @Test
    public void containsAll() {
        HashSet<Integer> answer = new HashSet<>();
        answer.add(10);
        answer.add(4);
        answer.add(2);
        answer.add(9);
        Treap<Integer> treap = new Treap<>();
        treap.add(10);
        treap.add(4);
        treap.add(2);
        treap.add(9);
        assertTrue(treap.containsAll(answer));
    }

    @Test
    public void find() {
        Treap<Integer> treap1 = new Treap<>();
        treap1.add(10, 3);
        treap1.add(4, 1);
        treap1.add(2, 2);
        treap1.add(9, 4);

        Treap.Print(treap1, 0);
        System.out.println("\n\n");
        Treap.Print(treap1.find(2), 0);
    }

    @Test
    public void add() {
        //1
        Treap<Integer> treap1 = new Treap<>();
        treap1.add(10);
        treap1.add(4);
        treap1.add(2);
        treap1.add(9);
        assertTrue(treap1.contains(4));
        assertTrue(treap1.contains(9));
        assertTrue(treap1.contains(10));
        assertTrue(treap1.contains(2));
        Treap.Print(treap1, 0);
        System.out.println("\n\n");
        //2
        Treap<String> treap2 = new Treap<>();
        treap2.add("a", 4);
        treap2.add("q", 8);
        treap2.add("b", 1);
        treap2.add("z", 9);
        assertTrue(treap2.contains("a"));
        assertTrue(treap2.contains("q"));
        assertTrue(treap2.contains("b"));
        assertTrue(treap2.contains("z"));
        Treap.Print(treap2, 0);
        System.out.println("\n\n");

        //3
        Treap<Integer> newTreap1 = new Treap<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            newTreap1.add(random.nextInt(100));
        }
        Treap.Print(newTreap1, 0);
        System.out.println("\n\n");
        System.out.println(newTreap1.toString());
    }

    @Test
    public void addAll() {
        HashSet<Integer> answer = new HashSet<>();
        answer.add(10);
        answer.add(4);
        answer.add(2);
        answer.add(9);
        Treap<Integer> treap = new Treap<>();
        treap.add(7);
        treap.add(1);
        treap.add(89);
        treap.add(6);
        treap.addAll(answer);
        assertEquals(8, treap.size());
    }

    @Test
    public void remove() {
        //1
        Treap<Integer> treap1 = new Treap<>();
        treap1.add(10, 1);
        treap1.add(4, 4);
        treap1.add(2, 6);
        treap1.add(9, 7);
        treap1.remove(9);
        assertTrue(treap1.contains(4));
        assertFalse(treap1.contains(9));
        assertTrue(treap1.contains(10));
        assertTrue(treap1.contains(2));
        Treap.Print(treap1, 0);
        System.out.println("\n\n");
        //2
        Treap<String> treap2 = new Treap<>();
        treap2.add("a", 4);
        treap2.add("q", 8);
        treap2.add("b", 1);
        treap2.add("z", 9);
        treap2.remove("b");
        treap2.remove("a");
        assertFalse(treap2.contains("a"));
        assertTrue(treap2.contains("q"));
        assertFalse(treap2.contains("b"));
        assertTrue(treap2.contains("z"));
        Treap.Print(treap2, 0);
        System.out.println("\n\n");
    }

    @Test
    public void findParent() {
        Treap<Integer> treap1 = new Treap<>();
        treap1.add(10, 3);
        treap1.add(4, 1);
        treap1.add(2, 2);
        treap1.add(9, 4);

        Treap.Print(treap1, 0);
        System.out.println("\n\n");
        Treap.Print(treap1.findParent(4), 0);
    }

    @Test
    public void removeAll() {
        HashSet<Integer> answer = new HashSet<>();
        answer.add(10);
        answer.add(4);
        answer.add(2);
        answer.add(9);
        Treap<Integer> treap = new Treap<>();
        treap.add(10);
        treap.add(4);
        treap.add(2);
        treap.add(9);
        treap.removeAll(answer);
        assertEquals(0, treap.size());
    }

    @Test
    public void hasNext() {
        Treap<Integer> treap = new Treap<>();
        treap.add(10);
        treap.add(4);
        treap.add(2);
        treap.add(9);
        Iterator<Integer> itr = treap.iterator();
        itr.next();
        itr.next();
        assertTrue(itr.hasNext());
    }

    @Test
    public void next() {
        Treap<Integer> treap = new Treap<>();
        treap.add(10);
        treap.add(4);
        treap.add(2);
        treap.add(9);
        Iterator<Integer> itr = treap.iterator();
        itr.next();
        int a = itr.next();
        assertEquals(a, 4);
    }

    @Test
    public void removeIterator() {
        Treap<Integer> treap = new Treap<>();
        treap.add(8);
        treap.add(1);
        treap.add(18);
        treap.add(13);
        Iterator<Integer> itr = treap.iterator();
        itr.next();
        itr.next();
        itr.remove();
        assertFalse(treap.contains(8));
    }

    @Test
    public void toArray() {
        Treap<Integer> treap = new Treap<>();
        treap.add(4);
        treap.add(1);
        treap.add(64);
        treap.add(16);
        treap.toArray();
        int our = 1;
        for (Object i : treap.toArray()) {
            assertEquals(i, our);
            our *= 4;
        }
    }

    @Test
    public void rerainAll() {
        HashSet<Integer> answer = new HashSet<>();
        answer.add(10);
        answer.add(4);
        Treap<Integer> treap = new Treap<>();
        treap.add(10);
        treap.add(4);
        treap.add(2);
        treap.add(9);
        treap.retainAll(answer);
        assertEquals(2, treap.size());
    }

    @Test
    public void deep() {
        Treap<Integer> treap = new Treap<>();
        treap.add(7, 1);
        treap.add(1, 4);
        treap.add(89, 9);
        treap.add(6, 56);
        int a = treap.deep();
        Treap.Print(treap, 0);
        assertEquals(a, 2);
    }
}