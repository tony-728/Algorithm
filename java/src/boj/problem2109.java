package boj;

import java.io.*;
import java.util.*;

public class problem2109 {
    /*
     * n개의 강연
     * 각 대학에서 d일 안에 와서 강연하면 p만큼의 강연료를 얻는다.
     * 
     * 가장 많은 돈을 벌 수 있도록 순회강연을 한다.
     * 하루에 최대 한개 강연을 할 수 있다.
     * 
     * 정렬 기준
     * - 날짜는 작게 강연료는 크게
     * 
     * - 주의할 점 d일 안에 와서 강의를 하는 것이다. d일에 와서 강의를 하는 것이 아니라
     * - 따라서 5일동안 강의를 한다면 4일 안에 가는 대학은 두번 갈 수도 있다.
     */

    static class Job implements Comparable<Job> {
        int cost;
        int day;

        public Job(int cost, int day) {
            this.cost = cost;
            this.day = day;
        }

        public int compareTo(Job o) {
            // 날짜가 작은 거 먼저

            // 날짜가 같으면
            // 코스트가 큰 거 먼저

            if (o.day == this.day) {
                return o.cost - this.cost;

            } else {
                return this.day - o.day;
            }
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static Job[] jobList;
    static int numOfJob;

    static int answer;

    static void inputData() throws IOException {

        answer = 0;

        numOfJob = Integer.parseInt(br.readLine().trim());

        jobList = new Job[numOfJob];

        for (int idx = 0; idx < numOfJob; idx++) {
            st = new StringTokenizer(br.readLine().trim());

            int cost = Integer.parseInt(st.nextToken());
            int day = Integer.parseInt(st.nextToken());

            jobList[idx] = new Job(cost, day);
        }

        Arrays.sort(jobList);
    }

    static void solution() {

        int jobCount = 0;
        PriorityQueue<Integer> pQ = new PriorityQueue<>();

        for (int idx = 0; idx < numOfJob; idx++) {
            // 이전까지 진행된 강연의 수가 강연 기한 날짜보다 작으면
            // 강연을 갈 수 있다.
            // ex) 이전까지 진행된 강의 3개, 추가하는 강연의 기한 날짜 4일일 때는 추가 강연을 할 수 있다.
            if (jobCount < jobList[idx].day) {
                jobCount++;
                answer += jobList[idx].cost;
                pQ.add(jobList[idx].cost);

                // 이전까지 진행된 강연의 수보다 추가 강연 기한 날짜가 같거나 오래걸린다.
                // 그리고 이전까지 진행된 강연최소비용보다 현재 강연의 비용이 더 크다면 갱신
                // 날짜를 더해주지 않는 이유는 이전 최소비용 강연을 안가고 현재 강연을 가기 떄문에
            } else if (pQ.peek() < jobList[idx].cost) {
                int minCost = pQ.poll();
                answer -= minCost;
                answer += jobList[idx].cost;
                pQ.add(jobList[idx].cost);

            }
        }
    }

    public static void main(String[] args) throws IOException {
        inputData();

        solution();

        System.out.println(answer);
    }
}
