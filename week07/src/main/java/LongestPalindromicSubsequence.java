import java.util.Objects;

/**
 *
 * 516. 最长回文子序列
 *
 * @author heyu
 * @date 2022/1/2
 */
public class LongestPalindromicSubsequence {

	public int longestPalindromeSubseq(String s) {
		int n = s.length();
		if (n == 1) {
			return 1;
		}
		char[] chars = s.toCharArray();
		// f 表示从i到j的最大回文子序列长度
		int[][] f = new int[n][n];
		for (int i = n - 1; i >= 0; i--) {
			f[i][i] = 1;
			char iChar = chars[i];
			for (int j = i + 1; j < n; j++) {
				char jChar = chars[j];
                // 如果两值相等，那么他的值为它i和j分别相邻的子序列最大值加2
                // 如果两值不相等，那么他的值为相邻子序列值的最大值
				if (Objects.equals(iChar, jChar)) {
					f[i][j] = f[i + 1][j - 1] + 2;
				} else {
					f[i][j] = Math.max(f[i][j - 1], f[i + 1][j]);
				}
			}
		}
		return f[0][n - 1];
	}

}
