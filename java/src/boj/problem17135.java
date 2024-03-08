package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class problem17135 {
    /*
     * 
     * 
     * NxM 격자판
     * 3<= N, M <= 15
     * 각 칸에 적이 있다. 적은 최대 1명
     * 
     * N번행 아래에(N+1) 성이 있다.
     * 
     * 성을 지키는 궁수는 3명, 궁수는 성에 배치할 수 있다.
     * 각 칸에 궁수는 한명
     * 
     * 각 턴마다 궁수는 적을 하나 공격할 수 있다. 모든 궁수는 동시에 공격
     * 
     * 궁수는 거리가 D이하인 적 중 가까운 적을 공격, 여려 명이면 가장 왼쪽을 공격
     * 
     * 턴이 끝나면 적은 한 칸 아래로 이동
     * 
     * 
     * 격자판은 N x M으로 생성한다.
     * 0 ~ N까지 입력받는다.
     * 
     * 궁수에 위치는 0-M까지에서 3개를 뽑는 조합을 만들고
     * 
     * 각 조합에서 최대로 많은 적을 무찌른 경우가 답이 된다.
     * 
     * 각 턴은 행을 역순으로 반복문을 돌면서 확인한다.
     * - 턴이 시작할 때 가장 마지막 행을 0으로 초기화하고 궁수의 위치를 설정
     * - 각 궁수의 위치에서 적의 위치 거리를 계산하고 가까운 순서로 제거
     * - 궁수의 위치에서 적을 확인하는 방법은 bfs로 탐색하면서 사정거리만큼 진행하고 거리 중 적을 만나면 탈출한다.
     * - 확인 순서는 좌 , 상, 우
     * 
     * 
     */

    public static final int BLANK = 0;
    public static final int ENEMY = 1;
    public static final int ARCHER = 2;
    public static final int ARCHER_COUNT = 3;

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st;

    public static int ROW;
    public static int COLUMN;
    public static int range;

    public static int[][] map;
    public static int[] archerPostion = new int[ARCHER_COUNT];
    public static Deque<int[]> enemyList;
    public static List<int[]> checkedEnemyList;

    // 좌, 상, 우
    public static int[] deltaX = { 0, -1, 0 };
    public static int[] deltaY = { -1, 0, 1 };

    public static boolean[][] visited;

    public static int answer;

    public static void combination(int selectIdx, int elementIdx) {

        // 1. 기저 조건
        if (selectIdx == ARCHER_COUNT) {
            // 궁수 공격
            simulation();
            return;
        }

        if (elementIdx == COLUMN) {
            return;
        }

        // 현재 선택함
        archerPostion[selectIdx] = elementIdx;
        combination(selectIdx + 1, elementIdx + 1);

        // 현재 선택 안함
        // archerPostion[selectIdx] = 0;
        combination(selectIdx, elementIdx + 1);
    }

    // 궁수가 적을 공격하기
    public static void simulation() {

        int deleteEnemyCount = 0;
        checkedEnemyList = new ArrayList<>();

        // 턴이 진행됨
        for (int rowIdx = ROW - 1; rowIdx >= 0; rowIdx--) {
            enemyList = new ArrayDeque<>(); // 궁수가 쏠 수 있는 적 리스트
            for (int archerIdx = 0; archerIdx < ARCHER_COUNT; archerIdx++) {
                // 각 궁수가 쏠수 있는 적을 확인하기 위해 BFS
                searchEnemy(rowIdx + 1, archerPostion[archerIdx]); // 궁수의 위치를 넘겨줌
            }

            // 모든 궁수가 활을 쐈으면 다음 턴으로
            // 다음 턴으로 가기 전에 공격한 적을 제거
            while (!enemyList.isEmpty()) {
                int[] enemyLocation = enemyList.poll();

                // 같은 적을 두번 처리하지 않도록 방문 확인
                if (!visited[enemyLocation[0]][enemyLocation[1]]) {
                    // 처음 죽인 적이라면 카운트 증가
                    deleteEnemyCount++;

                    // 죽인 적을 빈칸으로 바꿈
                    map[enemyLocation[0]][enemyLocation[1]] = BLANK;

                    // 죽인적을 돌려놓기 위해 리스트에 넣음
                    checkedEnemyList.add(enemyLocation);

                    // 처음 죽인 적이라면 방문처리
                    visited[enemyLocation[0]][enemyLocation[1]] = true;
                }

            }
        }

        // 턴이 완료되면 제거한 적을 다시 돌려놔야함
        for (int idx = 0; idx < checkedEnemyList.size(); idx++) {
            map[checkedEnemyList.get(idx)[0]][checkedEnemyList.get(idx)[1]] = ENEMY;
        }

        answer = Math.max(answer, deleteEnemyCount);
    }

    public static int calDistance(int originRowIdx, int originColIdx, int[] targetLocation) {

        return Math.abs(originRowIdx - targetLocation[0]) + Math.abs(originColIdx - targetLocation[1]);
    }

    /**
     * 궁수의 위치를 받아서 bfs를 진행한다.
     * 사정거리 내에 적이 있는지 확인하고 적을 만나면 저장하고 탈출한다.
     * 
     * @param rowIdx 궁수의 행 위치
     * @param colIdx 궁수의 열 위치
     */
    public static void searchEnemy(int rowIdx, int colIdx) {
        // 좌, 상, 우 순서로 BFS를 진행한다.
        // BFS는 궁수 사정거리만큼 진행한다.
        Deque<int[]> queue = new ArrayDeque<>();

        // 방문처리할 배열
        visited = new boolean[ROW][COLUMN];

        // 궁수 위치 큐에 넣기
        queue.offer(new int[] { rowIdx, colIdx });

        outLoop: while (!queue.isEmpty()) {

            int[] location = queue.poll();

            for (int dir = 0; dir < deltaX.length; dir++) {

                int[] newLocation = new int[] { location[0] + deltaX[dir], location[1] + deltaY[dir] };

                // 맵영역인지 확인
                // 턴마다 영역이 줄어드므로 (row가 줄어듬) 확인해야함
                if (0 > newLocation[0] || newLocation[0] >= rowIdx || 0 > newLocation[1] || newLocation[1] >= COLUMN) {
                    continue;
                }

                // 방문확인
                if (visited[newLocation[0]][newLocation[1]]) {
                    continue;
                }

                // 적이 있으면 추가 후 종료
                if (map[newLocation[0]][newLocation[1]] == ENEMY) {
                    enemyList.add(newLocation);
                    break outLoop;

                } else { // 적이 없고 사정거리가 되면 다음을 확인하기 위해 큐에 넣는다.
                    if (calDistance(rowIdx, colIdx, newLocation) < range) {
                        queue.offer(newLocation);
                        visited[newLocation[0]][newLocation[1]] = true;
                    }
                }
            }
        }

        return;
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine().trim());

        ROW = Integer.parseInt(st.nextToken());
        COLUMN = Integer.parseInt(st.nextToken());
        range = Integer.parseInt(st.nextToken());

        map = new int[ROW][COLUMN];

        // map 입력받기
        for (int rowIdx = 0; rowIdx < ROW; rowIdx++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int colIdx = 0; colIdx < COLUMN; colIdx++) {
                map[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
            }
        }

        // 아처 포지션 조합 만들기 + 만든 조합에서 확인
        combination(0, 0);

        System.out.println(answer);
    }
}
