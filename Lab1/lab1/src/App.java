import java.lang.Math;


public class App {

    static short[] c = new short[15];
    static double[] x = new double[13];
    static double a[][] = new double[15][13];

    static double x_min = -2.0;
    static double x_max = 15.0;
    static final short[] nums = {
        7, 9, 13, 16, 18, 19, 20
    };

    public static void main(String[] args) throws Exception {

        System.out.println( 1.0/4);

        // Task #1
        for (short i = 0, j = 20; i < c.length; i++, j--) {
            c[i] = j;
        }

        // Task #2
        for (int i = 0; i < x.length; i++) {
            x[i] = (Math.random() * (x_max - x_min) + x_min);
        }

        // Task #3
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                a[i][j] = calcA(c[i], x[j]);
            }
        }

        // Task #4
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                System.out.printf( "%.2f\t" , a[i][j]);
            }
            System.out.println();
        }

    }


    private static double calcA(short c, double x) {

        if (c == (short) 15) {
            return Math.cos(Math.tan(x * Math.pow((x - Math.PI), 2)));
        }

        for (short num: nums) {
            if (c == num) {
                double firstPart = (double) 2 / 3;
                double secondPart = Math.pow((3 * Math.cos(x)), 2);
                double result = firstPart / secondPart;
            
                return result;
            }
        }

        return Math.cos( Math.pow( Math.tan(0.5 / x), 1/3) );
    }

}
