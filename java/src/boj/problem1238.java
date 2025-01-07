package boj;

import java.util.*;
import java.io.*;

public class problem1238 {

    /*
     * N개의 숫자로 구분된 각각의 마을에 한 명의 학생이 있다.
     * N명의 학생이 x 마을에 모여서 파티를 한다.
     * 
     * 마을 사이에는 M개의 단방향 도로가 있다.
     * i번째 길을 지나는데 Ti 시간이 걸린다.
     * 
     * 각각의 학생은 파티참석을 위해 걸어가서 다시 본인 마을로 돌아와야 한다.
     * 
     * N 명의 학생들 중 오고 가는데 가장 많은 시간을 구하라
     * 
     * 단방향 그래프이고 출발지에서 목적지까지 간 시간과 목적지에서 다시 출발지로 간 시간의 합을 구해야한다.
     * 
     */

    static class Node {
        int to;
        int cost;
        Node next;

        public Node(int to, int cost, Node next) {
            this.to = to;
            this.cost = cost;
            this.next = next;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int numOfStudent;
    static int numOfEdge;
    static int target;

    static Node[] map;

    static int answer;

    static int[][] minDistance;

    static final int INF = Integer.MAX_VALUE;

    static void inputData() throws IOException {

        answer = 0;

        st = new StringTokenizer(br.readLine().trim());

        numOfStudent = Integer.parseInt(st.nextToken());
        numOfEdge = Integer.parseInt(st.nextToken());
        target = Integer.parseInt(st.nextToken());

        map = new Node[numOfStudent + 1];

        minDistance = new int[numOfStudent + 1][numOfStudent + 1];

        for (int idx = 0; idx < numOfEdge; idx++) {
            st = new StringTokenizer(br.readLine().trim());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            map[from] = new Node(to, cost, map[from]);

        }
    }

    static void dijkstra(int[] minDistance, int target) {

        // 기준 점을 기준으로 다익스트라

        boolean[] visited = new boolean[numOfStudent + 1];

        // 거리 배열을 최대값으로 설정
        Arrays.fill(minDistance, INF);
        minDistance[target] = 0;

        // 모든 노드에 대해서 적용
        for (int idx = 0; idx < numOfStudent; idx++) {
            int min = INF;
            int stopOver = -1;

            // 가보지 않은 정점을 찾는다.
            for (int nodeIdx = 1; nodeIdx < numOfStudent + 1; nodeIdx++) {
                if (!visited[nodeIdx] && min > minDistance[nodeIdx]) {
                    min = minDistance[nodeIdx];
                    stopOver = nodeIdx;
                }
            }

            if (stopOver == -1) {
                break;
            }

            visited[stopOver] = true;

            for (Node node = map[stopOver]; node != null; node = node.next) {
                // 최단 거리로 갱신
                if (minDistance[node.to] > min + node.cost) {
                    minDistance[node.to] = min + node.cost;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {

        inputData();

        for (int idx = 1; idx < numOfStudent + 1; idx++) {
            dijkstra(minDistance[idx], idx);
        }

        // 출발지에서 목적지까지 비용 + 목적지에서 출발지까지 비용의 최대
        for (int idx = 1; idx < numOfStudent + 1; idx++) {
            if(idx == target){
                continue;
            }
            answer = Math.max(answer, minDistance[target][idx] + minDistance[idx][target]);
        }

        System.out.println(answer);
    }
}
