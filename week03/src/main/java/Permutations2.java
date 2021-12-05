import java.util.*;

/**
 *
 * 全排列 II
 *
 * @author heyu
 * @date 2021/12/5
 */
public class Permutations2 {

    private List<List<Integer>> ans;

    private List<Integer> choose;

    private boolean[] used;

    private int totalNumber;

    public List<List<Integer>> permuteUnique(int[] nums) {
        choose = new ArrayList<>(totalNumber);
        ans = new ArrayList<>(totalNumber << 2);
        totalNumber = nums.length;
        used = new boolean[totalNumber];
        Arrays.sort(nums);
        sub(nums);
        return ans;
    }

    private void sub(int[] nums) {
        if (choose.size() == totalNumber)  {
            ans.add(new ArrayList<>(choose));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            // 这里为什么要这么做，我现在还不是很理解
            if (used[i] || (i > 0 && nums[i] == nums[i - 1] && !used[i - 1])) {
                continue;
            }
            choose.add(nums[i]);
            used[i] = true;
            sub(nums);
            used[i] = false;
            choose.remove(choose.size() - 1);
        }

    }

}
