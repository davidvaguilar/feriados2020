package cl.dyi.feriados.Interface;

import java.util.List;

import cl.dyi.feriados.Model.Specialty;
import cl.dyi.feriados.Response.LoginResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @GET("specialties")
    Call<List<Specialty>> getSpecialities();

    @POST("login")
    Call<LoginResponse> postLogin(
            @Query("email") String email,
            @Query("password")String password
    );
}
