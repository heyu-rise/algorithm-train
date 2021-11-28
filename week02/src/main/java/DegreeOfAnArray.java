import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * 数组的度
 *
 * @author heyu
 * @date 2021/11/28
 */
public class DegreeOfAnArray {

    public int findShortestSubArray(int[] nums) {
        int size = nums.length;
        // 创建map，存储每个值出现的下标
        Map<Integer, List<Integer>> map = new HashMap<>(size);
        // 存储下标出现的最大个数
        int maxSize = 0;
        for (int i = 0; i < nums.length; i++) {
            List<Integer> item = map.computeIfAbsent(nums[i], k -> new ArrayList<>(size / 2));
            item.add(i);
            maxSize = Math.max(maxSize, item.size());
        }
        int minResult = Integer.MAX_VALUE;
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            List<Integer> value = entry.getValue();
            if (value.size() == maxSize) {
                // 获取开头下标和结尾下表较小的一个
                minResult = Math.min(minResult, value.get(value.size() - 1) - value.get(0) + 1);
            }
        }
        return minResult;
    }

}
