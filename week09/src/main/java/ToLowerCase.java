/**
 *
 * 709. 转换成小写字母
 *
 * @author heyu
 * @date 2022/1/16
 */
public class ToLowerCase {

    public String toLowerCase(String s) {
        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = s.toCharArray();
        for (char aChar : chars) {
            if(aChar >= 'A' && aChar <= 'Z') {
                aChar = (char) (aChar - 'A' + 'a');
            }
            stringBuilder.append(aChar);
        }
        return stringBuilder.toString();
    }

}
