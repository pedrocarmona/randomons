package com.example.others;


import org.andengine.entity.modifier.PathModifier.Path;

/**
 * @author Andrew Kondratovich
 */
public class RoundedPath {
    private static double[][] M = { { -1,  3, -3, 1 },
            {  3, -6,  3, 0 },
            { -3,  3,  0, 0 },
            {  1,  0,  0, 0 } };

    public RoundedPath() {}

    public static Path path(float x1, float y1,
                            float x2, float y2,
                            float x3, float y3,
                            float x4, float y4) {
        return RoundedPath.path(x1, y1, x2, y2, x3, y3, x4, y4, 100);
    }

    public static Path path(float x1, float y1,
                            float x2, float y2,
                            float x3, float y3,
                            float x4, float y4,
                            int count) {
        Path path = new Path(count + 1);

        double[][] G = { { x1, y1 },
                { x2, y2 },
                { x3, y3 },
                { x4, y4 } };

        double[][] C = multiply(RoundedPath.M, G);

        for (int i = 0; i <= count; i++) {
            double t = i / (double) count;
            double[][] result = multiply(new double[][] { { t * t * t, t * t, t, 1 } }, C);

            path.to((float) result[0][0], (float) result[0][1]);
        }

        return path;
    }

    private static double[][] multiply(double[][] x, double[][] y) {
        int row = x.length;
        int col = y[0].length;

        double[][] result = new double[row][col];
        for (int i = 0; i < row; i++)
            for (int j = 0; j < col; j++) {
                double count = 0;
                for (int k = 0; k < y.length; k++)
                    count += x[i][k] * y[k][j];

                result[i][j] = count;
            }

        return result;
    }
}
