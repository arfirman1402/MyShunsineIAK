package com.alodokter.arfirman.myshunsineiak;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by alodokter-arfirman on 20/01/18.
 */

public interface WeatherApi {
    String BASE_URL = "http://api.openweathermap.org/data/2.5/";
    String API_KEY = App.getInstance().getApplicationContext().getString(R.string.api_key_weather);

    @GET("forecast/daily")
    Call getDailyForecast(@Query("q") String cityName, @Query("mode") String mode, @Query("units") String unit, @Query("cnt") int countData, @Query("appid") String apiKey);
}
