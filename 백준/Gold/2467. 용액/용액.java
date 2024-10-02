import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
	static int N; //2 이상 100,000 이하
	static long inputs[];
	// 산성 용액 : 1 ~ 10^9
	// 알칼리성 용액 : -10^9 ~ -1
	
	static ArrayDeque<Long> answer = new ArrayDeque<>();
	// 같은 양의 두 용액을 혼합하여 특성값이 0에 가장 가까운 용액을 만드는 두 용액의 특성값을 오름차순으로 출력하라.
	// 1. 한 종류의 용액만으로 특성값이 0에 가장 가까운 혼합 용액을 만드는 경우도 존재함
	// 2. 용액의 특성값이 정렬된 순서로 주어짐
	// 3. 답이 두 개 이상일 경우 아무거나 하나 출력
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		inputs = new long[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			inputs[i] = Long.parseLong(st.nextToken());
		}
		
		long min = Long.MAX_VALUE;
		// 완전 탐색 방식 -> 당연히 시간 초과 O(N*N)
		/*for(int i=0; i<N; i++) {
			for(int j=i; j<N; j++) {
				if(i==j) continue;
				if(Math.abs(inputs[i]+inputs[j]) < min) {
					min = Math.abs(inputs[i]+inputs[j]);
					answer.clear();
					answer.add(inputs[i]);
					answer.add(inputs[j]);
				}
			}
		}*/
		
		// 투 포인터
		/*
		반례
		7
		-99 -2 -1 1 98 100 101
		 */
		int i=0, j=N-1;
		while(i<j) {
			long a = inputs[i];
			long b = inputs[j];
			if(Math.abs(a+b) < min) {
				min = Math.abs(inputs[i]+inputs[j]);
				answer.clear();
				answer.add(a);
				answer.add(b);
				if(min == 0) break;
				if(Math.abs(a) < Math.abs(b)) j--;
				else i++;
			} else if(a+b < 0) {
				i++;
			} else {
				j--;
			}
		}
		
		
		
		for(long a : answer) {
			System.out.print(a+" ");
		}
	}

}