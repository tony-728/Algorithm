package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem2477 {
    /*
     * 참외밭은 ㄱ 모양이거나 ㄱ모양을 회전한 모양이다.
     * 
     * 1m2 면적에 자라는 참외가 주어진다.
     * 참외밭을 이루는 육각형의 임의의 한 꼭짓점에서 출발하여
     * 반시계방향으로 둘레르 돌면서 지나는 변의 방향과 길이가 순서대로 주어진다.
     * 
     * 동쪽: 1, 서쪽: 2, 남쪽: 3, 복쪽: 4
     * 
     * 참외밭에 자라는 참외의 개수를 구하라
     * 
     * 가로, 세로 각각 가장 긴 길이를 찾는다.
     * 
     * 가로를 기준으로 앞뒤 인덱스에 값은 세로이다. 이 때 가장 긴 세로가 아닌 세로를 찾는다.
     * 가로와 가장 긴 세로가 아닌 세로로 넓이를 구한다.
     * 
     * 세로를 기준으로 앞뒤 인덱스에 값은 가로이다. 이 때 가장 긴 가로가 아닌 가로를 찾는다.
     * 가장 긴 가로가 아닌 가로와 가장 긴 세로에서 앞에서 구한 가장 긴 세로가 아닌 세로를 뺸 값과의 넓이를 구한다.
     * 
     * 
     * 두 넓이의 합이 육면체의 넓이와 같다.
     * 
     */

    static class Line {
        int direction;
        int length;

        public Line(int direction, int length) {
            this.direction = direction;
            this.length = length;
        }
    }

    static final int NUM_OF_LINE = 6;
    static final int LEFT = 1;
    static final int RIGHT = 2;
    static final int DOWN = 3;
    static final int UP = 4;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int numOfCham;
    static Line[] lineList = new Line[NUM_OF_LINE];

    static int maxHeight = 0;
    static int maxHeightIndex = 0;
    static int maxWidth = 0;
    static int maxWidthIndex = 0;

    static int answer = 0;

    static int temp = 0;

    public static void inputTestCase() throws IOException {
        numOfCham = Integer.parseInt(br.readLine().trim());

        for (int idx = 0; idx < NUM_OF_LINE; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            int direction = Integer.parseInt(st.nextToken());
            int length = Integer.parseInt(st.nextToken());

            if (direction == LEFT || direction == RIGHT) {
                if (maxWidth < length) {
                    maxWidth = length;
                    maxWidthIndex = idx;
                }
            } else if (direction == DOWN || direction == UP) {
                if (maxHeight < length) {
                    maxHeight = length;
                    maxHeightIndex = idx;
                }
            }

            lineList[idx] = new Line(direction, length);
        }
    }

    public static void main(String[] args) throws IOException {

        inputTestCase();

        // 가장 긴 가로의 앞뒤 인덱스 값 중 가장 긴 세로가 아닌 값을 찾는다.
        // 가장 긴 세로가 아닌 값과 가장 긴 가로의 넓이
        int preHeightIndex = maxWidthIndex - 1 >= 0 ? maxWidthIndex - 1 : maxWidthIndex - 1 + NUM_OF_LINE;
        int nextHeightIndex = maxWidthIndex + 1 < NUM_OF_LINE ? maxWidthIndex + 1
                : (maxWidthIndex + 1) % NUM_OF_LINE;

        if (lineList[preHeightIndex].length == maxHeight) {
            answer += lineList[nextHeightIndex].length * maxWidth;
            temp = lineList[nextHeightIndex].length;
        } else {
            answer += lineList[preHeightIndex].length * maxWidth;
            temp = lineList[preHeightIndex].length;
        }

        // 가장 긴 세로의 앞뒤 인덱스 값 중 가장 긴 가로가 아닌 값을 찾는다.
        // 가장 긴 가로가 아닌 값과 (가장 긴 세로 - 위에서 구한 가장 긴 세로가 아닌 값)의 넓이
        int preWidthIndex = maxHeightIndex - 1 >= 0 ? maxHeightIndex - 1 : maxHeightIndex - 1 + NUM_OF_LINE;
        int nextWidthIndex = maxHeightIndex + 1 < NUM_OF_LINE ? maxHeightIndex + 1
                : (maxHeightIndex + 1) % NUM_OF_LINE;

        if (lineList[preWidthIndex].length == maxWidth) {
            answer += lineList[nextWidthIndex].length * (maxHeight - temp);
        } else {
            answer += lineList[preWidthIndex].length * (maxHeight - temp);
        }

        System.out.println(answer * numOfCham);
    }
}
