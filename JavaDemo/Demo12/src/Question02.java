import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * @author 11412
 * 1. 现有集合：ArrayList list = new ArrayList();
 * 2. 利用反射机制在这个泛型为Integer的ArrayList中存放一个String类型的对象。
 */
public class Question02 {
    public static void main(String[] args) throws Exception {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);

        Class<ArrayList> listClass = ArrayList.class;
        Method addMethod = listClass.getDeclaredMethod("add", Object.class);
        addMethod.invoke(list, "wzx");

        System.out.println(list);
    }
}
