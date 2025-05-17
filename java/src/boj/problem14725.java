package boj;

import java.io.*;
import java.util.*;

public class problem14725 {
    /*
     * 각 층을 따라 내려오면서 알게 된 먹이의 정보 갯수 n
     * 먹이 정보 갯수 k가 주어지고 
     * k개의 입력에 대해서 각 층마다 지나온 방에 있는 먹이 정보
     * - 알파벳 대문자로만 이루어져 있다.
     * 
     * 각 층에 대해서는  --로 구분한다.
     * 
     * 출력은 사전순서로 한다.
     * 
     * dfs
     * treeMap을 사용해서 정렬을 시킴
     */

    static class Node {
        TreeMap<String, Node> child;

        public Node() {
            this.child = new TreeMap<>();
        }
    }

    static int numOfFloor;
    static Node tree = new Node();
    static StringBuilder sb = new StringBuilder();

    static void inputData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        numOfFloor = Integer.parseInt(br.readLine().trim());

        for (int idx = 0; idx < numOfFloor; idx++) {

            st = new StringTokenizer(br.readLine().trim());

            int numOfFood = Integer.parseInt(st.nextToken());

            Node node = tree;

            for (int foodIdx = 0; foodIdx < numOfFood; foodIdx++) {
                String food = st.nextToken();

                // 처음 오는 먹이일 때 추가
                if (!node.child.containsKey(food)) {
                    node.child.put(food, new Node());
                }
                // 트리를 이어가기 위함
                node = node.child.get(food);
            }
        }
    }

    static void solution(Node node, String prefix) {
        for (String food : node.child.keySet()) {
            sb.append(prefix).append(food).append("\n");
            solution(node.child.get(food), prefix + "--");
        }
    }


    public static void main(String[] args) throws IOException {
        inputData();

        solution(tree, "");

        System.out.println(sb);
    }
}
