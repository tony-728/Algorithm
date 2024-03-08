package swea;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class problem1959 {
    /*
     * N개의 숫자열 A
     * M개의 숫자열 B
     * 
     * 두 숫자열의 마주보는 위치를 변경하여 마주보는 숫자의 곱의 합이 최대일 때를 구하라
     * 
     * 1. 배열의 길이가 작은 숫자열을 먼저 찾는다.
     * 2. 작은 길이의 배열을 긴 길이의 배열을 기준으로 첫번째 칸부터 최대로 이동할 수 있는 곳까지 이동하며
     * 곱의 합을 구한다.
     * 
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine().trim());

        for (int testCase = 1; testCase <= T; testCase++) {
            int answer = 0;

            // A, B 입력
            StringTokenizer st = new StringTokenizer(br.readLine().trim());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            int[] arrayA = new int[A];
            int[] arrayB = new int[B];

            int[] shortArray;
            int[] longArray;

            // A 배열 입력
            st = new StringTokenizer(br.readLine().trim());

            for (int idxA = 0; idxA < A; idxA++) {
                arrayA[idxA] = Integer.parseInt(st.nextToken());
            }

            // B 배열 입력
            st = new StringTokenizer(br.readLine().trim());

            for (int idxB = 0; idxB < B; idxB++) {
                arrayB[idxB] = Integer.parseInt(st.nextToken());
            }

            // 둘 중 작은 배열을 찾는다.
            int shortNum = Math.min(A, B);
            int longNum = Math.max(A, B);

            if (arrayA.length > arrayB.length) {
                longArray = arrayA;
                shortArray = arrayB;
            } else {
                shortArray = arrayA;
                longArray = arrayB;
            }

            // 마주보는 반복
            for (int loop = 0; loop < (longNum - shortNum + 1); loop++) {
                int temp = 0;
                for (int shortIdx = 0; shortIdx < shortNum; shortIdx++) {
                    temp += (shortArray[shortIdx] * longArray[shortIdx + loop]);
                }
                answer = Math.max(answer, temp);
            }
            // System.out.println("#" + testCase + " " + answer);
            bw.write("#" + testCase + " " + answer + "\n");

        }
        bw.flush();
        bw.close();
    }
}
