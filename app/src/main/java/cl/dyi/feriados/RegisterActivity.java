package cl.dyi.feriados;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cl.dyi.feriados.Interface.ApiService;
import cl.dyi.feriados.Response.LoginResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {

    private EditText edtRegisterName;
    private EditText edtRegisterEmail;
    private EditText edtRegisterPassword;
    private EditText edtRegisterConfirm;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }

    public void register(){
        edtRegisterName = findViewById(R.id.edtRegisterName);
        edtRegisterEmail = findViewById(R.id.edtRegisterEmail);
        edtRegisterPassword = findViewById(R.id.edtRegisterPassword);
        edtRegisterConfirm = findViewById(R.id.edtRegisterConfirm);
        String name = edtRegisterName.getText().toString();
        String email = edtRegisterEmail.getText().toString();
        String password = edtRegisterPassword.getText().toString();
        String confirm = edtRegisterConfirm.getText().toString();


        if( name.trim().isEmpty() || email.trim().isEmpty() || password.trim().isEmpty() || confirm.trim().isEmpty() ){
            Toast.makeText(this, "Por Favor ingrese llene todos los campos", Toast.LENGTH_LONG).show();
            return;
        }
      //  Toast.makeText(this, confirm, Toast.LENGTH_LONG).show();
        if( password.equals(confirm) ){
            Toast.makeText(this, "Las contraseñas no coinciden.", Toast.LENGTH_LONG).show();
            return;
        }
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://cita.dyi.cl/api/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        ApiService apiService = retrofit.create(ApiService.class);

        Call<LoginResponse> call = apiService.postRegister(name, email, password, confirm);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(RegisterActivity.this, "Codigo de Error: " + response.code(), Toast.LENGTH_SHORT).show();
                } else {
                    LoginResponse loginResponse = response.body();
                    if (loginResponse.getSuccess()) {
                        Toast.makeText(RegisterActivity.this, "Registro Exitoso", Toast.LENGTH_LONG).show();


                    } else {
                        Toast.makeText(RegisterActivity.this, "Ocurrio un error con el registro", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
