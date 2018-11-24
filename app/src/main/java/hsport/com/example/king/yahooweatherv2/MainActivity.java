package hsport.com.example.king.yahooweatherv2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    private ProgressDialog pDialog;
    private static List<Forecast>tenDaysForecast=new ArrayList<>();
    private String temp;

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences sharedPreferences=getSharedPreferences("settings",MODE_PRIVATE);
       temp= sharedPreferences.getString("temp","fahren");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();

        ListView listView=findViewById(R.id.listView);
        final ForecastAdapter forecastAdapter= new ForecastAdapter(MainActivity.this,tenDaysForecast);
        listView.setAdapter(forecastAdapter);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo." +
                "places(1)%20where%20text%3D%22cairo%2C%20eg%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        // mTextView.setText("Response is: "+ response.substring(0,500));
                        pDialog.dismiss();

                        try {
                            JSONObject root =new JSONObject(response);
                            JSONObject location =root.getJSONObject("query")
                                    .getJSONObject("results").getJSONObject("channel")
                                    .getJSONObject("location");
                            String city =location.getString("city");
                            String country =location.getString("country");

                            JSONArray forecast =root.getJSONObject("query")
                                    .getJSONObject("results").getJSONObject("channel")
                                    .getJSONObject("item").getJSONArray("forecast");
                            for(int i = 0; i < forecast.length(); i++){
                                String date = forecast.getJSONObject(i).getString("date");
                                String day = forecast.getJSONObject(i).getString("day");
                                String description = forecast.getJSONObject(i).getString("text");

                               int high =Integer.parseInt(forecast.getJSONObject(i).getString("high"));
                                int low =Integer.parseInt(forecast.getJSONObject(i).getString("low"));

                                /* String high=forecast.getJSONObject(i).getString("high");
                                String low=forecast.getJSONObject(i).getString("low");
*/
                               if(temp.equals("celisuis")){
                                   high=(high - 32) * 5/9;
                               }


                                Forecast forecast1 =new Forecast(date,day,description,high,low);

                                tenDaysForecast.add(forecast1);
           }
                            // Toast.makeText(MainActivity.this, ""+tenDaysForecast.get(9).getDay(), Toast.LENGTH_LONG).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                       forecastAdapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //mTextView.setText("That didn't work!");
                Toast.makeText(MainActivity.this, "That didn't work!", Toast.LENGTH_SHORT).show();
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        pDialog.dismiss();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.settings_id:
                startActivity(new Intent(MainActivity.this,Settings.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
