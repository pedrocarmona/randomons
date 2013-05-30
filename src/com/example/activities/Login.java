package com.example.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.example.data.Move;
import com.example.data.Randomon;
import com.example.data.Player;
import com.example.data.SharedData;
import com.savagelook.android.UrlJsonAsyncTask;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.holoeverywhere.widget.EditText;
import org.holoeverywhere.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class Login extends SherlockFragmentActivity
{
    private final static String LOGIN_API_ENDPOINT_URL = "http://randomons.herokuapp.com/api/v1/sessions.json";
    private android.content.SharedPreferences mPreferences;
    private String mUserEmail;
    private String mUserPassword;
    private SharedData shared;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        final ActionBar bar = getSupportActionBar();
        bar.setTitle("Randomons");
        bar.setLogo(R.drawable.icon_app);

        findViewById(R.id.registerButton).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // No account, load new account view
                        Intent intent = new Intent(Login.this,
                                RandomonInfo.class);
                        startActivityForResult(intent, 0);
                    }
                });


        mPreferences = getSharedPreferences("CurrentUser", MODE_PRIVATE);
    }



    public void login(View button) {
        EditText userEmailField = (EditText) findViewById(R.id.userEmail);
        mUserEmail = userEmailField.getText().toString();
        EditText userPasswordField = (EditText) findViewById(R.id.userPassword);
        mUserPassword = userPasswordField.getText().toString();

        if (mUserEmail.length() == 0 || mUserPassword.length() == 0) {
            // input fields are empty
            Toast.makeText(this, "Please complete all the fields",
                    Toast.LENGTH_LONG).show();
            return;
        } else {
            LoginTask loginTask = new LoginTask(Login.this);
            loginTask.setMessageLoading("Logging in...");
            loginTask.execute(LOGIN_API_ENDPOINT_URL);
        }
    }

    private class LoginTask extends UrlJsonAsyncTask {
        public LoginTask(Context context) {
            super(context);
        }

        @Override
        protected JSONObject doInBackground(String... urls) {

            DefaultHttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(urls[0]);
            JSONObject holder = new JSONObject();
            JSONObject userObj = new JSONObject();
            String response = null;
            JSONObject json = new JSONObject();

            try {
                try {
                    // setup the returned values in case
                    // something goes wrong
                    json.put("success", false);
                    json.put("info", "Something went wrong. Retry!");
                    // add the user email and password to
                    // the params
                    userObj.put("email", mUserEmail);
                    userObj.put("password", mUserPassword);
                    holder.put("user", userObj);
                    StringEntity se = new StringEntity(holder.toString());
                    post.setEntity(se);

                    // setup the request headers
                    post.setHeader("Accept", "application/json");
                    post.setHeader("Content-Type", "application/json");

                    ResponseHandler<String> responseHandler = new BasicResponseHandler();
                    response = client.execute(post, responseHandler);
                    json = new JSONObject(response);

                } catch (HttpResponseException e) {
                    e.printStackTrace();
                    Log.e("ClientProtocol", "" + e);
                    json.put("info", "Email and/or password are invalid. Retry!");
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("IO", "" + e);
                }
            } catch (JSONException e) {
                e.printStackTrace();
                Log.e("JSON", "" + e);
            }

            return json;
        }
        /*
        {
        "id":1,"name":"asdas","hitpoints":100.0,
        "attack":10.0,"defense":5.0,"speed":1.0,
        "growth":0.0,"level":1,"current_hitpoints":1.0,
        "current_experience":0.0,"status":42,"preference":0.0
        }
        */
        @Override
        protected void onPostExecute(JSONObject json) {

            try {
                if (json.getBoolean("success")) {
                    // everything is ok
                    SharedPreferences.Editor editor =  mPreferences.edit();
                    // save the returned auth_token into
                    // the SharedPreferences
                    editor.putString("AuthToken", json.getJSONObject("user").getString("authentication_token"));

                    Player player = new Player(1,json.getJSONObject("user").getString("name"),1,R.drawable.avatar_img);

                    JSONArray myRandomonsJSON = json.getJSONObject("user").getJSONArray("creatures");
                    for (int i=0; i< myRandomonsJSON.length();i++){
                        JSONObject randomonJSON = myRandomonsJSON.getJSONObject(i);
                        Randomon randomon =new Randomon(
                                randomonJSON.getString("name"),
                                randomonJSON.getJSONObject("specie").getJSONArray("specie_types").getInt(0),
                                randomonJSON.getInt("attack"),
                                randomonJSON.getInt("defense"),
                                randomonJSON.getInt("speed"),
                                randomonJSON.getDouble("growth"),
                                randomonJSON.getInt("hitpoints"),
                                randomonJSON.getInt("level"),
                                randomonJSON.getInt("current_hitpoints"),
                                randomonJSON.getInt("current_experience"),
                                randomonJSON.getString("status"),
                                randomonJSON.getJSONObject("specie").getString("description"),
                                randomonJSON.getJSONObject("specie").getInt("id"),
                                randomonJSON.getInt("id")
                        );
                        JSONArray randomonMovesJson = randomonJSON.getJSONObject("specie").getJSONArray("moves");
                        for (int j=0; j< randomonMovesJson.length();j++){
                            JSONObject moveJSON = randomonMovesJson.getJSONObject(j);
                            Move move = new Move(moveJSON.getString("name"),
                                    R.drawable.quest_mark,
                                moveJSON.getInt("attack"),
                                moveJSON.getInt("accuracy"),
                                moveJSON.getInt("status"),
                                moveJSON.getInt("status_probability"),
                                moveJSON.getString("description") ,
                                moveJSON.getJSONArray("move_types").getInt(0)

                            );
                            randomon.getMoves().add(move);
                        }
                        player.getRandomonCollection().add(randomon);



                    }

                    shared = SharedData.getInstance();
                    shared.setPlayer(player);

//                    Toast.makeText(context, player.getName(), Toast.LENGTH_LONG).show();

                    editor.putString("User","");

                    editor.commit();

                    // launch the HomeActivity and close this one
                    Intent intent = new Intent(getApplicationContext(), MainMenu.class);
                    startActivity(intent);
                    finish();

                }
            } catch (Exception e) {
                // something went wrong: show a Toast
                // with the exception message
                Toast.makeText(context, "cena"+e.getMessage(), Toast.LENGTH_LONG).show();
            } finally {
                super.onPostExecute(json);
            }
        }
    }
}
