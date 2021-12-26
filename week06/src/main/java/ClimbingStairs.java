/**
 * @author heyu
 * @date 2021/12/23
 */
public class ClimbingStairs {

	private int n;

	private int[] stepResult;

	public int climbStairs(int n) {
		if (n == 1) {
			return 1;
		}
		this.n = n;
		stepResult = new int[n + 1];
		// 使用分治
		int a = count(1);
		int b = count(2);
		return a + b;
	}

	private int count(int step) {
		int other = n - step;
		if (other == 0 || other == 1) {
			stepResult[step] = 1;
			return 1;
		}
		int a = stepResult[step + 1];
		int b = stepResult[step + 2];
		if (a == 0) {
			a = count(step + 1);
			stepResult[step + 1] = a;
		}
		if (b == 0) {
			b = count(step + 2);
			stepResult[step + 2] = b;
		}
		return a + b;
	}

}
