import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		char[] inputs = br.readLine().toCharArray();
		//System.out.println(Arrays.toString(inputs));
		
		ArrayDeque<Character> stack = new ArrayDeque<>();
		
		for(Character c : inputs) {
			if('A' <= c && c <= 'Z') sb.append(c);
			else if(c != '(' && c != ')') {
				while(!stack.isEmpty() && priority(stack.peek()) >= priority(c)) {
					sb.append(stack.poll());
				}
				stack.push(c);
			}
			else if(c == ')') {
				while(!stack.isEmpty() && stack.peek() != '(') {
					sb.append(stack.poll());
				}
				stack.poll(); // 괄호 제거
			} else stack.push(c); // '('
		}
		
		while(!stack.isEmpty()) {
			sb.append(stack.poll());
		}
		
		System.out.println(sb.toString());
	}
	
	public static int priority(char op) {
        if(op == '*' || op == '/') return 2;
        else if(op == '+' || op == '-') return 1;
        else return 0; // '('는 제일 낮은 값을 반환
    }

}
