package com.example.u_book;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import static java.security.AccessController.getContext;

public class logincs extends AppCompatActivity  {

    EditText editTextUsername, editTextPassword;
    Button Log_in;

    EditText  emails,passwords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logincs);

        final EditText emails = findViewById(R.id.maillog);
        final EditText passwords=  findViewById(R.id.passlog);
        final Button Log_in= findViewById(R.id.log_in);

        RequestQueue queue = Volley.newRequestQueue(this);
        Log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emails.getText().toString();
                String password =passwords.getText().toString();

                new loginimage(logincs.this).execute(email,password);

            }
        });



    }

    private class loginimage extends AsyncTask<String,Void, String> {
        private WeakReference<Context> context;

        public loginimage (Context context){

            this.context=new WeakReference<>(context);
        }


        @Override
        protected String doInBackground(String... params) {
            String register_url= "https://webserviceubook.000webhostapp.com/Api.php";
            String resultado;
            try {
                URL url= new URL(register_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
                String email= params[0];
                String password = params[1];

                String data= URLEncoder.encode("usuario","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")
                        +"&"+URLEncoder.encode("passwords","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream= httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while   ((line=bufferedReader.readLine())!=null){
                    stringBuilder.append(line);

                }
                resultado = stringBuilder.toString();
                bufferedReader.close();
                httpURLConnection.disconnect();






            }
            catch (MalformedURLException e) {
                Log.d("MiAPP","Se ha utilizadoun formato incorrecto");
                resultado="se ha producido un error";

            } catch (IOException e) {
                Log.d("MiAPP","Problemas de conexion a internet");
                resultado="se haproducido un error, revisa la conexion a internet";
            }

            return resultado;
        }
        protected void onPostExecute(String resultado){

            try {
                JSONObject obj = new JSONObject(resultado);
                boolean message = obj.getBoolean("pass");
                if (message==true){

                    startActivity(new Intent(logincs.this, Home.class));
                    User user = new User(
                            obj.getInt("id_usuario"),
                            obj.getString("usuario"),
                            obj.getString("nombre"),
                            obj.getString("apellido")



                    );

                }
                else    {
                    Toast.makeText(context.get(),"incorrect password or username",Toast.LENGTH_LONG).show();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            Toast.makeText(context.get(),resultado,Toast.LENGTH_LONG).show();
        }
    }
}
