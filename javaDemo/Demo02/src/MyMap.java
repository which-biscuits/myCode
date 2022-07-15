import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author 11412
目标：输出一个字符串中每个字符出现的次数。（经典面试题）

分析：
（1）键盘录入一个字符串。aabbccddaa123。
（2）定义一个Map集合，键是每个字符，值是其出现的次数。 {a=4 , b=2 ,...}
（3）遍历字符串中的每一个字符。
（4）拿着这个字符去Map集合中看是否有这个字符键，有说明之前统计过，其值+1
没有这个字符键，说明该字符是第一次统计，直接存入“该字符=1”

 */
public class MyMap {
    public static void main(String[] args) {
        Map<Character, Integer> maps = new HashMap<>(4);
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入一个字符串：");
        String input = scanner.nextLine();
        for (char in : input.toCharArray()) {
            if (maps.containsKey(in)) {
                maps.put(in, maps.get(in) + 1);
            } else {
                maps.put(in, 1);
            }
        }
        System.out.println(maps);
    }
}
