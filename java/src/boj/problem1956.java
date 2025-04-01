package boj;

import java.io.*;
import java.util.*;

public class problem1956 {
    /*
     * V개의 마을과 E개의 도로로 구성되어 있는 도시가 있다.
     * 
     * 도로는 일방통행이다
     * 마을에는 1번부터 V번까지 번호가 있다.
     * 
     * 도로를 따라 운동을 하기 위한 경로를 찾으려고 한다.
     * 사이클을 찾기 원한다.
     * 사이클을 이루는 도로의 길이의 합이 최소가 되도록 찾으려고 한다.
     * 
     * 최대 정점 400개, 간선의 개수 v*(v-1), 가중치는 최대 10000
     * 모든 간선을 사용해서 사이클이 되는 경우 400*399*10_000
     *
     * 
     * dfs
     * - 모든 점에 대해서 다시 나로 돌아오는 경로가 있는지 찾는다.
     * - 시간 초과 발생
     * 
     * 다익스트라
     * - 모든 점에 대해서 다익스트라를 적용하고 나로 다시 돌아오는 경로 중 최단 거리를 찾는다.
     * 
     */

    static class Vertex {
        int to;
        int weight;
        Vertex next;

        public Vertex(int to, int weight, Vertex next) {
            this.to = to;
            this.weight = weight;
            this.next = next;
        }
    }

    static int numOfVertex;
    static int numOfEdge;

    static int[] arrOfCost;
    static boolean[] visited;
    static Vertex[] map;

    static int[][] mapForFloyd;

    static int answer;

    static final int INF = 400 * 399 * 10_000 + 1;

    static void inputData() throws IOException {

        answer = INF;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine().trim());
        numOfVertex = Integer.parseInt(st.nextToken());
        numOfEdge = Integer.parseInt(st.nextToken());

        map = new Vertex[numOfVertex + 1];
        mapForFloyd = new int[numOfVertex + 1][numOfVertex + 1];

        for (int idx = 1; idx < numOfVertex + 1; idx++) {
            Arrays.fill(mapForFloyd[idx], INF);
            mapForFloyd[idx][idx] = 0;
        }

        for (int idx = 0; idx < numOfEdge; idx++) {
            st = new StringTokenizer(br.readLine().trim());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            map[from] = new Vertex(to, weight, map[from]);

            mapForFloyd[from][to] = weight;
        }
    }

    static void solution(int start) {
        // 다익스트라

        for (int idx = 0; idx < numOfVertex; idx++) {

            int minDist = INF;
            int mid = -1;

            // 방문하지 않은 정점에 대해서 출발지에서 가장 가까운 정점 선택
            for (int vertexIdx = 1; vertexIdx < numOfVertex + 1; vertexIdx++) {
                if (visited[vertexIdx]) {
                    continue;
                }

                if (minDist > arrOfCost[vertexIdx]) {
                    minDist = arrOfCost[vertexIdx];
                    mid = vertexIdx;
                }
            }

            if (mid == -1) {
                break;
            }

            visited[mid] = true;

            for (Vertex vertex = map[mid]; vertex != null; vertex = vertex.next) {
                arrOfCost[vertex.to] = Math.min(arrOfCost[vertex.to], minDist + vertex.weight);

                if (vertex.to == start) {
                    answer = Math.min(answer, arrOfCost[vertex.to]);
                }
            }
        }

    }

    static void floyd() {
        // 경유지
        for (int stepOver = 1; stepOver < numOfVertex + 1; stepOver++) {
            // 출발지
            for (int start = 1; start < numOfVertex + 1; start++) {
                //도착지
                for (int end = 1; end < numOfVertex + 1; end++) {
                    if (start == stepOver) {
                        continue;
                    }

                    mapForFloyd[start][end] = Math.min(mapForFloyd[start][end],
                            mapForFloyd[start][stepOver] + mapForFloyd[stepOver][end]);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        inputData();

        // for (int idx = 1; idx < numOfVertex + 1; idx++) {
        //     visited = new boolean[numOfVertex + 1];
        //     arrOfCost = new int[numOfVertex + 1];
        //     Arrays.fill(arrOfCost, Integer.MAX_VALUE);

        //     for (Vertex vertex = map[idx]; vertex != null; vertex = vertex.next) {
        //         arrOfCost[vertex.to] = vertex.weight;
        //     }

        //     solution(idx);
        // }

        floyd();
        for (int start = 1; start < numOfVertex + 1; start++) {
            for (int end = 1; end < numOfVertex + 1; end++) {
                if (start == end) {
                    continue;
                }

                // 자기 자신을 제외한 두 정점이
                // 서로에게 가는 경로가 있다면, 사이클이 존재한다는 뜻.
                if (mapForFloyd[start][end] != INF && mapForFloyd[end][start] != INF) {
                    answer = Math.min(answer, mapForFloyd[start][end] + mapForFloyd[end][start]);
                }
            }
        }

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }
}
