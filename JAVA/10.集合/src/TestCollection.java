import java.util.*;

/**
 * @author 11412
 */
public class TestCollection {
    public static void main(String[] args) {
        Collection<Number> numbers = new ArrayList<Number>();
        for (int index = 0; index < 5; index++) {
            // 自动装箱
            numbers.add(index);
        }
        System.out.println("numbers = " + numbers);

        Collection<Double> doubles = new ArrayList<Double>();
        // 自动装箱
        doubles.add(2.6);
        doubles.add(8.4);
        System.out.println("doubles = " + doubles);

        numbers.addAll(doubles);
        System.out.println("numbers = " + numbers);
        System.out.println("numbers.size() = " + numbers.size());

        // 自动装箱, 比较时依据方法equals()
        System.out.println("numbers.contains(2) = " + numbers.contains(2));
        System.out.println("numbers.contains(6) = " + numbers.contains(6));
        System.out.println("numbers.contains(2.6) = " + numbers.contains(2.6));
        System.out.println("numbers.contains(2.6f) = " + numbers.contains(2.6f));

        // 比较时依据方法equals()
        System.out.println("numbers.containsAll(doubles) = " + numbers.containsAll(doubles));

        Collection<Double> doubles1 = new ArrayList<Double>();
        doubles1.add(2.6);
        doubles1.add(9.8);
        System.out.println("doubles1 = " + doubles1);
        System.out.println("numbers.containAll(doubles1) = " + numbers.containsAll(doubles1));

        numbers.remove(1);
        numbers.remove(2);
        System.out.println("numbers = " + numbers);

        Object[] oa = numbers.toArray();
        System.out.println("oa = " + Arrays.toString(oa));

        // 如果参数指定的数组不能容纳当前集合中的所有元素, 则分配一个具有指定元素类型和当前集合大小的新数组
        Number[] na = numbers.toArray(new Number[0]);
        System.out.println("na = " + Arrays.toString(na));

        // 比较时依据方法equals()
        numbers.retainAll(doubles);
        System.out.println("numbers = " + numbers);
        numbers.removeAll(doubles1);
        System.out.println("numbers = " + numbers);
        System.out.println("numbers.isEmpty() = " + numbers.isEmpty());

        numbers.clear();
        System.out.println("numbers = " + numbers);
        System.out.println("numbers.isEmpty() = " + numbers.isEmpty());

        Collection<Num> nums = new ArrayList<Num>();
        for (int index = 0; index < 5; index++) {
            nums.add(new Num(index));
        }
        Num num = new Num(10);
        nums.add(num);
        System.out.println("nums = " + nums);
        System.out.println("nums.contains(new Num(10)) = " + nums.contains(new Num(10)));
        nums.remove(new Num(10));
        System.out.println("nums = " + nums);
        System.out.println("nums.contains(num) = " + nums.contains(num));
        nums.remove(num);
        System.out.println("nums = " + nums);
    }
}

class Num {
    int num;
     public Num(int num) {
         this.num = num;
     }

    @Override
    public String toString() {
        return Integer.toString(num);
    }
}
