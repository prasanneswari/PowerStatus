package com.jts.root.power_status;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import static com.jts.root.power_status.Power.mToastToShow;

public class Login extends AppCompatActivity {
    static String usernameS, pwdS, eid1, sessionid;
    Intent intent;
    Dialog popup;
    String jsonS, jES, JobRoleS, url1;
    Spinner JobRole;
    private ProgressDialog dialog_progress;
    AlertDialog.Builder builderLoading;
    JSONObject rmdt, jEmp = null;
    EditText usernameE, pwdE;
    Button Btnlogin;
    TextView newuserT;
    static String idS;
    String[] idA, eNameA, usermenuA, eRoleA, prdA, qtyA;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usernameE = (EditText) findViewById(R.id.usernameid);
        pwdE = (EditText) findViewById(R.id.pwdid);
        Btnlogin = (Button) findViewById(R.id.btnid);
        newuserT = (TextView) findViewById(R.id.newpwdid);
        dialog_progress = new ProgressDialog(Login.this);
        builderLoading = new AlertDialog.Builder(Login.this);
       newuserT.setPaintFlags(newuserT.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        Btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (usernameE.getText().toString().trim().length() == 0) {
                    usernameE.setError("Username is not entered");
                    usernameE.requestFocus();
                }
                if (pwdE.getText().toString().trim().length() == 0) {
                    pwdE.setError("Password is not entered");
                    pwdE.requestFocus();
                }
                else {
                    Intent intent = new Intent(Login.this, Power.class);
                    startActivity(intent);
                }
            }
        });

    }
}