package com.sivestafunder.android.Helpers;

import android.util.Base64;

import com.sivestafunder.android.ApiEndPoint.FunderEndPoint;
import com.sivestafunder.android.ApiEndPoint.KomoditasEndPoint;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ramdan Firdaus on 3/12/2017.
 */

public class RetrofitHelper {

    public FunderEndPoint getFunderService(String username, String password) {
        final Retrofit retrofit = createRetrofit(username, password);
        return retrofit.create(FunderEndPoint.class);
    }

    public KomoditasEndPoint komoditasService(String uname, String pass) {
        final Retrofit retrofit = createRetrofit(uname, pass);
        return retrofit.create(KomoditasEndPoint.class);
    }

    private OkHttpClient createOkHttpClient(final String username, final String password) {
        final OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        /*HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addNetworkInterceptor(httpLoggingInterceptor);*/

        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                String credentials = username + ":" + password;
                final String basic =
                        "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);

                Request.Builder requestBuilder = original.newBuilder()
                        .header("Authorization", basic)
                        .header("Accept", "application/json")
                        .method(original.method(), original.body());

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });

        return httpClient.build();
    }

    private Retrofit createRetrofit(String username, String password) {
        return new Retrofit.Builder()
                .baseUrl("http://192.168.137.250/sivesta/server-php/api/funder/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // <- add this
                .client(createOkHttpClient(username, password))
                .build();
    }
}

