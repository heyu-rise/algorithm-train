/**
 *
 * 154. 寻找旋转排序数组中的最小值 II
 *
 * @author heyu
 * @date 2021/12/19
 */
public class FindMinimumInRotatedSortedArray2 {

	public int findMin(int[] nums) {
		int left = 0;
		int right = nums.length - 1;
		while (left < right) {
			int mid = (left + right) / 2;
			if (nums[mid] < nums[right]) {
				right = mid;
			} else if (nums[mid] > nums[right]) {
				left = mid + 1;
			} else {
                // 当中间值与最右侧的值相等时，把最右侧的值向左移动一位，
                // 当mid是最小值时，left与right会相遇，就会返回left，否则继续查找
				right = right - 1;
			}
		}
		return nums[left];
	}

}
