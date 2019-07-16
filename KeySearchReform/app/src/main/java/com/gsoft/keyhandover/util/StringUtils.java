package com.gsoft.keyhandover.util;

import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.TreeMap;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Created by tanghao on 2017/3/16.
 */

public class StringUtils {

    /**
     * 过滤出字母、数字加中文
     */
    public static String filter(String character) {
        character = character.replaceAll("[^(a-zA-Z0-9\\u4e00-\\u9fa5)]", "");
        return character;
    }

    /**
     * 对含有特殊字符的链接进行编码
     */
    public static String getURLEncoderString(String str) {
        String result = "";
        if (null == str) {
            return "";
        }
        try {
            result = java.net.URLEncoder.encode(str, "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean isEmpty(String str) {
        if (str == null) {
            return true;
        }
        if ("".equals(str.replaceAll(" ", ""))) {
            return true;
        }
        return false;
    }

    public static String stringNumber(String str) throws PatternSyntaxException {

        String regEx = "^\\d+$";
        Pattern p = Pattern.compile(regEx);
        StringBuilder sb = new StringBuilder(str);

        for (int len = str.length(), i = len - 1; i >= 0; --i) {

            if (!p.matches(regEx, String.valueOf(str.charAt(i)))) {
                sb.deleteCharAt(i);
            }
        }
        return sb.toString();
    }

    /**
     * by lz
     * 判断TextView、EditText是否为空
     */
    public static boolean isEmpty(TextView... views) {
        for (TextView view : views) {
            if (isEmpty(view.getText().toString())) {
                return true;
            }
        }
        return false;
    }

    /**
     * by lz
     * 判断TextView、EditText是否为空
     */
    public static boolean isEmpty(String... strs) {
        for (String str : strs) {
            if (isEmpty(str)) {
                return true;
            }
        }
        return false;
    }

    /**
     * TextView  to  String
     */
    public static String TextviewToStr(TextView view) {
        return view.getText().toString().replaceAll(" ", "");
    }


    /**
     * 检测是否有emoji表情
     *
     * @param source
     * @return
     */
    public static boolean containsEmoji(String source) {
        int len = source.length();
        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);
            if (!isEmojiCharacter(codePoint)) { //如果不能匹配,则该字符是Emoji表情
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否是Emoji
     *
     * @param codePoint 比较的单个字符
     * @return
     */
    private static boolean isEmojiCharacter(char codePoint) {
        return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA) ||
                (codePoint == 0xD) || ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) ||
                ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) || ((codePoint >= 0x10000)
                && (codePoint <= 0x10FFFF));
    }


    public static String getPara(List<KeyValue> bodyParams) {
        StringBuffer sb = new StringBuffer();
        if (!bodyParams.isEmpty()) {
            for (KeyValue kv : bodyParams) {
                sb.append(kv.key).append("=").append(kv.value).append("&");
            }
            sb.deleteCharAt(sb.length() - 1);
        }
        L.i("lz","bodyParams:"+sb.toString());
        return sb.toString();
    }
}
