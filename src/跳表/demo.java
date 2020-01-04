package 跳表;

/**
 * @Classname MySkiplist
 * @Description 跳表模板
 * @Date 2020/1/2 9:32
 * @Author SonnSei
 */
class MySkiplist {
    private final int MAX_LEVEL = 32;
    private final double RATE = 0.5;

    private int levelCount;
    private Node head;

    public MySkiplist() {
        levelCount = 1;
        head = new Node(007);
    }

    public boolean search(int target) {
        return getNode(target) != null;
    }

    private Node getNode(int target) {
        Node p = head;
        for (int i = levelCount - 1; i >= 0; i--) {
            while (p.forwards[i] != null && p.forwards[i].value < target)
                p = p.forwards[i];
            if(p.forwards[i] != null &&p.forwards[i].value==target) return p.forwards[i];
        }
        return null;
    }


    public void add(int value) {
        Node node = getNode(value);
        if (node != null) {
            node.count++;
            return;
        }
        node = new Node(value);
        Node[] preNodes = getPreNodes(node.level,value);

        for (int i = 0; i < node.level; i++) {
            node.forwards[i] = preNodes[i].forwards[i];
            preNodes[i].forwards[i] = node;
        }

        if(levelCount<node.level)levelCount = node.level;
    }

    public boolean delete(int value) {
        Node node = getNode(value);
        if(node==null)return false;
        if (node.count > 1) {
            node.count--;
            return true;
        }
        Node[] preNodes = getPreNodes(node.level, value);
        for (int i = 0; i < preNodes.length; i++) {
            preNodes[i].forwards[i] = preNodes[i].forwards[i].forwards[i];
        }
        return true;
    }

    private Node[] getPreNodes(int level, int target){
        Node[] preNodes = new Node[level];
        for (int i = 0; i < preNodes.length; i++) {
            preNodes[i] = head;
        }

        Node p = head;
        for (int i = level-1; i >=0 ; i--) {
            while(p.forwards[i]!=null && p.forwards[i].value<target)
                p = p.forwards[i];
            preNodes[i] = p;
        }
        return preNodes;
    }


    class Node {
        int value;
        int level;
        int count;
        Node[] forwards;

        public Node(int value) {
            this.value = value;
            this.count = 1;
            this.level = getRandomLevel();
            forwards = new Node[MAX_LEVEL];
        }
    }

    private int getRandomLevel() {
        int ret = 1;
        while (ret < MAX_LEVEL && Math.random() < RATE) ret++;
        return ret;
    }
}
