package amoghjapps.com.gamesofthroneslast;

public class GotApiHandler {

    public static final String BASE_URL = "https://api.got.show/api/characters/";

    public static RetroFitInterf getGOTService() {
        return RetrofitClient.getClient(BASE_URL).create(RetroFitInterf.class);
    }
}
