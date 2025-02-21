package boj;

import java.io.*;
import java.util.*;

public class problem11403 {
    /*
     * 가중치 없는 방향 그래프 G 모든 정점에 대해서 다른 정점으로 가는 길이가
     * 양수인 경로가 있는지 구하는 프로그램
     * 
     * 다른 정점으로 가는 길이가 있으면 1 없으면 0
     * 
     * 최대 정점 개수 100개
     * 
     * bfs
     * 
     */

    static class Node {
        int to;
        Node next;

        public Node(int to, Node next) {
            this.to = to;
            this.next = next;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int numOfNode;
    static Node[] graph;
    static int[][] answer;

    static void inputData() throws IOException {
        numOfNode = Integer.parseInt(br.readLine().trim());
        graph = new Node[numOfNode];

        answer = new int[numOfNode][numOfNode];

        for (int leftIdx = 0; leftIdx < numOfNode; leftIdx++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int rightIdx = 0; rightIdx < numOfNode; rightIdx++) {
                int node = Integer.parseInt(st.nextToken());

                if (node == 1) {
                    graph[leftIdx] = new Node(rightIdx, graph[leftIdx]);
                }
            }
        }
    }

    static void solution(int start) {
        boolean[] visited = new boolean[numOfNode];

        Deque<Integer> q = new ArrayDeque<>();

        for (Node node = graph[start]; node != null; node = node.next) {
            q.addLast(node.to);
            visited[node.to] = true;
            answer[start][node.to] = 1;
        }

        while (!q.isEmpty()) {

            int to = q.pollFirst();

            for (Node node = graph[to]; node != null; node = node.next) {
                if (visited[node.to]) {
                    continue;
                }

                q.addLast(node.to);
                visited[node.to] = true;
                answer[start][node.to] = 1;
            }

        }

    }

    public static void main(String[] args) throws IOException {
        inputData();

        for (int idx = 0; idx < numOfNode; idx++) {
            solution(idx);
        }

        for (int leftIdx = 0; leftIdx < numOfNode; leftIdx++) {
            for (int rightIdx = 0; rightIdx < numOfNode; rightIdx++) {
                sb.append(answer[leftIdx][rightIdx]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);

    }
}
