package com.example.jsonarrayparsinginandrois;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView tvDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDisplay= findViewById(R.id.tvDisplay);


        // Initialize the RequestQueue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        // URL of the API endpoint
        String url = "https://hit-and-run-budgets.000webhostapp.com/apps/video.json";

        // Create the JSON array request
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Handle the JSON array response
                        Log.d("VolleyResponse", response.toString());

                        for (int x =0; x<response.length(); x++){

                            try {
                                JSONObject jsonObject = response.getJSONObject(x);
                                String title = jsonObject.getString("title");
                                String videoId = jsonObject.getString("videoId");

                                tvDisplay.append(x + "\n"+title + "\n" + videoId +"\n\n");

                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }

                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                        Log.e("VolleyError", error.toString());
                    }
                }
        );

        // Add the request to the RequestQueue
        requestQueue.add(jsonArrayRequest);
    }



}