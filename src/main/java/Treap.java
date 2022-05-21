
import java.util.*;

public class Treap<T extends Comparable<T>> implements Set<T> {
    //x - ключ y - приоритет
    private T x;
    private Integer y = null;
    private int size = 0;

    private Treap<T> Left = null;
    private Treap<T> Right = null;


    Treap(){
        this.x = null;
    }

    public Treap(T x, Integer y, Treap<T> Left, Treap<T> Right){
        this.x = x;
        this.y = y;
        this.Left = Left;
        this.Right = Right;
    }

    /**
     * Размер дерева
     */

    @Override
    public int size() {
        return size;
    }

    /**
     * Удаление дерева
     */

    @Override
    public void clear() {
        this.x = null;
        this.y = null;
        this.Left = null;
        this.Right= null;
        this.size = 0;
    }

    /**
     * Существует ли дерево
     */

    public boolean isEmpty() {
        return x == null;
    }

    /**
     * Содержится ли элемент в дереве
     */

    @Override
    public boolean contains(Object o) {
        @SuppressWarnings("unchecked")
        T t = (T) o;
        if (o == null)
            return false;
        Treap<T> find = find(t);
        return find != null;
    }

    /**
     * Содержатся ли все элементы коллекции в дереве
     */

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object element : c)
            if (!contains(element))
                return false;
        return true;
    }

    /**
     * Поиск элемента в дереве, выводит дерево, начиная с этого элемента
     */

    Treap<T> find(T value) {
        if (this.x == null)
            return null;
        return find(this, value);
    }


    private Treap<T> find(Treap<T> start, T value) {
        int comp = value.compareTo(start.x);
        if (comp == 0)
            return start;
        else if (comp < 0) {
            if (start.Left == null || start.Left.x == null) return null;
            return find(start.Left, value);
        }
        else {
            if (start.Right == null || start.Right.x == null) return null;
            return find(start.Right, value);
        }
    }



    /**
     * Слияние двух деревьев
     */

    public Treap<T> Merge(Treap<T> L, Treap<T> R) {
        if (L == null || L.x == null) {
            return R;
        }
        if (R == null || R.x == null) {
            return L;
        }

        Treap<T> result;

        if (L.y > R.y) { //если приотритет корня левого больше приоритета корня правого
            //R полностью добавляется в правое поддерево так как ключи у него больше
            // L.left полностью добавляется в левое поддерево так как ключи меньше
            // L. right добавится в правое, но перед этим сольется с R
            Treap<T> newR = Merge(L.Right, R);
            result = new Treap<>(L.x, L.y, L.Left, newR);

        }
        else {
            //если приотритет корня левого меньше приоритета корня правого
            Treap<T> newL = Merge(L, R.Left);
            result = new Treap<>(R.x, R.y, newL, R.Right);
        }
        return result;
    }

    public static class Pair<K, V> {

        private final K element0;
        private final V element1;


        public Pair(K element0, V element1) {
            this.element0 = element0;
            this.element1 = element1;
        }

        public K getElement0() {
            return element0;
        }

        public V getElement1() {
            return element1;
        }

    }

    /**
     * Разделение
     */

    private Pair<Treap<T>, Treap<T>> Split(T x){
        // одно дерево будет с вершиной > x, другое < x
        Treap<T> newTreap = new Treap<>();
        Treap<T> L, R;
        int comp = this.x.compareTo(x);
        if (comp <= 0) { // если оказалось, что <= x
            //тогда все элементы левого поддерева окажутся в L так как все ключи < x
            //элементы правого поддерева частично окажутся в правом L(когда ключи < x), а частично в R(когда ключи >x)
            if (Right == null || Right.x == null)
                R = null;
            else {
                Pair<Treap<T>, Treap<T>> afterSplit =  Right.Split(x);
                R = afterSplit.getElement1();
                newTreap = afterSplit.getElement0();
            }
            L = new Treap<>(this.x, y, Left, newTreap);
        }
        else  {
            if (Left == null || Left.x == null)
                L = null;
            else {
                Pair<Treap<T>, Treap<T>> afterSplit = Left.Split(x);
                L = afterSplit.getElement0();
                newTreap = afterSplit.getElement1();
            }
            R = new Treap<>(this.x, y, newTreap, Right);
        }
        return new Pair<>(L, R);
    }

    /**
     * Добавление элемента c приоритетом
     */

    boolean add(T x, Integer y) {
        //если такой элемент уже есть
        if (x == null || contains(x))
            return false;
        //если дерево пустое и мы добавляем первый эелемент
        if (isEmpty() || this.x == null) {
            this.x = x;
            this.y = y;
            this.Left = new Treap<>();
            this.Right = new Treap<>();
        }
        else {
            Pair<Treap<T>, Treap<T>> afterSplit = Split(x);
            Treap<T> l = afterSplit.getElement0();
            Treap<T> r = afterSplit.getElement1();
            Treap<T> oneVertex = new Treap<>(x, y, new Treap<T>(), new Treap<T>());
            Treap<T> treap = Merge(Merge(l, oneVertex), r);

            this.x = treap.x;
            this.y = treap.y;
            this.Left = treap.Left;
            this.Right = treap.Right;
        }
        size++;
        return true;
    }


    /**
     * Добавление элемента без заданного приоритета, приоритет в диапазоне [-100; 100]
     */

    @Override
    public boolean add(T value) {
        return add(value, (int)(Math.random()*(200+1) - 100));
    }

    /**
     * Добавление всех элементов из коллекции
     */

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean bool = false;
        for (T t : c)
            if (this.add(t))
                bool = true;
        return bool;
    }

    /**
     * Нахождение родителя узла, выводит дерево, начиная с родителя элемента
     */

    public Treap<T> findParent(T value) {
        //если элемента нет в дереве
        if (!contains(value))
            throw new IllegalArgumentException("Элемента нет в дереве");
        //если дерево пустое
        if (this.x == null) return null;
        return findParent(this, value);
    }

    public Treap<T> findParent(Treap<T> start, T value) {
        //нахождение в дереве start
        int comp = value.compareTo(start.x);
        if(comp == 0)
            return null;//если элемент оказался корнем
        else if (comp < 0) {
            if (start.Left == null) return start;
            if (start.Left.x.compareTo(value) == 0) return start;
            return findParent(start.Left, value);
        } else {
            if (start.Right == null) return start;
            if (start.Right.x.compareTo(value) == 0) return start;
            return findParent(start.Right, value);
        }
    }

    /**
     * Удаление элемента
     */

    @SuppressWarnings("unchecked")
    @Override
    public boolean remove(Object o) {
        T value = (T) o;
        //если дерево пустое или в нем нет такого элемента
        if (this.x == null || !this.contains(value))
            return false;
        //находим сам элемент
        Treap<T> treap = find(value);
        //удаляем корень
        Treap<T> result = Merge(treap.Left, treap.Right);
        //находим родителя
        Treap<T> parent = findParent(value);
        //если элемент был корнем
        if (parent == null || parent.x == null) {
            this.x = result.x;
            this.y = result.y;
            this.Left = result.Left;
            this.Right = result.Right;
        }
        else {
            //если наш элемента слева от родителя
            if (parent.Left != null && parent.Left.x != null && parent.Left.x.compareTo(value) == 0) {
                parent.Left.x = result.x;
                parent.Left.y = result.y;
                parent.Left.Left = result.Left;
                parent.Left.Right = result.Right;
            }
            //если наш элемента справа от родителя
            else {
                parent.Right.x = result.x;
                parent.Right.y = result.y;
                parent.Right.Left = result.Left;
                parent.Right.Right = result.Right;
            }
        }
        size--;
        return true;
    }

    /**
     * Удаление всех элементов из дерева
     */


    @Override
    public boolean removeAll(Collection<?> c) {
        boolean bool = false;
        for (Object t : c)
            if (this.contains(t)) {
                this.remove(t);
                bool = true;
            }
        return bool;
    }

    /**
     * Итератор (как в бинарном дереве)
     */

    @Override
    public Iterator<T> iterator() {
        return new TreapIterator();
    }

    public class TreapIterator implements Iterator<T> {

        private final Deque<Treap<T>> deque = new ArrayDeque<>();
        private Treap<T> current = null;

        public TreapIterator() {
            Treap<T> treap = Treap.this;
            while (treap != null && treap.x != null) {
                deque.push(treap);
                treap = treap.Left;
            }
        }

        @Override
        public boolean hasNext() {
            return !deque.isEmpty();
        }

        @Override
        public T next() {
            current = nextTreap();
            if (current == null) throw new NoSuchElementException();
            return current.x;
        }

        private Treap<T> nextTreap() {
            Treap<T> treap = deque.pop();
            current = treap;
            if (treap.Right != null && treap.Right.x != null) {
                treap = treap.Right;
                while (treap != null && treap.x != null) {
                    deque.push(treap);
                    treap = treap.Left;
                }
            }
            return current;
        }

        @Override
        public void remove() {
            Treap.this.remove(current.x);
        }
    }

    /**
     * Элементы дерева помещаются в массив
     */

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size()];
        Iterator<T> iterator = iterator();
        for (int i = 0; i < size(); i++)
            if (iterator.hasNext()) {
                array[i] = iterator.next();
            }
        return array;
    }

    /**
     * Элементы дерева помещаются в массив элементов
     */

    @SuppressWarnings("unchecked")
    @Override
    public <T1> T1[] toArray(T1[] a) {
        Object[] array = toArray();
        if (a.length < size)
            return (T1[]) Arrays.copyOf(array, size, a.getClass());
        System.arraycopy(array, 0, a, 0, size);
        if (a.length > size)
            a[size] = null;
        return a;
    }

    /**
     * Удаляет те элементы, которые есть в коллекции
     */

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean bool = false;
        Iterator<T> iterator = iterator();
        while (iterator.hasNext())
            if (!c.contains(iterator.next())) {
                iterator.remove();
                bool = true;
            }
        return bool;
    }


    /**
     * Нахождение роста дерева(длина наибольшей ветки)
     */

    public int deep(Treap<T> start) {
        if (start == null || start.x == null)
            return 0;
        return Math.max(deep(start.Left), deep(start.Left)) + 1;
    }

    int deep() {
        return deep(this);
    }

    /**
     * Вывод дерева в строку
     */

    @Override
    public String toString() {
        if (this. x == null) return null;
        if (this. y == null) return null;
        return "Node{" + "x=" + x + ", y=" + y + ", left=" + Left +
                ", right=" + Right + '}';
    }

    /**
     * Вывод дерева
     */

    public static void Print(Treap treap, int level){
        if (treap == null ) {
            return;
        }
        if(treap.x == null) return;
        if(treap.y == null) return;

        Print(treap.Right, level + 1);
        if (level != 0) {
            for(int i = 0; i < level - 1; i++)
                System.out.print("|        \t");
            System.out.println("|-----------" + "(" + treap.x + "; " + treap.y + ")");
        }
        else {
            System.out.println("(" + treap.x + "; " + treap.y + ")");
        }
        Print(treap.Left, level + 1);
    }

}