package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem1979 {
    /*
     * NxN 2차원 배열에서 0과 1로 빈칸을 구분하고(1이 빈칸이다.)
     * 가로와 세로를 각각 기준으로 할 때 K의 길이가 딱 들어가는 개수를 구하라
     * 
     * 2차원 배열을 가로와 세로를 각각 기준으로 확인하면서 연속된 빈칸의 길이가 K일 때를 찾는다.
     * 
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine().trim());
        int N, K;
        int[][] grid;

        for (int testCase = 1; testCase <= T; testCase++) {
            int answer = 0;

            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            grid = new int[N][N];

            // NxN grid 입력
            for (int row = 0; row < N; row++) {
                st = new StringTokenizer(br.readLine().trim());
                for (int col = 0; col < N; col++) {
                    grid[row][col] = Integer.parseInt(st.nextToken());
                }
            }

            // 연속된 빈칸 확인 1. 가로
            for (int row = 0; row < N; row++) {
                int continueBlank = 0;
                for (int col = 0; col < N; col++) {
                    if (grid[row][col] == 1) {
                        continueBlank++;
                    } else if (continueBlank > 0 && grid[row][col] == 0) {
                        // 단어가 빈칸에 들어가는지 확인
                        if (continueBlank == K) {
                            answer++;
                        }
                        continueBlank = 0;
                    }
                }
                if (continueBlank > 0) {
                    if (continueBlank == K) {
                        answer++;
                    }
                }
            }

            // 연속된 빈칸 확인 2. 세로
            for (int row = 0; row < N; row++) {
                int continueBlank = 0;
                for (int col = 0; col < N; col++) {
                    if (grid[col][row] == 1) {
                        continueBlank++;
                    } else if (continueBlank > 0 && grid[col][row] == 0) {
                        // 단어가 빈칸에 들어가는지 확인
                        if (continueBlank == K) {
                            answer++;
                        }
                        continueBlank = 0;
                    }
                }
                if (continueBlank > 0) {
                    if (continueBlank == K) {
                        answer++;
                    }
                }
            }

            System.out.println("#" + testCase + " " + answer);
        }
    }
}