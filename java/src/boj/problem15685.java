package boj;

import java.io.*;
import java.util.*;

public class problem15685 {
    /*
     * 드래곤 커브는 3가지 속성으로 이루어져 있다.
     * 1. 시작점
     * 2. 시작 방향
     * 3. 세대
     * 
     * x축은 우방향
     * y축은 하방향이다.
     * 
     * 드래곤 커브의 개수가 주어진다.
     * 드래곤 커브의 정보가 주어진다.
     * - x y d g
     * - x, y : 시작점
     * - d: 시작 방향
     * - g: 세대
     * 
     * 0, 1, 2, 3 = 우 상 좌 하
     * 
     * 격자의 크기는 100x100
     * 
     * 1x1인 정사각형의 네 꼭짓점이 모두 드래곤 커브의 일부인 것의 개수를 구하라
     * 
     * 드래곤커브를 이루는 방향을 리스트로 관리
     * - 세대가 변할 때는 리스트를 역순으로 순회하면서 그려나가면 된다.
     *  
     */

    static class DragonCurve {
        int rowIdx;
        int colIdx;
        int direction;
        int generation;

        public DragonCurve(int rowIdx, int colIdx, int direction, int generation) {
            this.rowIdx = rowIdx;
            this.colIdx = colIdx;
            this.direction = direction;
            this.generation = generation;
        }

        public DragonCurve(int rowIdx, int colIdx, int direction) {
            this.rowIdx = rowIdx;
            this.colIdx = colIdx;
            this.direction = direction;
        }
    }

    static final int MAX = 100;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int numOfCurve;

    static DragonCurve[] dList;
    static boolean[][] visited;

    static int answer;

    static final int[] dx = {0, -1, 0, 1};
    static final int[] dy = {1, 0, -1, 0};

    static void inputData() throws IOException {

        visited = new boolean[MAX + 1][MAX + 1];

        answer = 0;

        numOfCurve = Integer.parseInt(br.readLine().trim());

        dList = new DragonCurve[numOfCurve];

        for (int idx = 0; idx < numOfCurve; idx++) {
            st = new StringTokenizer(br.readLine().trim());

            int colIdx = Integer.parseInt(st.nextToken());
            int rowIdx = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken());
            int generation = Integer.parseInt(st.nextToken());

            dList[idx] = new DragonCurve(rowIdx, colIdx, direction, generation);
        }
    }

    static void goDragon(DragonCurve dc) {

        List<Integer> list = new ArrayList<>();

        int maxGeneration = dc.generation;
        int generation = 0;

        // 0 세대 처리
        visited[dc.rowIdx][dc.colIdx] = true;

        int rowIdx = dc.rowIdx + dx[dc.direction];
        int colIdx = dc.colIdx + dy[dc.direction];

        visited[rowIdx][colIdx] = true;

        list.add(dc.direction);

        // 1 세대부터 시작한다.
        while (generation < maxGeneration) {

            // 다음 세대 드래곤 커브는 리스트를 역순으로 순회
            for (int idx = list.size() - 1; idx > -1; idx--) {
                int currentDir = list.get(idx);

                int newDir = (currentDir + 1) % 4;

                rowIdx = rowIdx + dx[newDir];
                colIdx = colIdx + dy[newDir];

                visited[rowIdx][colIdx] = true;

                // 새로운 방향을 리스트에 추가
                list.add(newDir);
            }
            generation++;
        }
    }

    static void solution() {

        for (int idx = 0; idx < numOfCurve; idx++) {
            goDragon(dList[idx]);
        }

        // 정사각형의 갯수 확인
        for (int rowIdx = 0; rowIdx < MAX; rowIdx++) {
            for (int colIdx = 0; colIdx < MAX; colIdx++) {
                // // 4칸이 모두 방문처리 되었으면 정사각형
                if (visited[rowIdx][colIdx] && visited[rowIdx + 1][colIdx]
                        && visited[rowIdx][colIdx + 1] && visited[rowIdx + 1][colIdx + 1]) {
                    answer++;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {

        inputData();

        solution();

        System.out.println(answer);
    }
}
