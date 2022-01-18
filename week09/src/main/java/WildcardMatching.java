/**
 *
 * 44. 通配符匹配
 *
 * @author heyu
 * @date 2022/1/18
 */
public class WildcardMatching {

	public boolean isMatch(String s, String p) {
		int m = s.length();
		int n = p.length();
		s = " " + s;
		p = " " + p;
		char[] sChars = s.toCharArray();
		char[] pChars = p.toCharArray();
		boolean[][] f = new boolean[m + 1][n + 1];
		f[0][0] = true;
        // 连续的‘*’可以匹配空字符串
		for (int j = 1; j <= n; j++) {
			if (pChars[j] == '*') {
				f[0][j] = true;
			} else {
				break;
			}
		}
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
                // 当s[i]与p[i]相等时，延续s与p上一个字符的状态
				if (sChars[i] == pChars[j] || pChars[j] == '?') {
					f[i][j] = f[i - 1][j - 1];
				} else if (pChars[j] == '*') {
                    // 当p[i]等于‘*’时，会有两种情况
                    // f[i - 1][j] 表示继续匹配，因为
                    // f[i][j - 1] 表示停止匹配，
					f[i][j] = f[i][j - 1] || f[i - 1][j];
				}
			}
		}
		return f[m][n];
	}

}
