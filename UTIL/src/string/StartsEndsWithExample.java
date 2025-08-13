package string;

public class StartsEndsWithExample {
	public static void main(String[] args) {
		String filename = "document.pdf";
		String url = "https://www.example.com";

		System.out.println("PDF 파일인가: " + filename.endsWith(".pdf")); // 출력: true
		System.out.println("HTTPS 프로토콜인가: " + url.startsWith("https")); // 출력: true
		System.out.println("DOC 파일인가: " + filename.endsWith(".doc")); // 출력: false }
	}
}
