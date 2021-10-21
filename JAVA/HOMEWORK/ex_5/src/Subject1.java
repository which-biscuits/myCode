import java.util.*;

public class Subject1 {
    public static void main(String[] args) {
//        // class 1
//        List<Character> A = new LinkedList<>();
//        for (int i = 0; i < 6; i++) {
//            A.add((char) (i + 70));
//        }
//        List<Character> B = new LinkedList<>();
//        for (int i = 0; i < 6; i++) {
//            B.add((char) (i + 74));
//        }
//
//        gather.showList(A);
//        gather.showList(B);
//
//        List<Character> ans;
//        System.out.println("AUB : ");
//        ans = gather.intersection(A, B);
//        gather.showList(ans);
//
//        System.out.println("A∩B : ");
//        ans = gather.union(A, B);
//        gather.showList(ans);
//
//        System.out.println("A-B : ");
//        ans = gather.sub(A, B);
//        gather.showList(ans);
//        // class 2
//        HashSet<Integer> h = new HashSet<Integer>();
//        Random r = new Random();
//        for (int i = 0; i < 50; i++) {
//            h.add((int) (r.nextFloat() * 20));
//        }
//        System.out.println(h.toString());
//
//        ArrayList<Integer> a = new ArrayList<Integer>(h);
//        System.out.println(a.toString());
//
//        HashMap<Integer,Integer> m = new HashMap<Integer, Integer>();
//        for (int i = 5; i < 10; i++) {
//            m.put(95+i,a.get(i));
//        }
//        System.out.println(m);
        // class 3
        for (int i = 0; i < 3; i++) {
            Apple.add();
        }
        // 分类
        Apple.statistics();
        // 最大最小
        Apple.MinAndMax();
        // 删除
        Apple.remove();
        System.out.println("删除后剩余的苹果: " + Apple.apples.toString());

    }


}

class gather {
    public static List<Character> intersection(List<Character> a, List<Character> b) {    // AUB
        List<Character> ans = new LinkedList<>();
        ans.addAll(a);
        for (char c : b) {
            if (ans.indexOf(c) == -1)
                ans.add(c);
        }
        return ans;
    }

    public static List<Character> union(List<Character> a, List<Character> b) { // A∩B
        List<Character> ans = new LinkedList<>();
        for (char c : b) {
            if (a.indexOf(c) != -1) {
                ans.add(c);
            }
        }
        return ans;
    }

    public static List<Character> sub(List<Character> a, List<Character> b) { // A-B
        List<Character> ans = new LinkedList<>();
        for (char c : a) {
            if (b.indexOf(c) == -1)
                ans.add(c);
        }
        return ans;
    }

    public static void showList(List<Character> data) {
        System.out.println(data.toString());
    }
}


class Apple {
    static List<Float> apples = new LinkedList<>();

    public static void add() {
        System.out.println("请输入苹果数量: ");
        Scanner scanner = new Scanner(System.in);
        int count = scanner.nextInt();
        Random r = new Random();
        for (int i = 0; i < count; i++) {
            float weight = (float) (r.nextFloat() * 0.5 + 0.5);
            apples.add(weight);
        }
    }

    public static void statistics() {
        HashMap<String,Integer> m = new HashMap<String, Integer>();
        m.put("75#",0);m.put("80#",0);m.put("85#",0);
        for (Float data : apples) {
            if (data >= 0.5 && data < 0.6)
                m.put("75#", m.get("75#") + 1);
            else if (data >= 0.6 && data < 0.8)
                m.put("80#", m.get("80#") + 1);
            else if (data >= 0.8 && data < 1.0 )
                m.put("85#", m.get("85#") + 1);
        }
        System.out.println("苹果的分类结果为 : ");
        System.out.println(m);
    }

    public static void MinAndMax() {
        float Min = 0,Max = 0;
        for (float a : apples) {
            if (a < Min || Min == 0)
                Min = a;
            else if (a > Max || Max == 0)
                Max = a;
        }
        System.out.println("最重的苹果为: " + Max);
        System.out.println("最轻的苹果为: " + Min);
    }
    public static void remove() {
        apples.removeAll(apples);
    }
}
// P2.	编写如下程序（可选题）。
//有三批苹果，采用交互指令方式（命令行，或是程序中输入）输入每批苹果数量，即分三次运输到仓库，
// 苹果重量在0.5kg～1kg之间随机取值，试编写程序对苹果进行筛选（注： 使用集合操作）：
//1)	分别统计75#（0.5～0.6），80#（0.6～0.8），85#（0.8～1.0）苹果个数；
//2)	给出这些苹果的最大重量与最小重量;
//3)	加工这些苹果（remove，删除所有对象）。


