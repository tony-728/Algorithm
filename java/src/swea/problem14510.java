package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class problem14510 {

    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    /**
     * 
     * 1. 나무의 수와 나무 배열을 입력받는다.
     * 2. 해당 나무를 정렬한다.
     * 3. 나무들중 가장 높은 나무와의 차이를 저장한 ArrayList를 생성한다.
     * 4. 나무들 높이의 차이는 오름차순으로 정렬한다
     * 5. 높이의 차이가 적은 녀석부터 그리디 알고리즘으로 해결한다.
     * 6. 단 남은 나무가 1개이고 해당 나무의 높이가 2이면서 현재 자라는 높이가 1이면 넘어간다.
     * 7. 물을주고 나무의 높이가 최대 나무와 같아졌다면 그 나무는 ArrayList에서 삭제한다.
     * 8. ArrayList에 아무 나무도 남지 않을때가지 진행한다.
     * 
     */

    public static void main(String[] args) throws Exception {

        int test_case = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= test_case; tc++) {

            int N = Integer.parseInt(br.readLine().trim());
            int[] tree = new int[N];

            st = new StringTokenizer(br.readLine().trim());
            for (int index = 0; index < N; index++) {
                tree[index] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(tree);

            int top = tree[N - 1];
            List<Integer> temp = new ArrayList<Integer>();
            int total = 0;
            int answer = 0;

            // temp에는 최대높이 나무와의 차이를 오름차순으로 저장하고 total에는 temp의 총합을 저장한다
            for (int index = N - 1; index >= 0; index--) {
                int tmp = top - tree[index];
                if (tmp == 0)
                    continue;

                temp.add(tmp);
                total += tmp;
            }

            int num = 0;
            while (!temp.isEmpty()) {

                // 정답 1증가 및 현재 자라날 나무의 높이
                answer++;
                int water = 1 + (num++ % 2);

                // 위에 말한 넘겨야할 경우
                if (total == 2 && water == 1 && temp.size() == 1) {
                    continue;
                }

                // 현재 존재하는 나무중 앞에서탐색, 만약 물을 줄 수 있는 녀석 만나면 주고 끝냄
                for (int index = 0; index < temp.size(); index++) {
                    if (temp.get(index) > water) {
                        temp.set(index, temp.get(index) - water);
                        total -= water;
                        break;
                    } else if (temp.get(index) == water) {
                        temp.remove(index);
                        total -= water;
                        break;
                    }
                }
            }
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);

    }

}