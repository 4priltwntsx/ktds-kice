
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.util.StringContentProvider;
import org.eclipse.jetty.http.HttpHeader;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AITEST03 {
    private static Map<String, String> dictionary = new HashMap<>();
    private static List<String> stopwords = new ArrayList<>();
    private static final List<ModelInfo> models = new ArrayList<>();

    // 모델 정보 클래스
    public static class ModelInfo{
        String modelname;
        String url;
        List<ClassInfo> classes;ㄹ
    }
    public static class ClassInfo{
        String code;
        String value;
    }

    // 메인 서블릿
    public static class MainServlet extends HttpServlet {
        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
            Gson gson  = new Gson();
            JsonObject requestJson = gson.fromJson(new InputStreamReader(req.getInputStream()), JsonObject.class);
            String modelname = requestJson.get("modelname").getAsString();
            JsonArray queries = requestJson.getAsJsonArray("queries");
            String url = requestJson.get("url").getAsString();

            ModelInfo model = models.stream().filter(m->
                    m.modelname.equals(modelname)).findFirst().orElse(null);
            if(model==null){
                resp.setStatus(400);
                resp.getWriter().println("{\"error\":\"model not found\"}");
                return;
            }

            List<String> results = new ArrayList<>();
            for(JsonElement queryElem : queries){
                String query = queryElem.getAsString();
                String processed = preprocess(query); // 문장 전처리
                String code = null; // url과 전처리된 문장
                try {
                    code = requestModel(model.url, processed); // 모델서버에 Post 요청
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                String finalCode = code;
                String value = model.classes.stream().filter(c->c.code.equals(finalCode)).map(c->c.value).findFirst().orElse("unknown");
                results.add(value);
            }
        }
    }

    // 모델 서버에 HTTP POST 요청
    private static String requestModel(String url, String processed) throws Exception{
        Gson gson = new Gson(); // Gson 인스턴스
        HttpClient client = new HttpClient(); // Jetty HttpClient 생성
        client.start(); // http클라이언트 시작

        // Json 바디 생성
        String json = String.format("{\"query\":\"%s\"}", processed);

        // Post 요청 생성 및 전송
        ContentResponse response = client.POST(url)
                .header(HttpHeader.CONTENT_TYPE, "application/json") // content-type 지정
                .content(new StringContentProvider(json), "application/json") // json 바디
                .send();// 동기 전송

        // 응답 코드 및 바디 처리
        String responseBody = response.getContentAsString();
        JsonObject res = gson.fromJson(responseBody, JsonObject.class);

        client.stop(); // httpClient 종료

        return res.get("result").getAsString(); // 결과 추출


    }

    private static String preprocess(String sentence){ // 문장 전처리
        String[] tokens = sentence.trim().split("\\s+"); // 공백으로 토큰나뉙
        List<String> vectors = new ArrayList<>();
        for(String token : tokens){
            String key = token.toLowerCase();
            String vector = dictionary.get(key);
            if(vector!=null && !stopwords.contains(vector)){
                vectors.add(vector);
            }
        }
        return String.join(" ", vectors);

    }

    private static void loadModels(String path) throws IOException {
        Gson gson = new Gson();
        try(Reader reader = new FileReader(path)){
            JsonObject obj = gson.fromJson(reader, JsonObject.class);
            JsonArray arr = obj.getAsJsonArray("models");
            for(JsonElement elem : arr){
                models.add(gson.fromJson(elem, ModelInfo.class));
            }
        }
    }

    public static void loadDictionary(String fileName) { // 딕셔너리 로드
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            while((line = br.readLine()) != null) { // 파일 읽어오기
//				System.out.println(line);
                // spliter
                if(line.contains("#")) {
                    String[] parts = line.split("#", 2);
                    String word = parts[0].toLowerCase();
                    String embedding = parts[1];
                    dictionary.put(word, embedding);
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static void loadStopword(String fileName) { // 딕셔너리 로드
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            while((line = br.readLine()) != null) { // 파일 읽어오기
                System.out.println(line);
                stopwords.add(line);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        loadDictionary("DICTIONARY.txt");
        loadStopword("STOPWORD.txt");
        loadModels("MODELS.txt");

        Server server = new Server(8080);
        ServletHandler handler = new ServletHandler();
        handler.addServletWithMapping(MainServlet.class, "/");
        server.setHandler(handler);
        server.start();
        server.join();

    }
}
