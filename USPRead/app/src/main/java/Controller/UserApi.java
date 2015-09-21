package Controller;

/**
 * Created by Akshay on 9/7/2015.
 */


import android.util.Log;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Deepak on 1/03/2015.
 */
public class UserApi {

    private String TAG = "user functions";

    private JsonParser jsonParser;

    //  private static String loginURL = "http://scims.usp.ac.fj/~S11074661/loginsff/index.php";
    //  private static String registerURL = "http://scims.usp.ac.fj/~S11074661/loginsff/index.php";
    private static String URL = "http://10.0.2.2/USPRead/index2.php";
    //private static String URL = "http://scims.usp.ac.fj/~S11079253/bloodregistry/index2.php";

    private static String register_tag = "register";
    private static String login_tag = "login";





    public UserApi() {
        jsonParser = new JsonParser();

    }

    public JSONObject registerUser(String username,String email, String password,String gender,
                                   String dob, String town) {
        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag", register_tag));
        params.add(new BasicNameValuePair("u_email", email));
        params.add(new BasicNameValuePair("u_pass", password));
        params.add(new BasicNameValuePair("u_gender", gender));
        params.add(new BasicNameValuePair("u_dob", dob));
        params.add(new BasicNameValuePair("u_town", town));
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

    public JSONObject StudentOnLoan(String user_Id) {
        //String loan = "stud_onloan";
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag", "student_onloan"));
        params.add(new BasicNameValuePair("user_id",user_Id ));

        // getting JSON Object
        JSONObject json = jsonParser.getJSONFromUrl(URL, params);
        // return json
        return json;

    }
    public JSONObject StudentReserve(String user_Id) {
        //String loan = "stud_onloan";
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag", "student_reserve"));
        params.add(new BasicNameValuePair("user_id",user_Id ));

        // getting JSON Object
        JSONObject json = jsonParser.getJSONFromUrl(URL, params);
        // return json
        return json;

    }
    public JSONObject StudentDue(String user_Id) {
        //String loan = "stud_onloan";
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag", "student_due"));
        params.add(new BasicNameValuePair("user_id",user_Id ));

        // getting JSON Object
        JSONObject json = jsonParser.getJSONFromUrl(URL, params);
        // return json
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

    public JSONObject GetFbcGraph(String user_id) {
        // Building Parameters

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag", "fbc_graph"));
        params.add(new BasicNameValuePair("user_id", user_id));


        JSONObject json = jsonParser.getJSONFromUrl(URL, params);

        // return json
        // Log.e("JSON", json.toString());
        Log.i("return json", TAG);
        return json;
    }


    public JSONObject Video1() {
        String video = "video";
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag", "video"));
        params.add(new BasicNameValuePair("video", video));

        // getting JSON Object
        JSONObject json = jsonParser.getJSONFromUrl(URL, params);
        // return json
        return json;

    }

    public JSONObject Video() {
        // Building Parameters
        String user_id = "user_id";
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag", "student_onloan"));
        params.add(new BasicNameValuePair("user_id", user_id));


        JSONObject json = jsonParser.getJSONFromUrl(URL, params);

        // return json
        // Log.e("JSON", json.toString());
        Log.i("return json", TAG);
        return json;
    }


}
