package utils.fileInOut;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class FileToMapExample {
	public static void main(String[] args) throws IOException {
		String filePath = "sample.txt";
		String delimiter = "#"; // 구분자
		Map<String, String> map = new HashMap<>();

		BufferedReader br = new BufferedReader((new FileReader(filePath)));
		String line;
//		StringTokenizer st;
		while ((line = br.readLine()) != null) {
			String[] parts = line.split(delimiter); // 구분자로 분리
			if (parts.length == 2) {
				String key = parts[0].trim(); // key, value 쌍일 때만 저장
				String value = parts[1].trim();
				map.put(key, value);
			}
		}

		for (Map.Entry<String, String> entry : map.entrySet()) {
			System.out.println(entry.getKey() + "=>" + entry.getValue());
		}
	}
}
