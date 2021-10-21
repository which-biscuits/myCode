import java.util.*;

public class TestGenericList {
    public static void main(String[] args) {
        GenericList<Integer> ints = new GenericList<Integer>();
        for (int index = 0; index < 10; index++) {
            ints.add(index);
        }

        GenericList<String> strs = new GenericList<String>();
        for (int index = 0; index < 5; index++) {
            strs.add("str" + index);
        }
        System.out.println(ints);
        System.out.println(strs);
        TestGenericList.test(1,2);
        Map<Integer,Integer> list = new HashMap<Integer, Integer>();
        list.put();
        Collection

    }
    public static <T> void test(T a, int b) {
        System.out.println(a.toString());
        System.out.println(b);
    }
}

class Node<T> {
    private T item;
    private Node<T> next;
    public Node() {
        item = null;
        next = null;
    }
    public Node(T item, Node<T> next) {
        this.item = item;
        this.next = next;
    }
    public T getItem() {
        return item;
    }
    public void setItem(T item) {
        this.item = item;
    }
    public Node<T> getNext() {
        return next;
    }
    public void setNext(Node<T> next) {
        this.next = next;
    }
    public String toString() {
        return item.toString();
    }
}

class GenericList<N> {
    private Node<N> head;
    public GenericList() {
        head = null;
    }
    public void add(N item) {
        head = new Node<N>(item, head);
    }
    public Node<N> getHead() {
        return head;
    }
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (Node<N> node = head; node != null; node = node.getNext()) {
            str.append(node).append(" ");
        }
        return str.toString();
    }
}
