package com.example.qxx0101.haishangzuoye.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

/**
 * @PrejectName: HaiShang
 * @PackageName: com.example.qxx0101.haishangzuoye.Utils
 * @Desc:
 * @Author: qxx0101
 * @Date: 4/24/2019
 * @Version:
 */
public class SharedPreferencesUtils {

    private static final String USER = "user";

    public static void setLoggedStatus(Context context, boolean isLogged) {
        SharedPreferences sharedpreferences = context.getSharedPreferences(USER, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putBoolean("isLogged", isLogged);
        editor.apply();
    }

    public static boolean getLoggedStatus(Context context) {
        return context.getSharedPreferences(USER, MODE_PRIVATE).getBoolean("isLogged", false);
    }
}
