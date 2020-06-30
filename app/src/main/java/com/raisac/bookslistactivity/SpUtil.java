package com.raisac.bookslistactivity;

import android.content.Context;
import android.content.SharedPreferences;

public class SpUtil {
    public SpUtil() {
    }
    private static final String PREF_NAME = "BooksPreference";
    public static final String POSTION = "position";
    public static final String QUERY = "query";

    public static SharedPreferences getPrefs(Context context){
         return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
     }
     public static String getPreferenceString(Context context, String key){
         return getPrefs(context).getString(key, "");
     }
     public static int getPrefenceInt(Context context, String key){
         return getPrefs(context).getInt(key, 0);
     }
     public static void setPreferenceString(Context context, String key, String value){
         SharedPreferences.Editor editor = getPrefs(context).edit();
         editor.putString(key, value);
         editor.apply();
     }
    public static void setPreferenceInt(Context context, String key, int value){
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.putInt(key, value);
        editor.apply();
    }
}
