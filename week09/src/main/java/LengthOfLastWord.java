/**
 * 
 * 58. 最后一个单词的长度
 * 
 * @author heyu
 * @date 2022/1/16
 */
public class LengthOfLastWord {

	public int lengthOfLastWord(String s) {
		String[] ss = s.split(" ");
		return ss[ss.length - 1].length();
	}

}
