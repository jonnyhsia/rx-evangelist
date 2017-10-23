package com.citytogo.jonnyhsia.rxevangelist.helper;

import android.content.Context;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;

import com.citytogo.jonnyhsia.rxevangelist.App;

import java.util.Random;
import java.util.regex.Pattern;

/**
 * Created by JonnyHsia on 17/10/22.
 * 工具集
 */
public class Kits {

    /**
     * 判断网络是否连接
     *
     * @return true 为连接, false 未连接
     */
    public static boolean checkNetwork() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) App.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = null;
        if (connectivityManager != null) {
            info = connectivityManager.getActiveNetworkInfo();
        }
        return info != null && info.isConnected();
    }

    public static class UI {

        public static boolean isScreenOrientationPortrait(@NonNull Context context) {
            return context.getResources().getConfiguration().orientation
                    == Configuration.ORIENTATION_PORTRAIT;
        }
    }

    /**
     * 去除空格空行
     */
    public static String deleteBlankSpace(String input) {
        return input.replaceAll("\\s*|\t|\r|\n", "");
    }

    /**
     * 去除空格空行
     */
    public static String deleteBlankline(String input) {
        return input.replace("((\r\n)|\n)[\\s\t ]*(\\1)+", "$1");
    }

    public static String generateRandomString() {
        String base = "兔狗科技爪哇随机用户名尧婕鹏贵楠德豆岚";
        Random random = new Random();
        int randomLength = Math.max(5, random.nextInt(base.length()));
        StringBuffer buffer = new StringBuffer();

        for (int i = 0; i < randomLength; i++) {
            buffer.append(base.charAt(random.nextInt(base.length())));
        }
        return buffer.toString();
    }
}