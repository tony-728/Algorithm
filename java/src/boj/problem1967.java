package boj;

import java.io.*;
import java.util.*;

public class problem1967 {
    /*
     * 트리를 가장 길게 늘일 때 트리의 모든 노드들은 이 두 노드를 지름의 끝점으로 하는 원 안에 들어간다.
     * - 이런 두 노드 사이의 경로의 길이를 트리의 지름이라고 한다.
     * - 트리에 존재한느 모든 경로들 중에 가장 긴 것
     * 
     * 루트가 있는 트리를 가중치가 있는 간선으로 입력이 주어질 때 
     * 트리의 지름을 구해서 출력하는 프로그램
     * 
     * 마지막 리프노드끼리의 거리 중 최대를 구한다.
     * - 다익스트라
     * - 리프노드 하나를 선택해서 다익스트라를 동작 -> dfs가 더 빠름름
     * 
     * 문제 예시
     * 7-8, 7-9, 7-10, 7-11, 7-12
     * 8-9, 8-10, 8-11, 8-12
     * 9-10, 9-11, 9-12
     * 10-11, 10-12
     * 11-12
     * 
     * 
     * 문제가 되었던 것
     * - 리프노드가 항상 가장 큰 숫자의 노드일 보장이 없다.
     * - 리프노드가 무엇인지 찾아야한다. -> dfs
     * 
     * 루트에서 시작한 dfs로 리프노드를 찾고
     * 리프노드에서 dfs로 가장 먼 거리를 찾는다.
     * 
     */

    static class Node {
        int to;
        int weight;

        Node next;

        public Node(int to, int weight, Node next) {
            this.to = to;
            this.weight = weight;
            this.next = next;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int numOfNode;
    static Node[] graph;
    static boolean[] visited;
    static int leafIdx;

    static int answer;

    static final int INF = 10_000 * 100 + 1;

    static void inputData() throws IOException {

        answer = 0;
        leafIdx = 0;

        numOfNode = Integer.parseInt(br.readLine().trim());

        graph = new Node[numOfNode + 1];

        int[] tree = new int[numOfNode + 1];

        for (int idx = 0; idx < numOfNode - 1; idx++) {
            st = new StringTokenizer(br.readLine().trim());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            tree[from] = to;

            graph[from] = new Node(to, weight, graph[from]);
            graph[to] = new Node(from, weight, graph[to]);
        }
    }

    static void dfs(int idx, int count) {

        if (answer < count) {
            answer = count;
            leafIdx = idx;
        }

        for (Node node = graph[idx]; node != null; node = node.next) {
            if (!visited[node.to]) {
                visited[node.to] = true;
                dfs(node.to, count + node.weight);
            }
        }

    }

    static void dijkstra() {

        boolean[] visited = new boolean[numOfNode + 1];
        int[] minDistance = new int[numOfNode + 1];

        Arrays.fill(minDistance, INF);
        minDistance[leafIdx] = 0;

        int min = 0;
        int stopOver = 0;

        // 모든 노드에 대해서 적용
        for (int idx = 0; idx < numOfNode; idx++) {
            min = INF;
            stopOver = -1;

            // 노드를 하나씩 확인한다.
            for (int nodeIdx = 1; nodeIdx < numOfNode + 1; nodeIdx++) {
                if (!visited[nodeIdx] && min > minDistance[nodeIdx]) {
                    stopOver = nodeIdx;
                    min = minDistance[nodeIdx];
                }
            }

            if (stopOver == -1) {
                break;
            }

            visited[stopOver] = true;

            // 노드를 확인하면서 최단 경로 갱신
            for (Node node = graph[stopOver]; node != null; node = node.next) {
                if (minDistance[node.to] > min + node.weight) {
                    minDistance[node.to] = min + node.weight;
                }
            }
        }

        for (int idx = 1; idx < numOfNode + 1; idx++) {
            answer = Math.max(answer, minDistance[idx]);
        }
    }

    public static void main(String[] args) throws IOException {
        inputData();

        // 리프노드 찾기
        visited = new boolean[numOfNode + 1];
        visited[1] = true;
        dfs(1, 0);

        // 리프노드를 기준으로 가장 먼 곳 dfs로 찾아가기
        visited = new boolean[numOfNode + 1];
        visited[leafIdx] = true;
        dfs(leafIdx, 0);

        // dijkstra();

        System.out.println(numOfNode == 1 ? 0 : answer);
    }
}
