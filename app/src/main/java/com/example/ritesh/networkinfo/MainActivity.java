package com.example.ritesh.networkinfo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

@SuppressWarnings("deprecation")
public class MainActivity extends AppCompatActivity {

    String Str_Get_result = "", Str_Array = "";
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.tv_array);

       /* getNetworkClass(MainActivity.this);
        Toast.makeText(this, "Type :" + "" + getNetworkClass(MainActivity.this), Toast.LENGTH_LONG).show();


        final ConnectivityManager connMgr = (ConnectivityManager)
                this.getSystemService(Context.CONNECTIVITY_SERVICE);

        final android.net.NetworkInfo wifi =
                connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        final android.net.NetworkInfo mobile =
                connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if (wifi.isAvailable() && wifi.getDetailedState() == NetworkInfo.DetailedState.CONNECTED) {
            Toast.makeText(this, "Wifi", Toast.LENGTH_LONG).show();
        } else if (mobile.isAvailable() && mobile.getDetailedState() == NetworkInfo.DetailedState.CONNECTED) {
            Toast.makeText(this, "Mobile 3G ", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "No Network ", Toast.LENGTH_LONG).show();
        }*/

        MainCategoryJsontask task = new MainCategoryJsontask();
        task.execute();

    }



    /*public String getNetworkClass(Context context) {
        TelephonyManager mTelephonyManager = (TelephonyManager)
                context.getSystemService(Context.TELEPHONY_SERVICE);
        int networkType = mTelephonyManager.getNetworkType();
        switch (networkType) {
            case TelephonyManager.NETWORK_TYPE_GPRS:
            case TelephonyManager.NETWORK_TYPE_EDGE:
            case TelephonyManager.NETWORK_TYPE_CDMA:
            case TelephonyManager.NETWORK_TYPE_1xRTT:
            case TelephonyManager.NETWORK_TYPE_IDEN:
                return "2G";
            case TelephonyManager.NETWORK_TYPE_UMTS:
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
            case TelephonyManager.NETWORK_TYPE_HSDPA:
            case TelephonyManager.NETWORK_TYPE_HSUPA:
            case TelephonyManager.NETWORK_TYPE_HSPA:
            case TelephonyManager.NETWORK_TYPE_EVDO_B:
            case TelephonyManager.NETWORK_TYPE_EHRPD:
            case TelephonyManager.NETWORK_TYPE_HSPAP:
                return "3G";
            case TelephonyManager.NETWORK_TYPE_LTE:
                return "4G";
            default:
                return "Unknown";
        }
    }*/

    /*public String getNetworkType(Context context) {
        String networkType = null;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        if (activeNetwork != null) { // connected to the internet
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                networkType = "WiFi";
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                networkType = "Mobile";
            }
        } else {
            // not connected to the internet
        }
        return networkType;
    }*/


    /*private class SendPostRequest extends AsyncTask<String, Void, String> {

        boolean iserror = false;

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            //  loginprogressbar.setVisibility(View.VISIBLE);
            Log.e("******* NOW MainCategoryJsontask WEB SERVICE IS RUNNING onPreExecute*******", "YES");
            Log.e("******* NOW MainCategoryJsontask WEB SERVICE IS RUNNING onPreExecute*******", "YES");
//            Log.e("User_ID onPreExecute :", "" + User_ID);

        }

        @Override
        protected String doInBackground(String... params) {
            Log.e("******* NOW doInBackground TASK IS RUNNING *******", "YES");


            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(HttpUrlPath.urlPath);

            Log.e("URL :", "" + HttpUrlPath.urlPath);

            try {
                HttpResponse response = client.execute(post);
                String object = EntityUtils.toString(response.getEntity());
                Log.e("*******object******** :", "" + object);

                //JSONArray js = new JSONArray(object);
//                JSONObject jobect = new JSONObject(object);
//                Str_Get_result = jobect.getString("result");
//                JSONArray jaaray = new JSONArray(Str_Get_result);
//
//                Log.e("Array List :", "" + jaaray);
//                Str_Array = String.valueOf(jaaray);
//

                JSONArray myListsAll = new JSONArray(object);
                for (int i = 0; i < myListsAll.length(); i = 5) {
                    JSONObject jsonobject = (JSONObject) myListsAll.get(i);
                    String id = jsonobject.optString("nid");
                    String Name = jsonobject.optString("Name");
                    String Email = jsonobject.optString("Email");
                    String Password = jsonobject.optString("Password");
                    String Mobile = jsonobject.optString("Mobile");
                    String Status = jsonobject.optString("Status");
                    String regas = jsonobject.optString("regas");
                    String photo = jsonobject.optString("photo");
                    String regid = jsonobject.optString("regid");
                    String result = jsonobject.optString("result");

                    Log.e("id :", "" + id
                            + "\n" + "Name :" + "" + Name
                            + "\n" + "Email :" + "" + Email
                            + "\n" + "Password :" + "" + Password
                            + "\n" + "Mobile :" + "" + Mobile
                            + "\n" + "Status :" + "" + Status
                            + "\n" + "regas :" + "" + regas
                            + "\n" + "photo :" + "" + photo
                            + "\n" + "regid :" + "" + regid
                            + "\n" + "result :" + "" + result);
                }

            } catch (Exception e) {
                Log.v("22", "22" + e.getMessage());
                e.printStackTrace();
                iserror = true;
            }

            return Str_Get_result;
        }

        @Override
        protected void onPostExecute(String result1) {
            // TODO Auto-generated method stub
            super.onPostExecute(result1);

            if (!iserror) {
                Log.e("Complete :", "OK");
                *//*if (Str_Get_result.equalsIgnoreCase("1")) {

                    Log.e("Str_Array :", "" + Str_Array);



                }*//*
            } else {
                Log.e("Error ", "OK");
            }


        }

    }*/


    /*this is One working jsontast (Start)*/
    public class MainCategoryJsontasks extends AsyncTask<String, Void, String> {

        protected void onPreExecute() {
        }

        protected String doInBackground(String... arg0) {

            try {

                URL url = new URL(HttpUrlPath.urlPath); // here is your URL path

                JSONArray postDataParams = new JSONArray();
                Log.e("params", postDataParams.toString());

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(getPostDataString(postDataParams));

                writer.flush();
                writer.close();
                os.close();

                int responseCode = conn.getResponseCode();

                if (responseCode == HttpsURLConnection.HTTP_OK) {

                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(
                                    conn.getInputStream()));
                    StringBuffer sb = new StringBuffer("");
                    String line = "";

                    while ((line = in.readLine()) != null) {

                        sb.append(line);
                        break;
                    }

                    in.close();
                    return sb.toString();

                } else {
                    return new String("false : " + responseCode);
                }
            } catch (Exception e) {
                return new String("Exception: " + e.getMessage());
            }

        }

        @Override
        protected void onPostExecute(String result) {
            /*Toast.makeText(getApplicationContext(), result,
                    Toast.LENGTH_SHORT).show();*/
            Log.e("result :", "" + result);
            textView.setText(result);
        }
    }
    /*this is One working jsontast (End)*/

    public String getPostDataString(JSONArray params) throws Exception {

        StringBuilder result = new StringBuilder();
        boolean first = true;


        Log.e("result :", "" + result);

//        Iterator<String> itr = params.keys();
//
//        while (itr.hasNext()) {
//
//            String key = itr.next();
//            Object value = params.get(key);
//
//            if (first)
//                first = false;
//            else
//                result.append("&");
//
//            result.append(URLEncoder.encode(key, "UTF-8"));
//            result.append("=");
//            result.append(URLEncoder.encode(value.toString(), "UTF-8"));
//
//        }
        return result.toString();
    }


    public static String excutePost(String targetURL, String urlParameters) {
        URL url;
        HttpURLConnection connection = null;
        try {
            //Create connection
            url = new URL(targetURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");

            connection.setRequestProperty("Content-Length", "" +
                    Integer.toString(urlParameters.getBytes().length));
            connection.setRequestProperty("Content-Language", "en-US");

            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            //Send request
            DataOutputStream wr = new DataOutputStream(
                    connection.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();

            //Get Response
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuffer response = new StringBuffer();
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            return response.toString();

        } catch (Exception e) {

            e.printStackTrace();
            return null;

        } finally {

            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    /*this is Two working jsontast (Start)*/
    private class MainCategoryJsontask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            // These two need to be declared outside the try/catch
            // so that they can be closed in the finally block.
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            // Will contain the raw JSON response as a string.
            String forecastJsonStr = null;

            try {
                // Construct the URL for the OpenWeatherMap query
                // Possible parameters are avaiable at OWM's forecast API page, at
                // http://openweathermap.org/API#forecast
                URL url = new URL(HttpUrlPath.urlPath);

                // Create the request to OpenWeatherMap, and open the connection
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setReadTimeout(15000 /* milliseconds */);
                urlConnection.setConnectTimeout(15000 /* milliseconds */);
                urlConnection.setRequestMethod("POST");
                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);
                urlConnection.setRequestMethod("POST");
                urlConnection.connect();


                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                    // But it does make debugging a *lot* easier if you print out the completed
                    // buffer for debugging.
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    return null;
                }
                forecastJsonStr = buffer.toString();
                return forecastJsonStr;
            } catch (IOException e) {
                Log.e("PlaceholderFragment", "Error ", e);
                // If the code didn't successfully get the weather data, there's no point in attemping
                // to parse it.
                return null;
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e("PlaceholderFragment", "Error closing stream", e);
                    }
                }
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            textView.setText(s);
            Log.e("json", s);
        }
    }
    /*this is Two working jsontast (End)*/

}
