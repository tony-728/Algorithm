package boj;

import java.io.*;
import java.util.*;

public class problem11725 {

    static class Node {
        int to;
        Node next;

        public Node(int to, Node next) {
            this.to = to;
            this.next = next;
        }
    }

    static int numOfNode;
    static Node[] graph;
    static StringBuilder sb = new StringBuilder();

    static void inputData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        numOfNode = Integer.parseInt(br.readLine().trim());

        graph = new Node[numOfNode + 1];

        for (int idx = 1; idx < numOfNode; idx++) {
            st = new StringTokenizer(br.readLine().trim());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            graph[from] = new Node(to, graph[from]);
            graph[to] = new Node(from, graph[to]);
        }
    }

    static void solution() {

        Deque<Integer> q = new ArrayDeque<>();

        q.addLast(1);

        boolean[] visited = new boolean[numOfNode + 1];
        visited[1] = true;

        int[] arrOfParent = new int[numOfNode + 1];

        while (!q.isEmpty()) {
            int nodeIdx = q.pollFirst();

            for (Node node = graph[nodeIdx]; node != null; node = node.next) {
                if (visited[node.to]) {
                    continue;
                }

                arrOfParent[node.to] = nodeIdx;

                q.add(node.to);
                visited[node.to] = true;
            }
        }

        for (int idx = 2; idx <= numOfNode; idx++) {
            sb.append(arrOfParent[idx]).append("\n");
        }
    }

    public static void main(String[] args) throws IOException {
        inputData();

        solution();

        System.out.println(sb);
    }
}
