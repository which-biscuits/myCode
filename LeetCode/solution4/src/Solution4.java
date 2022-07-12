import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 11412
 */
public class Solution4 {
    /**
     * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
     * 算法的时间复杂度应该为 O(log (m+n)) 。
     * @param nums1 int[]
     * @param nums2 int[]
     * @return double
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int size = (nums1.length + nums2.length) / 2;
        int left = 0;
        double midNums1;
        double midNums2;
        while (left != size) {
            midNums1 = nums1.length % 2 == 0 ? (nums1[nums1.length / 2 - 1] + nums1[nums1.length / 2]) / 2.0 : nums1[nums1.length / 2];
            midNums2 = nums2.length % 2 == 0 ? (nums2[nums2.length / 2 - 1] + nums2[nums2.length / 2]) / 2.0 : nums2[nums2.length / 2];
            if (nums1.length == 1) {
                if (midNums1 > midNums2) {
                    return nums2[size - left];
                } else {
                    Arrays.binarySearch(nums2, nums1[0]);
                }
            } else if (nums2.length == 1) {
                if (midNums2 > midNums1) {
                    return nums1[size - left];
                }
            }
            if (midNums1 > midNums2) {
                left = left + nums2.length / 2;
                nums2 = Arrays.copyOfRange(nums2, nums2.length / 2, nums2.length);
            } else if (midNums1 < midNums2) {
                left = left + nums1.length / 2;
                nums1 = Arrays.copyOfRange(nums1, nums1.length / 2, nums1.length);
            } else {
                return midNums1;
            }
        }
        return 0;
    }

    /**
     * 输入：nums1 = [1,3], nums2 = [2]
     * 输出：2.00000
     * 解释：合并数组 = [1,2,3] ，中位数 2
     * @param args
     */
    public static void main(String[] args) {
        Solution4 solution4 = new Solution4();
        int[] nums1 = {1, 3, 5, 7, 9, 10};
        int[] nums2 = {1, 3};
//        double result = solution4.findMedianSortedArrays(nums1, nums2);
//        System.out.println(result);
}

}
