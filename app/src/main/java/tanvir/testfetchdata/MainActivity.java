package tanvir.testfetchdata;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<PersonDetails> personDetailsArrayList;

    private RecyclerView recyclerView;
    public RecyclerAdapter adapter;
    private LinearLayoutManager layoutManager;

    boolean isLoading = true;
    int pastVisibleItem, visibleItemCount, totalItemCount, previousTotal = 0;
    int viewThreshold = 15;

    ProgressBar progressBar;

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        personDetailsArrayList = new ArrayList<>();
        progressBar = findViewById(R.id.prgrs);

        textView = findViewById(R.id.error);

        recyclerView = findViewById(R.id.recyclerView);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        ///fetchData();
        ///volleyGetRequet();
        testGetRequestWithData();

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) {
                    ////Toast.makeText(MainActivity.this, "Enter "+i, Toast.LENGTH_SHORT).show();
                    ///loadMore();

                    visibleItemCount = layoutManager.getChildCount();
                    totalItemCount = layoutManager.getItemCount();
                    pastVisibleItem = layoutManager.findFirstVisibleItemPosition();

                    if (isLoading) {
                        if (totalItemCount > previousTotal) {
                            isLoading = false;
                            previousTotal = totalItemCount;
                        }
                    }

                    if (!isLoading && (totalItemCount - visibleItemCount) <= (pastVisibleItem + viewThreshold)) {
                        loadMore();
                        isLoading = true;
                    }


                }
            }
        });

        ///Toast.makeText(this, "personDetailsArrayList.size(Oncreate) : "+Integer.toString(personDetailsArrayList.size()), Toast.LENGTH_SHORT).show();

        ///adapter = new RecyclerAdapter(this,personDetailsArrayList);

        ///recyclerView.setAdapter(adapter);


    }

    public void nextPage(View view) {

        Intent myIntent = new Intent(this, InsertData.class);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        this.startActivity(myIntent);
        finish();
    }


    public void testGetRequestWithData() {
        ///String url = "http://www.farhandroid.com/testGetRequest.php?tst=hello";
        String url = "http://www.farhandroid.com/getAllDataWithLimit.php?position=0";
        ///Toast.makeText(this, "Enter Fetch Data", Toast.LENGTH_SHORT).show();


        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(final String response) {

                        ///textView.setText("respone : "+response);

                        try {
                            JSONArray jsonArray = new JSONArray(response);

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                PersonDetails personDetails = new PersonDetails(jsonObject.getString("name"), jsonObject.getString("mobile_num"), jsonObject.getString("email"));
                                personDetailsArrayList.add(personDetails);
                            }


                            //if (jsonArray.length()>0)
                            /// Toast.makeText(PostViewActivity.this, "Image data found", Toast.LENGTH_SHORT).show();

                        } catch (JSONException e) {
                            e.printStackTrace();

                            Toast.makeText(MainActivity.this, "\"Json Exception : " + e.toString(), Toast.LENGTH_LONG).show();
                        }

                        adapter = new RecyclerAdapter(MainActivity.this, personDetailsArrayList);

                        recyclerView.setAdapter(adapter);


                    }
                }
                ,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(final VolleyError error) {
                        textView.setText("error : " + error.toString());

                    }
                }
        );

        MySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

    public void loadMore() {

        progressBar.setVisibility(View.VISIBLE);


        String p = Integer.toString(personDetailsArrayList.size());

        //Toast.makeText(this, "p: "+p, Toast.LENGTH_SHORT).show();
        String url = "http://www.farhandroid.com/getAllDataWithLimit.php?position="+p;
        ///Toast.makeText(this, "Enter Fetch Data", Toast.LENGTH_SHORT).show();


        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(final String response) {

                        ///textView.setText("respone : "+response);

                        try {
                            JSONArray jsonArray = new JSONArray(response);

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                PersonDetails personDetails = new PersonDetails(jsonObject.getString("name"), jsonObject.getString("mobile_num"), jsonObject.getString("email"));
                                personDetailsArrayList.add(personDetails);
                            }




                            //if (jsonArray.length()>0)
                            /// Toast.makeText(PostViewActivity.this, "Image data found", Toast.LENGTH_SHORT).show();

                        } catch (JSONException e) {
                            e.printStackTrace();

                            Toast.makeText(MainActivity.this, "\"Json Exception : " + e.toString(), Toast.LENGTH_LONG).show();
                        }
                        progressBar.setVisibility(View.GONE);

                        adapter .notifyDataSetChanged();


                    }
                }
                ,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(final VolleyError error) {
                        textView.setText("error : " + error.toString());

                    }
                }
        );

        MySingleton.getInstance(this).addToRequestQueue(stringRequest);

    }
}
