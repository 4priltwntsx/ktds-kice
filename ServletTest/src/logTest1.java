import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class logTest1 {

    static class Log{
        String timestamp;
        String level;
        String message;
        String category;
        LocalTime time;

        public Log(String timestamp, String level, String message, String category, String time){
            this.timestamp = timestamp;
            this.level = level;
            this.message = message;
            this.category = "UNKNOWN";

            // 시간 파싱
            try {
                String timeOnly = timestamp.split(" ")[1]; // "14:30:15" 부분 추출
                this.time = LocalTime.parse(timeOnly);
            } catch (Exception e) {
                this.time = LocalTime.now();
            }
        }
    }

    static class ClassificationRule{
        String category;
        List<String> keywords;
        Pattern compiledPattern;

        public ClassificationRule(String category, List<String> keywords) {
            this.category = category;
            this.keywords = keywords;

            // 키워드들을 정규표현식 패턴으로 컴파일
            String pattern = keywords.stream()
                    .map(keyword -> "(?i).*" + keyword + ".*")  // 대소문자 무시, 포함 검사
                    .collect(Collectors.joining("|", "(", ")"));
            this.compiledPattern = Pattern.compile(pattern);
        }

        public boolean matches(String message) {
            return compiledPattern.matcher(message).matches();
        }
    }

    static void FileLoad(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        while((line=br.readLine())!=null){
            System.out.println(line);


        }
    }

    private static List<ClassificationRule> classificationRules = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String filename = "CLASSIFY.TXT";

        FileLoad(filename);


    }
}
