package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class problem2636 {
    /*
     * N x M 판에 치즈가 놓여 있다.
     * 판에 가장자리에는 치즈가 놓여있지 않는다.
     * 
     * 치즈 1, 빈칸 0
     * 
     * 치즈 가장 자리는 1초 지날때마다 녹어 없어진다.
     * 
     * 일정 시간이 모두 지나면 치즈가 모두 녹아 없어진다.
     * 
     * 이 때 치즈가 모두 녹아없어지는데 걸리는 시간과
     * 모두 녹아없어지기 1초전에 남은 치즈 칸의 개수를 구하라
     * 
     * 
     * 
     * 0,0 부터 빈칸을 찾아나간다. 치즈를 만나면 치즈 위치를 따로 저장한다.
     * 모든 칸을 확인했을 때 치즈인 곳과 아닌 곳을 구분할 수 있다. 이때 치즈인 영역을 모두 1로 바꾸고
     * 시간을 증가한다.
     * 
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int height;
    static int width;
    static int[][] map;
    static boolean[][] visited;

    static int time;
    static List<Integer> answer;

    static final int BLANK = 0;
    static final int CHEESE = 1;
    static final int[] deltaX = { -1, 0, 1, 0 };
    static final int[] deltaY = { 0, 1, 0, -1 };

    static List<int[]> cheeseLocList;
    static Deque<int[]> blankQueue;

    public static int searchCheese() {
        cheeseLocList = new ArrayList<>();
        blankQueue = new ArrayDeque<>();
        visited = new boolean[height][width];

        blankQueue.add(new int[] { 0, 0 });
        visited[0][0] = true;

        int cheeseCount = 0;

        while (!blankQueue.isEmpty()) {
            int[] location = blankQueue.poll();

            for (int dir = 0; dir < deltaX.length; dir++) {
                int newRowIdx = location[0] + deltaX[dir];
                int newColIdx = location[1] + deltaY[dir];

                // 범위 확인
                if (0 > newRowIdx || newRowIdx >= height || 0 > newColIdx || newColIdx >= width) {
                    continue;
                }

                // 방문확인
                if (visited[newRowIdx][newColIdx] == false) {

                    // 방문처리
                    visited[newRowIdx][newColIdx] = true;

                    if (map[newRowIdx][newColIdx] == BLANK) {
                        blankQueue.add(new int[] { newRowIdx, newColIdx });
                    } else if (map[newRowIdx][newColIdx] == CHEESE) {
                        cheeseLocList.add(new int[] { newRowIdx, newColIdx });
                    }

                }
            }
        }

        cheeseCount = cheeseLocList.size();

        // 확인한 가장자리 치즈를 0으로 바꿔줌
        for (int idx = 0; idx < cheeseLocList.size(); idx++) {
            map[cheeseLocList.get(idx)[0]][cheeseLocList.get(idx)[1]] = BLANK;
        }

        return cheeseCount;

    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine().trim());

        height = Integer.parseInt(st.nextToken());
        width = Integer.parseInt(st.nextToken());

        map = new int[height][width];

        // 맵 입력받기
        for (int rowIdx = 0; rowIdx < height; rowIdx++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int colIdx = 0; colIdx < width; colIdx++) {
                map[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
            }
        }

        answer = new ArrayList<>();

        while (true) {

            int cheese = searchCheese();
            answer.add(cheese);

            if (cheese == 0) {
                break;
            }

            time++;
        }

        System.out.println(String.format("%d\n%d", time, answer.get(time - 1)));
    }
}
