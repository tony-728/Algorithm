package boj;

import java.io.*;
import java.util.*;

public class problem1991 {
    /*
     * 이진트리를 입력받아서 전위, 중위, 후위 순회 결과를 출력하라
     */

    static class Node {
        char value;
        Node left;
        Node right;

        public Node() {}

        public Node(char value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    static int numOfNode;
    static Node root = new Node('A', null, null);

    static void setNode(Node node, char value, char left, char right) {
        if (node.value == value) {
            node.left = (left == '.') ? null : new Node(left, null, null);
            node.right = (right == '.') ? null : new Node(right, null, null);
        } else {
            if (node.left != null) {
                setNode(node.left, value, left, right);
            }

            if (node.right != null) {
                setNode(node.right, value, left, right);
            }
        }
    }

    static void inputData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        numOfNode = Integer.parseInt(br.readLine().trim());


        for (int idx = 0; idx < numOfNode; idx++) {
            st = new StringTokenizer(br.readLine().trim());

            char value = st.nextToken().charAt(0);
            char left = st.nextToken().charAt(0);
            char right = st.nextToken().charAt(0);

            setNode(root, value, left, right);
        }
    }

    static void preOrder(Node node) {
        if (node == null) {
            return;
        }

        System.out.print(node.value);
        preOrder(node.left);
        preOrder(node.right);
    }

    static void inOrder(Node node) {
        if (node == null) {
            return;
        }

        inOrder(node.left);
        System.out.print(node.value);
        inOrder(node.right);
    }

    static void postOrder(Node node) {
        if (node == null) {
            return;
        }

        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.value);
    }

    public static void main(String[] args) throws IOException {
        inputData();

        preOrder(root);
        System.out.println();
        inOrder(root);
        System.out.println();
        postOrder(root);
    }
}
