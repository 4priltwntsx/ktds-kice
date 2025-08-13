package string;

public class CharAtExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "Java Programming";
		
		System.out.println("첫 번째 문자: " + str.charAt(0)); //J
		System.out.println("다섯 번째 문자: " + str.charAt(str.length()-1));
		
		char[] result = str.toCharArray();
		System.out.println(result[0]);
		
	}

}
