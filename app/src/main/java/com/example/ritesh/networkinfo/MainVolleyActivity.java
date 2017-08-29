package com.example.ritesh.networkinfo;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ritesh on 6/7/17.
 */

public class MainVolleyActivity extends AppCompatActivity {


    /* for using volley refer

    preference ONE
    https://www.simplifiedcoding.net/android-login-example-using-php-mysql-and-volley/

    preference TWO
    http://www.androidhive.info/2012/01/android-login-and-registration-with-php-mysql-and-sqlite/

    * */

    @BindView(R.id.btn_volley)
    Button Btn_volley;

    @BindView(R.id.tv_volley)
    TextView TV_volley;

    @BindView(R.id.tv_status)
    TextView tv_status;

    @BindView(R.id.tv_message)
    TextView tv_message;

    @BindView(R.id.tv_user_number)
    TextView tv_user_number;

    @BindView(R.id.tv_password)
    TextView tv_password;

    @BindView(R.id.tv_request_time)
    TextView tv_request_time;

    @BindView(R.id.tv_response_time)
    TextView tv_response_time;

    @BindView(R.id.tv_user_id)
    TextView tv_user_id;

    @BindView(R.id.tv_user_name)
    TextView tv_user_name;

    @BindView(R.id.tv_user_mobile)
    TextView tv_user_mobile;

    @BindView(R.id.tv_user_email)
    TextView tv_user_email;

    @BindView(R.id.tv_user_varifed)
    TextView tv_user_varifed;

    @BindView(R.id.tv_user_image)
    TextView tv_user_image;


    String Str_userphone = "7415984946", Str_userpassword = "123456";
    String StrGet_Control = "",
            StrGet_Status = "",
            StrGet_Message = "",
            StrGet_Username = "",
            StrGet_Password = "",
            StrGet_RequestTime = "",
            StrGet_ResponseTime = "",
            StrGet_UserId = "",
            StrGet_Name = "",
            StrGet_Mobile = "",
            StrGet_Email = "",
            StrGet_MobileVerified = "",
            StrGet_UserImage = "";

    private static final String REGISTER_URL = "http://13.229.13.246:80/web-api/index.php/User/logOn";
    public static final String LOGIN_URL = "http://13.229.13.246:80/web-api/index.php/User/logOn";

    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_volley);
        ButterKnife.bind(this);

        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);


//        NetworkImageView avatar = (NetworkImageView)view.findViewById(R.id.twitter_avatar);
//        avatar.setImageUrl("http://someurl.com/image.png",mImageLoader);

        Btn_volley.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogON();

//                userLogin();

            }
        });


    }


    private void userLogON() {
        final String username = Str_userphone;
        final String password = Str_userpassword;

        // Tag used to cancel the request
        String tag_string_req = "req_login";
        pDialog.setMessage("Logging in ...");
        showDialog();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        Toast.makeText(MainVolleyActivity.this,response,Toast.LENGTH_LONG).show();
                        hideDialog();
                        Log.e("Response :", "" + response);
                        try {
                            JSONObject jobjresponse = new JSONObject(response);
                            Log.e("jobjresponse :", "" + jobjresponse);
                            JSONObject jobjControl = jobjresponse.getJSONObject("Control");
                            Log.e("jobjControl :", "" + jobjControl);

                            StrGet_Status = jobjControl.getString("Status");
                            Log.e("StrGet_Status :", "" + StrGet_Status);
                            if (StrGet_Status.equalsIgnoreCase("1")) {
                                Log.e("StrGet_Status is:", "1");

                                tv_status.setText(StrGet_Status);

                                StrGet_Message = jobjControl.getString("Message");
                                Log.e("StrGet_Message is:", "" + StrGet_Message);
                                tv_message.setText(StrGet_Message);

                                StrGet_Username = jobjControl.getString("Username");
                                Log.e("StrGet_Username is:", "" + StrGet_Username);
                                tv_user_number.setText(StrGet_Username);

                                StrGet_Password = jobjControl.getString("Password");
                                Log.e("StrGet_Password is:", "" + StrGet_Password);
                                tv_password.setText(StrGet_Password);

                                StrGet_RequestTime = jobjControl.getString("RequestTime");
                                Log.e("StrGet_RequestTime is:", "" + StrGet_RequestTime);
                                tv_request_time.setText(StrGet_RequestTime);

                                StrGet_ResponseTime = jobjControl.getString("ResponseTime");
                                Log.e("StrGet_ResponseTime is:", "" + StrGet_ResponseTime);
                                tv_response_time.setText(StrGet_ResponseTime);


                                JSONObject jobjData = jobjresponse.getJSONObject("Data");
                                Log.e("jobjData :", "" + jobjData);

                                StrGet_UserId = jobjData.getString("UserId");
                                Log.e("StrGet_UserId is:", "" + StrGet_UserId);
                                tv_user_id.setText(StrGet_UserId);

                                StrGet_Name = jobjData.getString("Name");
                                Log.e("StrGet_Name is:", "" + StrGet_Name);
                                tv_user_name.setText(StrGet_Name);

                                StrGet_Mobile = jobjData.getString("Mobile");
                                Log.e("StrGet_Mobile is:", "" + StrGet_Mobile);
                                tv_user_mobile.setText(StrGet_Mobile);

                                StrGet_Email = jobjData.getString("Email");
                                Log.e("StrGet_Email is:", "" + StrGet_Email);
                                tv_user_email.setText(StrGet_Email);

                                StrGet_MobileVerified = jobjData.getString("MobileVerified");
                                Log.e("StrGet_MobileVerified is:", "" + StrGet_MobileVerified);
                                tv_user_varifed.setText(StrGet_MobileVerified);

                                StrGet_UserImage = jobjData.getString("UserImage");
                                Log.e("StrGet_UserImage is:", "" + StrGet_UserImage);
                                tv_user_image.setText(StrGet_UserImage);


                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        TV_volley.setText(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        hideDialog();
//                        Toast.makeText(MainVolleyActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                        Log.e("Error Response :", "" + error.toString());

                        TV_volley.setText(error.toString());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Username", username);
                params.put("Password", password);
                return params;
            }

        };

        // Adding request to request queue

        /*http://www.androidhive.info/2012/01/android-login-and-registration-with-php-mysql-and-sqlite/*/
        AppController.getInstance().addToRequestQueue(stringRequest, tag_string_req);
        /*http://www.androidhive.info/2012/01/android-login-and-registration-with-php-mysql-and-sqlite/*/

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }


    private void userLogin() {
        final String username = Str_userphone;
        final String password = Str_userpassword;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.trim().equals("success")) {
                            openProfile();
                        } else {
                            Toast.makeText(MainVolleyActivity.this, response, Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainVolleyActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("Username", username);
                map.put("Password", password);
                return map;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    private void openProfile() {
        Log.e("Login Result", "Success");
    }


}
