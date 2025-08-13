package string;

import java.util.StringTokenizer;

public class SplitExample {

	public static void main(String[] args) {
		String str = "apple,banana,orange";
		StringTokenizer st = new StringTokenizer(str,",");
		while(st.hasMoreTokens()) {
			System.out.println(st.nextToken());
		}
		
		System.out.println("==============================");
		String[] fruits = str.split(",");
		
		for(String fruit: fruits) {
			System.out.println(fruit);
		}
		
		System.out.println("==============================");
		String text = "Java1234Python1235C++";
		String[] languages = text.split("\\d+");
		
		for(String lang: languages) {
			System.out.println(lang);
		}
	}
}
