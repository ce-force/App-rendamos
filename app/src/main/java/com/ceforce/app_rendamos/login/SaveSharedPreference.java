package com.ceforce.app_rendamos.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import org.json.JSONObject;

import java.net.PasswordAuthentication;

public class SaveSharedPreference
{
    static final String DNI= "";

    static final String Password= "";

    static final String userData = "";
    static final String loginData = "";

    public static String getUserData(Context ctx) {
        return getSharedPreferences(ctx).getString(userData, "");
    }

    public static void setUserData(Context ctx, String newUserData) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(userData, newUserData);
        editor.commit();
    }

    public static String getLoginData(Context ctx) {
        return getSharedPreferences(ctx).getString(loginData, "");
    }

    public static void setLoginData(Context ctx, String newLoginData) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(loginData, newLoginData);
        editor.commit();
    }

    public static String getPassword(Context ctx) {
        return getSharedPreferences(ctx).getString(Password, "");
    }

    public static void setPassword(Context ctx, String password) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(Password, password);
        editor.commit();
    }

    public static String getDNI(Context ctx) {
        return getSharedPreferences(ctx).getString(DNI, "");
    }

    public static void setDNI(Context ctx, String password) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(DNI, password);
        editor.commit();
    }


    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static boolean IsLoged(Context ctx){
        if (getDNI(ctx).length()!=0 && getPassword(ctx).length()!=0){
            return true;
        }
        return false;
    }

    public static void CleanLogIn(Context ctx){
        setDNI(ctx,"");
        setPassword(ctx,"");
    }

}