import java.util.ArrayList;
import java.util.List;

/**
 *
 * 684. 冗余连接
 *
 * @author heyu
 * @date 2022/1/9
 */
public class RedundantConnection {

	private List<List<Integer>> toEdge;
	private boolean hasCircle = false;
	private boolean[] vis;

	public int[] findRedundantConnection(int[][] edges) {
        // 计算节点最大值
		int max = 0;
		for (int[] edge : edges) {
			int x = edge[0];
			int y = edge[1];
			max = Math.max(max, Math.max(x, y));
		}
		max++;
        // 构造出边数组
		toEdge = new ArrayList<>(max);
		for (int i = 0; i <= max; i++) {
			List<Integer> to = new ArrayList<>(max);
			toEdge.add(to);
		}
		for (int[] edge : edges) {
			int x = edge[0];
			int y = edge[1];
            // 只有遍历到该边的时候，才将边加到出边数组中，这样就能保证最后出现的边是使树变为图的边
			toEdge.get(x).add(y);
			toEdge.get(y).add(x);
            // 重载访问数组
			vis = new boolean[max];
			dfs(x, 0);
			if (hasCircle) {
				return edge;
			}
		}
		return new int[0];
	}

	private void dfs(int place, int father) {
		List<Integer> to = toEdge.get(place);
		vis[place] = true;
		for (int s : to) {
			if (s == father) {
				continue;
			}
			if (vis[s]) {
				hasCircle = true;
				return;
			}
			vis[s] = true;
			dfs(s, place);
			if (hasCircle) {
				return;
			}
			vis[s] = false;
		}
	}
}
