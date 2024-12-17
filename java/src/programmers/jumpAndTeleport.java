package programmers;

import java.io.*;
public class jumpAndTeleport {

     /*
    - 한 번에 k칸을 앞으로 점프, k만큼의 건전지를 사용한다.
    - (현재까지 온 거리) * 2에 해당하는 위치로 순간이동, 건전지를 사용하지 않음
    
    거리가 N만큼 떨어져 있는 장소로 이동하려고 한다.
    건전지 사용량을 줄이기 위해 점프로 이동하는 것을 최소로 하려고 한다.
    
    사용해야하는 건전지 사용량의 최솟값을 구하라
    
    처음 위치는 0
    
    현재 위치가 짝수면 /2
    현재 위치가 홀수면 -1
    */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static public int solution(int n) {
        int ans = 0;
        
        while(n != 0){
            // 짝수
            if(n % 2 == 0){
                n /= 2;
            } else{
                n--;
                ans++;
            }
        }
        
        return ans;
    }

    public static void main(String[] args) throws IOException{

        int n = Integer.parseInt(br.readLine().trim());

        int answer = solution(n);

        System.out.println(answer);

    }
}
