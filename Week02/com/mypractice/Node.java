package com.mypractice;

import java.util.List;
import java.util.Vector;
/**
 * N叉树的数据结构
 * */
public class Node {

    public int val;

    List<Node> children;

    public Node() {

    }

    public Node(int _val) {
        this.val=_val;
    }
    public Node(int _val , List<Node> children) {
        this.val=_val;
        this.children=children;
    }
}
