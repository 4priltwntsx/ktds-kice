import java.io.*;
import java.util.*;
public class FileToMapExample {
    public static void main(String[] args) throws IOException {
        String filePath = "sample.txt"; // 읽을 파일 경로
        String delimiter = "#"; // 구분자(Delemeter) 지정
        Map<String, String> map = new HashMap<>(); // 결과 저장용 Map
// 파일을 한 줄씩 읽기
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) { // 한 줄씩 읽음
                String[] parts = line.split(delimiter); // 구분자로 분리
                if (parts.length == 2) { // Key/Value 쌍일 때만 저장
                    String key = parts[0].trim(); // 앞부분: Key
                    String value = parts[1].trim(); // 뒷부분: Value
                    map.put(key, value); // Map에 저장
                }
            }
        }
// 결과 확인: Map 전체 출력
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " => " + entry.getValue());
        }
    }
}