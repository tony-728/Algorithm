package boj;

import java.io.*;
import java.util.*;

public class problem1963 {
    /*
     * 4자리 소수
     * 
     * 비밀번호를 한 번에 한 자리 밖에 못 바꾼다.
     * 
     * 네 자리 소수 두 개를 입력받아서 바꾸는 데 몇 단계가 필요한지
     * - 항상 네 자리 소수를 유지해야한다. 예를 들어 0039와 같은 1000미만의 비밀번호는 허용되지 않는다.
     */

    static StringBuilder sb = new StringBuilder();
    static boolean[] arrOfPrime = new boolean[10_001];
    static boolean[] visited = new boolean[10_001];

    static void solution(int start, int end) {

        Deque<int[]> pQ = new ArrayDeque<>();

        boolean flag = false;

        pQ.add(new int[] {start, 0});

        visited = new boolean[10_001];
        visited[start] = true;

        while (!pQ.isEmpty()) {

            int[] password = pQ.poll();

            // System.out.println("password " + password[0] + " count " + password[1]);

            // 찾은 비밀번호가 목적 비밀번호일 때
            if (password[0] == end) {
                sb.append(password[1]).append("\n");
                flag = true;
                break;
            }


            // 첫번째 자리부터 마지막자리까지 바꾼 값을 추가한다.
            for (int idx = 0; idx < 4; idx++) {
                char[] arrOfPassword = String.valueOf(password[0]).toCharArray();

                // 자리에서 0부터 9까지 수를 바꿔서 소수인지 확인한다.
                for (int number = 0; number < 10; number++) {
                    if (idx == 0 && number == 0) {
                        continue;
                    }

                    // 자리의 숫자 변환
                    arrOfPassword[idx] = (char) (number + '0');

                    String stringPassword = "";

                    for (int passIdx = 0; passIdx < 4; passIdx++) {
                        stringPassword += arrOfPassword[passIdx];
                    }

                    int intPassword = Integer.parseInt(stringPassword);

                    // 방문처리
                    if (visited[intPassword]) {
                        continue;
                    }

                    // 소수인지 확인
                    if (arrOfPrime[intPassword]) {
                        pQ.add(new int[] {intPassword, password[1] + 1});
                        visited[intPassword] = true;
                    }
                }
            }
        }

        if (!flag) {
            sb.append("Impossible").append("\n");
        }
    }

    static void isPrime() {

        for (int idx = 2; idx <= 10000; idx++) {
            if (visited[idx] == false) {
                visited[idx] = true;
                arrOfPrime[idx] = true;

                for (int next = idx; next <= 10000; next = next + idx) {
                    visited[next] = true;
                }
            }
        }

        // for(int idx=1; idx<=10000; idx++){
        //     System.out.print(idx + " " + arrOfPrime[idx] + " ");
        //     if(idx % 10 == 0) {
        //         System.out.println();
        //     }
        // }
    }

    public static void main(String[] args) throws IOException {

        isPrime();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int testCase = Integer.parseInt(br.readLine().trim());

        for (int tc = 0; tc < testCase; tc++) {
            st = new StringTokenizer(br.readLine().trim());

            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());

            solution(left, right);

        }

        System.out.println(sb);
    }
}
