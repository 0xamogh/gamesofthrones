package amoghjapps.com.gamesofthroneslast;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface RetroFitInterf {

    @GET("{user}")
    Call<ResponseModel> getCharacter(@Path("user") String character);

}
