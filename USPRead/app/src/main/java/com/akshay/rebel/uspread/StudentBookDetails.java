package com.akshay.rebel.uspread;

/**
 * Created by Rebel on 9/16/2015.
 */

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;

import Controller.ConnectionDetector;
import Controller.UserSessionManager;

import android.content.Intent;
import android.widget.Toast;

public class StudentBookDetails extends ActionBarActivity {

    View view;
    TextView res_title, res_auth, res_edit,res_cat,res_stat;
    String title,author,edition,cat,stat;
    UserSessionManager session;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState)
    {
        view = inflater.inflate(R.layout.student_reservebk, container, false);


        res_title = (TextView) view.findViewById(R.id.res_title);
        res_auth = (TextView) view.findViewById(R.id.res_auth);
        res_edit = (TextView) view.findViewById(R.id.res_edit);
        res_cat = (TextView) view.findViewById(R.id.res_cat);
        res_stat = (TextView) view.findViewById(R.id.res_stat);


       // HashMap<String, String> user = session.getClass( StudentSearch());
        Intent dash = getIntent();
        title = dash.getStringExtra("bk_title");
        author = dash.getStringExtra("bk_author");
        edition = dash.getStringExtra("bk_edition");
        cat = dash.getStringExtra("bkCatNum");
        stat = dash.getStringExtra("copyStat_desc");

        res_title.setText(title);
        Toast.makeText(getApplicationContext(), title, Toast.LENGTH_LONG).show();
        res_auth.setText(author);
        res_edit.setText(edition);
        res_cat.setText(cat);
        res_stat.setText(stat);

        return view;
    }

}