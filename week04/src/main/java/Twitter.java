import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 设计推特
 * 
 * @author heyu
 * @date 2021/12/12
 */
public class Twitter {

    /**
     * 推文排序
     */
    private final AtomicLong atomicLong;

    /**
     * 单个用户最大推文数量
     */
    private static final int MAX_LENGTH = 10;

    /**
     * feed最大推文数量
     */
    private static final int FEED_MAX_LENGTH = 10;

    /**
     * 用户关系
     */
    private final Map<Integer, Set<Integer>> userRelate;

    /**
     * 用户推文，使用队列，先进先出
     */
    private final Map<Integer, Queue<Tweet>> userTwitter;

    public Twitter() {
        atomicLong = new AtomicLong();
        userRelate = new HashMap<>(100);
        userTwitter = new HashMap<>(100);
    }

    public void postTweet(int userId, int tweetId) {
        Queue<Tweet> queue = userTwitter.computeIfAbsent(userId, k -> new LinkedList<>());
        int length = queue.size();
        while (length >= MAX_LENGTH) {
            queue.poll();
            length = queue.size();
        }
        queue.offer(new Tweet(tweetId, atomicLong.incrementAndGet()));
    }

    public List<Integer> getNewsFeed(int userId) {
        Set<Integer> useIdSet = userRelate.get(userId);
        if (useIdSet == null) {
            useIdSet = new HashSet<>();
        }
        useIdSet.add(userId);
        // 使用优先队列排序
        PriorityQueue<Tweet> priorityQueue = new PriorityQueue<>((o1, o2) -> o1.order > o2.order ? -1 : 1);
        for (Integer integer : useIdSet) {
            Queue<Tweet> queue = userTwitter.get(integer);
            if (queue == null) {
                continue;
            }
            priorityQueue.addAll(queue);
        }
        List<Integer> result = new ArrayList<>(FEED_MAX_LENGTH);
        for (int i = 0; i < FEED_MAX_LENGTH; i++) {
            if (priorityQueue.isEmpty()) {
                break;
            }
            result.add(priorityQueue.poll().tweetId);
        }
        return result;
    }

    public void follow(int followerId, int followeeId) {
        Set<Integer> set = userRelate.computeIfAbsent(followerId, k -> new HashSet<>());
        set.add(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {
        Set<Integer> set = userRelate.get(followerId);
        if (set == null) {
            return;
        }
        set.remove(followeeId);
    }

    private static class Tweet {
        public int tweetId;

        public long order;

        public Tweet() {

        }
        public Tweet(int tweetId, long order) {
            this.tweetId = tweetId;
            this.order = order;
        }
    }

}
