package boj;

import java.io.*;
import java.util.*;

public class problem1774 {
    /*
     * 황선자와 연결된 우주신이 있다.
     * 새로운 우주신이 황선자를 이용하려고 할 때 바로 황선자와 연결할 필요없이 연결된 우주신을 거처 이용할 수 있다.
     * 
     * 새로 만들어야 할 통신 길이의 합이 최소가 되도록 통로를 만들자
     * 
     * 우주신의 개수 N(최대 1000)
     * 이미 연결된 통로 M(최대 1000)
     * 
     * 통로의 길이의 합을 소수점 둘째 자리까지 반올림
     * 
     * MST
     * 전처리
     * - 미리 좌표간의 거리를 계산한다
     * - 미리 연결된 좌표를 처리한다.
     * - 남은 좌표를 연결된 좌표에 MST로 연결
     */
    static class God {
        int rowIdx;
        int colIdx;

        public God(int rowIdx, int colIdx) {
            this.rowIdx = rowIdx;
            this.colIdx = colIdx;
        }
    }

    static class Edge implements Comparable<Edge> {
        int from;
        int to;
        double weight;

        public Edge(int from, int to, double weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        public int compareTo(Edge o) {
            return Double.compare(this.weight, o.weight);
        }
    }

    static int numOfGod;
    static int numOfRoot;
    static int totalEdge;
    static Edge[] arrOfEdge;
    static God[] arrOfGod;

    static double answer;

    static int[] arrOfParent;

    static void inputData() throws IOException {

        answer = 0;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine().trim());

        numOfGod = Integer.parseInt(st.nextToken());
        numOfRoot = Integer.parseInt(st.nextToken());

        arrOfParent = new int[numOfGod + 1];

        for (int idx = 1; idx < numOfGod + 1; idx++) {
            arrOfParent[idx] = idx;
        }

        arrOfGod = new God[numOfGod + 1];
        for (int idx = 1; idx < numOfGod + 1; idx++) {
            st = new StringTokenizer(br.readLine().trim());

            int rowIdx = Integer.parseInt(st.nextToken());
            int colIdx = Integer.parseInt(st.nextToken());

            arrOfGod[idx] = new God(rowIdx, colIdx);
        }

        totalEdge = numOfGod * (numOfGod - 1) / 2;
        arrOfEdge = new Edge[totalEdge];
        int edgeIdx = 0;
        // 전처리
        for (int godIdx = 1; godIdx < numOfGod; godIdx++) {
            for (int idx = godIdx + 1; idx < numOfGod + 1; idx++) {
                arrOfEdge[edgeIdx++] = new Edge(godIdx, idx, calDistance(godIdx, idx));
            }
        }
        Arrays.sort(arrOfEdge);

        for (int idx = 0; idx < numOfRoot; idx++) {
            st = new StringTokenizer(br.readLine().trim());

            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());

            union(left, right);
        }
    }

    static double calDistance(int left, int right) {
        God leftGod = arrOfGod[left];
        God rightGod = arrOfGod[right];

        return Math.pow(leftGod.rowIdx - rightGod.rowIdx, 2)
                + Math.pow(leftGod.colIdx - rightGod.colIdx, 2);
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

        for (int idx = 0; idx < totalEdge; idx++) {
            if (union(arrOfEdge[idx].from, arrOfEdge[idx].to)) {
                // System.out.println("left " + arrOfEdge[idx].from + " right " + arrOfEdge[idx].to
                //         + " weight " + arrOfEdge[idx].weight);
                answer += Math.sqrt(arrOfEdge[idx].weight);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        inputData();

        solution();

        System.out.println(String.format("%.2f", answer));
    }
}
