import java.util.HashMap;
import java.util.Map;

/**
 *
 * 和为 K 的子数组
 *
 * @author heyu
 * @date 2021/11/28
 */
public class SubarraySumEqualsK {

    public int subarraySum(int[] nums, int k) {
        int length = nums.length;
        // 求前缀和
        int[] sums = new int[length + 1];
        // 记录前缀和每个值出现的次数
        Map<Integer, Integer> map = new HashMap<>(length + 1, 1);
        map.put(0, 1);
        for (int i = 1; i < length + 1; i++) {
            sums[i] = sums[i - 1] + nums[i - 1];
            map.merge(sums[i], 1, Integer::sum);
        }
        int result = 0;
        // 从右到左记录前缀和已经出现值的次数
        Map<Integer, Integer> mapPass = new HashMap<>(length + 1, 1);
        for (int i = sums.length - 1; i >= 1; i--) {
            mapPass.merge(sums[i], 1, Integer::sum);
            int a = sums[i] - k;
            Integer count = map.get(a);
            if (count == null) {
                continue;
            }
            Integer passCount = mapPass.get(a);
            if (passCount != null) {
                count = count - passCount;
            }
            result += count;
        }
        return result;
    }

}
