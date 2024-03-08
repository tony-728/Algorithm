package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem2615 {

    /*
     * 19x19 오목판에 흰, 검 바둑알을 놓는다.
     * 같은 색 알 5개가 연속적으로 직선(직선, 대각선)을 이루면 이긴다.
     * 6알 이상이 연속적으로 놓인 경우는 이긴 것이 아님
     * 
     * 바둑판을 확인하여 승,패,판단불가 를 판단한다.
     * (검, 흰이 동시에 이기는 경우는 없다.-> 무조건 승패가 있음)
     * 
     * 검 1 흰 2 없는자리 0
     * 
     * 승부가 결정나지 않았으면 0 검은색이 이기면 1, 흰색이 이기면 2를 출력
     * 
     * 승부가 결정되었다면 이기게된 바둑알 중 가장 왼쪽에 있는 바둑알의 가로번호, 세로번호 출력
     * (가로, 대각선일 때 가장 왼쪽, 세로일 때 가장 위쪽)
     * 
     * 1. 오목판을 낮은 인덱스부터 차례로 확인한다.
     * 2. 빈칸이 아닌 경우
     * 3 우, 우상, 우하, 하 방향으로 오목을 이루는지 확인한다.
     * 3.1 각 방향을 같은 방향으로 계속 탐색한다.
     * 3.2 바둑판 범위 안에 있을 경우 같은 돌인지 확인하여 개수를 증가하고 아니면 중지한다.
     * 3.3 반복이 완료되었을 때 같은 돌의 개수가 5개이면
     * 3.3.1 같은 직선의 반대 방향의 돌의 색을 확인한다.
     * - 같은 경우 확인한 방향에서는 오목이지만 전체적으로는 6목이다.
     * - 다른 경우 값을 저장한다.
     * 
     */
    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("Test5.txt"));
        // 여기에 코드를 작성하세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[][] grid = new int[19][19];
        int answer = 0;
        int winRow = 0;
        int winCol = 0;

        for (int row = 0; row < 19; row++) {
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            for (int col = 0; col < 19; col++) {
                grid[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        // 1,1부터 우, 우상, 우하, 하를 확인하며 연속된 5자리인지 확인한다.
        // 연속된 5자리를 넘는지도 함께 확인해야 한다.
        int[] deltaX = { 0, -1, 1, 1 };
        int[] deltaY = { 1, 1, 1, 0 };

        for (int row = 0; row < 19; row++) {
            for (int col = 0; col < 19; col++) {
                if (grid[row][col] > 0) { // 빈칸이 아니여야함
                    int stone = grid[row][col]; // 시작 바둑돌

                    // 4방향으로 연속된 돌이 있는지 확인
                    for (int idx = 0; idx < 4; idx++) {
                        int count = 1;
                        int loop = 1;

                        while (true) {
                            // 같은 방향으로 계속 탐색할 수 있도록 반복할 때마다 loop을 더함
                            int newRow = row + deltaX[idx] * loop;
                            int newCol = col + deltaY[idx] * loop;

                            // 바둑판 범위 안에 있어야함
                            if (-1 < newRow && newRow < 19 && -1 < newCol && newCol < 19) {
                                // 시작한 돌과 같은 경우
                                if (grid[newRow][newCol] == stone) {
                                    count++;
                                } else {
                                    break;
                                }
                            } else {
                                break;
                            }

                            // 확인한 방향의 같은 돌이 6개 이상이면 6목이므로 아웃
                            if (count > 5) {
                                break;
                            }
                            loop++;
                        }

                        if (count == 5) {
                            // 반대 방향에 돌이 같은 돌인지 확인해야 한다.
                            int checkRow = row + (deltaX[idx] * -1);
                            int checkCol = col + (deltaY[idx] * -1);

                            // 범위 안에 있는 경우
                            if (-1 < checkRow && checkRow < 19 && -1 < checkCol && checkCol < 19) {
                                // 현재 돌과 달라야 한다.
                                if (grid[checkRow][checkCol] != stone) {
                                    answer = stone;
                                    winRow = row + 1;
                                    winCol = col + 1;
                                }
                            } else { // 범위 밖에 있는 경우는 확인한 줄이 오목임
                                answer = stone;
                                winRow = row + 1;
                                winCol = col + 1;
                            }
                        }
                    }
                }
            }
        }

        System.out.println(answer);
        if (answer > 0) {
            System.out.println(winRow + " " + winCol);
        }

    }
}
