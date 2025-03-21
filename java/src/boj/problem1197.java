package boj;

import java.io.*;
import java.util.*;

public class problem1197 {
    /*
     * MST
     * 
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

    static int numOfVertex;
    static int numOfEdge;

    static Edge[] arrOfEdge;

    static int[] arrOfParent;

    static int answer;

    static void inputData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;


        st = new StringTokenizer(br.readLine().trim());

        numOfVertex = Integer.parseInt(st.nextToken());
        numOfEdge = Integer.parseInt(st.nextToken());

        arrOfEdge = new Edge[numOfEdge];

        arrOfParent = new int[numOfVertex + 1];
        for (int idx = 0; idx < numOfVertex + 1; idx++) {
            arrOfParent[idx] = idx;
        }

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

        if (leftParent <= rightParent) {
            arrOfParent[rightParent] = leftParent;
        } else {
            arrOfParent[leftParent] = rightParent;
        }

        return true;
    }

    static void solution() {


        for (int idx = 0; idx < numOfEdge; idx++) {

            Edge edge = arrOfEdge[idx];

            if (union(edge.from, edge.to)) {
                answer += edge.weight;
            }
        }

    }

    public static void main(String[] args) throws IOException {
        inputData();

        solution();

        System.out.println(answer);

    }
}
