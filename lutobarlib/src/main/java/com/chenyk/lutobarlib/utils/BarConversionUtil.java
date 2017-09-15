package com.chenyk.lutobarlib.utils;

/**
 * Created by chenyk on 2017/9/7.
 * 转换工具类
 */

public class BarConversionUtil {
    /**
     * calculate color
     *
     * @param color initial color
     * @param alpha initial alpha
     * @return final color
     */
    public static int calculateColorWithAlpha(int color, int alpha) {
        if (alpha == 0) {
            return color;
        }
        float a = 1 - alpha / 255f;
        int red = color >> 16 & 0xff;
        int green = color >> 8 & 0xff;
        int blue = color & 0xff;
        red = (int) (red * a + 0.5);
        green = (int) (green * a + 0.5);
        blue = (int) (blue * a + 0.5);
        return 0xff << 24 | red << 16 | green << 8 | blue;
    }
}
