package in.co.gstsamadhan.gstsamadhan.Session;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

import in.co.gstsamadhan.gstsamadhan.MainActivity;

public class SessionManager {
    SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    public Context context;
    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "LOGIN";
    private static final String LOGIN = "IS_LOGIN";
    public static final String CLIENT_ID = "CLIENT_ID";
    public static final String CLIENT_EMAIL = "CLIENT_EMAIL";
    public static final String CLIENT_NAME = "CLIENT_NAME";
    public static final String CLIENT_MOBILE = "CLIENT_MOBILE";
    public static final String CLIENT_PLAN = "CLIENT_PLAN";
    // public static final String ID = "ID";

    public SessionManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    public void createSession(String client_id,String client_name,String client_email,String client_mobile,String Client_plan){

        editor.putBoolean(LOGIN, true);
        editor.putString(CLIENT_ID, client_id);
        editor.putString(CLIENT_EMAIL, client_email);
        editor.putString(CLIENT_NAME, client_name);
        editor.putString(CLIENT_MOBILE, client_mobile);
        editor.putString(CLIENT_PLAN,Client_plan);
        editor.apply();

    }

    public boolean isLoggin(){
        return sharedPreferences.getBoolean(LOGIN, false);
    }



    public HashMap<String, String> getUserDetail(){
        HashMap<String, String> user = new HashMap<>();
        user.put(CLIENT_ID, sharedPreferences.getString(CLIENT_ID, null));
        user.put(CLIENT_NAME, sharedPreferences.getString(CLIENT_NAME, null));
        user.put(CLIENT_EMAIL, sharedPreferences.getString(CLIENT_EMAIL, null));
        user.put(CLIENT_MOBILE, sharedPreferences.getString(CLIENT_MOBILE, null));
        user.put(CLIENT_PLAN, sharedPreferences.getString(CLIENT_PLAN, null));
        return user;
    }
    public void update(String client_id,String client_name,String client_email,String client_mobile,String client_plan){
        editor.clear();
        editor.commit();
        editor.putBoolean(LOGIN, true);
        editor.putString(CLIENT_ID, client_id);
        editor.putString(CLIENT_EMAIL, client_email);
        editor.putString(CLIENT_NAME, client_name);
        editor.putString(CLIENT_MOBILE, client_mobile);
        editor.putString(CLIENT_PLAN,client_plan);
        editor.apply();
    }
    public void logout(){
        editor.clear();
        editor.commit();
        Intent i = new Intent(context, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }

}