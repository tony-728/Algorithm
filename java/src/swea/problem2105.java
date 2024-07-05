package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem2105 {
    /*
     *
     * NxN 모양을 가진 지역
     * 각 위치에 카페에 디저트의 종류가 있다.
     * 각 위치는 대각선 방향으로 움직일 수 있는 길이 있다.
     * 어느 한 카페에서 출발하여 디저트를 되도록 많이 먹으려고 한다.
     * 서로 다른 디저트를 먹으면서 사각형 모양을 그리며 출발한 카페로 돌아와야 한다.
     *  - 같은 숫자의 디저트가 있는 곳은 가면 안된다.
     * 
     * 가장 많은 카페를 들리는 경우를 찾는다.
     * 디저트를 먹을 수 없는 경우는 -1을 출력한다.
     * 
     * 
     * 입력조건
     * - 한 변의 길이는 4 <= N <= 20
     * - 디저트 종류는 1 <= <= 100
     * 
     * 
     * 각 변의 각 끝점은 시작점이 될 수 없다.
     * 
     * purning condition
     * 1. 현재 경로에 동일한 디저트가 있으면 return
     * 2. 3번째 꼭짓점에서 총 먹을 수 있는 종류을 알 수 있기 때문에 비교 후 return
     * 
     * 
     * 
     */


    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int testCase;
    static int mapSize;
    static int[][] map;

    static int leftUpCount;
    static int leftDownCount;
    static int rightUpCount;
    static int rightDownCount;

    static int answer;

    static boolean[] visited;

    // 이동
    static final int[] LEFT_UP = {-1, -1};
    static final int[] LEFT_DOWN = {-1, 1};
    static final int[] RIGHT_UP = {1, -1};
    static final int[] RIGHT_DOWN = {1, 1};

    static void inputTestCase() throws IOException {

        visited = new boolean[101];

        mapSize = Integer.parseInt(br.readLine().trim());
        map = new int[mapSize][mapSize];

        for (int rowIdx = 0; rowIdx < mapSize; rowIdx++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int colIdx = 0; colIdx < mapSize; colIdx++) {
                map[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void find(){

    }

    public static void main(String[] args) throws IOException{
        testCase = Integer.parseInt(br.readLine().trim());

        for(int tc=1; tc<=testCase; tc++){
            inputTestCase();


        }
    }
}
