package Controller;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;


import com.akshay.rebel.uspread.LoginActivity;
import com.akshay.rebel.uspread.MainActivity;

import java.util.HashMap;

/**
 * Created by Akshay on 9/8/2015.
 */
public class UserSessionManager {

    // Shared Preferences reference
    SharedPreferences pref;

    // Editor reference for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREFER_NAME = "BMI";

    // All Shared Preferences Keys
    private static final String IS_USER_LOGIN = "IsUserLoggedIn";

    // User name (make variable public to access from outside)
    public static final String KEY_NAME = "name";

    // Email address (make variable public to access from outside)
    public static final String KEY_EMAIL = "email";


    public static final String KEY_USERID = "userid";
    public static final String KEY_UNAME = "username";
    public static final String KEY_DOB = "user_dob";

    public static final String KEY_GENDER = "user_gender";

    public static final String KEY_TOWN = "user_town";
    public static final String KEY_COUNTRY = "user_country";

    // Constructor
    public UserSessionManager(Context context){
        this._context = context;
        Log.i("user seesion mamnger", "context");
        pref = _context.getSharedPreferences(PREFER_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    //Create login session
    public void createUserLoginSession(String email, String userid){
        // Storing login value as TRUE
        editor.putBoolean(IS_USER_LOGIN, true);

        // Storing name in pref
       // editor.putString(KEY_NAME, name);
       // Log.i("user id", name);

        // Storing email in pref
        editor.putString(KEY_EMAIL, email);


        editor.putString(KEY_USERID, userid);

       // editor.putString(KEY_UNAME, name);
       // editor.putString(KEY_DOB, dob);

       // editor.putString(KEY_GENDER, gender);
//
       // editor.putString(KEY_TOWN, town);
        //editor.putString(KEY_COUNTRY, country);




        // commit changes
        editor.commit();
    }

    /**
     * Check login method will check user login status
     * If false it will redirect user to login page
     * Else do anything
     * */
    public boolean checkLogin(){
        // Check login status
        if(!this.isUserLoggedIn()){

            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, MainActivity.class);

            // Closing all the Activities from stack
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            _context.startActivity(i);

            return true;
        }
        return false;
    }



    /**
     * Get stored session data
     * */
    public HashMap<String, String> getUserDetails(){

        //Use hashmap to store user credentials
        HashMap<String, String> user = new HashMap<String, String>();

        // user name
        user.put(KEY_NAME, pref.getString(KEY_NAME, null));

        // user email id
        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));
        user.put(KEY_USERID, pref.getString(KEY_USERID, null));

        user.put(KEY_UNAME, pref.getString(KEY_UNAME, null));
        user.put(KEY_DOB, pref.getString(KEY_DOB, null));

        user.put(KEY_GENDER, pref.getString(KEY_GENDER, null));

        user.put(KEY_TOWN, pref.getString(KEY_TOWN, null));

        user.put(KEY_COUNTRY, pref.getString(KEY_COUNTRY, null));


        // return user
        return user;
    }

    /**
     * Clear session details
     * */
    public void logoutUser(){

        // Clearing all user data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Login Activity
        Intent i = new Intent(_context, LoginActivity.class);

        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        _context.startActivity(i);
    }

    public void GoWIki()
    {

    }



    // Check for login
    public boolean isUserLoggedIn(){
        return pref.getBoolean(IS_USER_LOGIN, false);
    }






}
