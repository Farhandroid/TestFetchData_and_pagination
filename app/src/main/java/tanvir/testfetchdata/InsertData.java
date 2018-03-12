package tanvir.testfetchdata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class InsertData extends AppCompatActivity {

    EditText nameET , mobile_numET , emailET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_data);

        nameET = findViewById(R.id.name);
        mobile_numET=findViewById(R.id.mobile_num);
        emailET= findViewById(R.id.email);
    }

    public void insertDataINLocalDB(View view) {

        ///Toast.makeText(this, "Enter insertDataINLocalDB", Toast.LENGTH_SHORT).show();


        ///String url = "https://192.168.0.104/TestFetchData/insertData.php";
        //String url = "http://192.168.0.104/TestFetchData/insertData.php";

        String url = "http://www.farhandroid.com/insertData.php";

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        ///imgname.setText(response);

                        Toast.makeText(getApplicationContext(), "response " + response, Toast.LENGTH_LONG).show();
                        nameET.setText(response);


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(getApplicationContext(), "Error " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<String, String>();

                try {

                    params.put("name", nameET.getText().toString());
                    params.put("mobile_num", mobile_numET.getText().toString());
                    params.put("email", emailET.getText().toString());

                    ///params.put("imgName", imgname.getText().toString());


                } catch (Exception e) {
                    Toast.makeText(InsertData.this, "Exception", Toast.LENGTH_SHORT).show();
                }

                return params;

            }
        };


        MySingleton.getInstance(this).addToRequestQueue(postRequest);


    }


}
