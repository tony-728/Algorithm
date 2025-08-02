package boj;

import java.io.*;

public class problem10775 {
    /*
     * G개의 게이트, P개의 비행기가 있다.
     * 
     * 비행기는 1~gi까지 게이트에 도킹할 수 있다.
     * 
     * 비행기가 최대한 많은 게이트에 도킹할 수 있는 갯수를 구하라
     * 
     * 그리디
     * 분리집합
     * 
     * 
     */

    static int numOfGate;
    static int numOfPlane;
    static int[] arrOfPlane;
    static int[] parent;
    static int answer;

    static void inputData() throws IOException {

        answer = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        numOfGate = Integer.parseInt(br.readLine().trim());
        numOfPlane = Integer.parseInt(br.readLine().trim());

        parent = new int[numOfGate + 1];

        for (int idx = 0; idx < numOfGate + 1; idx++) {
            parent[idx] = idx;
        }

        for (int idx = 0; idx < numOfPlane; idx++) {
            int gate = Integer.parseInt(br.readLine().trim());

            int dockingGate = find(gate);

            if (dockingGate == 0) {
                break;
            }

            // dockingGate와 dockingGate-1을 union하는 이유
            // 3번 게이트에 최초 도킹한다고 할 때 
            // 3번 게이트는 그 아래 게이트와 합쳐지게 되어 관리한다.
            union(dockingGate, dockingGate - 1);
            answer++;
        }
    }

    static int find(int gate) {

        if (parent[gate] == gate) {
            return gate;
        }

        return parent[gate] = find(parent[gate]);
    }

    static void union(int gate1, int gate2) {
        int left = find(gate1);
        int right = find(gate2);

        parent[left] = right;
    }


    public static void main(String[] args) throws IOException {
        inputData();

        System.out.println(answer);
    }
}
