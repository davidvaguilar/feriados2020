package cl.dyi.feriados.Interface;

import java.util.List;

import cl.dyi.feriados.Model.Feriado;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("2020")
    Call<List<Feriado>> getFeriados();
}
