package com.sivestafunder.android.ApiEndPoint;

import com.sivestafunder.android.Models.Funder;

import io.reactivex.Observable;
import retrofit2.http.POST;

/**
 * Created by Ramdan Firdaus on 5/12/2017.
 */

public interface FunderEndPoint {

    @POST("login")
    Observable<Funder> checkLogin();
}
