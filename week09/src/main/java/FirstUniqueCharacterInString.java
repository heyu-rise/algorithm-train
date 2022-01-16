import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 387. 字符串中的第一个唯一字符
 * 
 * @author heyu
 * @date 2022/1/16
 */
public class FirstUniqueCharacterInString {

    public int firstUniqChar(String s) {
		char[] chars = s.toCharArray();
		Map<Character, Integer> map = new HashMap<>(s.length());
		for (char c : chars) {
			map.put(c, map.getOrDefault(c, 0) + 1);
		}
		for (int i = 0; i < chars.length; i++) {
			if (map.get(s.charAt(i)) == 1) {
				return i;
			}
		}
		return -1;
	}

}
