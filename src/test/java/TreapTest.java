import org.junit.Test;
import java.util.Random;

import static org.junit.Assert.*;


public class TreapTest extends Treap {

    @Test
    public void Merge() {
        //1
        Treap treap1 = new Treap(3);
        treap1.add(4);
        treap1.add(2);
        treap1.add(9);
        Treap treap2 = new Treap(10);
        treap2.add(87);
        treap2.add(20);
        treap2.add(13);
        Treap result = Merge(treap1, treap2);
        assertTrue(result.contains(3));
        assertTrue(result.contains(4));
        assertTrue(result.contains(9));
        assertTrue(result.contains(10));
        assertTrue(result.contains(87));
        assertTrue(result.contains(20));
        assertTrue(result.contains(13));
        assertTrue(result.contains(2));
        //2
        Random random = new Random();
        Treap treap3 = new Treap((int)(Math.random() * 50));
        Treap treap4 = new Treap((int)(Math.random() * 100 + 50));
        for (int i = 0; i < 10; i++) {
            int x = random.nextInt(100);
            if (x >= 50) treap4.add(x);
                else treap3.add(x);
        }
        Treap answer = Merge(treap3, treap4);
        Print(treap3, 0);
        System.out.println("\n");
        Print(treap4, 0);
        System.out.println("\n");
        Print(answer, 0);
    }

    @Test
    public void Split() {
        //1
        Treap treap = new Treap(3);
        treap.add(4);
        treap.add(2);
        treap.add(9);
        treap.add(10);
        treap.add(87);
        treap.add(20);
        treap.add(13);
        Treap[] twoTreaps = treap.Split(11);
        Treap treap1 = twoTreaps[0];
        Treap treap2 = twoTreaps[1];
        assertTrue(treap1.contains(3));
        assertTrue(treap1.contains(4));
        assertTrue(treap1.contains(9));
        assertTrue(treap1.contains(10));
        assertTrue(treap2.contains(87));
        assertTrue(treap2.contains(20));
        assertTrue(treap2.contains(13));
        assertTrue(treap1.contains(2));
        //2
        Random random = new Random();
        Treap treapNew = new Treap(random.nextInt(100));
        int x = 0;
        for (int i = 0; i < 10; i ++) {
            x = random.nextInt(100);
            treapNew.add(x);
        }
        Treap[] twoNewTreaps = treapNew.Split(x);
        Treap treap3 = twoNewTreaps[0];
        Treap treap4 = twoNewTreaps[1];
        Print(treapNew, 0);
        System.out.println("\n");
        System.out.println(x);
        System.out.println("\n");
        Print(treap3, 0);
        System.out.println("\n");
        Print(treap4, 0);
    }

    @Test
    public void add() {
        //1
        Treap treap = new Treap(8);
        treap.add(10);
        treap.add(4);
        treap.add(2);
        treap.add(9);
        assertTrue(treap.contains(4));
        assertTrue(treap.contains(9));
        assertTrue(treap.contains(10));
        assertTrue(treap.contains(2));
        //2
        Treap newTreap = new Treap(8);
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            newTreap.add(random.nextInt(100));
        }
        Print(newTreap, 0);
    }

    @Test
    public void SizeOfTreap() {
        //1
        Treap treap = new Treap(3);
        treap.add(4);
        treap.add(2);
        treap.add(9);
        assertEquals(4, SizeOfTreap(treap));
        //2
        Treap treapNew = new Treap(6);
        treapNew.add(4);
        treapNew.add(2);
        treapNew.add(9);
        treapNew.add(9);
        treapNew.add(9);
        assertEquals(6, SizeOfTreap(treapNew));
    }

    @Test
    public void remove() {
        //1
        Treap treap1 = new Treap(3);
        treap1.add(4);
        treap1.add(2);
        treap1.add(9);
        treap1.remove( 9);
        assertFalse(treap1.contains(9));
        //2
        Treap treap2 = new Treap(99);
        treap2.add(11);
        treap2.add(27);
        treap2.add(89);
        Print(treap2, 0);
        System.out.println("\n");
        treap2.remove( 27);
        Print(treap2, 0);
    }

    @Test
    public void KthElement() {
        //1
        Treap treap1 = new Treap(3);
        treap1.add(4);
        treap1.add(2);
        treap1.add(9);
        int answer = treap1.KthElement(3);
        assertEquals(9, answer);
        //2
        Treap treap2 = new Treap(3);
        treap2.add(4);
        treap2.add(2);
        treap2.add(11);
        treap2.add(65);
        treap2.add(98);
        int result = treap2.KthElement(4);
        assertEquals(65, result);

    }

    @Test
    public void deep() {
        //1
        Treap treap1 = new Treap(1);
        treap1.add(2);
        treap1.add(3);
        treap1.add(4);
        boolean a = false;
        if (deep(treap1) == 3 || deep(treap1) == 4) a = true;
        assertTrue(a);
        //2
        Treap treap2 = new Treap(1);
        treap2.add(12);
        treap2.add(3);
        treap2.add(4);
        treap2.add(7);
        treap2.add(11);
        Print(treap2, 0);
        System.out.println("\n");
        System.out.println(deep(treap2));
        boolean b = false;
        if (deep(treap2) <= 6  && deep(treap2) >= 3) b = true;
        assertTrue(b);
    }

    @Test
    public void Print() {
        //1
        Treap treap = new Treap(8);
        treap.add(10);
        treap.add(4);
        treap.add(2);
        treap.add(9);
        Print(treap, 0);
        System.out.println("\n");
        System.out.println(treap.toString());
        System.out.println("\n");

        //2
        Random random = new Random();
        Treap newTreap = new Treap(random.nextInt(100));
        for (int i = 0; i < 10; i++) {
            newTreap.add(random.nextInt(100));
        }
        Print(newTreap, 0);
        System.out.println("\n");
        System.out.println(newTreap.toString());
    }
}
