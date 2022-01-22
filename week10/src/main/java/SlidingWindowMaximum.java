import java.util.PriorityQueue;

/**
 * 239. 滑动窗口最大值
 * 
 * @author heyu
 * @date 2022/1/22
 */
public class SlidingWindowMaximum {

	public int[] maxSlidingWindow(int[] nums, int k) {
		// 用堆记录数组信息，安值从大到小排序
		PriorityQueue<Node> queue = new PriorityQueue<>((o1, o2) -> {
			if (o1.val == o2.val) {
				return 0;
			} else {
				return o1.val < o2.val ? 1 : -1;
			}
		});
		// 记录前k - 1个数组
		for (int i = 0; i < k - 1; i++) {
			queue.offer(new Node(i, nums[i]));
		}
		int[] result = new int[nums.length - k + 1];
		for (int i = k - 1; i < nums.length; i++) {
			queue.offer(new Node(i, nums[i]));
			// 去除堆内最大值
			Node node = queue.peek();
			// 如果堆内最大值在滑动窗口外，放弃，取下一个
			while (node.pos < i - k + 1) {
				queue.poll();
				node = queue.peek();
			}
			result[i - k + 1] = node.val;
		}
		return result;
	}

	/**
	 * 记录数组的值和所在位置
	 */
	private static class Node {

		public int pos;

		public int val;

		public Node(int pos, int val) {
			this.pos = pos;
			this.val = val;
		}
	}

}
