package com.example.connection;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.example.alerts.ProximityAlert;
import com.savagelook.android.UrlJsonAsyncTask;
import org.json.JSONArray;
import org.json.JSONObject;

public class getNearbyEventsTask extends UrlJsonAsyncTask
{
    public getNearbyEventsTask(Context context)
    {
        super(context);
    }

    @Override
    protected void onPostExecute(JSONObject json)
    {
        try
        {
            JSONArray jsonPopulations = json.getJSONArray("populations");

            Log.i("GET", String.valueOf(jsonPopulations));

            int length = jsonPopulations.length();

            if(length > 0)
            {
                int randomChoice = (int) (Math.random() * length);

                Intent intent = new Intent(context, ProximityAlert.class);

                Log.i("GET", "type: "+jsonPopulations.getJSONObject(randomChoice).getJSONObject("population").getString("latitude"));
                Log.i("GET", "type: "+jsonPopulations.getJSONObject(randomChoice).getJSONObject("population").getString("longitude"));
                Log.i("GET", "name: "+jsonPopulations.getJSONObject(randomChoice).getJSONObject("population")
                        .getJSONObject("specie").getString("name"));

                intent.putExtra("name", jsonPopulations.getJSONObject(randomChoice).getJSONObject("population")
                        .getJSONObject("specie").getString("name"));

                context.startActivity(intent);

                Log.i("GET", "AQUI");
            }

        }
        catch (Exception ignored) {}
        finally {
            super.onPostExecute(json);
        }
    }
}
