package datastructure.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenzw
 * @date 2021/7/12
 */
public class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        this.val = 0;
        this.neighbors = new ArrayList<Node>();
    }
    public Node(int val) {
        this.val = val;
        this.neighbors = new ArrayList<Node>();
    }
    public Node(int val, ArrayList<Node> _neighbors) {
        this.val = val;
        this.neighbors = neighbors;
    }
}
