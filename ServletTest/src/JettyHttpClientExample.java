import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.util.StringContentProvider;
import org.eclipse.jetty.http.HttpHeader;

public class JettyHttpClientExample {
    public static void main(String[] args) {
        HttpClient httpClient = new HttpClient();

        try {
            httpClient.start();

            String processed = "sample query";
            String json = String.format("{\"query\":\"%s\"}", processed);

            System.out.println("전송할 JSON: " + json);

            var response = httpClient.POST("http://localhost:8080/hello")
                    .header(HttpHeader.CONTENT_TYPE, "application/json")
                    .content(new StringContentProvider(json), "application/json")
                    .send();

            System.out.println("응답 상태: " + response.getStatus());
            String responseBody = response.getContentAsString();
            System.out.println("서버 응답: " + responseBody);

        } catch (Exception e) {
            System.err.println("요청 중 오류 발생: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                httpClient.stop();
            } catch (Exception e) {
                System.err.println("HttpClient 종료 중 오류: " + e.getMessage());
            }
        }
    }
}