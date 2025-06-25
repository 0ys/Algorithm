import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static HashMap<String, Integer> map = new HashMap<>();

    static class Word implements Comparable<Word>{
        String word;

        Word(String word) {
            this.word = word;
        }

        @Override
        public int compareTo(Word o) {
            int freq = map.get(o.word) - map.get(this.word);
            if(freq != 0) return freq;

            int len = o.word.length() - this.word.length();
            if(len != 0) return len;

            return this.word.compareTo(o.word);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        PriorityQueue<String> pq = new PriorityQueue<>();
        for(int i=0; i<N; i++){
            String input = br.readLine();
            if(input.length() >= M){
                map.put(input, map.getOrDefault(input, 0) + 1);
            }
        }

        List<Word> list = new ArrayList<>();
        for(String key : map.keySet()){
            list.add(new Word(key));
        }

        Collections.sort(list);

        StringBuilder sb = new StringBuilder();
        for(Word w : list) {
            sb.append(w.word).append('\n');
        }

        System.out.println(sb);
    }
}
