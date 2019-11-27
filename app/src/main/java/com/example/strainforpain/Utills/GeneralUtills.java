package com.example.strainforpain.Utills;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.NetworkInfo;
import android.util.Log;

import com.example.strainforpain.Config;

public class GeneralUtills {

    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor editor;


    public static SharedPreferences.Editor putStringValueInEditor(Context context, String key, String value) {
        sharedPreferences = getSharedPreferences(context);
        editor = sharedPreferences.edit();
        editor.putString(key, value).commit();
        return editor;
    }

    public static SharedPreferences.Editor putIntegerValueInEditor(Context context, String key, int value) {
        sharedPreferences = getSharedPreferences(context);
        editor = sharedPreferences.edit();
        editor.putInt(key, value).commit();
        return editor;
    }

    public static SharedPreferences.Editor putBooleanValueInEditor(Context context, String key, boolean value) {
        sharedPreferences = getSharedPreferences(context);
        editor = sharedPreferences.edit();
        editor.putBoolean(key, value).commit();
        return editor;
    }


    public static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(Config.MY_PREF, 0);
    }




    public static SharedPreferences.Editor putValueInEditor(Context context) {
        sharedPreferences = getSharedPreferences(context);
        editor = sharedPreferences.edit();
        return editor;
    }


//
//    public static boolean isInternetAvailable(Context context) {
//        NetworkInfo info = (NetworkInfo) ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
//
//        if (info == null) {
//            Log.d(TAG, "no internet connection");
//            return false;
//        } else {
//            if (info.isConnected()) {
//                Log.d(TAG, " internet connection available...");
//                return true;
//            } else {
//                Log.d(TAG, " internet connection");
//                return true;
//            }
//
//        }
//
//
//
//    }

}
