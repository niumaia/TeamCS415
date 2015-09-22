package com.akshay.rebel.uspread;

/**
 * Created by Rebel on 9/16/2015.
 */

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;

import Controller.ConnectionDetector;
import Controller.UserSessionManager;


public class ClerkProfile extends Fragment {
    View view;
    TextView name,dob,healthid, gender, email,address;

    UserSessionManager session;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState)
    {
        view = inflater.inflate(R.layout.clerk_home, container, false);


        session = new UserSessionManager(getActivity().getApplicationContext());
        name = (TextView) view.findViewById(R.id.user_name);
        dob = (TextView) view.findViewById(R.id.user_dob);
        gender = (TextView) view.findViewById(R.id.user_gender);
        email = (TextView) view.findViewById(R.id.user_email);
        // country = (TextView) view.findViewById(R.id.user_country);
        address = (TextView) view.findViewById(R.id.user_add);
        healthid = (TextView) view.findViewById(R.id.user_health_id);
        // phone = (TextView) view.findViewById(R.id.user_phone);

        HashMap<String, String> user = session.getUserDetails();

        name.setText(user.get(UserSessionManager.KEY_NAME));
        dob.setText(user.get(UserSessionManager.KEY_DOB));
        email.setText(user.get(UserSessionManager.KEY_EMAIL));
        gender.setText(user.get(UserSessionManager.KEY_GENDER));
        address.setText(user.get(UserSessionManager.KEY_TOWN));
        //country.setText(user.get(UserSessionManager.KEY_COUNTRY));
        healthid.setText(user.get(UserSessionManager.KEY_USERID));
        //  phone.setText(user.get(UserSessionManager.KEY));

        return view;
    }

}