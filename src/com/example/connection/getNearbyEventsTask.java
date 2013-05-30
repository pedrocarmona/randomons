package com.example.connection;

import android.content.Context;
import android.util.Log;
import com.savagelook.android.UrlJsonAsyncTask;
import org.json.JSONArray;
import org.json.JSONObject;

public class getNearbyEventsTask extends UrlJsonAsyncTask
{
    public getNearbyEventsTask(Context context)
    {
        super(context.getApplicationContext());
    }

    @Override
    protected void onPostExecute(JSONObject json)
    {
        try
        {
            Log.i("GET", "ENTROU");
            JSONArray jsonPopulations = json.getJSONArray("populations");
            int length = jsonPopulations.length();

            Log.i("GET", String.valueOf(length));

            int randomChoice = (int) (Math.random() * length);

            for (int i = 0; i < length; i++)
            {
                Log.i("GET", "type: "+jsonPopulations.getJSONObject(i).getJSONObject("population").getString("specie"));
            }

        }
        catch (Exception ignored) {}
        finally {
            super.onPostExecute(json);
        }
    }
}
