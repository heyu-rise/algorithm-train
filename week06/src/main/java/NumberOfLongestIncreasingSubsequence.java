import java.util.Arrays;

/**
 * 最长递增子序列的个数
 * 
 * @author heyu
 * @date 2021/12/26
 */
public class NumberOfLongestIncreasingSubsequence {

	public int findNumberOfLIS(int[] nums) {
		int result = 1;
		int[] resultNums = new int[nums.length];
		Arrays.fill(resultNums, 1);
		int[] results = new int[nums.length];
		Arrays.fill(results, 1);
		for (int i = 1; i < nums.length; i++) {
			int subMax = 0;
			for (int j = 0; j < i; j++) {
				if (nums[i] > nums[j]) {
					// 求j的最大子序列长度
					subMax = Math.max(subMax, results[j]);
					// 当前最长递增子序列
					if (results[i] < results[j] + 1) {
						results[i] = results[j] + 1;
						result = Math.max(result, results[i]);
					}
				}
			}
			if (subMax == 0) {
				continue;
			}
			// 把j最大子序列数量值相同的子序列个数相加
			resultNums[i] = 0;
			for (int j = 0; j < i; j++) {
				if (nums[i] > nums[j]) {
					if (results[j] == subMax) {
						resultNums[i] = resultNums[i] + resultNums[j];
					}
				}
			}
		}
		// 把最大子序列个数量相同的子序列个数相加
		int resultSize = 0;
		for (int i = 0; i < results.length; i++) {
			if (results[i] == result) {
				resultSize = resultSize + resultNums[i];
			}
		}
		return resultSize;
	}

}
