package boj;

import java.io.*;
import java.util.*;

public class problem15657 {
    /*
     * 중복 조합
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int elementCount;
    static int selectCount;
    static int[] elementList;
    static int[] selectList;

    static void inputData() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        elementCount = Integer.parseInt(st.nextToken());
        selectCount = Integer.parseInt(st.nextToken());

        elementList = new int[elementCount];
        selectList = new int[selectCount];

        st = new StringTokenizer(br.readLine().trim());

        for (int idx = 0; idx < elementCount; idx++) {
            elementList[idx] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(elementList);
    }

    static void solution(int elementIdx, int selectIdx) {
        if (selectIdx == selectCount) {
            for (Integer val : selectList) {
                sb.append(val).append(" ");
            }
            sb.append("\n");
            return;
        }

        if (elementIdx == elementCount) {
            return;
        }


        // 현재 인덱스를 선택했다.
        selectList[selectIdx] = elementList[elementIdx];
        solution(elementIdx, selectIdx + 1);
        
        // 현재 인덱스를 선택하지 않았다.
        selectList[selectIdx] = 0;
        solution(elementIdx+1, selectIdx);
    }

    public static void main(String[] args) throws IOException {
        inputData();

        solution(0, 0);

        System.out.println(sb);
    }
}
