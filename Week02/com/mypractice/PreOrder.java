package com.mypractice;


import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class PreOrder {
    public List<Integer> preOrder(Node root) {
        LinkedList<Node> stack=new LinkedList<>();
        LinkedList<Integer> output=new LinkedList<>();
        if (root == null) {
            return output;
        }
        stack.add(root);
        while (!stack.isEmpty()) {
            Node node=stack.pollLast();
            output.add(node.val);
            Collections.reverse(node.children);
            for (Node item : node.children) {
                stack.add(item);
            }
        }

        return output;
    }
}
