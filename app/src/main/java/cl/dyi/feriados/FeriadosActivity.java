package cl.dyi.feriados;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import cl.dyi.feriados.Interface.ApiService;
import cl.dyi.feriados.Model.Feriado;
import cl.dyi.feriados.Model.Specialty;
import cl.dyi.feriados.Util.FeriadoAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FeriadosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feriados);
        getFeriados();
    }


    private void getFeriados(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://cita.dyi.cl/api/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        ApiService apiService = retrofit.create(ApiService.class);
        Call<List<Specialty>> call = apiService.getSpecialities();
        call.enqueue(new Callback<List<Specialty>>() {
            @Override
            public void onResponse(Call<List<Specialty>> call, Response<List<Specialty>> response) {
                if( !response.isSuccessful() ){
                    //jsonText.setText("Codigo de Error:"+response.code());
                    Toast.makeText(FeriadosActivity.this, "Codigo de Error: "+response.code(), Toast.LENGTH_SHORT);
                    return;
                }

                ListView feriado_list_view = findViewById(R.id.feriado_list_view);
                List<Specialty> specialties = response.body();
                FeriadoAdapter feriadoAdapter = new FeriadoAdapter(FeriadosActivity.this, R.layout.feriado_list_item, specialties);
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
            public void onFailure(Call<List<Specialty>> call, Throwable t) {
                //jsonText.setText(t.getMessage());
                Toast.makeText(FeriadosActivity.this, t.getMessage(), Toast.LENGTH_SHORT);
            }
        });
    }
}
