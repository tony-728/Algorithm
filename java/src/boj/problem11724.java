package boj;

import java.io.*;
import java.util.*;

public class problem11724 {

    /*
     * 
     * 방향 없는 그래프가 주어졌을 때 연결요소의 개수를 구하는 프로그램
     * 
     * 
     * 정점의 개수와 간선의 개수가 주어진다.
     * 
     * union find
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int numOfNode, numOfEdge;

    static int answer;

    static int[] nodeList;

    static int find(int node) {

        if (nodeList[node] == node) {
            return node;
        }

        return nodeList[node] = find(nodeList[node]);
    }

    static void union(int leftNode, int rightNode) {

        int leftParent = find(leftNode);
        int rightParent = find(rightNode);

        if (leftParent == rightParent) {
            return;
        }

        if(leftParent < rightParent){
            nodeList[rightParent] = leftParent;
        } else{
            nodeList[leftParent] = rightParent;
        }

    }

    public static void main(String[] args) throws IOException {
        answer = 0;

        st = new StringTokenizer(br.readLine().trim());

        numOfNode = Integer.parseInt(st.nextToken());
        numOfEdge = Integer.parseInt(st.nextToken());

        nodeList = new int[numOfNode + 1];

        for (int idx = 0; idx < numOfNode + 1; idx++) {
            nodeList[idx] = idx;
        }

        for (int idx = 0; idx < numOfEdge; idx++) {
            st = new StringTokenizer(br.readLine().trim());

            int leftNode = Integer.parseInt(st.nextToken());
            int rightNode = Integer.parseInt(st.nextToken());

            union(leftNode, rightNode);
        }

        for (int idx = 1; idx < numOfNode + 1; idx++) {
            find(idx);
        }

        boolean[] visited = new boolean[numOfNode + 1];
        for (int idx = 1; idx < numOfNode + 1; idx++) {
            if (visited[nodeList[idx]] == false) {
                answer++;
                visited[nodeList[idx]] = true;
            }
        }

        System.out.println(answer);
    }
}
