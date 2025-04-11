package boj;

import java.io.*;
import java.util.*;

public class problem1647 {
    /*
     * N개의 집과 M개의 길이 있다. 무방향 그래프이다.
     * 
     * 마을을 두 개의 분리된 마을로 분할할 계획을 가지고 있다.
     * - 마을 분할할 때는 마을 안에 집들이 서로 연결되도록 분할해야 한다.
     * - 분리된 두 마을은 연결될 필요가 없다.
     * - 각 분리된 마을 안에서도 임의의 두 집 사이에 경로가 항상 존재하면서 길을 없앨 수 있다.
     * - 유지비를 최소
     * - 마을은 집이 하나 이상 있어야 한다.
     * 
     * 
     * MST
     * - 모든 마을을 잇는 mst를 구성한다.
     * - mst에서 가장 큰 비용 길를 제거한다.
     */

    static class Edge implements Comparable<Edge> {
        int from;
        int to;
        int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;

        }

        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }

    static int numOfNode;
    static int numOfEdge;
    static Edge[] arrOfEdge;

    static int[] arrOfParent;

    static int answer;

    static void inputData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine().trim());

        numOfNode = Integer.parseInt(st.nextToken());
        numOfEdge = Integer.parseInt(st.nextToken());

        // 부모 초기화
        arrOfParent = new int[numOfNode + 1];
        for (int idx = 1; idx < numOfNode + 1; idx++) {
            arrOfParent[idx] = idx;
        }

        arrOfEdge = new Edge[numOfEdge];

        for (int idx = 0; idx < numOfEdge; idx++) {
            st = new StringTokenizer(br.readLine().trim());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            arrOfEdge[idx] = new Edge(from, to, weight);
        }

        Arrays.sort(arrOfEdge);

    }

    static int find(int node) {
        if (arrOfParent[node] == node) {
            return node;
        }

        return arrOfParent[node] = find(arrOfParent[node]);
    }

    static boolean union(int left, int right) {
        int leftParent = find(left);
        int rightParent = find(right);

        if (leftParent == rightParent) {
            return false;
        }

        if (leftParent < rightParent) {
            arrOfParent[rightParent] = leftParent;
        } else {
            arrOfParent[leftParent] = rightParent;
        }

        return true;
    }

    static void solution() {

        int count = 0;

        for (int idx = 0; idx < numOfEdge; idx++) {
            Edge edge = arrOfEdge[idx];

            if (union(edge.from, edge.to)) {
                answer += edge.weight;
                count++;
            }

            if (count == numOfNode - 1) {
                answer = answer - edge.weight;
                break;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        inputData();

        solution();

        System.out.println(answer);
    }
}
