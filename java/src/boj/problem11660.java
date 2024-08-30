package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class problem11660 {
	
	/*
	 * 구간 합 구하기 5
	 * 
	 * NxN 크기 표에서
	 * (x1,y1) 부터 (x2,y2)까지 합을 구하라
	 * 
	 * 표 크기 N, 합을 구해야 하는 횟수 M
	 * 
	 * NxN의 수
	 * M 개의 좌표들 x1, y1, x2, y2
	 * 
	 * 맵을 입력받을 때 행을 기준으로 누적합을 구해놓는다.
     * 행과 열의 구간이 주어졌을 땐
     * 행을 기준으로 미리 구해놓은 누적합으로 구간의 합을 구할 수 있다.
	 * 
	 */
	
	public static BufferedReader br;
	public static StringTokenizer st;
	public static StringBuilder sb;
	
	public static int mapSize;
	public static int count;
	public static int[][] map;
	
	public static int x1, y1, x2, y2;
	public static int answer;
	
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine().trim());
		
		mapSize = Integer.parseInt(st.nextToken());		
		count = Integer.parseInt(st.nextToken());
		
		map = new int[mapSize+1][mapSize+1];
		
		// map 입력받기
		for(int rowIdx = 1; rowIdx<=mapSize; rowIdx++) {
			st = new StringTokenizer(br.readLine().trim());
			
			for(int colIdx=1;colIdx<=mapSize; colIdx++) {
				map[rowIdx][colIdx] = Integer.parseInt(st.nextToken()) + map[rowIdx][colIdx-1] ;
			}
		}
	
		
		for(int c=0; c<count; c++) {
			answer = 0;
			st = new StringTokenizer(br.readLine().trim());
			
			x1 = Integer.parseInt(st.nextToken());
			y1 = Integer.parseInt(st.nextToken());
			x2 = Integer.parseInt(st.nextToken());
			y2 = Integer.parseInt(st.nextToken());
			
				
			for(int rowIdx=x1; rowIdx<=x2; rowIdx++) {
				
				answer += map[rowIdx][y2] - map[rowIdx][y1-1];
			}
			
			
			sb.append(answer).append("\n");
			
		}
		
		
		System.out.println(sb);
		
		
	}

}
