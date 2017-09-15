package com.chenyk.lutobarlib.utils;

import android.app.Activity;
import android.graphics.Color;

/**
 * Created by chenyk on 2017/9/7.
 * 资源工具类
 */

public class BarResUtil {
    public static int getColor(Activity activity, Object object) {
        if (object instanceof String) {// "#666666"
            return Color.parseColor((String) object);
        } else if (object instanceof Integer) {
            if ((Integer) object > 0)// R.string.app_color
                return activity.getResources().getColor((Integer) object);
            else return (Integer) object;// Color.BLUE
        } else throw new IllegalStateException("The current color is not found");
    }

    public static String getString(Activity activity, Object object) {
        if (object instanceof String)//"title"
            return (String) object;
        else if (object instanceof Integer && (Integer) object > 0) //R.string.app_name
            return activity.getResources().getString((Integer) object);
        else throw new IllegalStateException("The current string is not found");
    }
}
