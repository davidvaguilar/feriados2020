package cl.dyi.feriados;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import cl.dyi.feriados.Interface.ApiService;
import cl.dyi.feriados.Model.Feriado;
import cl.dyi.feriados.Util.FeriadoAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

    }

    public void feriados(View view) {
        Intent i = new Intent(this, FeriadosActivity.class );
        startActivity(i);
    }

}
