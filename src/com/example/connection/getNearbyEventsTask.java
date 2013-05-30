package com.example.connection;

import android.content.Context;
import android.util.Log;
import com.savagelook.android.UrlJsonAsyncTask;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.holoeverywhere.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class getNearbyEventsTask extends UrlJsonAsyncTask
{

    public getNearbyEventsTask(Context context)
    {
        super(context);
    }

    @Override
    protected JSONObject doInBackground(String... urls)
    {
        DefaultHttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet(urls[0]);
        //JSONObject holder = new JSONObject();
        JSONObject userObj = new JSONObject();
        String response;
        JSONObject json = new JSONObject();

        try
        {
            try
            {
                // setup the returned values in case
                // something goes wrong
                json.put("success", false);
                json.put("info", "Something went wrong. Retry!");
                // add the user email and password to
                // the params
                userObj.put("lat", urls[1]);
                userObj.put("lng", "At "+urls[2]);
                userObj.put("auth_token", urls[3]);

                //holder.put("point_of_interest", userObj);
                //StringEntity se = new StringEntity(holder.toString());
                //post.setEntity(se);

                // setup the request headers
                get.setHeader("Accept", "application/json");
                get.setHeader("Content-Type", "application/json");

                ResponseHandler<String> responseHandler = new BasicResponseHandler();
                response = client.execute(get, responseHandler);
                json = new JSONObject(response);

            }
            catch (HttpResponseException e)
            {
                e.printStackTrace();
                Log.e("ERROR", "" + e);
                json.put("info", "Something went wrong. Retry!");
            }
            catch (IOException e)
            {
                e.printStackTrace();
                Log.e("ERROR", "" + e);
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
            Log.e("ERROR", "" + e);
        }

        return json;
    }

    @Override
    protected void onPostExecute(JSONObject json)
    {
        try
        {
            JSONArray jsonPopulations = json.getJSONArray("populations");
            int length = jsonPopulations.length();

            int randomChoice = (int) (Math.random() * length);

            for (int i = 0; i < length; i++)
            {
                Log.i("GET", "type: "+jsonPopulations.getJSONObject(i).getJSONObject("population").getString("specie"));
            }

        }
        catch (Exception e) {
            Toast.makeText(context, e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
        finally {
            super.onPostExecute(json);
        }
    }
}
