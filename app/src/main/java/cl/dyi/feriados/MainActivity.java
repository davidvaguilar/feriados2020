package cl.dyi.feriados;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import cl.dyi.feriados.Interface.ApiService;
import cl.dyi.feriados.Model.Feriado;
import cl.dyi.feriados.Model.Ley;
import cl.dyi.feriados.Util.FeriadoAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    //private TextView jsonText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //jsonText = findViewById(R.id.jsonText);
        getFeriados();
    }

    private void getFeriados(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://apis.digital.gob.cl/fl/feriados/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        ApiService apiService = retrofit.create(ApiService.class);
        Call<List<Feriado>> call = apiService.getFeriados();
        call.enqueue(new Callback<List<Feriado>>() {
            @Override
            public void onResponse(Call<List<Feriado>> call, Response<List<Feriado>> response) {
                if( !response.isSuccessful() ){
                    //jsonText.setText("Codigo de Error:"+response.code());
                    Toast.makeText(MainActivity.this, "Codigo de Error: "+response.code(), Toast.LENGTH_SHORT);
                    return;
                }

                ListView feriado_list_view = findViewById(R.id.feriado_list_view);
                    List<Feriado> feriados = response.body();
                    FeriadoAdapter feriadoAdapter = new FeriadoAdapter(MainActivity.this, R.layout.feriado_list_item, feriados);
                feriado_list_view.setAdapter(feriadoAdapter);

              //  for(Feriado feriado: feriados){
                   /* String content = "";
                    content = content+"nombre: "+feriado.getNombre()+"\n";
                    content = content+"fecha: "+feriado.getFecha()+"\n";
                    content = content+"irrenunciable: "+feriado.getIrrenunciable()+"\n";
                    content = content+"tipo: "+feriado.getTipo()+"\n\n";

                    for (Ley ley: feriado.getLeyes()){
                        content = content+"Ley nombre: "+ley.getNombre()+"\n";
                        content = content+"Url: "+ley.getUrl()+"\n";
                    }
                    content += "\n";
                    jsonText.append(content);*/
              //  }

            }

            @Override
            public void onFailure(Call<List<Feriado>> call, Throwable t) {
                //jsonText.setText(t.getMessage());
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT);
            }
        });
    }
}
