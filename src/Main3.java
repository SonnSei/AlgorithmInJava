import java.util.*;

/**
 * @Classname Main3
 * @Description TODO
 * @Date 2020/1/5 11:20
 * @Author SonnSei
 */
public class Main3 {
    public static void main(String[] args) {

    }

    public List<String> watchedVideosByFriends(List<List<String>> watchedVideos, int[][] friends, int id, int level) {
        int n = friends.length;
        boolean[][] map = new boolean[n][n];
        for (int i = 0; i < friends.length; i++) {
            int[] friend = friends[i];
            for (int val : friend) {
                map[i][val]=true;
                map[val][i]=true;
            }
        }

        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(id);
        for (int i = 0; i < level; i++) {
            System.out.println(queue);
            int size = queue.size();
            for (int j = 0; j < size; j++) {
                int cur = queue.poll();
                visited[cur] = true;
                for (int k = 0; k < n; k++) {
                    if (map[cur][k] == true) {
                        queue.add(k);
                    }
                }
            }
        }
        System.out.println(queue);
        Map<String, Integer> hashMap = new HashMap<>();
        for (int item :
                queue) {
            List<String> videos = watchedVideos.get(item);
            for (String video : videos) {
                if (hashMap.containsKey(video)) {
                    hashMap.put(video, hashMap.get(video) + 1);
                } else {
                    hashMap.put(video, 1);
                }
            }
        }
        PriorityQueue<Video> priorityQueue = new PriorityQueue<>();
        for (String  name : hashMap.keySet()) {
            priorityQueue.add(new Video(name, hashMap.get(name)));
        }
        List<String> ret = new ArrayList<>();
        while (!priorityQueue.isEmpty()) {
            ret.add(priorityQueue.poll().name);
        }
        return ret;
    }

    class Video implements Comparable<Video>{
        String name;
        int count;

        public Video(String name, int count) {
            this.name = name;
            this.count = count;
        }

        @Override
        public int compareTo(Video o) {
            int diff = this.count-o.count;
            return diff==0?this.name.compareTo(o.name):diff;
        }

        @Override
        public String toString() {
            return "Video{" +
                    "name='" + name + '\'' +
                    ", count=" + count +
                    '}';
        }
    }
}
