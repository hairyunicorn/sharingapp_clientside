package shareapp.mobileapps.master.zhaw.ch.sharingapp_clientside;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    private int networkResponseCode;

    private final Response.Listener<String> onPostsLoaded =
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Gson gson = new Gson();
                    Item[] itemList = gson.fromJson(response, Item[].class);
                    Log.e("test", itemList[0].getOwnerId());
                    Log.e("testHttpCode", String.valueOf(networkResponseCode));
                    /*
                    Better:
                    Type collectionType = new TypeToken<Collection<channelSearchEnum>>(){}.getType();
                    Collection<channelSearchEnum> enums = gson.fromJson(yourJson, collectionType);
                     */
                }

            };
    private final Response.ErrorListener onPostsError =
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("PostActivity", error.toString());
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        serverCall();
    }

    private void serverCall() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        //10.0.2.2 points to "localhost" of the machine on which the emulator is running
        //see https://developer.android.com/studio/run/emulator-networking
        //our server: http://fabled-coder-210208.appspot.com/items
        StringRequest request = new StringRequest(Request.Method.GET,
                "http://10.0.2.2:8080/items",
                onPostsLoaded, onPostsError) {
            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                if (response != null) {
                    networkResponseCode = response.statusCode;
                }
                return super.parseNetworkResponse(response);
            }
        };
        requestQueue.add(request);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
