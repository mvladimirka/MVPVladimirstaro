package hr.tvz.android.listavladimir;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataHelper {

    public static Retrofit createRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("https://gitlab.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static Service createService() {
        return createRetrofit().create(Service.class);
    }


}
