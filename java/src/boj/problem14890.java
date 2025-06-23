package boj;

import java.io.*;
import java.util.*;

public class problem14890 {

    /*
     * NxN에 높이 정보와 경사로의 길이 x가 주어질 때 활주로를 건설할 수 있는 경우의 수를 계산하라
     * 
     * 활주로는 가로 또는 세로 방향으로 건설할 수 있는 가능성을 확인한다. 활주로는 높이가 동일한 구간에서 건설이 가능하다
     * 
     * 높이가 다른 구간의 경우 경사로를 설치하여 활주로를 건설할 수 있다. 경사로는 높이가 1 길이가 L이다. 경사로의 길이만큼 낮은 지형의 길이가 있어야 한다.
     * 
     * 경사로를 설치할 때 가로 또는 세로 전체에 활주로를 건설해야하기 때문에 높이가 다른 곳이 양쪽에 모두 존재한 경우 모두 만족시켜야한다.
     * 
     * 
     */

    static final int START = 0;
    static final int LEFT = -1;
    static final int RIGHT = 1;

    static int mapSize;
    static int needLength;
    static int[][] map; // 좌우로 확인
    static int[][] rMap; // 상하로 확인

    static boolean[][] visited;

    static int answer;


    public static void inputTestCase() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        answer = 0;

        st = new StringTokenizer(br.readLine().trim());

        mapSize = Integer.parseInt(st.nextToken());
        needLength = Integer.parseInt(st.nextToken());

        map = new int[mapSize][mapSize];
        rMap = new int[mapSize][mapSize];

        for (int rowIdx = 0; rowIdx < mapSize; rowIdx++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int colIdx = 0; colIdx < mapSize; colIdx++) {

                int element = Integer.parseInt(st.nextToken());

                map[rowIdx][colIdx] = element;
                rMap[colIdx][rowIdx] = element;
            }
        }

    }

    public static void checkLine(int[][] map, int rowIdx) {

        // 같은 행에 값이 모두 같은지 확인한다.
        // 다른 높이가 등장하는 경우
        // 높이의 차이가 1인 경우 활주로 건설이 가능한지 확인한다.
        // 높이의 차이가 1보다 큰 경우 활주로 건설이 불가능하다 -> 다른 행으로 이동

        Deque<int[]> queue = new ArrayDeque<>();

        if (rowIdx == mapSize) {
            return;
        }

        boolean flag = true;

        // 기준 상태 초기화
        int status = map[rowIdx][START];

        outLoop: for (int colIdx = 1; colIdx < mapSize; colIdx++) {
            // 기준 상태와 다르다면
            if (status != map[rowIdx][colIdx]) {

                if (visited[rowIdx][colIdx]) {
                    continue;
                }

                int diff = map[rowIdx][colIdx] - status;

                // 차이가 1보다 크면 절대 활주로 건설 불가
                // 다음 행 검사하러 간다.
                if (Math.abs(diff) > 1) {
                    flag = false;
                    break;
                }

                // 활주로 건설 가능한지 확인한다.
                // 활주로는 작은 쪽에 설치해야한다.
                // 오른쪽
                if (diff < 0) {
                    queue.offer(new int[] {rowIdx, colIdx});

                    // 오른쪽 확인
                    for (int idx = 1; idx < needLength; idx++) {

                        int newColIdx = colIdx + idx * RIGHT;

                        if (newColIdx >= mapSize) {
                            flag = false;
                            queue.clear();
                            break outLoop;
                        }

                        if (map[rowIdx][newColIdx] - status == diff
                                && !visited[rowIdx][newColIdx]) {
                            queue.offer(new int[] {rowIdx, newColIdx});
                        } else {
                            flag = false;
                            queue.clear();
                            break outLoop;
                        }

                    }

                    status = map[rowIdx][colIdx];

                    // 왼쪽
                } else if (diff > 0) {
                    queue.offer(new int[] {rowIdx, colIdx - 1});

                    // 왼쪽 확인
                    for (int idx = 0; idx < needLength; idx++) {

                        int newColIdx = colIdx + (idx + 1) * LEFT;

                        if (newColIdx < 0) {
                            flag = false;
                            queue.clear();
                            break outLoop;
                        }

                        if (map[rowIdx][colIdx] - map[rowIdx][newColIdx] == diff
                                && !visited[rowIdx][newColIdx]) {
                            queue.offer(new int[] {rowIdx, newColIdx});
                        } else {
                            flag = false;
                            queue.clear();
                            break outLoop;
                        }
                    }

                    status = map[rowIdx][colIdx];
                }

                while (!queue.isEmpty()) {
                    int[] point = queue.poll();

                    visited[point[0]][point[1]] = true;
                }

            }

        }

        if (flag) {
            answer++;
        }

        checkLine(map, rowIdx + 1);
    }

    public static void main(String[] args) throws IOException {


        inputTestCase();

        // 좌우 체크
        visited = new boolean[mapSize][mapSize];
        checkLine(map, 0);

        // 상하체크
        visited = new boolean[mapSize][mapSize];
        checkLine(rMap, 0);

        System.out.println(answer);
    }
}
