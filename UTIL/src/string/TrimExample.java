package string;

public class TrimExample {
	public static void main(String[] args) {
		String str = "    Hello World     ";
		System.out.println("원본: '" + str +"'");
		System.out.println("trim 후 : '" + str.trim() + "'");
		
		// 중간 공백은 제거되지 않음.
		
	}
}
