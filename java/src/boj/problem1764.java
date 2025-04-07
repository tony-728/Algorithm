package boj;

import java.io.*;
import java.util.*;

public class problem1764 {
    /*
    n을 해시맵으로 관리
    m을 입력받고 정렬
    
    m을 순서대로 해시맵에 있는지 확인

    두 집합에 중복이 없기 때문에 간단하게 처리할 수 있다.
    */

    static int n, m;

    static HashMap<String, String> nList = new HashMap<>();
    static String[] mList;

    static StringBuilder sb = new StringBuilder();

    static int count;

    static void inputData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        count = 0;

        st = new StringTokenizer(br.readLine().trim());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // 듣도 못한 사람
        // 해쉬셋에 추가
        for (int idx = 0; idx < n; idx++) {
            String input = br.readLine().trim();

            nList.put(input, input);
        }

        // 보도 못한 사람
        mList = new String[m];
        for (int idx = 0; idx < m; idx++) {
            String input = br.readLine().trim();

            mList[idx] = input;
        }
    }

    static void solution() {

        // 보도 못한 사람을 정렬
        Arrays.sort(mList);

        // 중복되는 인원 체크
        for (int idx = 0; idx < m; idx++) {
            String name = mList[idx];

            if (nList.get(name) != null) {
                count++;
                sb.append(name).append("\n");
            }
        }


    }

    public static void main(String args[]) throws IOException {
        inputData();

        solution();

        System.out.println(count);
        System.out.println(sb);

    }
}
