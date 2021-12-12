import java.util.*;

import javafx.util.Pair;

/**
 * 被围绕的区域
 * 
 * @author heyu
 * @date 2021/12/12
 */
public class SurroundedRegions {

	/**
	 * 构建方向数组
	 */
	private static final int[] DX = new int[] { 0, 0, -1, 1 };
	private static final int[] DY = new int[] { -1, 1, 0, 0 };

	private char[][] board;

	private int m;

	private int n;

	private boolean[][] used;

	private static final char X = 'X';

	private static final char O = 'O';

	/**
	 * 围绕区域坐标
	 */
	private List<Pair<Integer, Integer>> selected;

	private Queue<Pair<Integer, Integer>> queue;

	public void solve(char[][] board) {
		this.m = board.length;
		this.n = board[0].length;
		if (m <= 2 || n <= 2) {
			return;
		}
		this.board = board;
		this.used = new boolean[m][n];
		selected = new ArrayList<>(m * n);
		queue = new LinkedList<>();
		// 去掉边遍历
		for (int i = 1; i < m - 1; i++) {
			for (int j = 1; j < n - 1; j++) {
				if (Objects.equals(board[i][j], X) || used[i][j]) {
					continue;
				}
				used[i][j] = true;
				queue.add(new Pair<>(i, j));
				// 广搜
				boolean x = bfs();
				// 当判断为围绕时，把该区域值置为X
				if (x) {
					for (Pair<Integer, Integer> pair : selected) {
						board[pair.getKey()][pair.getValue()] = X;
					}
					selected.clear();
				}
			}
		}
	}

	private boolean bfs() {
		boolean result = true;
		while (!queue.isEmpty()) {
			Pair<Integer, Integer> pair = queue.poll();
			selected.add(pair);
			int x = pair.getKey();
			int y = pair.getValue();
			for (int i = 0; i < 4; i++) {
				int xi = x + DX[i];
				int yi = y + DY[i];
				// 当出界时判断当前区域为非围绕，但是不用立即返回，把当前区域扫描完后返回
				if (xi >= m || yi >= n || xi < 0 || yi < 0) {
					result = false;
					continue;
				}
				if (Objects.equals(board[xi][yi], X) || used[xi][yi]) {
					continue;
				}
				used[xi][yi] = true;
				Pair<Integer, Integer> pair1 = new Pair<>(xi, yi);
				queue.add(pair1);
			}
		}
		// 当前区域为非围绕时，把围绕区域清空
		if (!result) {
			selected.clear();
		}
		return result;
	}

}
