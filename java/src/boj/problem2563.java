package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem2563 {
    /*
     * 가로 세로 크기가 각각 100인 정사각형 모양의 도화지가 있다.
     * 한 변의 길이가 10인 색종이를 한 장 또는 여러 장 붙인 후 색종이가 붙은 영역의 넓이를 구하라
     * 
     * 색종이의 개수가 주어진다.
     * 왼쪽 변과 도화지의 왼쪽 변 사이의 거리, 아래쪽 변과 도화지의 아래쪽 변 사이의 거리 
     * 순서로 주어진다.
     * 
     * 범위에 해당하는 위치를 1로 마킹한다.
     * 모든 색종이를 마킹한 후 전체 범위에 도화지를 확인하여 1로 마킹된 곳을 모두 더한다.
     * 
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static final int LENGTH = 10;


    static int[][] map = new int[100][100];
    static int answer;
    static int numOfPaper;
    static int leftStart;
    static int bottomStart;

    public static void main(String[] args) throws IOException {

        numOfPaper = Integer.parseInt(br.readLine().trim());

        for (int paper = 0; paper < numOfPaper; paper++) {
            st = new StringTokenizer(br.readLine().trim());

            leftStart = Integer.parseInt(st.nextToken());
            bottomStart = Integer.parseInt(st.nextToken());

            for (int start = leftStart; start < leftStart + LENGTH; start++) {
                for (int bottom = bottomStart; bottom < bottomStart + LENGTH; bottom++) {
                    map[start][bottom] = 1;
                }
            }
        }

        for (int row = 0; row < 100; row++) {
            for (int col = 0; col < 100; col++) {
                answer += map[row][col];

            }
        }

        System.out.println(answer);

    }
}
