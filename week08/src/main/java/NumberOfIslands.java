import java.util.Objects;

/**
 *
 * 200. 岛屿数量
 *
 * @author heyu
 * @date 2022/1/9
 */
public class NumberOfIslands {

	private static final char ZERO = '0';
	/**
	 * 方向数组
	 */
	private final int[] mx = new int[] { 0, 0, -1, 1 };
	private final int[] my = new int[] { -1, 1, 0, 0 };

	private int n;

	/**
	 * 并查集数组
	 */
	private int[] fa;

	public int numIslands(char[][] grid) {
		int m = grid.length;
		n = grid[0].length;
		// 初始化并查集，m*n为水
		fa = new int[m * n + 1];
		for (int i = 0; i < m * n + 1; i++) {
			fa[i] = i;
		}
		int outSide = m * n;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				// 当值为水时，连接m*n
				if (Objects.equals(ZERO, grid[i][j])) {
					unionSet(outSide, num(i, j));
					continue;
				}
				for (int z = 0; z < 4; z++) {
					int x = i + mx[z];
					int y = j + my[z];
					if (x < 0 || y < 0 || x > m - 1 || y > n - 1 || Objects.equals(grid[x][y], ZERO)) {
						continue;
					}
					// 当相邻区域有岛屿时，彼此相连
					unionSet(num(i, j), num(x, y));
				}
			}
		}
		// 计算根的数量，减去水的根
		int ans = -1;
		for (int i = 0; i < fa.length; i++) {
			if (fa[i] == i) {
				ans++;
			}
		}
		return ans;
	}

	private int num(int i, int j) {
		return i * n + j;
	}

	private int find(int x) {
		if (x == fa[x]) {
			return x;
		}
		return fa[x] = find(fa[x]);
	}

	private void unionSet(int x, int y) {
		x = find(x);
		y = find(y);
		if (x != y) {
			fa[x] = y;
		}
	}

}
