package com.example.heros;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private  ExampleAdapter mExampleAdapter;
    private ArrayList<ExampleItem> mExampleList;
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mExampleList = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(this);
        parseJSON();
    }
    private  void  parseJSON(){
        String url = "https://cdn.jsdelivr.net/gh/akabab/superhero-api@0.3.0/api/all.json";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        JSONArray jsonArray = new JSONArray();

                        for(int i=0; i< jsonArray.length();i++){
                            JSONObject hit = null;
                            try {
                                hit = jsonArray.getJSONObject(i);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            String creatorName = null;
                            try {
                                creatorName = hit.getString("name");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            String imageUrl = null;
                            try {
                                imageUrl = hit.getString("images.xs");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            mExampleList.add(new ExampleItem(imageUrl, creatorName));
                        }

                        mExampleAdapter = new ExampleAdapter(MainActivity.this,mExampleList);
                        mRecyclerView.setAdapter(mExampleAdapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mRequestQueue.add(request);
    }
}