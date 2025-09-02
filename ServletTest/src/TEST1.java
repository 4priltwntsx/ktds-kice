import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class TEST1 {

    // 이동 평균 계산 클래스
    static class MovingAvgCalculator{
        private Queue<Double> values;
        private int windowSize;
        private double sum;

        public MovingAvgCalculator(int windowSize){
            this.windowSize = windowSize;
            values = new LinkedList<Double>();
            sum = 0.0;
        }

        public double addValue(double value){
            if(values.size()>=windowSize){ // 지금 사이즈가 윈도우 사이즈보다 크거나 같다면
                double removed = values.poll();
                sum -= removed;
            }
            values.offer(value);
            sum += value;
            return sum/values.size(); // 새 값 넣고 평균값 계산
        }
    }

    // 메트릭 정보 클래스
    static class Metric{
        String name;
        int windowSize;
        double threshold;
        MovingAvgCalculator movingAvgCalculator;

        public Metric(String name, int windowSize, double threshold) {
            this.name = name;
            this.windowSize = windowSize;
            this.threshold = threshold;
            this.movingAvgCalculator = new MovingAvgCalculator(windowSize);
        }
    }

    private static HashMap<String, Metric> metrics = new HashMap<>();

    // 파일 처리
    private static void loadFile(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line = null;
        while((line=br.readLine())!=null){
            if(line.contains("#")){
                String[] split = line.split("#");
                String name = split[0];
                int windowSize = Integer.parseInt(split[1]);
                double threshold = Double.parseDouble(split[2]);
                metrics.put(name, new Metric(name, windowSize, threshold));
                System.out.println(name + " " + windowSize + " " + threshold);
            }

        }
    }

    // 메트릭 데이터 처리




    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        loadFile("THRESHOLD.txt");

        // 콘솔입력 분리
        while(true){
            String line = br.readLine();
            if(line!=null){
                String[] split = line.split(":");
                String metricName = split[0]; // 메트릭 이름
                double value = Double.parseDouble(split[1]); // 수치값
                Metric metric = metrics.get(metricName);
                double average = metric.movingAvgCalculator.addValue(value);
                String status = average <= metric.threshold ? "OK" : "ALERT";
                System.out.println("평균값: "+average);
                System.out.println("임계값: "+metric.threshold);
                System.out.println(metric.name + " AVG:" + average+ " " + status);
            }
        }
    }
}
