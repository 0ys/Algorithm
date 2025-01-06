import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static ArrayDeque<Long> stack = new ArrayDeque<>();
    static ArrayList<String> program = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String command = br.readLine();
        while(!command.equals("QUIT")) {
            while(!command.equals("END")) {
                program.add(command);
                command = br.readLine();
            }
            //System.out.println(program);

            N = Integer.parseInt(br.readLine());
            for(int i = 0; i< N; i++){
                Long a = Long.parseLong(br.readLine());
                stack.push(a);
                boolean flag = false;
                for(String s : program) {
                    if(!working(s)) {
                        //System.out.println(stack);
                        flag = true;
                        break;
                    }
                }

                if(flag || stack.size() != 1) {
                    System.out.println("ERROR");
                    stack.clear();
                }
                else System.out.println(stack.pop());
            }
            System.out.println();

            br.readLine();
            program.clear();
            command = br.readLine();
        }

    }

    static boolean working(String s) {
        StringTokenizer st = new StringTokenizer(s);
        String input = st.nextToken();
        Long n1 = 0L;
        Long n2 = 0L;
        Long n3 = 0L;
        switch (input){
            case "NUM":
                Long n = Long.parseLong(st.nextToken());
                stack.push(n);
                break;
            case "POP":
                if(!stack.isEmpty()) stack.pop();
                else return false;
                break;
            case "INV":
                if(!stack.isEmpty())  {
                    n1 = stack.pop();
                    stack.push(-1 * n1);
                } else return false;
                break;
            case "DUP":
                if(!stack.isEmpty())  {
                    n1 = stack.pop();
                    stack.push(n1);
                    stack.push(n1);
                } else return false;
                break;
            case "SWP":
                if(stack.size() >= 2){
                    n1 = stack.pop();
                    n2 = stack.pop();
                    stack.push(n1);
                    stack.push(n2);
                } else {
                    return false;
                }
                break;
            case "ADD":
                if(stack.size() >= 2){
                    n1 = stack.pop();
                    n2 = stack.pop();
                    n3 = n1 + n2;
                    if(Math.abs(n3) > 1000000000) return false;
                    else stack.push(n3);
                } else {
                    return false;
                }
                break;
            case "SUB":
                if(stack.size() >= 2){
                    n1 = stack.pop();
                    n2 = stack.pop();
                    n3 = n2 - n1;
                    if(Math.abs(n3) > 1000000000) return false;
                    else stack.push(n3);
                } else {
                    return false;
                }
                break;
            case "MUL":
                if(stack.size() >= 2){
                    n1 = stack.pop();
                    n2 = stack.pop();
                    n3 = n2 * n1;
                    if(Math.abs(n3) > 1000000000) return false;
                    else stack.push(n3);
                } else {
                    return false;
                }
                break;
            case "DIV":
                if(stack.size() >= 2){
                    n1 = stack.pop();
                    n2 = stack.pop();
                    if(n1 == 0) return false;
                    if(n1 * n2 < 0) {
                        n3 = Math.abs(n2) / Math.abs(n1);
                        if(Math.abs(n3) > 1000000000) return false;
                        else stack.push(-1 * n3);
                    } else {
                        n3 = Math.abs(n2) / Math.abs(n1);
                        if(Math.abs(n3) > 1000000000) return false;
                        else stack.push(n3);
                    }
                } else {
                    return false;
                }
                break;
            case "MOD":
                if(stack.size() >= 2){
                    n1 = stack.pop();
                    n2 = stack.pop();
                    if(n1 == 0) return false;
                    if(n2 < 0) {
                        n3 = Math.abs(n2) % Math.abs(n1);
                        if(Math.abs(n3) > 1000000000) return false;
                        else stack.push(-1 * n3);
                    } else {
                        n3 = Math.abs(n2) % Math.abs(n1);
                        if(Math.abs(n3) > 1000000000) return false;
                        else stack.push(n3);
                    }
                } else {
                    return false;
                }
                break;
        }

        return true;
    }
}