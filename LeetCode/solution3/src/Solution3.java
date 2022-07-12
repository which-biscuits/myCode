import java.util.*;

/**
 * @author 11412
 */
public class Solution3 {
    /**
     给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
     输入: s = "abcabcbb"
     输出: 3
     解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     */
    public int lengthOfLongestSubstringTest1(String s) {
        int max = 0;
        int index;
        char[] source = s.toCharArray();
        List<Character> list = new ArrayList<>();
        for (char c : source) {
            index = list.indexOf(c);
            if (index != -1) {
                max = Math.max(list.size(), max);
                list.subList(0, index + 1).clear();
            }
            list.add(c);
        }
        return Math.max(list.size(), max);
    }

    public int lengthOfLongestSubstring(String s) {
        int max = 0;
        int min = 0;
        int index;
        for (int i = 0; i < s.length(); i++) {
            index = s.indexOf(s.charAt(i), min);
            if (index != -1 && index < i) {
                max = Math.max(i - min, max);
                min = index + 1;
            }
        }
        return Math.max(s.length() - min, max);
    }

    public static void main(String[] args) {
        Solution3 solution3 = new Solution3();
        int a = solution3.lengthOfLongestSubstring("aaaaabaaaaa");
        System.out.println(a);
    }
}
