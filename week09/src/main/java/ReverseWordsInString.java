import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * 151. 翻转字符串里的单词
 *
 * @author heyu
 * @date 2022/1/16
 */
public class ReverseWordsInString {

	private static final String SPACE = " ";

	public String reverseWords(String s) {
		String[] chars = s.split(SPACE);
		List<String> ss = new ArrayList<>(chars.length);
		for (String s1 : s.split(SPACE)) {
			if (s1.length() > 0) {
				ss.add(s1);
			}
		}
		Collections.reverse(ss);
		return String.join(" ", ss);
	}
}
