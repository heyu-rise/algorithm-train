import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 699. 掉落的方块
 * 
 * @author heyu
 * @date 2022/1/23
 */
public class FallingSquares {

	public List<Integer> fallingSquares(int[][] positions) {
		int length = positions.length;
		// 构造高度数组
		int[] heights = new int[length];
		for (int i = 0; i < length; i++) {
			int left = positions[i][0];
			int size = positions[i][1];
			int right = left + size;
			heights[i] = heights[i] + size;
			// 叠加获取数组高度
			for (int j = i + 1; j < length; j++) {
				int left2 = positions[j][0];
				int size2 = positions[j][1];
				int right2 = left2 + size2;
				// 当正方形冲突时，更新冲突部分的最小高度
				if (left2 < right && left < right2) {
					heights[j] = Math.max(heights[j], heights[i]);
				}
			}
		}
		List<Integer> ans = new ArrayList<>();
		int cur = Integer.MIN_VALUE;
		for (int x : heights) {
			cur = Math.max(cur, x);
			ans.add(cur);
		}
		return ans;
	}

}
