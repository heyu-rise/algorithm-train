/**
 *
 * 合并K个升序链表
 *
 * @author heyu
 * @date 2021/12/5
 */
public class MergeKSortedLists {

	public ListNode mergeKLists(ListNode[] lists) {
		if (lists.length == 0) {
			return null;
		}
		return mergeKList(lists, 0, lists.length - 1);
	}

	/**
	 * 合并一段列表
	 * 
	 * @param lists
	 *            原链表集合
	 * @param start
	 *            链表起始
	 * @param end
	 *            链表结尾
	 * @return
	 */
	private ListNode mergeKList(ListNode[] lists, int start, int end) {
		// 起始等于结尾时，返回该位置原链表
		if (start == end) {
			return lists[start];
		}
		// 起始，结尾相距1时，合并2个升序链表
		if (end - start == 1) {
			return mergeTwoLists(lists[start], lists[end]);
		}
		// 分开两段链表集合，递归执行合并一段列表
		int middle = (end + start) / 2;
		ListNode a = mergeKList(lists, start, middle);
		ListNode b = mergeKList(lists, middle + 1, end);
		return mergeTwoLists(a, b);
	}

	/**
	 * 合并2个升序链表
	 *
	 * @param l1
	 *            链表1
	 * @param l2
	 *            链表2
	 * @return
	 */
	private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		ListNode listNode = new ListNode(-1);
		ListNode prev = listNode;
		while (l1 != null && l2 != null) {
			if (l1.val >= l2.val) {
				prev.next = l2;
				l2 = l2.next;
			} else {
				prev.next = l1;
				l1 = l1.next;
			}
			prev = prev.next;
		}
		prev.next = l1 == null ? l2 : l1;
		return listNode.next;
	}

	public static class ListNode {
		int val;
		ListNode next;

		ListNode() {
		}

		ListNode(int val) {
			this.val = val;
		}

		ListNode(int val, ListNode next) {
			this.val = val;
			this.next = next;
		}
	}

}
