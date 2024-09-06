package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem20040 {
    /*
     * 두 명의 플레이어가 있다.
     * 선 플레이어는 홀수번, 후 플레이어는 짝수번
     * 
     * 게임 시작 시 0부터 n-1까지 고유한 번호가 부여된 평면 상의 점 n개가 주어진다.
     * 
     * 어느 세점도 일직선 위에 놓이지 않는다.
     * 
     * 매 차례마다 플레이어는 두 점을 선택해서 이를 연결하는 선분을 긋는다.
     * - 이전에 그린 선분을 다시 그을 수는 없다.
     * - 이미 그린 다른 선분과 교차하는 것은 가능
     * 
     * 게임을 진행하다가 사이클을 완성하는 순간 종료
     * 
     * 사이클 c
     * - 그린 선분들의 부분집합
     * - c에 속한 임의의 선분의 한 끝점에서 출발하여 모든 선분을 한 번씩만 지나서 출발점으로 되돌아 올 수 있다.
     * 
     * 문제
     * 게임 상황이 주어지면 사이클이 몇번째 차례에 사이클이 완성되었는지 구하는 문제
     * 모든 차례가 끝나도 사이클이 완성되지 않았다면 0
     * 
     * 점의 개수, 진행된 차례 수
     * 선택한 두 점의 번호가 주어짐
     * 
     * 풀이
     * 주어진 두 점의 부모가 같으면 사이클이 완성됨
     * union-find
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int numOfPoint;
    static int maxTurn;

    static int currentTurn;

    static int[] parentsList;

    static int find(int node) {

        if (parentsList[node] == node) {
            return node;
        }

        return parentsList[node] = find(parentsList[node]);
    }

    static void union(int leftNode, int rightNode) {

        int leftParentNode = find(leftNode);
        int rightParentNode = find(rightNode);

        if (leftParentNode == rightParentNode) {
            return;
        }

        if (leftParentNode < rightParentNode) {
            parentsList[rightParentNode] = leftParentNode;
        } else {
            parentsList[leftParentNode] = rightParentNode;
        }

        return;
    }


    static void inputTestCase() throws IOException {

        currentTurn = 0;

        st = new StringTokenizer(br.readLine().trim());

        numOfPoint = Integer.parseInt(st.nextToken());
        maxTurn = Integer.parseInt(st.nextToken());

        parentsList = new int[numOfPoint];

        for (int idx = 0; idx < numOfPoint; idx++) {
            parentsList[idx] = idx;
        }


        for (int idx = 0; idx < maxTurn; idx++) {
            // 턴은 1부터 시작
            currentTurn++;

            st = new StringTokenizer(br.readLine().trim());

            int leftNode = Integer.parseInt(st.nextToken());
            int rightNode = Integer.parseInt(st.nextToken());

            // 부모 노드 찾기
            int leftParentNode = find(leftNode);
            int rightParentNode = find(rightNode);

            // 부모 노드가 같으면 종료 == 사이클 완성
            if (leftParentNode == rightParentNode) {
                return;
            }

            union(leftNode, rightNode);
        }
        currentTurn++;
    }

    public static void main(String[] args) throws IOException {
        inputTestCase();

        // 최대 턴을 넘으면 사이클을 완성하지 못함을 의미
        if (currentTurn > maxTurn) {
            currentTurn = 0;
        }
        System.out.println(currentTurn);
    }

}
