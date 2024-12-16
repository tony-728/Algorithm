package programmers;

import java.io.*;

public class PCCPTest1_1 {

    /*
     * 알파벳 소문자로만 이루어진 문자열에서 
     * 2회 이상 나타난 알파벳이 2개 이상의 부분으로 나뉘어 있으면 외톨이 알파벳이라고 한다.
     * 
     * edeaaabbccd
     * - a는 2회 이상 나타나지만, 하나의 덩어리로 뭉쳐있으므로 외톨이 알파벳이 아니다.
     * - b와 c도 a와 같은 이유로 외톨이 알파벳이 아니다.
     * 
     * - e, d는 2회 이상 나타났지만 2개의 부분으로 나뉘어 있으므로 외톨이 알파벳이다.
     * 
     * eeddee
     * - e는 2회 이상 나타나는데 2개의 부분으로 나뉘어 있으므로 외톨이 알파벳이다.
     * - d는 2회 이상 나타나는데 나뉘어 있지 않으므로 외톨이 알파벳이 아니다
     * 
     * 외톨이 알파벳이 있으면 사전순으로 출력하라
     * 외톨이 알파벳이 없다면 N을 출력하라
     *  
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


    public static void main(String[] args) throws IOException {
        String inputString = br.readLine().trim();

        String answer = solution(inputString);

        System.out.println(answer);
    }

    public static String solution(String input_string) {

        char a = 'a';

        int[] alphabetList = new int[26];
        int[] answerAlphabet = new int[26];

        String answer = "";

        char postAlpha = 'a';

        for (int idx = 0; idx < input_string.length(); idx++) {
            char alpha = input_string.charAt(idx);


            int alphaIndex = alpha - a;

            // 알파벳 횟수 증가
            alphabetList[alphaIndex]++;

            // 알파벳 등장이 2회 이상 && 이전 알파벳과 같은지 않으면
            // 외톨이 알파벳
            if (alphabetList[alphaIndex] > 1 && alpha != postAlpha) {
                answerAlphabet[alpha - a] = 1;
            }

            alphabetList[alphaIndex]++;


            postAlpha = alpha;
        }

        // 알파벳 추가
        for (int idx = 0; idx < 26; idx++) {
            if (answerAlphabet[idx] > 0) {
                answer += (char) (idx + a);
            }
        }

        if (answer.equals("")) {
            answer += "N";
        }


        return answer;
    }
}
