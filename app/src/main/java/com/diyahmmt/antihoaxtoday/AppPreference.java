package com.diyahmmt.antihoaxtoday;

import android.content.Context;
import android.content.SharedPreferences;

public class AppPreference {
    private final String ONETIME_DATE = "onetimedate";
    private final String ONETIME_TIME = "onetimetime";
    private final String ONETIME_MESSAGE = "onetimemessage";
    private final String REPEATING_TIME = "repeatingtime";
    private final String REPEATING_MESSAGE = "repeatingmessage";
    private String PREF_NAME = "UserPref";
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    public AppPreference(Context context) {
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public void setONETIME_DATE(String date){
        editor.putString(ONETIME_TIME, date);
        editor.commit();
    }

    public String getONETIME_DATE() {
        return preferences.getString(ONETIME_DATE, null);
    }

    public void setONETIME_TIME(String time){
        editor.putString(ONETIME_TIME, time);
        editor.commit();
    }

    public String getONETIME_TIME() {
        return preferences.getString(ONETIME_TIME, null);
    }

    public void setONETIME_MESSAGE(String message){
        editor.putString(ONETIME_MESSAGE, message);
        editor.commit();
    }

    public String getONETIME_MESSAGE() {
        return preferences.getString(ONETIME_MESSAGE, null);
    }

    public String getREPEATING_TIME() {
        return preferences.getString(REPEATING_TIME, null);
    }

    public String getREPEATING_MESSAGE() {
        return preferences.getString(REPEATING_MESSAGE, null);
    }

    public void setRepeating(String time, String message) {
        editor.putString(REPEATING_TIME, time);
        editor.commit();
        editor.putString(REPEATING_MESSAGE, time);
        editor.commit();
    }
}
