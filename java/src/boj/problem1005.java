package boj;

import java.io.*;
import java.util.*;

public class problem1005 {
    /*
     * N개의 건물이 있다.
     * 건물을 지을 때 시간이 소요된다.
     * 어떤 건물은 다른 건물이 완성되어야 완성할 수 있다.
     * 
     * 임의의 건물을 선택할 때 이 건물을 지을 때 소요되는 시간을 구하라
     * 
     * 건물에 방문할 때 진입차수를 감소시킨다.
     * 진입차수가 0일 때 다음 건물을 짓기 위한 스텝이 이루어짐
     * 
     */

    static class Building {
        int to;
        Building next;

        public Building(int to, Building next) {
            this.to = to;
            this.next = next;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int numOfBuilding;
    static int[] arrOfBuilding;
    static int[] arrOfDegree;

    static Building[] graph;

    static int target;

    static void inputData() throws IOException {
        st = new StringTokenizer(br.readLine().trim());

        numOfBuilding = Integer.parseInt(st.nextToken());
        int numOfRelation = Integer.parseInt(st.nextToken());

        arrOfBuilding = new int[numOfBuilding + 1];
        arrOfDegree = new int[numOfBuilding + 1];
        graph = new Building[numOfBuilding + 1];

        st = new StringTokenizer(br.readLine().trim());

        for (int idx = 1; idx < numOfBuilding + 1; idx++) {
            arrOfBuilding[idx] = Integer.parseInt(st.nextToken());
        }

        for (int idx = 0; idx < numOfRelation; idx++) {
            st = new StringTokenizer(br.readLine().trim());

            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());

            arrOfDegree[right]++;

            // 그래프
            graph[left] = new Building(right, graph[left]);
        }

        target = Integer.parseInt(br.readLine().trim());
    }

    static void solution() {

        Deque<Integer> q = new ArrayDeque<>();

        int[] arrOfCost = new int[numOfBuilding + 1];

        // 진입차수가 0인 노드를 q에 넣는다.
        for (int idx = 1; idx < numOfBuilding + 1; idx++) {
            if (arrOfDegree[idx] == 0) {
                q.add(idx);
                arrOfCost[idx] = arrOfBuilding[idx];
            }
        }

        while (!q.isEmpty()) {

            int current = q.poll();

            if (current == target && arrOfDegree[current] == 0) {
                sb.append(arrOfCost[target]).append("\n");
                break;
            }

            for (Building building = graph[current]; building != null; building = building.next) {
                // 진입차수 감소
                arrOfDegree[building.to]--;

                if (arrOfCost[building.to] < arrOfCost[current] + arrOfBuilding[building.to]) {
                    arrOfCost[building.to] = arrOfCost[current] + arrOfBuilding[building.to];
                }

                // 진입차수가 0이라는 것은 건물을 짓기 위한 조건이 충족되었다.
                if (arrOfDegree[building.to] == 0) {
                    q.add(building.to);
                }
            }

        }
    }

    public static void main(String[] args) throws IOException {

        int testCase = Integer.parseInt(br.readLine().trim());

        for (int tc = 0; tc < testCase; tc++) {
            inputData();

            solution();
        }

        System.out.println(sb);

    }
}
