package com.sivestafunder.android.ApiEndPoint;

import com.sivestafunder.android.Models.Funder;
import com.sivestafunder.android.Models.SimpleResp;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

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

    @Multipart
    @POST("account/update")
    Observable<SimpleResp> updateProfileFunder(
            @Part("nama") RequestBody name,
            @Part("email") RequestBody email,
            @Part("telepon") RequestBody phone,
            @Part("alamat") RequestBody address,
            @Part("id_funders") RequestBody idFunders

            );
}
