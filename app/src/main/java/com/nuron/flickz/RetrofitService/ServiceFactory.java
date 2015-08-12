package com.nuron.flickz.RetrofitService;

import retrofit.RestAdapter;
import retrofit.android.AndroidLog;

/**
 * Created by sunil on 30-Jul-15.
 */
public class ServiceFactory {

    /**
     * Creates a retrofit service from an arbitrary class (clazz)
     * @param clazz Java interface of the retrofit service
     * @param endPoint REST endpoint url
     * @return retrofit service with defined endpoint
     */
    public static <T> T createRetrofitService(final Class<T> clazz, final String endPoint) {
        final RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(endPoint)
                .setLogLevel(RestAdapter.LogLevel.BASIC)
                .setLog(new AndroidLog("Flickz"))
                .build();

        return restAdapter.create(clazz);
    }
}