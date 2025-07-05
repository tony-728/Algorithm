package boj;

import java.io.*;
import java.util.*;

public class problem11657 {
    /**
     * 
     * N개의 도시 M개의 경로가 있을 때
     * 양의 가중치, 음의 가중치가 있다.
     * 
     * 1번 도시에서 출발해서 다른 도시로 갈 때 최단거리를 구하라
     * 
     * 벨만포드
     * 
     * 노드의 개수가 500, 경로의 개수가 6000, 가중치가 -10,000 or 10,000이므로 int범위를 벗어난다.
     * 따라서 long으로 관리해야함
     */

    static class Edge {
        int from;
        int to;
        int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    static int numOfNode;
    static int numOfEdge;
    static StringBuilder sb = new StringBuilder();
    static List<Edge> edgeList = new ArrayList<Edge>();

    static final long MAX_VALUE = Long.MAX_VALUE;

    static void inputData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine().trim());

        numOfNode = Integer.parseInt(st.nextToken());
        numOfEdge = Integer.parseInt(st.nextToken());

        for (int idx = 0; idx < numOfEdge; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            edgeList.add(new Edge(from, to, weight));
        }
    }

    static void solution() {
        long[] distance = new long[numOfNode + 1];

        Arrays.fill(distance, MAX_VALUE);

        distance[1] = 0;

        // 최단거리 만들기
        for (int node = 0; node < numOfNode - 1; node++) {
            for (Edge edge : edgeList) {
                if (distance[edge.from] != MAX_VALUE
                        && distance[edge.to] > distance[edge.from] + edge.weight) {
                    distance[edge.to] = distance[edge.from] + edge.weight;
                }
            }
        }

        // 사이클 체크
        boolean isCycle = false;
        for (Edge edge : edgeList) {
            if (distance[edge.from] != MAX_VALUE
                    && distance[edge.to] > distance[edge.from] + edge.weight) {
                isCycle = true;
                break;
            }
        }

        if (isCycle) {
            sb.append(-1);
        } else {
            for (int idx = 2; idx < numOfNode + 1; idx++) {
                if (distance[idx] == MAX_VALUE) {
                    sb.append(-1).append("\n");
                } else {
                    sb.append(distance[idx]).append("\n");
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        inputData();

        solution();

        System.out.println(sb);
    }
}
