import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Set<String> usedPrefix = new HashSet<>();
        Map<String, Integer> nameCount = new HashMap<>();

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            String nickname = br.readLine();
            String alias = null;

            // 가능한 가장 짧은 접두사 찾기 (이전 접두사들과 겹치지 않는)
            for (int len = 1; len <= nickname.length(); len++) {
                String prefix = nickname.substring(0, len);
                if (!usedPrefix.contains(prefix)) {
                    alias = prefix;
                    break;
                }
            }

            if (alias != null) {
                sb.append(alias).append("\n");
            } else {
                int count = nameCount.getOrDefault(nickname, 0) + 1;
                nameCount.put(nickname, count);
                if (count == 1) {
                    sb.append(nickname).append("\n");
                } else {
                    sb.append(nickname).append(count).append("\n");
                }
            }

            for (int len = 1; len <= nickname.length(); len++) {
                usedPrefix.add(nickname.substring(0, len));
            }

            if(alias != null) {
                nameCount.putIfAbsent(nickname, 1);
            }

        }

        System.out.print(sb);
    }
}
