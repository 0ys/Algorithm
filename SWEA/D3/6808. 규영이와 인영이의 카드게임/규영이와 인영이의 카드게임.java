import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static int TOTAL_SCORE = 171; // 얻을 수 있는 점수의 총합
	static int TOTAL_DECK = 18;
	static int DECK_SIZE = 9;
	static boolean[] isSelected;
	static int[] gCards, iCards;
	static int win, lose;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			
			isSelected = new boolean[TOTAL_DECK+1];
			gCards = new int[DECK_SIZE];
			iCards = new int[DECK_SIZE];
			
			for(int i=0; i<DECK_SIZE; i++) {
				int card = Integer.parseInt(st.nextToken());
				isSelected[card] = true;
				gCards[i] = card;
			}
			
			win = 0;
			lose = 0;
			permutation(0);
			
			System.out.println("#"+tc+" "+win+" "+lose);
		}
	}
	
	static void permutation(int cnt) {
		if(cnt == DECK_SIZE) { // 종료 조건
			// 순열이 정해지면 점수를 계산함
			calScore();
			return;
		} 
		
		for(int i=1; i<=TOTAL_DECK; i++) { // 반복 조건
			if(isSelected[i]) continue;
			
			iCards[cnt] = i;
			isSelected[i] = true;
			
			permutation(cnt+1);
			isSelected[i] = false; 
		}
	}
	
	static void calScore() {
		int score = 0;
		for(int i=0; i<DECK_SIZE; i++) {
			if(iCards[i] < gCards[i]) { // 규영이가 이기는 경우 점수 계산
				score += iCards[i]+gCards[i];
			}
		}
		if(score > TOTAL_SCORE / 2) win++;
		else lose++;
	}
}