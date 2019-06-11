package hr.tvz.android.listavladimir;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Service {
    @GET("/LaserManiac/tvz-2019-android-lab-2/raw/master/app/src/main/res/raw/languages.json")
    Call<List<ModelClass>> getLista();
}
