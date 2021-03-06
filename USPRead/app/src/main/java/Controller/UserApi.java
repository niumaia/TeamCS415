package Controller;

import android.util.Log;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by Akshay on 9/7/2015.
 */
public class UserApi {

    private String TAG = "user functions";

    private JsonParser jsonParser;

    //  private static String loginURL = "http://scims.usp.ac.fj/~S11074661/loginsff/index.php";
    //  private static String registerURL = "http://scims.usp.ac.fj/~S11074661/loginsff/index.php";
    private static String URL = "http://10.0.2.2/USPRead/index2.php";
   // private static String URL = "http://scims.usp.ac.fj/~S11079253/bloodregistry/index2.php";

    private static String register_tag = "register";
    private static String login_tag = "login";

    public UserApi() {
        jsonParser = new JsonParser();
    }

    public JSONObject registerUser(String username,String email, String password,String gender,
                                   String dob, String town, String country) {
        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag", register_tag));
        params.add(new BasicNameValuePair("u_email", email));
        params.add(new BasicNameValuePair("u_pass", password));
        params.add(new BasicNameValuePair("u_gender", gender));
        params.add(new BasicNameValuePair("u_dob", dob));
        params.add(new BasicNameValuePair("u_town", town));
        params.add(new BasicNameValuePair("u_country", country));
        params.add(new BasicNameValuePair("u_name", username));


        // getting JSON Object
        JSONObject json = jsonParser.getJSONFromUrl(URL, params);
        Log.i("register url json ..", TAG);
        // return json
        return json;
    }

    public JSONObject loginUser(String user_id, String user_password) {
        // Building Parameters
        //Log.i("login user - user functions..", TAG);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag", login_tag));
        params.add(new BasicNameValuePair("user_id", user_id));
        params.add(new BasicNameValuePair("user_password", user_password));

        JSONObject json = jsonParser.getJSONFromUrl(URL, params);

        // return json
        // Log.e("JSON", json.toString());
        Log.i("return json", TAG);
        return json;
    }


    public JSONObject GetBloodTypeResult(String user_id) {
        // Building Parameters

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag", "blood_type_test"));
        params.add(new BasicNameValuePair("user_id", user_id));
        params.add(new BasicNameValuePair("blood_type", "blood_type"));

        JSONObject json = jsonParser.getJSONFromUrl(URL, params);

        // return json
        // Log.e("JSON", json.toString());
        Log.i("return json", TAG);
        return json;
    }

    public JSONObject GetBloodCultureResult(String user_id) {
        // Building Parameters

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag", "blood_culture_test"));
        params.add(new BasicNameValuePair("user_id", user_id));


        JSONObject json = jsonParser.getJSONFromUrl(URL, params);

        // return json
        // Log.e("JSON", json.toString());
        Log.i("return json", TAG);
        return json;
    }






    public JSONObject ForgetPassword(String user_id,String user_email) {
        // Building Parameters
        Log.i(user_id+" "+user_email, TAG);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag", "forget"));
        params.add(new BasicNameValuePair("user_id", user_id));
        params.add(new BasicNameValuePair("user_email", user_email));


        JSONObject json = jsonParser.getJSONFromUrl(URL, params);

        // return json
        // Log.e("JSON", json.toString());
        Log.i("return json", TAG);
        return json;
    }

    public JSONObject ResetPassword(String user_id,String user_email,String userFname) {
        // Building Parameters

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag", "reset_password"));
        params.add(new BasicNameValuePair("user_id", user_id));
        params.add(new BasicNameValuePair("user_email", user_email));
        params.add(new BasicNameValuePair("user_fname", userFname));


        JSONObject json = jsonParser.getJSONFromUrl(URL, params);

        // return json
        // Log.e("JSON", json.toString());
        Log.i("return json", TAG);
        return json;
    }






}
