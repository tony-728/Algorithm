package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class problem17471 {
    /*
     * 
     * 1부터 N까지 구역이 있다.
     * 
     * 각 구역에 인구수가 주어진다.
     * 
     * 각 구역과 인접한 구역이 주어진다.
     * 
     * 선거 구역을 2개로 나눌때
     * - 하나의 선거구는 하나의 구역을 포함해야 한다.
     * - 선거구에 구역은 모두 연결되어 있어야 한다.
     * 
     * 두 선거구역의 인구수 차이의 최소값을 구하라
     * 
     * 
     * 1. 1 ~ 구역/2 개로 만들 수 있는 조합을 구한다.
     * - 선택o / 선택x 이므로 구역 수의 절반까지의 조합을 만들어도 확인이 가능하다
     * 2. 만든 조합의 구역으로 선거 구역을 만들 수 있는지(연결가능한지) 확인한다.
     * - 선거 구역이 연결확인을 bfs를 사용하여 확인한다.
     * 3. 선거구역을 만들 수 있으면 두 선거 구역의 차이를 구한다.
     * 
     * 차이가 가장 적을 떄의 값을 출력한다. 선거구역을 나눌 수 없을 때는 -1
     * 
     */

    static class Area {
        int to;
        Area next;

        public Area(int to, Area next) {
            this.to = to;
            this.next = next;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int numOfArea;
    static int totalPopulation;
    static int[] populationByAreaList;
    static Area[] adjAreaList;
    static int answer = Integer.MAX_VALUE;

    static int selectCount;
    static boolean[] selectList;

    // 선택한 구역이 서로 연결되어 있는지 확인하기
    public static boolean checkConnection(boolean flag) {
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[numOfArea + 1];

        // 탐색 시작 인덱스를 찾아서 넣어줌
        for (int idx = 1; idx < numOfArea + 1; idx++) {
            if (selectList[idx] == flag) {
                visited[idx] = true;
                queue.offer(idx);
                break;
            }
        }

        int searchCount = 0; // 탐색에 성공한 노드의 수

        while (!queue.isEmpty()) {
            int curNode = queue.poll();

            searchCount++;

            for (Area area = adjAreaList[curNode]; area != null; area = area.next) {
                if (visited[area.to])
                    continue;

                if (selectList[area.to] == flag) {
                    visited[area.to] = true;
                    queue.offer(area.to);
                }
            }
        }

        if (flag) {
            if (searchCount != selectCount)
                return false; // 고른 선거구 전체를 탐색할 수 없다면
        } else {
            if (searchCount != numOfArea - selectCount)
                return false; // 고른 선거구 전체를 탐색할 수 없다면
        }

        return true;
    }

    public static void makeVotingArea(int selectIdx, int elementIdx) {

        if (selectIdx == selectCount) {

            // 선택한 구역이 서로 연결되는지 확인
            if (!checkConnection(true)) {
                return;
            }

            // 선택되지 않은 구역끼리 연결되는지 확인
            if (!checkConnection(false)) {
                return;
            }

            // 두 구역이 각각 연결되면 두 구역의 인구수 합의 차이를 구하기
            int selectAreaPopulation = 0;

            // 연결된 구역의 인구수 합 구하기
            for (int selectAreaIdx = 1; selectAreaIdx < numOfArea + 1; selectAreaIdx++) {
                if (selectList[selectAreaIdx]) {
                    selectAreaPopulation += populationByAreaList[selectAreaIdx];
                }
            }

            // 선택되지 않은 구역은 전체 인구 수에서 선택된 인구를 빼면 알 수 있다.
            answer = Math.min(answer, Math.abs(selectAreaPopulation - (totalPopulation - selectAreaPopulation)));
            return;
        }

        if (elementIdx == numOfArea + 1) {
            return;
        }

        selectList[elementIdx] = true;
        makeVotingArea(selectIdx + 1, elementIdx + 1);

        selectList[elementIdx] = false;
        makeVotingArea(selectIdx, elementIdx + 1);

    }

    public static void main(String[] args) throws IOException {
        numOfArea = Integer.parseInt(br.readLine().trim());

        populationByAreaList = new int[numOfArea + 1];

        st = new StringTokenizer(br.readLine().trim());

        // 구역별 인구수 입력받기
        for (int idx = 1; idx < numOfArea + 1; idx++) {
            int population = Integer.parseInt(st.nextToken());
            populationByAreaList[idx] = population;
            totalPopulation += population;

        }

        // 구역별 인접한 구역 입력받기
        adjAreaList = new Area[numOfArea + 1];

        for (int idx = 1; idx < numOfArea + 1; idx++) {

            st = new StringTokenizer(br.readLine().trim());

            int numOfAdj = Integer.parseInt(st.nextToken());

            for (int adjIdx = 0; adjIdx < numOfAdj; adjIdx++) {
                adjAreaList[idx] = new Area(Integer.parseInt(st.nextToken()), adjAreaList[idx]);
            }
        }

        // 전체 구역수의 절반에 조합만 구해도 모든 경우를 알 수 있다.
        // 선택된 구역 <-> 선택되지 않은 구역이기 때문
        for (int idx = 1; idx <= numOfArea / 2; idx++) {
            selectCount = idx;
            selectList = new boolean[numOfArea + 1];

            makeVotingArea(0, 1);
        }

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }
}