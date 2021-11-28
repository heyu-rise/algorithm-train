import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 子域名访问计数
 *
 * @author heyu
 * @date 2021/11/28
 */
public class SubdomainVisitCount {

	public static List<String> subdomainVisits(String[] cpdomains) {
		int size = cpdomains.length;
		List<String> result = new ArrayList<>(size * 3);
		// 用map存储每个域名出现得次数
		Map<String, Integer> resultCount = new HashMap<>(size * 3);
		for (String cpdomain : cpdomains) {
			String[] item = cpdomain.split(" ");
			int count = Integer.parseInt(item[0]);
			String[] itemWebsite = item[1].split("\\.");
			if (itemWebsite.length == 1) {
				resultCount.merge(itemWebsite[0], count, Integer::sum);
				continue;
			}
			String x = null;
			for (int i = itemWebsite.length - 1; i >= 0; i--) {
				String webItem = itemWebsite[i];
				if (x == null) {
					x = webItem;
				} else {
					// 拼接成子域名
					x = webItem + "." + x;
				}
				// 计算数量
				resultCount.merge(x, count, Integer::sum);
			}
		}
		// 拼接结果
		resultCount.forEach((key, value) -> {
			result.add(value + " " + key);
		});
		return result;
	}

}
