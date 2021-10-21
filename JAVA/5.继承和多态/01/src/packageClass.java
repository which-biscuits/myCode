public class packageClass {
    public static void main(String[] args) {
        Integer a = Integer.valueOf("1");
        Integer b = Integer.valueOf("1");
        System.out.println(a.equals(b));
        System.out.println(a.hashCode());
        System.out.println(b.hashCode());
    }
}
