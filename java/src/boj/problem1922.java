package boj;

import java.io.*;
import java.util.*;

public class problem1922 {
    /*
     * mst
     * 
     * 크루스칼 또는 프림으로 mst를 구성하면 된다.
     * 
     * - 크루스칼: union-find, 간선기준
     * - 프림: pQ, 노드 기준
     */

    static class Edge implements Comparable<Edge> {
        int from;
        int to;
        int cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int numOfNode;
    static int numOfEdge;
    static Edge[] edgeList;

    static int[] parentsList;

    static int answer;

    static void inputData() throws IOException {

        answer = 0;

        numOfNode = Integer.parseInt(br.readLine().trim());
        numOfEdge = Integer.parseInt(br.readLine().trim());

        edgeList = new Edge[numOfEdge];
        parentsList = new int[numOfNode + 1];

        for (int idx = 0; idx < numOfEdge; idx++) {
            st = new StringTokenizer(br.readLine().trim());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            edgeList[idx] = new Edge(from, to, cost);
        }

        for (int idx = 1; idx <= numOfNode; idx++) {
            parentsList[idx] = idx;
        }

        Arrays.sort(edgeList);
    }

    static int find(int idx) {

        if (idx == parentsList[idx]) {
            return idx;
        }

        return parentsList[idx] = find(parentsList[idx]);
    }

    static boolean union(int left, int right) {
        int leftParent = find(left);
        int rightParent = find(right);

        // 부모가 같으면 합칠 수 없다.
        if (leftParent == rightParent) {
            return false;
        }

        if (leftParent <= rightParent) {
            parentsList[rightParent] = leftParent;
        } else {
            parentsList[leftParent] = rightParent;
        }

        return true;
    }

    static void makeTree() {

        int count = 0;

        for (int idx = 0; idx < numOfEdge; idx++) {
            Edge edge = edgeList[idx];

            if (union(edge.from, edge.to)) {
                answer += edge.cost;

                count++;
            }

            if (count == numOfNode - 1) {
                break;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        inputData();

        makeTree();

        System.out.println(answer);
    }
}
