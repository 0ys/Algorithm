import java.io.*;
import java.util.*;

public class Solution {
	static final int YEAR = 12;
	static int min, day, month, month3, year;
	static int[] plan, dp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			day = Integer.parseInt(st.nextToken());
			month = Integer.parseInt(st.nextToken());
			month3 = Integer.parseInt(st.nextToken());
			year = Integer.parseInt(st.nextToken());
			min = year; // 1년 이용권을 사용할 때
			
			plan = new int[YEAR+1];
			st = new StringTokenizer(br.readLine());
			for(int i=1; i<=YEAR; i++) {
				plan[i] = Integer.parseInt(st.nextToken());
			}
			
			price(0, 0);
			sb.append("#"+t+" "+min+"\n");
		}
		System.out.println(sb.toString());
	}
	
	static void price(int now, int cost) {
		if(cost > min || now > 12) {
			min = Math.min(min, cost);
			return;
		}
		
		price(now+1, cost+Math.min(day*plan[now], month));
		price(now+3, cost+month3);
	}

}