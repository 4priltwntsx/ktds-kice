import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class imageTest1 {

    public static class RGB {
        int a;
        int b;
        int c;

        public RGB(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }

    public static class Vector {
        double r;
        double g;
        double b;

        public Vector(double a, double b, double c) {
            this.r = a;
            this.g = b;
            this.b = c;
        }
    }

    static class Image {
        int width, height;
        RGB[][] pixels;

        public Image(int width, int height, RGB[][] pixels) {
            this.width = width;
            this.height = height;
            this.pixels = new RGB[height][width];
        }
    }

    private static void fileLoad(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split("#");
            String[] rgb = parts[1].split(",");
            int a = Integer.parseInt(rgb[0]);
            int b = Integer.parseInt(rgb[1]);
            int c = Integer.parseInt(rgb[2]);
            String[] vector = parts[2].split(",");
            double r = Double.parseDouble(vector[0]);
            double g = Double.parseDouble(vector[1]);
            double b2 = Double.parseDouble(vector[2]);

            colors.put(new RGB(a, b, c), new Vector(r, g, b2));
        }
    }

    private static HashMap<RGB, Vector> colors = new HashMap<>();

    public static void main(String[] args) throws IOException {
        String fileName = "COLORBOOK.TXT";

        fileLoad(fileName);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


    }
}
