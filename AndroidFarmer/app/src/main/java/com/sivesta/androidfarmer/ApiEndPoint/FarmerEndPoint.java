package com.sivesta.androidfarmer.ApiEndPoint;

import com.sivesta.androidfarmer.ApiRespWrapper.ModifyResp;
import com.sivesta.androidfarmer.Models.Farmer;


import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


/**
 * Created by Ramdan Firdaus on 3/12/2017.
 */

public interface FarmerEndPoint {

    @POST("login")
    Observable<Farmer> checkLogin();

}
