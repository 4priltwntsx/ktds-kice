package string;

public class IndexOfExample {
	public static void main(String[] args) {
		String str = "Java is great, Java is powerful";
		
		System.out.println("Java의 첫 번째 위치 : " + str.indexOf("Java"));
		System.out.println("is의 첫 번째 위치 : " + str.indexOf("is"));
		
		
		System.out.println("is의 마지막 위치 : " + str.lastIndexOf("is"));
		
		System.out.println("Python  위치: "+str.indexOf("Python"));
	}

}
