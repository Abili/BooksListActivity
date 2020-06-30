package com.raisac.bookslistactivity;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefUtil {
    public static String PREF_NAME = "BooksPReference";
    public static String POSITION = "position";
    public static String QUERY = "query";

    public PrefUtil() {
    }

    public static SharedPreferences getPref(Context context) {
        return context.getSharedPreferences(PREF_NAME, context.MODE_PRIVATE);
    }

    public static String getPreferenceString(Context context, String key) {
        return getPref(context).getString(key, "");
    }

    public int getPreferenceInt(Context context, String key) {
        return getPref(context).getInt(key, 0);
    }

    public static void setPreferenceString(Context context, String key, String value) {
        SharedPreferences.Editor editor = getPref(context).edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static void setPreferenceInt(Context context, String key, int value) {
        SharedPreferences.Editor editor = getPref(context).edit();
        editor.putInt(key, value);
        editor.apply();
    }
}
