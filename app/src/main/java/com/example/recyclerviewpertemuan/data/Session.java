package com.example.recyclerviewpertemuan.data;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.recyclerviewpertemuan.DashboardActivity;
import com.example.recyclerviewpertemuan.auth.LoginActivity;

import java.net.UnknownServiceException;

public class Session {
    SharedPreferences pref;

    SharedPreferences.Editor editor;

    Context _context;

    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "restoMobpro";

    private static final String IS_LOGIN = "IsLoged";
    private static final String USER_ID = "userId";
    private static final String IS_NOT_ALARMT = "IsAlarm";

    public Session(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void checkLogin() {
        if (isLoggedIn()) {
            Intent i = new Intent(_context, DashboardActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(i);
        }
    }


    public void logoutUser() {
        editor.clear();
        editor.commit();
        editor.putBoolean(IS_LOGIN, false);
        editor.putString(USER_ID, "");
        editor.putBoolean(IS_NOT_ALARMT, true);
        setIsLogin(false);

        Intent i = new Intent(_context, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ((Activity) _context).finish();
        _context.startActivity(i);
    }

    public void clearSession() {
        editor.clear();
        editor.commit();
        editor.putBoolean(IS_LOGIN, false);
        editor.putBoolean(IS_NOT_ALARMT, true);
    }

    public boolean isLoggedIn() {
        return pref.getBoolean(IS_LOGIN, false);
    }

    public String getUserId() {
        return pref.getString(USER_ID,"no_id");
    }

    public void setIsLogin(Boolean v) {
        editor.putBoolean(IS_LOGIN, v);
        editor.commit();
    }

    public void setUserId(String v) {
        editor.putString(USER_ID, v);
        editor.commit();
    }
}
