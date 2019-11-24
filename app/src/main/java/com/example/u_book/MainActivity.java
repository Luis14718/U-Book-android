package com.example.u_book;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

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

public class MainActivity extends AppCompatActivity {
    int RC_SIGN_IN = 0;
    SignInButton signInButton;
    GoogleSignInClient mGoogleSignInClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Initializing Views

        signInButton = findViewById(R.id.sign_in_button);
        final Button sign_normal = findViewById(R.id.signinnormal);
        final EditText fieldtext11 = findViewById(R.id.fieldtext1);
        final EditText fieldtext22= findViewById(R.id.fieldtext2);
        final EditText fieldtext33=findViewById(R.id.fieldtext3);
        final EditText fieldtext44=findViewById(R.id.fieldtext4);






        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();


        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        sign_normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name= fieldtext11.getText().toString();
                String lastname= fieldtext22.getText().toString();
                String email= fieldtext33.getText().toString();
                String password = fieldtext44.getText().toString();

                new Descargarimagen(MainActivity.this).execute(name,lastname,email,password);
                startActivity(new Intent(MainActivity.this, firstpage.class));
            }
        });

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
    }




    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            // Signed in successfully, show authenticated UI.
            startActivity(new Intent(MainActivity.this, Home.class));
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("Google Sign In Error", "signInResult:failed code=" + e.getStatusCode());
            Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_LONG).show();
        }
    }
    @Override
    protected void onStart() {
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if(account != null) {
            startActivity(new Intent(MainActivity.this, Home.class));
        }
        super.onStart();
    }







    public static class Descargarimagen extends AsyncTask<String,Void, String> {

        private WeakReference<Context> context;
        public Descargarimagen (Context context){

            this.context=new WeakReference<>(context);
        }


        @Override
        protected String doInBackground(String... params) {
            String register_url= "https://webserviceubook.000webhostapp.com/register.php";
            String resultado;
            try {
                URL url= new URL(register_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
                String name = params[0];
                String lastname = params[1];
                String email= params[2];
                String password = params[3];

                String data= URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")
                        +"&"+URLEncoder.encode("lastname","UTF-8")+"="+URLEncoder.encode(lastname,"UTF-8")
                        +"&"+URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")
                        +"&"+URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
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

            Toast.makeText(context.get(),resultado,Toast.LENGTH_LONG).show();
        }
    }
}
