package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class problem20056 {
    /*
     * NxN 격자에 파이어볼 M개
     * 
     * 각 파이어볼마다 위치, 질량, 방향, 속력 값이 있다.
     * - 위치는 행, 열
     * 
     * 격자에서 1번행과 N번행이 연결, 1번열과 N번열이 연결
     * 
     * 파이어볼 방향은 시계방향으로 0번부터 7번이 부여됨(8방)
     * 
     * 파이어볼 이동
     * - 자신의 방향으로 속력 칸만큼 이동
     *  - 같은 칸에 여러 개의 파이어볼이 있을 수 있다.
     * 
     * 파이어볼 이동 종료
     * 1. 같은 칸에 있는 파이어볼은 하나로 합쳐짐
     * 2. 파이어볼은 4개의 파이어볼로 나누어진다.
     * 3. 나누어진 파이어볼의 질량, 속력, 방향 결정하기
     *  3-1. 질량은 ceil(질량의 합 / 5)
     *  3-2. 속력은 ceil(속력의 합 / 합쳐진 파이어볼 개수)
     *  3-3. 합쳐지는 파이어볼 방향이 모두 홀수(짝수)면 방향은 0,2,4,6 아니면 1,3,5,7
     * 4. 질량이 0인 파이어볼은 소멸됨
     * 
     * 모든 명령이 끝난 후 남아있는 파이어볼 질량의 합
     * 
     */
    static class FireBall {
        int rowIdx;
        int colIdx;
        int mass;
        int speed;
        int direction;

        public FireBall(int rowIdx, int colIdx, int mass, int speed, int direction) {
            this.rowIdx = rowIdx;
            this.colIdx = colIdx;
            this.mass = mass;
            this.speed = speed;
            this.direction = direction;
        }

        @Override
        public String toString() {
            return "FireBall [rowIdx=" + rowIdx + ", colIdx=" + colIdx + ", mass=" + mass
                    + ", speed=" + speed + ", direction=" + direction + "]";
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int mapSize;
    static int numOfFireBall;
    static int numOfCommand;

    static int[][] speedMap;

    static int[][] directionMap;
    static boolean[][] visitedMap;
    static int[][] splitDirectionMap;

    static int[][] massMap;
    static int[][] countMap;

    static final int[] MOVE_UP = {-1, 0};
    static final int[] MOVE_RIGHT_UP = {-1, 1};
    static final int[] MOVE_RIGHT = {0, 1};
    static final int[] MOVE_RIGHT_DOWN = {1, 1};
    static final int[] MOVE_DOWN = {1, 0};
    static final int[] MOVE_LEFT_DOWN = {1, -1};
    static final int[] MOVE_LEFT = {0, -1};
    static final int[] MOVE_LEFT_UP = {-1, -1};

    // 상, 우상, 우, 우하, 하, 우좌, 좌, 좌상
    static final int[][] DIRECTION_LIST = {MOVE_UP, MOVE_RIGHT_UP, MOVE_RIGHT, MOVE_RIGHT_DOWN,
            MOVE_DOWN, MOVE_LEFT_DOWN, MOVE_LEFT, MOVE_LEFT_UP};

    static final int MASS_DIVIDER = 5;

    static final int CROSS = 0;
    static final int X = 1;

    static Queue<FireBall> fireBallQ = new ArrayDeque<>();

    static int answer;

    static int[] move(int rowIdx, int colIdx, int speed, int direction) {
        int[] move = DIRECTION_LIST[direction];

        // 위치 설정
        // 행렬, 양끝은 서로 연결되어 있다.
        int newRowIdx = (rowIdx + (speed * move[0]) + (mapSize * speed)) % mapSize;
        int newColIdx = (colIdx + (speed * move[1]) + (mapSize * speed)) % mapSize;

        return new int[] {newRowIdx, newColIdx};
    }

    static void runCommand() {
        // 이동 명령 실행

        while (!fireBallQ.isEmpty()) {
            FireBall fireBall = fireBallQ.poll();

            int rowIdx = fireBall.rowIdx;
            int colIdx = fireBall.colIdx;
            int mass = fireBall.mass;
            int speed = fireBall.speed;
            int direction = fireBall.direction;

            // 새로운 위치로 이동
            int[] newLocation = move(rowIdx, colIdx, speed, direction);

            int newRowIdx = newLocation[0];
            int newColIdx = newLocation[1];

            massMap[newRowIdx][newColIdx] += mass;
            speedMap[newRowIdx][newColIdx] += speed;
            countMap[newRowIdx][newColIdx]++;

            // 방문한 적이 있다..
            if (visitedMap[newRowIdx][newColIdx]) {

                // 저장된 방향과 나머지가 같은지 확인
                if (directionMap[newRowIdx][newColIdx] % 2 == direction % 2) {
                    continue;
                }

                // 저장된 방향과 나머지가 다르면 
                splitDirectionMap[newRowIdx][newColIdx] = X;


            } else {
                visitedMap[newRowIdx][newColIdx] = true;
                directionMap[newRowIdx][newColIdx] = direction;
                splitDirectionMap[newRowIdx][newColIdx] = CROSS;
            }
        }

    }

    static void endCommand() {
        // 이동 명령 종료 후 파이어볼이 있는 칸을 확인해서 나뉨

        for (int rowIdx = 0; rowIdx < mapSize; rowIdx++) {
            for (int colIdx = 0; colIdx < mapSize; colIdx++) {
                // 위치에 파이어볼이 하나인 경우
                if (countMap[rowIdx][colIdx] == 1) {
                    // 확인해야할 파이어볼 정보
                    // 질량
                    int mass = massMap[rowIdx][colIdx];
                    // 방향
                    int direction = directionMap[rowIdx][colIdx];
                    // 속도
                    int speed = speedMap[rowIdx][colIdx];

                    FireBall fireBall = new FireBall(rowIdx, colIdx, mass, speed, direction);

                    fireBallQ.offer(fireBall);

                    // 위치에 파이어볼이 2개 이상인 경우
                } else if (countMap[rowIdx][colIdx] > 1) {
                    // 질량
                    int mass = massMap[rowIdx][colIdx] / MASS_DIVIDER;

                    // 질량이 0인 파이어볼은 없어짐
                    if (mass == 0) {
                        continue;
                    }

                    // 속도
                    int speed = speedMap[rowIdx][colIdx] / countMap[rowIdx][colIdx];

                    // 방향
                    int direction = splitDirectionMap[rowIdx][colIdx];

                    for (int dirIdx = direction; dirIdx < DIRECTION_LIST.length; dirIdx =
                            dirIdx + 2) {

                        FireBall fireBall = new FireBall(rowIdx, colIdx, mass, speed, dirIdx);

                        fireBallQ.offer(fireBall);
                    }
                }
            }
        }
    }

    static int countFireBallMass() {
        int result = 0;
        for (FireBall fireBall : fireBallQ) {
            result += fireBall.mass;
        }

        return result;
    }

    static void initalizeMap() {
        massMap = new int[mapSize][mapSize]; // 질량 맵
        speedMap = new int[mapSize][mapSize]; // 속도 맵
        directionMap = new int[mapSize][mapSize]; // 방향 맵
        visitedMap = new boolean[mapSize][mapSize]; // 방문처리 
        splitDirectionMap = new int[mapSize][mapSize]; // 나누어지는 방향
        countMap = new int[mapSize][mapSize]; // 개수 맵
    }

    public static void main(String[] args) throws IOException {

        answer = 0;

        st = new StringTokenizer(br.readLine().trim());

        mapSize = Integer.parseInt(st.nextToken()); // 맵 크기
        numOfFireBall = Integer.parseInt(st.nextToken()); // 파이어볼 개수
        numOfCommand = Integer.parseInt(st.nextToken()); // 명령 개수

        initalizeMap(); // 사용하는 맵 초기화

        // 파이어볼 입력
        for (int idx = 0; idx < numOfFireBall; idx++) {
            st = new StringTokenizer(br.readLine().trim());

            int rowIdx = Integer.parseInt(st.nextToken()) - 1;
            int colIdx = Integer.parseInt(st.nextToken()) - 1;
            int mass = Integer.parseInt(st.nextToken());
            int speed = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken());

            FireBall fireBall = new FireBall(rowIdx, colIdx, mass, speed, direction);

            fireBallQ.offer(fireBall);
        }

        for (int idx = 0; idx < numOfCommand; idx++) {
            runCommand(); // 이동 명령 수행
            endCommand(); // 이동 후 명령 수행
            initalizeMap(); // 맵 초기화
        }

        answer = countFireBallMass();

        System.out.println(answer);

    }
}
