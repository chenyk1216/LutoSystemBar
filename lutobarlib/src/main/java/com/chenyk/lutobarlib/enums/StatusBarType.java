package com.chenyk.lutobarlib.enums;

import java.io.Serializable;

/**
 * Created by chenyk on 2017/9/7.
 * 状态栏类型
 */

public enum StatusBarType implements Serializable{
    GRADIENT(1, "渐变"),//6.0以上，仅对部分机型有效，否则依旧为纯色效果
    PURECOLOR(2, "纯色");
    int code;
    String name;

    StatusBarType(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    /**
     * 获取对应名字
     *
     * @param code
     * @return
     */
    public static String getName(int code) {
        String resultName = "";
        for (StatusBarType tintType : StatusBarType.values()) {
            if (tintType.code == code) {
                resultName = tintType.name;
                break;
            }
        }
        return resultName;
    }
}
