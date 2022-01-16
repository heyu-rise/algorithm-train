/**
 *
 * 344. 反转字符串
 *
 * @author heyu
 * @date 2022/1/16
 */
public class ReverseString {

	public void reverseString(char[] s) {
		int length = s.length;
		if (length <= 1) {
			return;
		}
		for (int i = 0; i < length / 2; i++) {
			char a = s[i];
			s[i] = s[length - i - 1];
			s[length - i - 1] = a;
		}
	}

}
