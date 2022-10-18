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
import com.google.gson.Gson;

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
    private static boolean BUILT = false;

    public void jsonParse(Activity activity)
    {
        RequestQueue queue = Volley.newRequestQueue(activity);
        String url = activity.getString(R.string.MyUrl);
        // Request a string response from the provided URL.
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // NEXT, we need to use GSON to turn that JSON into a model
                        try {
                            JSONObject object = response.getJSONObject("record");
                            JSONArray jsonArray = object.getJSONArray("FalloutNewVegasFactions");
                            MODELS.clear();
                            MODELS_MAP.clear();
                            for(int i = 0; i < jsonArray.length(); i++)
                            {
                                JSONObject gameCompany = jsonArray.getJSONObject(i);
                                String json = String.valueOf(gameCompany);
                                Gson gson = new Gson();
                                App model = gson.fromJson(json, App.class);

                                MODELS.add(model);
                                MODELS_MAP.put(model.getName(), model);
                            }
                            if(!BUILT) {
                                BUILT = true;
                                activity.recreate();
                            }
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