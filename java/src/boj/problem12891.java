package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem12891 {

    /*
     * 처음 생각
     * DNA 문자열은 모든 문자열에 'A', 'C', 'G', 'T'가 등장한 문자열을 말한다.
     * 
     * 임의의 DNA 문자열의 부분문자열을 뽑았을 때 등장하는 문자의 개수가 특정 개수 이상이어야 비밀번호로 사용가능
     * 
     * S: 임의로 만든 DNA 문자열 길이
     * P: 비밀번호로 사용할 부분문자열의 길이
     * 
     * 임의의 DNA 문자열
     * 
     * ACGT 순서대로 각 문자를 최소 사용 횟수 가 입력으로 들어온다.
     * 
     * 최소 사용 횟수의 총 합은 S 보다 작거나 같다.
     * 
     * 부분문자열이 등장하는 위치가 다르다면 부분문자열이 같아도 다른 문자열로 취급한다.
     * 
     * 1. 큐 자료구조로 부분문자열을 만들고 한칸씩 이동하면서 부분문자열 검사를 한다.
     * 2. 큐에 넣을 때 부분문자열에 포함되어야할 각 문자열의 개수를 충족하는지 검사한다.
     * 
     * 
     * 
     * 다시 생각해보기
     * 
     * 그 정보 자체를 저장할 필요가 없다.
     * 몇번 등장했는지가 중요한 점이다.
     * 
     * 1. 임의의 문자열을 입력받아 부분문자열의 길이까지 각 DNA 문자가 몇번 등장했는지 확인한다.
     * 
     * 2. 확인해야할 것은 각 DNA문자가 최소 등장 횟수이상 등장했는지 확인하여 비밀번호가 되는지 되지 않는지 확인한다.
     * 
     * 3. 확인이 되면 다음 문자를 확인하여 등장횟수를 갱신한다. 부분문자열길이만큼 앞에 있는 문자의 등장 횟수를 감소시킨다.
     * 
     * 위 과정을 임의의 문자열 길이 - 부분문자열 길이 만큼 수행한다.
     * 
     */

    public static BufferedReader br;
    public static StringTokenizer st;

    public static String fullStr;

    public static int fullLength;
    public static int partLength;
    public static int answer = 0;

    public static final int KindOfDNA = 4;
    public static int[] minimumDNA = new int[KindOfDNA];
    public static int[] countDNA = new int[KindOfDNA];

    // 비밀번호 조건이 만족하는지 확인
    public static void checkPassword() {
        boolean flag = true;

        // 부분문자열에 등장횟수가 필수로 들어가야하는 문자의 갯수보다 모두 많은지 확인
        for (int dnaIdx = 0; dnaIdx < KindOfDNA; dnaIdx++) {
            if (countDNA[dnaIdx] < minimumDNA[dnaIdx]) {
                flag = false;
            }
        }

        if (flag) {
            answer++;
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine().trim());

        fullLength = Integer.parseInt(st.nextToken());
        partLength = Integer.parseInt(st.nextToken());

        fullStr = br.readLine().trim();

        st = new StringTokenizer(br.readLine().trim());

        for (int idx = 0; idx < KindOfDNA; idx++) {
            minimumDNA[idx] = Integer.parseInt(st.nextToken());
        }

        // 시작인덱스부터 부분문자열의 길이만큼 각 문자가 몇번 등장하는지 확인
        for (int idx = 0; idx < partLength; idx++) {
            char dnaChar = fullStr.charAt(idx);

            switch (dnaChar) {
                case 'A':
                    countDNA[0]++;
                    break;

                case 'C':
                    countDNA[1]++;
                    break;

                case 'G':
                    countDNA[2]++;
                    break;

                case 'T':
                    countDNA[3]++;
                    break;
            }
        }

        // 하나의 부분문자열을 만들었으므로 비밀번호확인
        // 비밀번호 확인 후 한칸씩 옮기면서 부분문자열확인
        // 한칸 옮기는 과정에서 추가되는 문자의 등장횟수를 증가
        // 빠지는 문자의 등장횟수는 감소
        for (int idx = partLength; idx < fullLength; idx++) {
            checkPassword();

            char dnaChar = fullStr.charAt(idx);

            switch (dnaChar) {
                case 'A':
                    countDNA[0]++;
                    break;

                case 'C':
                    countDNA[1]++;
                    break;

                case 'G':
                    countDNA[2]++;
                    break;

                case 'T':
                    countDNA[3]++;
                    break;
            }

            char preDnaChar = fullStr.charAt(idx - partLength);

            switch (preDnaChar) {
                case 'A':
                    countDNA[0]--;
                    break;

                case 'C':
                    countDNA[1]--;
                    break;

                case 'G':
                    countDNA[2]--;
                    break;

                case 'T':
                    countDNA[3]--;
                    break;
            }

        }
        checkPassword();
        System.out.println(answer);

    }

}
