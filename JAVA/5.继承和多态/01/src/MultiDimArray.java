import java.io.IOException;
import java.util.Arrays;

public class MultiDimArray {
    public static void main(String[] args) throws IOException {
        int[][][] a1 = {{{1,2,},{3,4,},},{{5,6,},{7,8,},},{{9,10,},{11,12,},},};
        System.out.println("a1.length = " + a1.length);
        for (int i = 0; i < a1.length; i++) {
            for (int j = 0; j < a1[i].length; j++) {
                for (int k = 0; k < a1[i][j].length; k++) {
                    System.out.println("a1[" + i + "][" + j + "][" + k + "]"+ a1[i][j][k]);
                }
            }
            System.out.println();
        }

        int[][] a2 = new int[][] {{2,4,},{6,8,},};
        System.out.println("a2.length = " + a2.length);
        for (int i = 0; i < a2.length; i++) {
            for (int j = 0; j < a2[i].length; j++) {
                System.out.println("a2[" + i + "][" + j + "]" + a2[i][j]);
            }
        }
        System.out.println();

        int[][] a3;
        a3 = new int[2][2];
        System.out.println("a3.length = " + a3.length);
        for (int i = 0; i < a3.length; i++) {
            for (int j = 0; j < a3[i].length; j++) {
                System.out.println("a3[" + i + "][" + j + "]" + a3[i][j]);
            }
        }

        int[] a4 = new int[8];
        Arrays.fill(a4,(int) 1);
        System.out.println(Arrays.deepToString(a3));
        System.out.println(factorial(5));
    }

    static long factorial(int num) {
        if (num <= 1) {
            return 1;
        } else {
            return num * factorial(num-1);
        }
    }

    public class Cat {
        int age;
        int weight;
        // 方法名 与 类名 相同
        Cat (int itsage,int itsweight) {	// 方法体,参数列表可为空
            age = itsage;
            weight = itsweight;
            System.out.println("a");
        }

    }
//    Cat cat1 = new Cat();	// 调用 默认 构造方法
    Cat cat2 = new Cat(12,15);	// 调用 自定义 构造方法
}
