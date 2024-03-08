package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class problem2007 {
    /*
     * 입력받은 문자열에 패턴이 존재하는데 패턴의 길이를 출력
     * 
     * 1. 입력받은 문자열을 하나씩 저장
     * 2. 저장된 문자열이 짝수 일 때 앞부분과 뒷부분이 같은지 확인
     * 3. 같다면 저장
     * 4. 패턴의 최대 길이는 10이므로 10보다 큰 패턴일 땐 저장하지 않음
     * 
     */

    static final int MAX_LENGTH = 10;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine().trim());

        for (int testCase = 1; testCase <= T; testCase++) {
            List<Character> charList = new ArrayList<>();
            String patterns = br.readLine().trim();
            String pattern = patterns;
            int answer = 0;

            for (int idx = 0; idx < patterns.length(); idx++) {

                charList.add(patterns.charAt(idx));

                if (idx > 0 && idx % 2 == 0) {

                    char[] firstArr = new char[(idx + 1) / 2];
                    char[] secondArr = new char[(idx + 1) / 2];

                    for (int arrIdx = 0; arrIdx < charList.size(); arrIdx++) {
                        if (arrIdx < (idx + 1) / 2) {
                            firstArr[arrIdx] = charList.get(arrIdx);
                        } else {
                            secondArr[arrIdx % ((idx + 1) / 2)] = charList.get(arrIdx);
                        }
                    }

                    if (Arrays.equals(firstArr, secondArr)) {
                        if (!String.copyValueOf(firstArr).contains(pattern)) {
                            answer = Math.max(answer, (idx + 1) / 2);
                            pattern = String.copyValueOf(firstArr);
                        }

                    }
                }
            }
            System.out.println("#" + testCase + " " + answer);

        }
    }
}
