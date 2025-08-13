package string;

public class ReplaceExample {
	public static void main(String[] args) {
		String str = "Hello World Java World";
		
		System.out.println(str.replace("World", "Universe")); // 모든 발생 치환
	
		
		// 정규식을 사용한 치환
		String text = "Java1234Python467";
		System.out.println(str.replaceAll("\\d+", "-"));
	
		// 첫 번째 발생만 치환
		System.out.println(str.replaceFirst("World", "Universe"));
		
	}
}
