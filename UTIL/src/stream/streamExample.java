package stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class streamExample {
	public static void main(String[] args) {
		List<String> names = Arrays.asList("Tom", "Jerry", "Anna");
		Stream<String> stream = names.stream();

		// 4글자 이상인 이름을 대문자로 변환, 중복 제거, 정렬 후 리스트로 반환
		List<String> result = names.stream().filter(name -> name.length() >=3).map(String::toUpperCase) // 대문자로 변환
				.distinct() // 중복제거
				.sorted() // 정렬
				.collect(Collectors.toList()); // 리스트로 변환

		System.out.println(result); // [ANNA, JERRY, MIKE]

		// StreamNumberExample
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
		int sumOfSquares = numbers.stream()
				.filter(n->n%2==0) // 짝수
				.map(n->n*n) // 제곱,  map : 
				.reduce(0, Integer::sum);
		
		System.out.println(sumOfSquares);
	}
}
