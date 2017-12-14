package com.sivestafunder.android.Helpers;

import android.util.Base64;

import com.sivestafunder.android.ApiEndPoint.ArtikelEndPoint;
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

    public KomoditasEndPoint getKomoditasService(String uname, String pass) {
        final Retrofit retrofit = createRetrofit(uname, pass);
        return retrofit.create(KomoditasEndPoint.class);
    }

    public KomoditasEndPoint getKomoditasServiceNoAuth() {
        final Retrofit retrofit = retrofitNoAuth();
        return retrofit.create(KomoditasEndPoint.class);
    }

    public ArtikelEndPoint getArtikelService() {
        final Retrofit retrofit = retrofitNoAuth();
        return retrofit.create(ArtikelEndPoint.class);
    }

    private OkHttpClient createOkHttpClient(final String username, final String password) {
        final OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

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
                .baseUrl("http://10.99.226.213/sivesta/server-php/api/funder/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(createOkHttpClient(username, password))
                .build();
    }

    private Retrofit retrofitNoAuth() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        OkHttpClient okHttpClient = httpClient.build();
        return new Retrofit.Builder()
                .baseUrl("http://10.99.226.213/sivesta/server-php/api/funder/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }
}

