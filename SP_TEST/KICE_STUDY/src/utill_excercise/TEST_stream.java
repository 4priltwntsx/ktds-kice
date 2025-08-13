package utill_excercise;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TEST_stream {

	public static void main(String[] args) {
		List<String> names = Arrays.asList("Tom", "Jerry", "Anna");
		Stream<String> stream = names.stream();
		

        // 4글자 이상인 이름을 대문자로 변환, 중복 제거, 정렬 후 리스트로 반환
        List<String> result = names.stream()
            .filter(name -> name.length() >= 4)
            .map(String::toUpperCase)
            .distinct() // 중복제거
            .sorted() //정렬
            .collect(Collectors.toList()); // 리스트로 변환

        System.out.println(result); // [ANNA, JERRY, MIKE]
	}
}
