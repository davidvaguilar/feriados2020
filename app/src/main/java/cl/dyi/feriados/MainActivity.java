package cl.dyi.feriados;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import cl.dyi.feriados.Interface.ApiService;
import cl.dyi.feriados.Model.Feriado;
import cl.dyi.feriados.Model.Ley;
import cl.dyi.feriados.Response.LoginResponse;
import cl.dyi.feriados.Util.FeriadoAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    //private TextView jsonText;
    private EditText edtEmail;
    private EditText edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });



    }

    public void GoToRegister(View view) {
        Intent i = new Intent(this, RegisterActivity.class );
        startActivity(i);
    }

    public void login(){
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword =  findViewById(R.id.edtPassword);
        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();
        if( email.trim().isEmpty() || password.trim().isEmpty() ){
            Toast.makeText(this, "Por Favor ingrese un correo y contraseña", Toast.LENGTH_LONG).show();
        } else {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://cita.dyi.cl/api/")
                    .addConverterFactory(GsonConverterFactory.create()).build();

            ApiService apiService = retrofit.create(ApiService.class);
            Call<LoginResponse> call = apiService.postLogin(email, password);
            call.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    if (!response.isSuccessful()) {
                        Toast.makeText(MainActivity.this, "Codigo de Error: " + response.code(), Toast.LENGTH_SHORT).show();
                        return;
                    }
                    LoginResponse loginResponse = response.body();
                    if (loginResponse.getSuccess()) {

                        createSessionPreference( loginResponse.getJwt() );
                        Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(MainActivity.this, "Las credenciales son incorrectas", Toast.LENGTH_LONG).show();
                    }
                    return;
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void createSessionPreference(String jwt){
        SharedPreferences preferences = getSharedPreferences("credencials", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("jwt", jwt);
        editor.commit();
    }



}
