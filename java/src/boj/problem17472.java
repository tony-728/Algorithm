package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class problem17472 {
    /*
     * 
     * NxM 맵이 있다.
     * 
     * 1은 땅이고 0은 바다이다.
     * 
     * 섬 위치에서 4방을 확인한다.
     * 
     * 2칸 이상으로 다른 섬에 연결할 수 있으면 통과
     * 
     * 0. 입력을 받는다.
     * 1. 섬을 구성한다.
     * 1-1. 섬을 구성하는 것은 2차원 배열에서 땅을 만났을 때 bfs를 시도하여
     * 연결되어 있는 땅을 찾으면 된다.
     * 2. 연결할 수 있는 다리를 찾는다.
     * 3. 놓을 수 있는 다리를 모두 놓아본다.
     * 4. 모든 섬을 연결하는 최소 길이의 다리를 확정한다.
     * 
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int height, width;
    static int[][] map;

    static int answer;

    static final int[] deltaX = { -1, 0, 1, 0 };
    static final int[] deltaY = { 0, -1, 0, 1 };

    static final int SEA = 0;
    static final int LAND = 1;
    static final int BRIDGE_LENGTH_IDX = 2;

    static Queue<int[]> bridgeQueue;
    static List<Land> landList;

    static int[] parentList;
    static int[] rankList;

    static class Land {
        int landNum;
        List<int[]> landMemberList;

        public Land(int landNum) {
            this.landNum = landNum;
            this.landMemberList = new ArrayList<>();
        }
    }

    public static void inputTestCase() throws IOException {

        answer = 0;

        st = new StringTokenizer(br.readLine().trim());

        height = Integer.parseInt(st.nextToken());
        width = Integer.parseInt(st.nextToken());

        map = new int[height][width];

        for (int rowIdx = 0; rowIdx < height; rowIdx++) {
            st = new StringTokenizer(br.readLine().trim());

            for (int colIdx = 0; colIdx < width; colIdx++) {
                map[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
            }
        }
    }

    // 1. 섬을 구성한다.
    // 땅인 곳을 만나면 BFS 탐색을 통해 인접해 있는 땅들을 찾는다.
    public static void makeLand() {
        // 섬의 순서
        int landNum = 1;

        // 방문확인 배열
        boolean[][] visited = new boolean[height][width];

        // 섬을 저장할 리스트
        landList = new ArrayList<>();

        for (int rowIdx = 0; rowIdx < height; rowIdx++) {
            for (int colIdx = 0; colIdx < width; colIdx++) {

                // 물인 경우
                if (map[rowIdx][colIdx] == SEA) {
                    continue;
                }

                // 방문한 곳인 경우
                if (visited[rowIdx][colIdx]) {
                    continue;
                }

                // 땅을 찾으면 인접한 땅이 있는지 확인한다.
                Land land = new Land(landNum);
                land.landMemberList.add(new int[] { rowIdx, colIdx });
                landList.add(land);

                Deque<int[]> queue = new ArrayDeque<>();

                // 최초 발견한 땅을 넣어준다.
                queue.offer(new int[] { rowIdx, colIdx });

                map[rowIdx][colIdx] = landNum; // 섬의 번호를 기록한다.
                visited[rowIdx][colIdx] = true; // 방문처리

                while (!queue.isEmpty()) {
                    int[] landMember = queue.poll();
                    int currentRowIdx = landMember[0];
                    int currentColIdx = landMember[1];

                    for (int dir = 0; dir < deltaX.length; dir++) {
                        int nextRowIdx = currentRowIdx + deltaX[dir];
                        int nextColIdx = currentColIdx + deltaY[dir];

                        // 범위 확인
                        if (nextRowIdx < 0 || nextRowIdx >= height || nextColIdx < 0 || nextColIdx >= width) {
                            continue;
                        }

                        // 바다인지 확인
                        if (map[nextRowIdx][nextColIdx] == SEA) {
                            continue;
                        }

                        // 이미 방문한 땅 확인
                        if (visited[nextRowIdx][nextColIdx]) {
                            continue;
                        }

                        // 구성할 수 있는 땅
                        // 번호 기록
                        map[nextRowIdx][nextColIdx] = landNum;
                        // 방문처리
                        visited[nextRowIdx][nextColIdx] = true;

                        // 큐에 넣어준다.
                        queue.offer(new int[] { nextRowIdx, nextColIdx });
                        // 현재 섬에 멤버로 넣어준다.
                        land.landMemberList.add(new int[] { nextRowIdx, nextColIdx });
                    }
                }
                // 다음 섬 번호로 증가
                landNum++;
            }
        }
    }

    // 2. 연결할 수 있는 다리를 찾는다.
    // 섬에 존재하는 모든 땅에 4방 탐색을 진행한다.
    public static void findBridge() {

        // 우선순위 큐를 사용하는 이유
        // 다리정보는 시작섬, 도착섬, 다리길이로 구성되어 있다.
        // 문제에서 다리의 길이가 짧은 경우의 합을 구해야하므로
        // 다리 길이를 기준으로 정렬하고 사용한다.
        bridgeQueue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[BRIDGE_LENGTH_IDX] - o2[BRIDGE_LENGTH_IDX];
            }
        });

        // 각 섬들을 하나씩 꺼내서 다리를 만들어 준다.
        for (Land land : landList) {
            // 다리를 만든다.
            connectBridge(land);

        }
    }

    // 3. 다리를 연결한다.
    // 다리를 놓을 때 조건
    // 1. 다리의 길이가 2이상이여야한다.
    // 2. 동일한 섬을 연결하지 않아야 한다.
    public static void connectBridge(Land land) {

        // 섬에 존재하는 땅을 꺼내서 확인한다.
        for (int landIdx = 0; landIdx < land.landMemberList.size(); landIdx++) {

            // 4방 탐색으로 다리를 연결하자
            for (int dir = 0; dir < deltaX.length; dir++) {
                int bridgeLength = 0;
                int connectedLandNum = 0;
                boolean isConnected = false;

                int[] landMember = land.landMemberList.get(landIdx);
                int currentRowIdx = landMember[0];
                int currentColIdx = landMember[1];

                while (true) {
                    int nextRowIdx = currentRowIdx + deltaX[dir];
                    int nextColIdx = currentColIdx + deltaY[dir];

                    // 범위 확인
                    if (nextRowIdx < 0 || nextRowIdx >= height || nextColIdx < 0 || nextColIdx >= width) {
                        break;
                    }

                    // 동일한 섬으로 연결하려는 경우
                    if (map[nextRowIdx][nextColIdx] == land.landNum) {
                        break;
                    }

                    // 다른 섬으로 연결이 된 경우
                    if (map[nextRowIdx][nextColIdx] >= LAND) {
                        // 연결된 섬의 번호로 초기화
                        connectedLandNum = map[nextRowIdx][nextColIdx];
                        isConnected = true;
                        break;
                    }
                    // 아직 연결되지 않음
                    // 다리의 길이를 증가시켜서 다시 확인
                    bridgeLength++;
                    currentRowIdx = nextRowIdx;
                    currentColIdx = nextColIdx;
                }

                // 섬을 연결하였고 다리의 길이가 1보다 큰 경우만
                // 다리의 자격이 있다.
                if (isConnected && bridgeLength > 1) {
                    // 연결 시작 섬, 연결 도착 섬, 다리 길이
                    bridgeQueue.offer(new int[] { land.landNum, connectedLandNum, bridgeLength });
                }

            }
        }
    }

    public static void make() {
        parentList = new int[landList.size() + 1];
        rankList = new int[landList.size() + 1];

        for (int idx = 0; idx < parentList.length; idx++) {
            parentList[idx] = idx;
        }
    }

    public static int find(int element) {
        if (element == parentList[element]) {
            return element;
        }

        return parentList[element] = find(parentList[element]);
    }

    public static void union(int element1, int element2) {
        int e1Parent = find(element1);
        int e2Parent = find(element2);

        if (e1Parent == e2Parent) {
            return;
        }

        if (rankList[e1Parent] > rankList[e2Parent]) {
            parentList[e2Parent] = e1Parent;
            return;
        }

        parentList[e1Parent] = e2Parent;

        if (rankList[e1Parent] == rankList[e2Parent]) {
            rankList[e2Parent]++;
        }
    }

    public static void decideMinLengthBridge() {
        // 섬이 연결되었는지 확인하는 배열
        // 섬의 번호가 1부터 시작하므로 1개 더 크게 생성한다.
        boolean[] connectLand = new boolean[landList.size() + 1];

        int connectedBridgeCount = 0;

        // 다리를 연결할 수 없는 경우도 있기 때문에 다리의 개수를 확인한다.
        // DisjointSet을 이용하여 동일한 섬에 연결되지 않도록 한다.
        // 모든 섬에 연결하는 것이 목적이기 때문에 동일한 섬에 연결하는 것은 잘못됨

        // 서로소 집합을 만든다.
        make();

        while (!bridgeQueue.isEmpty()) {
            int[] bridge = bridgeQueue.poll();

            int startLandNum = bridge[0];
            int endLandNum = bridge[1];
            int bridgeLength = bridge[2];

            // 연결된 섬이 서로 다른 섬이라면 하나의 섬으로 연결
            if (find(startLandNum) != find(endLandNum)) {
                connectLand[startLandNum] = true;
                connectLand[endLandNum] = true;

                union(startLandNum, endLandNum);

                answer += bridgeLength;
                connectedBridgeCount++;
            }
        }

        // 연결되지 않은 섬이 있는지 확인한다.
        for (int landIdx = 1; landIdx < landList.size() + 1; landIdx++) {
            if (!connectLand[landIdx]) {
                answer = -1;
                return;
            }
        }

        // 사용된 다리의 개수가 섬의 개수 -1 인지 확인한다.
        if (connectedBridgeCount != landList.size() - 1) {
            answer = -1;
            return;
        }

    }

    public static void main(String[] args) throws IOException {
        inputTestCase();

        // 1. 섬을 구성한다.
        makeLand();

        // 2. 연결할 수 있는 다리를 모두 찾는다.
        findBridge();

        // 3. 다리의 길이가 최소가 되는 경우를 찾아 모든 섬을 연결한다.
        decideMinLengthBridge();

        System.out.println(answer);
    }
}