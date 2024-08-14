import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.HashMap;

public class Solution {
	public static void main(String[] args) throws Exception {
		HashMap<Character, Integer> parentheses = new HashMap<Character, Integer>() {{
			put('(', 1);
			put(')', 1);
			put('[', 2);
			put(']', 2);
			put('{', 3);
			put('}', 3);
			put('<', 4);
			put('>', 4);
		}};
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		for(int t=1; t<=10; t++) {
			int N = Integer.parseInt(br.readLine());
			String inputs = br.readLine();
			
			ArrayDeque<Integer> stack = new ArrayDeque<>();
			for(int i=0; i<N; i++) {
				char now = inputs.charAt(i);
				if(now=='(' || now=='[' || now=='{' || now=='<') {
					stack.push(parentheses.get(now));
					//System.out.println("push "+now+" "+parentheses.get(now));
				} else {
					if(stack.peek() == parentheses.get(now)) {
						int top = stack.pop();
						//System.out.println("pop "+top);
					} else {
						stack.push(parentheses.get(now));
					}
				}
				
			}
			
			int ans = -1;
			if(!stack.isEmpty()) {
				ans = 0;
			} else {
				ans = 1;
			}
			
			bw.write("#"+t+" "+ans+"\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}