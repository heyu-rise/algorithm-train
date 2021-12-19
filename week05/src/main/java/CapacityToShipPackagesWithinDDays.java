/**
 *
 * 1011. 在 D 天内送达包裹的能力
 *
 * @author heyu
 * @date 2021/12/19
 */
public class CapacityToShipPackagesWithinDDays {

	public int shipWithinDays(int[] weights, int days) {
		// 求最大值
		int max = 0;
		for (int weight : weights) {
			max = Math.max(weight, max);
		}
		// 最小载重重量应为载重的最大值
		// 求最小载重重量
		int left = max;
		int right = max * weights.length;
		while (left < right) {
			int mid = (left + right) / 2;
			if (countDays(weights, mid) <= days) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		return left;
	}

	/**
	 * 计算该载重重量需要的运载天数
	 * 
	 * @param weights
	 *            重量数组
	 * @param weight
	 *            载重重量
	 * @return 运载天数
	 */
	private int countDays(int[] weights, int weight) {
		int count = 0;
		int total = 0;
		for (int j : weights) {
			total = total + j;
            // 当总重量大于载重重量时，天数加一，重载总重量
			if (total > weight) {
				count++;
				total = j;
			}
		}
        // 添加最后一船需要的天数
		count++;
		return count;
	}

}
