package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class problem5639 {

    static class Node {
        int value;
        Node left;
        Node right;


        public Node(int value) {
            this.value = value;
        }

        public Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        void insert(int value) {
            // 현재 노드보다 입력이 작다
            if (value < this.value) {
                if (this.left == null) {
                    this.left = new Node(value);
                } else {
                    this.left.insert(value);
                }

                // 현재 노드보다 입력이 크다
            } else {
                if (this.right == null) {
                    this.right = new Node(value);
                } else {
                    this.right.insert(value);
                }
            }
        }
    }


    /*
    전위 순회 -> 후위 순회
    */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static void postOrder(Node node) {
        if (node == null) {
            return;
        }

        postOrder(node.left);
        postOrder(node.right);
        sb.append(node.value).append("\n");

    }

    public static void main(String args[]) throws IOException {

        Node root = new Node(Integer.parseInt(br.readLine()));

        while (true) {
            String input = br.readLine();

            if (input == null) {
                break;
            }

            int value = Integer.parseInt(input);

            root.insert(value);
        }

        postOrder(root);

        System.out.println(sb);

    }
}
