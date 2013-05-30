package com.example.server;

import android.content.Context;
import android.util.Log;
import com.example.activities.R;
import com.example.data.Move;
import com.example.data.Randomon;
import com.example.data.SharedData;
import com.savagelook.android.UrlJsonAsyncTask;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.holoeverywhere.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class ServerRandomon {


    ServerRandomon(Context context,Randomon randomon){

        RandomonCreate loginTask = new RandomonCreate(context, randomon);

    }

    private class RandomonCreate extends UrlJsonAsyncTask {
        Randomon randomon;
        public RandomonCreate(Context context, Randomon randomon) {
            super(context);
            this.randomon = randomon;
        }

        @Override
        protected JSONObject doInBackground(String... urls) {
            DefaultHttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(urls[0]);
            JSONObject holder = new JSONObject();
            JSONObject randomonObj = new JSONObject();
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
                    randomonObj.put("specie_id", randomon.getPicId());
                    randomonObj.put("name", randomon.getName());
                    randomonObj.put("hitpoints", randomon.getHitpoints());
                    randomonObj.put("attack", randomon.getAttack());
                    randomonObj.put("defense", randomon.getDefense());
                    randomonObj.put("speed", randomon.getSpeed());
                    randomonObj.put("level", randomon.getLevel());
                    randomonObj.put("current_hitpoints", randomon.getCurrent_hitpoints());
                    holder.put("creature", randomonObj);
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
        @Override
        protected void onPostExecute(JSONObject json) {

            try {
                if (json.getBoolean("success")) {
                    // everything is ok
                    JSONObject randomonJSON = json.getJSONObject("creature");
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
                    SharedData shared = SharedData.getInstance();
                    shared.getPlayer().getRandomonCollection().add(randomon);
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

