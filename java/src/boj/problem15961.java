package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class problem15961 {
    /*
     * 
     * 1. K개의 접시를 연속해서 먹을 경우 할인된 정액 가격으로 초밥을 제공
     * 2. 1번 행사에 참여하면 쿠폰의 초밥을 무료로 제공
     * - 벨트에 없을 경우 새롭게 제공한다.
     * 
     * 벨트에 놓인 초밥 수 N
     * 연속해서 먹는 접시 수 K
     * 쿠폰 번호 C
     * 
     * 회전 방향이 존재
     * 
     * 회전 방향대로의 K개의 초밥 조합을 만든다.
     * 이중 쿠폰 번호에 초밥이 없는 경우가 가장 많은 초밥을 먹을 수 있다.
     * 
     * 
     * 연속해서 먹는 접시 K를 접시 순서대로 이동하면서 초밥의 종류를 카운팅한다.
     * 
     * 
     */
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int numOfDish;
    static int numOfSushi;
    static int numofContinueSushi;
    static int couponNumber;

    static int[] dishList;
    static Deque<Integer> mySushiList;
    static int[] selectedSushiList;

    static int answer;
    static int count;

    /**
     * 
     * @param depth:   모든 접시의 초밥을 확인하기 위한 깊이
     * @param dishIdx: 접시에 스시를 선택하기 위한 인덱스
     */
    public static void makeDishList(int depth, int dishIdx) {

        // 마지막 접시까지 확인후 종료
        if (depth == numOfDish + numofContinueSushi) {
            return;
        }

        // 아직 연속된 접시를 채우지 않았을 때
        if (depth < numofContinueSushi) {
            mySushiList.add(dishList[dishIdx % numOfDish]);

            // 처음 먹는 스시면 카운트 증가
            if (selectedSushiList[dishList[dishIdx % numOfDish]] == 0) {
                count++;
            }
            // 보유 스시 종류 카운팅
            selectedSushiList[dishList[dishIdx % numOfDish]]++;

            makeDishList(depth + 1, dishIdx + 1);
        } else { // 연속된 접시를 채웠을 때 확인하기
            answer = Math.max(answer, count);

            // 다음 접시를 넣기 위해 제거
            int outSushi = mySushiList.poll();

            // 삭제한 접시의 스시가 쿠폰과 다를 때
            if (outSushi != couponNumber) {
                selectedSushiList[outSushi]--;
                if (selectedSushiList[outSushi] == 0) {
                    count--;
                }
            } else { // 삭제한 접시의 스시가 쿠폰과 같을 때
                if (selectedSushiList[outSushi] > 1) {
                    selectedSushiList[outSushi]--;
                }
            }

            // 다음 순서 접시 추가
            mySushiList.add(dishList[dishIdx % numOfDish]);

            // 처음 먹는 스시면 카운트 증가
            if (selectedSushiList[dishList[dishIdx % numOfDish]] == 0) {
                count++;
            }
            // 보유 스시 종류 카운팅
            selectedSushiList[dishList[dishIdx % numOfDish]]++;

            makeDishList(depth + 1, dishIdx + 1);
        }

    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine().trim());

        numOfDish = Integer.parseInt(st.nextToken());
        numOfSushi = Integer.parseInt(st.nextToken());
        numofContinueSushi = Integer.parseInt(st.nextToken());
        couponNumber = Integer.parseInt(st.nextToken()) - 1;

        dishList = new int[numOfDish];
        selectedSushiList = new int[numOfSushi];

        // 접시 입력받기
        for (int idx = 0; idx < numOfDish; idx++) {
            dishList[idx] = Integer.parseInt(br.readLine().trim()) - 1;
        }

        selectedSushiList[couponNumber] = 1;
        count++;

        // 보유 중인 스시리스트
        mySushiList = new ArrayDeque<Integer>();

        makeDishList(0, 0);

        System.out.println(answer);

    }
}
