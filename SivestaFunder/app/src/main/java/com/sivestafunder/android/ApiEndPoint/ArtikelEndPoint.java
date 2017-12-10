package com.sivestafunder.android.ApiEndPoint;

import com.sivestafunder.android.ApiRespWrapper.ListArtikelResp;
import com.sivestafunder.android.ApiRespWrapper.ListKomoditasResp;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ArtikelEndPoint {
    @GET("artikel/gets")
    Observable<ListArtikelResp> getArtikelService();
}
