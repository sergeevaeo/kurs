import java.util.Random;

public class Treap {
    //x - ключ y - приоритет
    public int x;
    public int y;
    public int Size;

    public Treap Left;
    public Treap Right;

    public Treap(int x, int y, Treap left, Treap right) {
        this.x = x;
        this.y = y;
        this.Left = left;
        this.Right = right;
    }

    public Treap() {

    }
    
    // для создания дерева из одного узла (в методе add)
    public Treap(int x) {
        Random random = new Random();
        this.x = x;
        this.y = random.nextInt(100);
        Size = 1;
        this.Left = null;
        this.Right = null;
    }


    public int SizeOf(Treap treap) {
        return treap == null ? 0 : treap.Size;
    }

    /**
     * Вычисление размера дерева
     */

    public int SizeOfTreap(Treap treap) {
        int x;
        int y;
        if(treap.Right == null ) x = 0;
        else x = treap.Right.Size;
        if(treap.Left == null ) y = 0;
        else y = treap.Left.Size;
        return  x + y + 1;
    }
    

    public void Recalc() {
        Size = SizeOf(Left) + SizeOf(Right) + 1;
    }

    /**
     * Слияние
     */

    public Treap Merge(Treap L, Treap R) {

        if (L == null) return R;
        if (R == null) return L;

        Treap result;

        if (L.y > R.y) { //если приотритет корня левого больше приоритета корня правого
            //R полностью добавляется в правое поддерево так как ключи у него больше
            // L.left полностью добавляется в левое поддерево так как ключи меньше
            // L. right добавится в правое, но перед этим сольется с R
            Treap newR = Merge(L.Right, R);
            result = new Treap(L.x, L.y, L.Left, newR);
        } else {
            Treap newL = Merge(L, R.Left);
            result = new Treap(R.x, R.y, newL, R.Right);
        }
        result.Recalc();
        return result;
    }

    /**
     * Разделение
     */

    public Treap[] Split(int x) {
        // одно дерево будет с вершиной > x, другое < x
        Treap newTree = null;
        Treap L, R;
        if (this.x <= x) { // если оказалось, что < x
            //тогда все элементы левого поддерева окажутся в L так как все ключи < x
            //элементы правого поддерева частично окажутся в правом L(когда ключи < x), а частично в R(когда ключи >x)
            if (Right == null)
                R = null;
            else {
                Treap[] afterSplit = Right.Split(x);
                newTree = afterSplit[0];
                R = afterSplit[1];
            }
            L = new Treap(this.x, y, Left, newTree);
            L.Recalc();
        } else {
            if (Left == null)
                L = null;
            else {
                Treap[] afterSplit = Left.Split(x);
                L = afterSplit[0];
                newTree = afterSplit[1];

            }
            R = new Treap(this.x, y, newTree, Right);
            R.Recalc();
        }
        Treap[] result = new Treap[2];
        result[0] = L;
        result[1] = R;
        return result;
    }

    /**
     * Содержится ли  элемент в дереве
     */

    public boolean contains(int value) {
        if (this.x == value) {
            return true;
        }
        if (this.x > value)
            if (Left != null)
                return Left.contains(value);
        if (this.x < value)
            if (Right != null)
                return Right.contains(value);
        return false;
    }

    /**
     * Добавление
     */

    public void add(int x) {
        //разделим дерево на два(левое и правое)
        Treap[] t = Split(x);
        Treap l = t[0];
        Treap r = t[1];
        //создаем из элемента дерево
        Treap oneVertex = new Treap(x);
        //соединим левое и элемент, который добавляем
        //соединим все с правым
        Treap treap = Merge(Merge(l, oneVertex), r);
        this.x = treap.x;
        this.y = treap.y;
        this.Right = treap.Right;
        this.Left = treap.Left;
    }

    /**
     * Удаление
     */

    public void remove(int x) {
        //1) разделим на леове и правое по ключу x - 1
        //<= x- 1 в левом, > в правом (тут и искомый элемент)
        Treap[] afterSplit = Split(x - 1);
        Treap L = afterSplit[0];
        Treap R = afterSplit[1];
        //2) разделим правое по ключу x
        //искомый будет в левом
        Treap[] newAfterSplit = R.Split(x);
        Treap r = newAfterSplit[1];
        //3) соединим L и r
        Treap treap = Merge(L, r);
        this.x = treap.x;
        this.y = treap.y;
        this.Right = treap.Right;
        this.Left = treap.Left;
    }

    /**
     * Нахождение k-го элемента (начиная с 0)
     */


    public Integer KthElement(int K) {
        Treap current = this;
        while (current != null) {
            int sizeLeft = SizeOf(current.Left);//смотрим на размер левого поддерева

            if (sizeLeft == K) // если равен к, то отв корень
                return current.x;

            current = sizeLeft > K ? current.Left : current.Right;// если больше, то спускаемся в левое
            //наоборот спускаемся в правое
            if (sizeLeft < K)
                K -= sizeLeft + 1; // при этом уменьшаем величину к
        }
        return null;
    }

    /**
     * Нахождение роста дерева(длина наибольшей ветки)
     */

    public Integer deep(Treap treap) {
        Treap R = treap.Right;
        Treap L = treap.Left;
        int deep = 1;
        int right = 0;
        int left = 0;
        if (L == null && R == null) { //если дошли до конца
            return deep;
        }
        if (L != null)
            right += deep + deep(L);
        if (R != null)
            left += deep + deep(R);
        return Math.max(left, right);
    }


    /**
     * Вывод дерева в строку
     */

    @Override
    public String toString() {
        return "Node{" + "x=" + x + ", y=" + y + ", left=" + Left +
                ", right=" + Right + '}';
    }

    /**
     * Вывод дерева
     */

    public static void Print(Treap treap, int level){
        if (treap == null ) return;
        Print(treap.Right, level + 1);
        if(level != 0){
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