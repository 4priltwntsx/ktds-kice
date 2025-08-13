package string;

public class SubStringExample {
	public static void main(String[] args) {
		String str = "Hello World Java";
		
		System.out.println(str.substring(6)); // 인덱스6부터 끝까지
		System.out.println(str.substring(0, 5)); // Hello
		System.out.println(str.substring(6, 11)); // world
	}
}
