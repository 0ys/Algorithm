import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

// KMP 알고리즘(Knuth–Morris–Pratt Algorithm) 
// O(N+M)
//1)실패함수: 패턴에서 접두사와 접미사가 같을때의 길이를 구함
//i=1(접미사), j=0 (접두사)부터 문자 비교 시작
//일치하지 않으면  j(비교할 패턴의 수정위치) = fail[j-1] 조정 후 비교: 문자가 일치하거나 j ==0 일때까지 계속 비교
//일치 => i,j 위치 모두 증가하고 fail[i] = ++j;

//2)텍스트와 비교: 텍스트 길이 만큼 패턴과 비교
//i,j모두 0에서부터 비교
public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String text = br.readLine();
		String pattern = br.readLine();
		
		int tLength = text.length(), pLength = pattern.length();
		
		//1) 실패함수 만들기 : KMP의 아이디어를 똑같이 적용
		int[] pi = new int[pLength];//(i위치까지의 부분 문자열을 조사했을 때) 접두사이면서 접미사인 최대 문자열 길이 저장 배열
		pi[0] = 0;
		
		//(i:접미사 포인터), (j:접두사 포인터)
	    for(int i=1, j=0; i<pLength; i++){	    	
	    	//1.불일치: j값 조정.
	    	while (j>0 && pattern.charAt(i) != pattern.charAt(j)) {
	    		j = pi[j-1];
	    	}
	    	//2.조정된 j인덱스 위치와 인덱스 i번째까지 조사했을 때 앞뒤가 똑같은 문자열의 최대 길이는 ....
	    	if(pattern.charAt(i) == pattern.charAt(j)) {
	    		j++;
	    	}
	    	pi[i] = j;
	    	
	       
	    }
	    //System.out.println(Arrays.toString(pi));
		
		int cnt = 0;//텍스트 안에서 패턴이 발견된 횟수
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		//2)텍스트와 비교하기
		// i : 텍스트 포인터 , j: 패턴 포인터 
		for(int i=0,j=0; i< tLength; i++) {
			//System.out.println(i+" "+text.charAt(i)+", "+j+" "+pattern.charAt(j));
			while (j > 0 && text.charAt(i) != pattern.charAt(j)) {
                j = pi[j - 1];  // 불일치 시, j값 조정
            }
			
			if(pattern.charAt(j) == text.charAt(i)) {
				j++;
			}

			if(j == pLength) {
				cnt++;
				list.add(i-j+2);
				j = pi[j-1];
			} 
		}
		
		System.out.println(cnt);
		if(cnt>0) {
			for(int ans : list) {
				System.out.print(ans+" ");
			}
		}
	}
}