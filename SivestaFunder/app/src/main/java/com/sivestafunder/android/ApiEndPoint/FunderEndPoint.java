package com.sivestafunder.android.ApiEndPoint;

import com.sivestafunder.android.Models.Funder;
import com.sivestafunder.android.Models.SimpleResp;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Ramdan Firdaus on 5/12/2017.
 */

public interface FunderEndPoint {

    @POST("login")
    Observable<Funder> checkLogin();

    @FormUrlEncoded
    @POST("account/create")
    Observable<SimpleResp> submitCreateAccount(
        @Field("nama") String nama,
        @Field("email") String email,
        @Field("password") String password

    );
}
