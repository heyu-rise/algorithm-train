import java.util.Arrays;

/**
 *
 * 279. 完全平方数
 *
 * @author heyu
 * @date 2022/1/2
 */
public class PerfectSquares {

    public int numSquares(int n) {
        int[] f = new int[n + 1];
        Arrays.fill(f, Integer.MAX_VALUE);
        f[0] = 0;
        for(int i = 1; i * i <= n; i++) {
            for(int j = i * i; j <= n; j++) {
                f[j] = Math.min(f[j], f[j - i * i] + 1);
            }
        }
        return f[n];
    }

}
