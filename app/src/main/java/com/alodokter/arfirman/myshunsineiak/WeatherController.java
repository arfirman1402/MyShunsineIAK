package com.alodokter.arfirman.myshunsineiak;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by alodokter-arfirman on 20/01/18.
 */

public class WeatherController {
    public void getWeatherList() {
        App.getInstance().getWeatherApi().getDailyForecast("Jakarta", "json", "metric", 10, WeatherApi.API_KEY);
    }
}
