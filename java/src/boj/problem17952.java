package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class problem17952 {
    /*
     * 분단위로 업무가 추가된다.
     * 
     * 업무의 마감은 1분기가 끝날 때까지이다.
     * 
     * 업무 처리 규칙
     * - 가장 최근의 주어진 순서대로 한다. 업무를 받으면 바로 시작한다.
     * - 업무를 하던 도중 새로운 업무가 추가 된다면, 하던 업무를 중단하고 새로운 업무를 진행한다.
     * - 새로운 업무가 끝났다면, 이전에 하던 업무를 이전에 하던 부분부터 이어서 한다.
     * 
     * 업무를 처리하는데 걸리는 시간을 안다.
     * 마감한 업무는 평가에서 만점을 받는다.
     * 
     * 이번 분기에 받을 업무 평가를 점수로 예산한다.
     * 
     * 
     * 몇 분기인지 주어진다. 1<=n<1_000_000
     * N분째에 주어진 업무의 정보가 라애 두 경우 중 하나로 주어진다.
     * 
     * 1 A T: 업무의 만점은 A, 과제를 하는데 T분이 걸린다.
     * 0: 업무가 주어지지 않았다.
     * 
     * 1. 일을 받는 경우
     * 1-1. 일을 받으면 스택에 추가한다.
     * 1-2. 스택에 가장 상단에 일을 선택해 남은 업무 시간을 1감소한다.
     * 
     * 2. 일을 받지 않은 경우
     * 2-1. 스택에 가장 상단에 일을 선택해 남은 업무 시간을 1감소한다.
     * 
     * 3. 1,2 중 하나를 완료한 후 스택에 가장 상단에 일의 남은 시간이 0인지 확인한다.
     * 3-1. 남은 시간이 0이면 점수에 추가한다.
     */

    static class Job {
        int score;
        int takeTime;

        public Job(int score, int takeTime) {
            this.score = score;
            this.takeTime = takeTime;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int totalTime;
    static final int WORK = 1;

    static Deque<Job> jobStack = new ArrayDeque<>();

    static int answer;

    public static void inputTestCase() throws IOException {

        answer = 0;

        totalTime = Integer.parseInt(br.readLine().trim());

        for (int time = 0; time < totalTime; time++) {
            st = new StringTokenizer(br.readLine().trim());

            int job = Integer.parseInt(st.nextToken());

            if (job == WORK) {
                // 업무 점수
                int score = Integer.parseInt(st.nextToken());
                // 업무에 걸리는 시간
                int takeTime = Integer.parseInt(st.nextToken());

                // 일을 스택에 추가한다.
                jobStack.offer(new Job(score, takeTime));

                // 스택에 가장 위에 있는 일을 선택한다.
                Job currentJob = jobStack.peekLast();

                // 일은 받자마자 시작하므로 1분이 감소
                currentJob.takeTime--;

                // 아무 일도 받지 않았을 때
                // 이전에 하고 있는 일을 한다.
            } else {

                if (!jobStack.isEmpty()) {
                    // 이전에 하고 있는 일을 한다.
                    Job currentJob = jobStack.peekLast();

                    // 일을 시작하므로 1분이 감소
                    currentJob.takeTime--;
                }
            }

            // 현재 일이 끝났으면 업무 스택에서 빼고 점수 추가
            if (!jobStack.isEmpty() && jobStack.peekLast().takeTime == 0) {
                answer += jobStack.peekLast().score;
                jobStack.pollLast();
            }
        }
    }

    public static void main(String[] args) throws IOException {

        inputTestCase();

        System.out.println(answer);

    }

}
