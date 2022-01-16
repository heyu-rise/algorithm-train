import java.util.HashSet;
import java.util.Set;

/**
 * 
 * 14. 最长公共前缀
 * 
 * @author heyu
 * @date 2022/1/16
 */
public class LongestCommonPrefix {

	public String longestCommonPrefix(String[] strs) {
        Set<Character> characterSet = new HashSet<>();
        int length = Integer.MAX_VALUE;
        for (String str : strs) {
            length = Math.min(length, str.length());
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            for (String str : strs) {
                characterSet.add(str.charAt(i));
            }
            if (characterSet.size() == 1) {
                stringBuilder.append(strs[1].charAt(i));
                characterSet.clear();
            } else {
                break;
            }
        }
        return stringBuilder.toString();
	}

}
