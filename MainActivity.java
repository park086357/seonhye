package org.targetyou.targetyou;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button BtnSignIn, BtnSignUp;
    EditText inputID, inputPW;
    HttpPost httppost;
    HttpResponse response;
    HttpClient httpclient;
    List<NameValuePair> nameValuePairs;
    ProgressDialog dialog = null;
    URL phpUrl;
    StringBuilder jsonHtml;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("                         SIGN IN");
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        BtnSignUp = findViewById(R.id.btn_signup);
        BtnSignIn = findViewById(R.id.btn_singin);
        inputID = findViewById(R.id.user_id);
        inputPW = findViewById(R.id.user_pw);

        BtnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = ProgressDialog.show(MainActivity.this, "",
                        "Validating user...", true);
                new Thread(new Runnable() {
                    public void run() {
                        login();
                    }
                }).start();
            }
        });
    }

    void login(){
        try {
            httpclient = new DefaultHttpClient();
            phpUrl = new URL("phpURL");
            httppost = new HttpPost(String.valueOf(phpUrl));
            nameValuePairs = new ArrayList<NameValuePair>(2);
            nameValuePairs.add(new BasicNameValuePair("username", inputID.getText().toString()));
            nameValuePairs.add(new BasicNameValuePair("password", inputPW.getText().toString()));
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            response = httpclient.execute(httppost);
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            final String response = httpclient.execute(httppost, responseHandler);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    dialog.dismiss();
                    jsonHtml = new StringBuilder();
                    try {
                        HttpURLConnection conn = (HttpURLConnection)phpUrl.openConnection();
                        if ( conn != null ) {
                            conn.setConnectTimeout(10000);
                            conn.setUseCaches(false);

                            if ( conn.getResponseCode() == HttpURLConnection.HTTP_OK ) {
                                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                                while ( true ) {
                                    String line = br.readLine();
                                    if ( line == null )
                                        break;
                                    jsonHtml.append(line + "\n");
                                }
                                br.close();
                            }
                            conn.disconnect();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            if (response.equalsIgnoreCase("null")) {
                Toast.makeText(MainActivity.this, "Login Fail", Toast.LENGTH_SHORT).show();
            }
            else {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                    }
                });

                try {
                    JSONObject jObject = new JSONObject(response);

                    String getName =jObject.get("USER_NAME").toString();
                    String getSex=jObject.get("USER_SEX").toString();

                    if (getSex.equals("여성")) {
                        intent=new Intent(MainActivity.this, FirstActivity.class);
                    }
                    else {
                        intent=new Intent(MainActivity.this, FirstmanActivity.class);
                    }

                    intent.putExtra("name", getName);
                    startActivity(intent);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }
        catch(Exception e)
        {
            dialog.dismiss();
            System.out.println("Exception : " + e.getMessage());
        }}



    public void CliSignUp(View view)
    {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}


