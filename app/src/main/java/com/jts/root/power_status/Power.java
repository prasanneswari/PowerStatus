package com.jts.root.power_status;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class Power extends AppCompatActivity {
    private SeekBar seekBar;
    private TextView textView,pwmtxt;
    Button imagebtn1,imagebtn2,imagebtn3,imagebtn4,imagebtn5,imagebtn6,imagebtn7,imagebtn8;
    int[] array = {0,0,0,0,0,0,0,0};
    Switch simpleSwitch;
    RequestQueue requestQueue;
    private Timer timer = new Timer();
    boolean b1,b2,b3,b4,b5,b6,b7,b8,bs1,bs2,bs3,bs4,bs5,bs6,bs7,bs8,sw1;
    String val;
    RequestQueue sch_RequestQueue;
     static Toast mToastToShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power);
        seekBar = (SeekBar) findViewById(R.id.seekBar1);
        textView = (TextView) findViewById(R.id.textView1);
        pwmtxt = (TextView) findViewById(R.id.textpwm);
        imagebtn1 = (Button) findViewById(R.id.imagebtn1);
        imagebtn2 = (Button) findViewById(R.id.imagebtn2);
        imagebtn3 = (Button) findViewById(R.id.imagebtn3);
        imagebtn4 = (Button) findViewById(R.id.imagebtn4);
        imagebtn5 = (Button) findViewById(R.id.imagebtn5);
        imagebtn6 = (Button) findViewById(R.id.imagebtn6);
        imagebtn7 = (Button) findViewById(R.id.imagebtn7);
        imagebtn8 = (Button) findViewById(R.id.imagebtn8);

        simpleSwitch = (Switch) findViewById(R.id.simpleSwitch1);



        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Log.d("runableeeeeeeeeee","\n");

                last_voltage();

               /* for(int i=0; i<array.length;i++)
                {
                    if(array[i] == 1)
                    {
                        //call url string by passing parameters
                        if (array[0]==1){
                            String url = "http://192.168.173.200/Temp";
                            Log.d("temp url11","\n"+url);
                            sendRequestToServer(url);
                            //Toast.makeText(getApplicationContext(), url, Toast.LENGTH_SHORT).show();
                        }
                        else if (array[1]==1){
                            String url= "http://192.168.173.201/Temp";
                            Log.d("temp url222","\n"+url);
                            sendRequestToServer(url);
                            //Toast.makeText(getApplicationContext(), url, Toast.LENGTH_SHORT).show();


                        }
                        else if (array[2]==1){
                            String url = "http://192.168.173.202/Temp";
                            Log.d("temp url333","\n"+url);
                            sendRequestToServer(url);
                            //Toast.makeText(getApplicationContext(), url, Toast.LENGTH_SHORT).show();

                        }
                        else if (array[3]==1){
                            String url = "http://192.168.173.203/Temp";
                            Log.d("temp url4444","\n"+url);
                            sendRequestToServer(url);
                            // Toast.makeText(getApplicationContext(), url, Toast.LENGTH_SHORT).show();
                        }
                    }
                }*/
            }
        }, 0, 10000);//5 seconds



        simpleSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                if (isChecked) {
                    // do something when check is selected
                    sw1=true;



                    /*if(val!=null) {

                        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Power.this);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("on" + val, "on");

                    }
                    else{
                        new AlertDialog.Builder(Power.this)
                                .setTitle("Alert")
                                .setMessage("Please select the button")
                                .setPositiveButton("ok", null)
                                .show();
                    }*/
                } else {
                    //do something when unchecked
                    sw1=false;

                    /*if(val!=null) {
                        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Power.this);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("on" + val, "off");
                    }
                    else{

                        new AlertDialog.Builder(Power.this)
                                .setTitle("Alert")
                                .setMessage("Please select the button")
                                .setPositiveButton("ok", null)
                                .show();

                    }*/

                }

                switch_on();

            }
        });

        imagebtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                val="1";
                if(array[0]==0){
                     array[0] = 1; // ON
                    imagebtn1.setBackgroundResource(R.drawable.unnamed);
                    imagebtn2.setBackgroundResource(R.drawable.button);
                    imagebtn3.setBackgroundResource(R.drawable.button);
                    imagebtn4.setBackgroundResource(R.drawable.button);
                    imagebtn5.setBackgroundResource(R.drawable.button);
                    imagebtn6.setBackgroundResource(R.drawable.button);
                    imagebtn7.setBackgroundResource(R.drawable.button);
                    imagebtn8.setBackgroundResource(R.drawable.button);
                    b1=true;
                    array[1]=0;
                    array[2]=0;
                    array[3]=0;
                    array[4]=0;
                    array[5]=0;
                    array[6]=0;
                    array[7]=0;

                    last_voltage();

                    if(bs1==true){
                        simpleSwitch.setChecked(true);
                    }
                    else{
                        simpleSwitch.setChecked(false);
                    }
                }
                else if(array[0] == 1)
                {
                    array[0] = 0; //OFF
                    imagebtn1.setBackgroundResource(R.drawable.button);
                    array[1]=1;
                    array[2]=1;
                    array[3]=1;
                    array[4]=1;
                    array[5]=1;
                    array[6]=1;
                    array[7]=1;
                    b1=false;
                }
               /* SharedPreferences preferences1 = PreferenceManager.getDefaultSharedPreferences(Power.this);
                String sval = preferences1.getString("on" + val, "on");

                Log.d("REQUEST","\n"+sval);

                if(sval.equals("on")){
                    simpleSwitch.setChecked(true);
                }
                else{
                    simpleSwitch.setChecked(false);
                }*/
                //Toast.makeText(getApplicationContext(),"btn1",Toast.LENGTH_LONG).show();// display the toast on home button click
            }
        });
        imagebtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                val="2";
                if(array[1]==0){
                    array[1] = 1; // ON
                    imagebtn2.setBackgroundResource(R.drawable.unnamed);
                    imagebtn3.setBackgroundResource(R.drawable.button);
                    imagebtn1.setBackgroundResource(R.drawable.button);
                    imagebtn4.setBackgroundResource(R.drawable.button);
                    imagebtn5.setBackgroundResource(R.drawable.button);
                    imagebtn6.setBackgroundResource(R.drawable.button);
                    imagebtn7.setBackgroundResource(R.drawable.button);
                    imagebtn8.setBackgroundResource(R.drawable.button);
                    array[0]=0;
                    array[2]=0;
                    array[3]=0;
                    array[4]=0;
                    array[5]=0;
                    array[6]=0;
                    array[7]=0;
                    last_voltage();
                    b2=true;
                    if(bs2==true){
                        simpleSwitch.setChecked(true);
                    }
                    else{
                        simpleSwitch.setChecked(false);
                    }
                }
                else if(array[1] == 1)
                {
                    array[1] = 0; //OFF
                    imagebtn2.setBackgroundResource(R.drawable.button);
                    //simpleSwitch.setChecked(false);
                    array[0]=1;
                    array[2]=1;
                    array[3]=1;
                    array[4]=1;
                    array[5]=1;
                    array[6]=1;
                    array[7]=1;

                    b2=false;
                }
                /*SharedPreferences preferences1 = PreferenceManager.getDefaultSharedPreferences(Power.this);
                String sval = preferences1.getString("on" + val, "on");


                if(sval.equals("on")){
                    simpleSwitch.setChecked(true);
                }
                else{
                    simpleSwitch.setChecked(false);
                }*/
                //Toast.makeText(getApplicationContext(),"btn2",Toast.LENGTH_LONG).show();// display the toast on you tube button click
            }
        });
        imagebtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //seekBar.setProgress(Integer.parseInt("80"));
                val="3";

                    if (array[2] == 0) {
                        array[2] = 1; // ON
                        imagebtn3.setBackgroundResource(R.drawable.unnamed);
                        imagebtn1.setBackgroundResource(R.drawable.button);
                        imagebtn2.setBackgroundResource(R.drawable.button);
                        imagebtn4.setBackgroundResource(R.drawable.button);
                        imagebtn5.setBackgroundResource(R.drawable.button);
                        imagebtn6.setBackgroundResource(R.drawable.button);
                        imagebtn7.setBackgroundResource(R.drawable.button);
                        imagebtn8.setBackgroundResource(R.drawable.button);
                        array[1]=0;
                        array[0]=0;
                        array[3]=0;
                        array[4]=0;
                        array[5]=0;
                        array[6]=0;
                        array[7]=0;
                        last_voltage();
                        b3 = true;
                        if(bs3==true){
                            simpleSwitch.setChecked(true);
                        }
                        else{
                            simpleSwitch.setChecked(false);
                        }

                    }

                else if(array[2] == 1) {
                    array[2] = 0; //OFF
                    imagebtn3.setBackgroundResource(R.drawable.button);
                    //simpleSwitch.setChecked(false);
                        array[0]=1;
                        array[1]=1;
                        array[3]=1;
                        array[4]=1;
                        array[5]=1;
                        array[6]=1;
                        array[7]=1;
                        b3=false;
                    }
               /* SharedPreferences preferences1 = PreferenceManager.getDefaultSharedPreferences(Power.this);
                String sval = preferences1.getString("on" + val, "on");
                Log.d("button3333","\n"+sval);


                if(sval.equals("on")){
                    simpleSwitch.setChecked(true);
                }
                else{
                    simpleSwitch.setChecked(false);
                }*/
                //Toast.makeText(getApplicationContext(),"btn3",Toast.LENGTH_LONG).show();// display the toast on you tube button click
            }
        });

        imagebtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (array[3] == 0) {
                    array[3] = 1; // ON
                    imagebtn5.setBackgroundResource(R.drawable.button);
                    imagebtn4.setBackgroundResource(R.drawable.unnamed);
                    imagebtn1.setBackgroundResource(R.drawable.button);
                    imagebtn2.setBackgroundResource(R.drawable.button);
                    imagebtn3.setBackgroundResource(R.drawable.button);
                    imagebtn6.setBackgroundResource(R.drawable.button);
                    imagebtn7.setBackgroundResource(R.drawable.button);
                    imagebtn8.setBackgroundResource(R.drawable.button);
                    array[0]=0;
                    array[1]=0;
                    array[2]=0;
                    array[4]=0;
                    array[5]=0;
                    array[6]=0;
                    array[7]=0;
                    b4=true;
                    last_voltage();
                    if(bs4==true){
                        simpleSwitch.setChecked(true);
                    }
                    else{
                        simpleSwitch.setChecked(false);
                    }
                }

                else if(array[3] == 1) {
                    array[3] = 0; //OFF
                    imagebtn4.setBackgroundResource(R.drawable.button);
                    array[0]=1;
                    array[1]=1;
                    array[2]=1;
                    array[4]=1;
                    array[5]=1;
                    array[6]=1;
                    array[7]=1;
                    b4=false;

                }
            }
        });
        imagebtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (array[4] == 0) {
                    array[4] = 1; // ON
                    imagebtn5.setBackgroundResource(R.drawable.unnamed);
                    imagebtn4.setBackgroundResource(R.drawable.button);
                    imagebtn1.setBackgroundResource(R.drawable.button);
                    imagebtn2.setBackgroundResource(R.drawable.button);
                    imagebtn3.setBackgroundResource(R.drawable.button);
                    imagebtn6.setBackgroundResource(R.drawable.button);
                    imagebtn7.setBackgroundResource(R.drawable.button);
                    imagebtn8.setBackgroundResource(R.drawable.button);

                    array[5]=0;
                    array[6]=0;
                    array[7]=0;
                    array[0]=0;
                    array[1]=0;
                    array[2]=0;
                    array[3]=0;
                    b5=true;
                    last_voltage();
                    if(bs5==true){
                        simpleSwitch.setChecked(true);
                    }
                    else{
                        simpleSwitch.setChecked(false);
                    }
                }

                else if(array[4] == 1) {
                    array[4] = 0; //OFF
                    imagebtn5.setBackgroundResource(R.drawable.button);
                    array[5]=1;
                    array[6]=1;
                    array[7]=1;
                    array[0]=1;
                    array[1]=1;
                    array[2]=1;
                    array[3]=1;
                    b5=false;

                }
            }
        });
        imagebtn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (array[5] == 0) {
                    array[5] = 1; // ON
                    imagebtn6.setBackgroundResource(R.drawable.unnamed);
                    imagebtn1.setBackgroundResource(R.drawable.button);
                    imagebtn2.setBackgroundResource(R.drawable.button);
                    imagebtn3.setBackgroundResource(R.drawable.button);
                    imagebtn5.setBackgroundResource(R.drawable.button);
                    imagebtn4.setBackgroundResource(R.drawable.button);
                    imagebtn7.setBackgroundResource(R.drawable.button);
                    imagebtn8.setBackgroundResource(R.drawable.button);

                    array[6]=0;
                    array[7]=0;
                    array[0]=0;
                    array[1]=0;
                    array[2]=0;
                    array[3]=0;
                    array[4]=0;
                    b6=true;
                    last_voltage();
                    if(bs6==true){
                        simpleSwitch.setChecked(true);
                    }
                    else{
                        simpleSwitch.setChecked(false);
                    }
                }

                else if(array[5] == 1) {
                    array[5] = 0; //OFF
                    imagebtn6.setBackgroundResource(R.drawable.button);
                    array[6]=1;
                    array[7]=1;
                    array[0]=1;
                    array[1]=1;
                    array[2]=1;
                    array[3]=1;
                    array[4]=1;
                    b6=false;

                }
            }
        });
        imagebtn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (array[6] == 0) {
                    array[6] = 1; // ON
                    imagebtn7.setBackgroundResource(R.drawable.unnamed);
                    imagebtn1.setBackgroundResource(R.drawable.button);
                    imagebtn2.setBackgroundResource(R.drawable.button);
                    imagebtn3.setBackgroundResource(R.drawable.button);
                    imagebtn5.setBackgroundResource(R.drawable.button);
                    imagebtn6.setBackgroundResource(R.drawable.button);
                    imagebtn4.setBackgroundResource(R.drawable.button);
                    imagebtn8.setBackgroundResource(R.drawable.button);

                    array[7]=0;
                    array[0]=0;
                    array[1]=0;
                    array[2]=0;
                    array[3]=0;
                    array[4]=0;
                    array[5]=0;
                    b7=true;
                    last_voltage();
                    if(bs7==true){
                        simpleSwitch.setChecked(true);
                    }
                    else{
                        simpleSwitch.setChecked(false);
                    }
                }

                else if(array[6] == 1) {
                    array[6] = 0; //OFF
                    imagebtn7.setBackgroundResource(R.drawable.button);
                    array[7]=1;
                    array[0]=1;
                    array[1]=1;
                    array[2]=1;
                    array[3]=1;
                    array[4]=1;
                    array[5]=1;
                    b7=false;

                }
            }
        });
        imagebtn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (array[7] == 0) {
                    array[7] = 1; // ON
                    imagebtn8.setBackgroundResource(R.drawable.unnamed);
                    imagebtn1.setBackgroundResource(R.drawable.button);
                    imagebtn2.setBackgroundResource(R.drawable.button);
                    imagebtn3.setBackgroundResource(R.drawable.button);
                    imagebtn5.setBackgroundResource(R.drawable.button);
                    imagebtn6.setBackgroundResource(R.drawable.button);
                    imagebtn7.setBackgroundResource(R.drawable.button);
                    imagebtn4.setBackgroundResource(R.drawable.button);
                    array[0]=0;
                    array[1]=0;
                    array[2]=0;
                    array[3]=0;
                    array[4]=0;
                    array[5]=0;
                    array[6]=0;
                    b8=true;
                    last_voltage();
                    if(bs8==true){
                        simpleSwitch.setChecked(true);
                    }
                    else{
                        simpleSwitch.setChecked(false);
                    }
                }

                else if(array[7] == 1) {
                    array[7] = 0; //OFF
                    imagebtn8.setBackgroundResource(R.drawable.button);
                    array[0]=1;
                    array[1]=1;
                    array[2]=1;
                    array[3]=1;
                    array[4]=1;
                    array[5]=1;
                    array[6]=1;
                    b8=false;

                }
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                progress = progresValue;
                textView.setText(String.valueOf(progress));
                //Toast.makeText(getApplicationContext(), "Changing seekbar's progress", Toast.LENGTH_SHORT).show();

            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //Toast.makeText(getApplicationContext(), "Started tracking seekbar", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                textView.setText( progress + "/" + seekBar.getMax());
                //Toast.makeText(getApplicationContext(), "Stopped tracking seekbar", Toast.LENGTH_SHORT).show();

                for(int i=0; i<array.length;i++)
                {
                    Log.d("entering to pwm......","\n");
                    if(array[i] == 1)
                    {
                        //call url string by passing parameters
                        if (array[0]==1){
                            String url = "http://192.168.1.200/PWM="+progress;
                            Log.d("URL111","\n"+url);
                            getRequest(url);
                            //Toast.makeText(getApplicationContext(), url, Toast.LENGTH_SHORT).show();
                        }
                        else if (array[1]==1){
                            String url= "http://192.168.1.201/PWM="+progress;
                            Log.d("URL222","\n"+url);
                            getRequest(url);
                            //Toast.makeText(getApplicationContext(), url, Toast.LENGTH_SHORT).show();
                        }
                       else if (array[2]==1){
                            String url = "http://192.168.1.202/PWM="+progress;
                            Log.d("URL333","\n"+url);
                            getRequest(url);
                            //Toast.makeText(getApplicationContext(), url, Toast.LENGTH_SHORT).show();
                        }
                       else if (array[3]==1){
                            String url = "http://192.168.1.203/PWM="+progress;
                            Log.d("URL4444","\n"+url);
                            getRequest(url);
                           // Toast.makeText(getApplicationContext(), url, Toast.LENGTH_SHORT).show();
                        }
                        else if (array[4]==1){
                            String url = "http://192.168.1.204/PWM="+progress;
                            Log.d("URL5555","\n"+url);
                            getRequest(url);
                            // Toast.makeText(getApplicationContext(), url, Toast.LENGTH_SHORT).show();
                        }
                        else if (array[5]==1){
                            String url = "http://192.168.1.205/PWM="+progress;
                            Log.d("URL6666","\n"+url);
                            getRequest(url);
                            // Toast.makeText(getApplicationContext(), url, Toast.LENGTH_SHORT).show();
                        }else if (array[6]==1){
                            String url = "http://192.168.1.206/PWM="+progress;
                            Log.d("URL7777","\n"+url);
                            getRequest(url);
                            // Toast.makeText(getApplicationContext(), url, Toast.LENGTH_SHORT).show();
                        }else if (array[7]==1){
                            String url = "http://192.168.1.207/PWM="+progress;
                            Log.d("URL8888","\n"+url);
                            getRequest(url);
                            // Toast.makeText(getApplicationContext(), url, Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

    }
    @Override
    public void onDestroy()
    {
        super.onDestroy();
    }

    public void last_voltage() {
        String bval = null;

        for (int i = 0; i < array.length; i++) {
            if (array[i] == 1) {
                //call url string by passing parameters
                if (array[0] == 1) {
                    bval = "1";
                    //Toast.makeText(getApplicationContext(), url, Toast.LENGTH_SHORT).show();
                } else if (array[1] == 1) {
                    bval = "2";
                    //Toast.makeText(getApplicationContext(), url, Toast.LENGTH_SHORT).show();
                } else if (array[2] == 1) {
                    bval = "3";
                    //Toast.makeText(getApplicationContext(), url, Toast.LENGTH_SHORT).show();

                } else if (array[3] == 1) {
                    bval = "4";
                    // Toast.makeText(getApplicationContext(), url, Toast.LENGTH_SHORT).show();
                }
                else if (array[4] == 1) {
                    bval = "5";
                    // Toast.makeText(getApplicationContext(), url, Toast.LENGTH_SHORT).show();
                }
                else if (array[5] == 1) {
                    bval = "6";
                    // Toast.makeText(getApplicationContext(), url, Toast.LENGTH_SHORT).show();
                }
                else if (array[6] == 1) {
                    bval = "7";
                    // Toast.makeText(getApplicationContext(), url, Toast.LENGTH_SHORT).show();
                }
                else if (array[7] == 1) {
                    bval = "8";
                    // Toast.makeText(getApplicationContext(), url, Toast.LENGTH_SHORT).show();
                }


                String voltageS = "{\"username\":\"admin\",\"password\":\"admin\",\"Power_Id\":\"" + bval + "\"}";
                //String add_posts = "{\"orderid\":\"" + lID + "\"}";
                Log.d("jsnresponse statusS", "---" + voltageS);
                //trigger[asycount]=new SendJsonDataToServer1().execute(String.valueOf(water_status));
                //String room_ssid_url = "http://cld003.jts-prod.in:5906/AssetTrackerApp/get_rooms_ssid/";
                String url = "http://cld003.jts-prod.in:5910/PowerApp/get_last_voltage/";
                // String urlrs= "https://jtsha.in/service/validate_web";
                JSONObject lstrmdt = null;
                // try {
                try {
                    lstrmdt = new JSONObject(voltageS);
                } catch (JSONException e) {

                }
                Log.d("jsnresponse....", "---" + voltageS);
                JSON_VOLATGE(url, lstrmdt);
            }
        }
    }
    public void JSON_VOLATGE(String url, final JSONObject json)
    {
        Log.d("save_url-", "---"+url);
        Log.d("555555", "00000000"+json.toString());
        //RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                url, json,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.d("----New Data Response-----", "---"+response.toString());

                        try {
                            JSONArray new_array1;
                            String [] voltage;
                            List<String> voltageL = new ArrayList<String>();
                            new_array1 = response.getJSONArray("get_last_details");
                            Log.d(" Array", " : " + new_array1);

                            for (int i = 0, count = new_array1.length(); i < count; i++){
                                String voltageS = new_array1.getJSONObject(i).getString("voltage");
                                voltageL.add(voltageS);

                            }
                            voltage = new String[voltageL.size()];

                            for (int l = 0; l < voltageL.size(); l++) {

                                voltage[l] = voltageL.get(l);
                                Log.d("eName ", voltage[l]);
                                pwmtxt.setText(voltage[l]);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            int new_data = response.getInt("error_code");
                            String er_discp=response.getString("error_desc");
                            String[] newdata = er_discp.split("=");
                            if(new_data==0){

                               /* int toastDurationInMilliSeconds = 1000;
                                mToastToShow = Toast.makeText(getApplicationContext(), newdata[1], Toast.LENGTH_LONG);
                                //Toast.makeText(getApplicationContext(), newdata[1], Toast.LENGTH_LONG).show();

                                // Set the countdown to display the toast
                                CountDownTimer toastCountDown;
                                toastCountDown = new CountDownTimer(toastDurationInMilliSeconds, 1000 *//*Tick duration*//*) {
                                    public void onTick(long millisUntilFinished) {
                                        mToastToShow.show();
                                    }
                                    public void onFinish() {
                                        mToastToShow.cancel();
                                    }
                                };

                                // Show the toast and starts the countdown
                                mToastToShow.show();
                                toastCountDown.start();*/
                                final Toast toast = Toast.makeText(getApplicationContext(), newdata[1], Toast.LENGTH_LONG);

                                toast.show();

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        toast.cancel();
                                    }
                                }, 500);
                            }
                            //else

                           // mToastToShow = Toast.makeText(getApplicationContext(), newdata[1], Toast.LENGTH_LONG);
                            //mToastToShow.cancel();

                            //Toast.makeText(getApplicationContext(), newdata[1], Toast.LENGTH_LONG).show();

                        } catch (JSONException e) {

                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(" ", "Error: " + error.getMessage());
                //Toast.makeText(getApplicationContext(), "connection error ", Toast.LENGTH_LONG).show();
            }
        }) {
            /**
             * Passing some request headers
             * */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };
        // Adding request to request queue
        // jsonObjReq.setTag("");
        addtoreqvoltage(jsonObjReq);
    }
    public <T> void addtoreqvoltage(Request<T> req) {
        if (sch_RequestQueue == null) {
            sch_RequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        sch_RequestQueue.add(req);
        //getRequestQueue().add(req);
    }


    public void switch_on() {
        String url=null;
        RequestQueue queue = Volley.newRequestQueue(this);
        if(array[0]==1){
            if(sw1==true){
                url="http://192.168.1.200/ON";
                bs1=true;
            }else{
                bs1=false;
                url="http://192.168.1.200/OFF";
            }

        }

        else if(array[1]==1){

            if(sw1==true){
                bs2=true;
                url="http://192.168.1.201/ON";
            }else{
                bs2=false;
                url="http://192.168.1.201/OFF";
            }
        }

        else if(array[2]==1 ){
            if(sw1==true){
                bs3=true;
                url="http://192.168.1.202/ON";
            }else{
                bs3=false;
                url="http://192.168.1.202/OFF";
            }

        }

        else if(array[3]==1 ){
            if(sw1==true){
                bs4=true;
                url="http://192.168.1.203/ON";
            }else{
                bs4=false;
                url="http://192.168.1.203/OFF";
            }
        }

        else if(array[4]==1 ){
            if(sw1==true){
                bs5=true;
                url="http://192.168.1.204/ON";
            }else{
                bs5=false;
                url="http://192.168.1.204/OFF";
            }
        }
        else if(array[5]==1 ){
            if(sw1==true){
                bs6=true;
                url="http://192.168.1.205/ON";
            }else{
                bs6=false;
                url="http://192.168.1.205/OFF";
            }
        }
        else if(array[6]==1 ){
            if(sw1==true){
                bs7=true;
                url="http://192.168.1.206/ON";
            }else{
                bs7=false;
                url="http://192.168.1.206/OFF";
            }
        }
        else if(array[7]==1 ){
            if(sw1==true){
                bs8=true;
                url="http://192.168.1.207/ON";
            }else{
                bs8=false;
                url="http://192.168.1.207/OFF";
            }
        }


        try {
        Log.d("sending switch on url :", url.toString());
        // Request a string response from the provided URL.

           StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                   new Response.Listener<String>() {
                       @Override
                       public void onResponse(String response) {
                           // Display the first 500 characters of the response string.
                           try {
                               Log.d(" response switch on:", response.toString());
                           }
                           catch (NullPointerException e){

                           }
                       }
                   }, new Response.ErrorListener() {
               @Override
               public void onErrorResponse(VolleyError error) {
                   //mTextView.setText("That didn't work!");
                   Log.d("hello1 ", "error.......");
               }
           });
           queue.add(stringRequest);

       }
       catch (NullPointerException e){

       }
        // Add the request to the RequestQueue.
    }
    public void sendRequestToServer(final String url) {
        RequestQueue queue = Volley.newRequestQueue(this);
        //String url="http://192.168.173.200/Temp";
        Log.d("sending string is :", url.toString());
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Log.d("hello response :", response.toString());
                        Log.d("Analog firing value123 ",response.replaceAll("\\<[^>]*>",""));

                        String analogval=response.replaceAll("\\<[^>]*>","");
                        pwmtxt.setText(analogval);
                        JSONObject responseJSON = null;
                        JSONObject jsonReq;
                            try {
                                int login_code = responseJSON.getInt("error_code");
                                String er_discp=responseJSON.getString("error_desc");

                                String[] separated = er_discp.split("=");
                                if(login_code==0){
                                    //Toast.makeText(getApplicationContext(), separated[1], Toast.LENGTH_LONG).show();
                                }else {
                                   // Toast.makeText(getApplicationContext(), separated[1], Toast.LENGTH_LONG).show();
                                }
                                } catch (JSONException e) {

                            }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //mTextView.setText("That didn't work!");
                Log.d("hello1 ","error.......");
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
    public void getRequest(final String url)
    {
        Log.d("REQUEST ..........","\n"+url);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("RESPONSE","\n"+ response);
                Log.d("Index of 1st", String.valueOf(response.indexOf('=')));
                Log.d("Index of 2nd", String.valueOf(response.indexOf("</")));
                Log.d("Analog value is ",response.substring(response.indexOf('=')+1,response.indexOf("</")));
                Log.d("Analog value is1111 ",response.replaceAll("\\<[^>]*>",""));

                String analogval=response.replaceAll("\\<[^>]*>","");
                //String analogval=response.substring(response.indexOf("\\<.*?\\>", Integer.parseInt("")));
                pwmtxt.setText(analogval);

               String  getRequest1=("http://cld003.jts-prod.in:20101/add_voltagevalues?voltagevalue="+response.substring(response.indexOf('=')+1,response.indexOf("</")));
                //getRequest1(getRequest1);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Write a Log to check error
            }
        })
        {
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<String, String>();
                MyData.put("Field", "Value"); //Add the data you'd like to send to the server.
                return MyData;
            }
        };
        addToRequestQueue(stringRequest);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        if (requestQueue == null)
        {
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        requestQueue.add(req);
    }
    public void getRequest1(final String getRequest1)
    {
        Log.d("REQUEST","\n"+getRequest1);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, getRequest1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("RESPONSE","\n"+ response);
                /*Log.d("Index of 1st", String.valueOf(response.indexOf('=')));
                Log.d("Index of 2nd", String.valueOf(response.indexOf("</")));
                Log.d("Analog value is ",response.substring(response.indexOf('=')+1,response.indexOf("</")));

                getRequest1("http://cld003.jts-prod.in:20101/add_voltagevalues?voltagevalue="+response.substring(response.indexOf('=')+1,response.indexOf("</")));
            }*/
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                //Write a Log to check error
            }
        })
        {
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<String, String>();
                MyData.put("Field", "Value"); //Add the data you'd like to send to the server.
                return MyData;
            }
        };
        addToRequestQueue1(stringRequest);
    }
    public <T> void addToRequestQueue1(Request<T> req) {
        if (requestQueue == null)
        {
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        requestQueue.add(req);
    }
}
