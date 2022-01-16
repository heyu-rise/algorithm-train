import java.util.HashSet;
import java.util.Set;

/**
 *
 * 771. 宝石与石头
 *
 * @author heyu
 * @date 2022/1/16
 */
public class JewelsAndStones {

	public int numJewelsInStones(String jewels, String stones) {
		Set<Character> jewelSet = new HashSet<>();
		for (char c : jewels.toCharArray()) {
			jewelSet.add(c);
		}
		char[] chars = stones.toCharArray();
		int count = 0;
		for (char aChar : chars) {
			if (jewelSet.contains(aChar)) {
				count++;
			}
		}
		return count;
	}

}
