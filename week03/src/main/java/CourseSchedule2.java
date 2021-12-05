import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * 课程表 II
 *
 * @author heyu
 * @date 2021/12/5
 */
public class CourseSchedule2 {

    /**
     * 出边数组
     */
	private List<List<Integer>> to;

    /**
     * 入边数组的组
     */
	private int[] inDeg;

    /**
     * 参加课程的个数
     */
	private int lessonCount = 0;

	public int[] findOrder(int numCourses, int[][] prerequisites) {
		to = new ArrayList<>(numCourses);
		inDeg = new int[numCourses];
        // 构建出边数组初始值
		for (int i = 0; i < numCourses; i++) {
			List<Integer> toItem = new ArrayList<>(numCourses);
			to.add(toItem);
		}
		for (int[] prerequisite : prerequisites) {
			int x = prerequisite[0];
			int y = prerequisite[1];
            // 入边数组个数加一，表示需要学本门课程需要学的其他课程的数量
            inDeg[x]++;
            List<Integer> toItem = to.get(y);
            // 构建出边数组，表示学完本课程可以满足学习其他课程的条件之一的课程
            toItem.add(x);
		}
		Queue<Integer> queue = new LinkedList<>();
		for (int i = 0; i < numCourses; i++) {
            // 可以马上学习的课程集合
			if (inDeg[i] == 0) {
				queue.offer(i);
			}
		}
		int[] lesson = new int[numCourses];
		while (!queue.isEmpty()) {
			int x = queue.poll();
            // 学习的课程入库，数量加一
			lesson[lessonCount] = x;
			lessonCount++;
			for (int y : to.get(x)) {
                // 学完本课程，满足学习其他课程的条件减一
				inDeg[y]--;
                // 当条件个数为0时，可惜学习该课程，加入递归循环
				if (inDeg[y] == 0) {
					queue.offer(y);
				}
			}
		}
		return lessonCount == numCourses ? lesson : new int[0];
	}

}
