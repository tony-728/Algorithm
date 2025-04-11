package boj;

import java.io.*;
import java.util.*;

public class problem15681 {
    /*
     * 트리가 주어질 때 입력으로 주어진 정점을 시작으로 하는 서브트리의 정점의 개수를 구하라
     * 
     * dfs
     * dp
     * - 메모이제이션
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
    static int root;
    static int numOfQuery;

    static Node[] graph;
    static boolean[] visited;
    static int[] arrOfSubNode;

    static void inputData() throws IOException {

        st = new StringTokenizer(br.readLine().trim());

        numOfNode = Integer.parseInt(st.nextToken());
        root = Integer.parseInt(st.nextToken());
        numOfQuery = Integer.parseInt(st.nextToken());

        graph = new Node[numOfNode + 1];
        visited = new boolean[numOfNode + 1];
        arrOfSubNode = new int[numOfNode + 1];

        for (int idx = 1; idx < numOfNode; idx++) {

            st = new StringTokenizer(br.readLine().trim());

            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());

            graph[left] = new Node(right, graph[left]);
            graph[right] = new Node(left, graph[right]);
        }

        visited[root] = true;
        dfs(root);

        // System.out.println(Arrays.toString(arrOfSubNode));
    }

    static void solution() throws IOException {

        for (int idx = 0; idx < numOfQuery; idx++) {
            int node = Integer.parseInt(br.readLine().trim());

            sb.append(arrOfSubNode[node]).append("\n");
        }
    }

    static void dfs(int nodeIdx) {

        for (Node node = graph[nodeIdx]; node != null; node = node.next) {

            if (visited[node.to]) {
                continue;
            }

            visited[node.to] = true;
            dfs(node.to);
             
            // 서브 트리 정점의 개수를 추가
            arrOfSubNode[nodeIdx] += arrOfSubNode[node.to];
            visited[node.to] = false;
        }

        // 더이상 갈 수 없는 경우 나 자신 노드가 있기 때문에 +1를 한다.
        arrOfSubNode[nodeIdx]++;
    }

    public static void main(String[] args) throws IOException {
        inputData();

        solution();

        System.out.println(sb);
    }
}
