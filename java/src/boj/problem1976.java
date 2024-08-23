package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem1976 {
    /*
    union-find
    
    여행 순서가 중요한 것이 아닌 도시들을 연결해서 여행할 수 있는지가 중요하므로
    union-find를 사용할 수 있다.
    
    */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int numOfCity;
    static int numOfPlan;
    static int[] planList;

    static final int CONNECT = 1;
    static final int NO_CONNECT = 0;

    static int[] parentList;

    static void inputTestCase() throws IOException {
        numOfCity = Integer.parseInt(br.readLine().trim());
        numOfPlan = Integer.parseInt(br.readLine().trim());

        planList = new int[numOfPlan];

        parentList = new int[numOfCity + 1];

        // 나 자신을 부모로 초기화
        for (int idx = 1; idx < numOfCity + 1; idx++) {
            parentList[idx] = idx;
        }

        // 도시에 연결상태를 입력
        for (int rowIdx = 1; rowIdx < numOfCity + 1; rowIdx++) {
            st = new StringTokenizer(br.readLine().trim());


            for (int colIdx = 1; colIdx < numOfCity + 1; colIdx++) {
                int isConnected = Integer.parseInt(st.nextToken());

                // 연결됐다.
                if (isConnected == CONNECT) {
                    // union
                    union(rowIdx, colIdx);
                }
            }
        }

        // 어떤 도시들을 여행해야하는지 확인
        st = new StringTokenizer(br.readLine().trim());

        for (int planIdx = 0; planIdx < numOfPlan; planIdx++) {
            planList[planIdx] = Integer.parseInt(st.nextToken());
        }

    }

    static int find(int node) {

        // 내가 부모다.
        if (parentList[node] == node) {
            return node;
        }

        // 경로 압축
        return parentList[node] = find(parentList[node]);
    }

    static void union(int leftNode, int rightNode) {

        int leftParent = find(leftNode);
        int rightParent = find(rightNode);

        // 두 노드의 부모가 같다.
        if (leftParent == rightParent) {
            return;
        }

        // 왼쪽 부모가 더 작으면 오른쪽 노드의 부모를 왼쪽 부모로 설정
        if (leftParent < rightParent) {
            parentList[rightParent] = leftParent;
            return;
        }

        parentList[leftParent] = rightParent;
        return;

    }

    // 여행 경로에 도시의 부모가 모두 같은지 확인
    static void check() {

        // 부모집합 한번더 확인
        for (int idx = 1; idx < numOfCity + 1; idx++) {
            find(idx);
        }

        int parent = parentList[planList[0]];

        for (int idx = 0; idx < numOfPlan; idx++) {
            // 하나라도 다르면 안됨
            if (parent != parentList[(planList[idx])]) {
                System.out.println("NO");
                return;
            }
        }

        System.out.println("YES");
        return;
    }

    public static void main(String[] args) throws IOException {
        inputTestCase();

        check();

    }

}
