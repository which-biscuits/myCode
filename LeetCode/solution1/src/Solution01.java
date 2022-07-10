import java.util.Arrays;
import java.util.HashMap;

/**
 * @author 11412
 */
public class Solution01 {
    /**
     给定一个整数数组 nums 和一个整数目标值 target，
     请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
     你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
     你可以按任意顺序返回答案。
     输入：nums = [2,7,11,15], target = 9
     输出：[0,1]
     解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。

     */
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (hashMap.containsKey(target - nums[i])) {
                return new int[]{i, hashMap.get(target - nums[i])};
            }
            hashMap.put(nums[i], i);
        }
        return null;
    }

    public static void main(String[] args) {
        Solution01 solution01 = new Solution01();
        int[] nums = new int[]{3, 3,12,11,19, 12,7};
        int target = 6;
        System.out.println(Arrays.toString(solution01.twoSum(nums, target)));
    }
}
