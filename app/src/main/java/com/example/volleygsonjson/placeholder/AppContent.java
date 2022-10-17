package com.example.volleygsonjson.placeholder;

import android.app.Activity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.volleygsonjson.App;
import com.example.volleygsonjson.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class AppContent {

    public static final List<App> MODELS = new ArrayList<>();
    public static final Map<String, App> MODELS_MAP = new HashMap<>();

    public void jsonParse(Activity activity)
    {
        RequestQueue queue = Volley.newRequestQueue(activity);
        String url = activity.getString(R.string.Url);
        // Request a string response from the provided URL.
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // NEXT, we need to use GSON to turn that JSON into a model
                        try {
                            JSONObject object = response.getJSONObject("record");
                            JSONArray jsonArray = object.getJSONArray("gameCompanies");
                            MODELS.clear();
                            MODELS_MAP.clear();
                            for(int i = 0; i < jsonArray.length(); i++)
                            {
                                JSONObject gameCompany = jsonArray.getJSONObject(i);
                                String name = gameCompany.getString("name");
                                Integer year = gameCompany.getInt("year");
                                String recentConsole = gameCompany.getString("recentConsole");
                                App model = new App(name, year, recentConsole);
                                MODELS.add(model);
                                MODELS_MAP.put(name, model);
                            }
                            activity.recreate();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // you should drop a breakpoint RIGHT HERE if you need to see the error coming back
                error.printStackTrace();
            }
        });

        queue.add(objectRequest);

    }


    //Method to add an item to our List and Map
    private static void addItem(App item) {
        MODELS.add(item);
        MODELS_MAP.put(item.getName(), item);
    }
}