package com.sivestafunder.android.ApiEndPoint;

import com.sivestafunder.android.ApiRespWrapper.ListKomoditasResp;
import com.sivestafunder.android.ApiRespWrapper.ListSimulationResp;
import com.sivestafunder.android.Models.Simulation;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface KomoditasEndPoint {


    @GET("komoditas/get_new_komoditas")
    Observable<ListKomoditasResp> getPopularKomoditasService();

    @GET("komoditas/get_komoditas")
    Observable<ListKomoditasResp> getAllKomoditasService();

    @GET("komoditas/simulation")
    Observable<ListSimulationResp> getSimulationService(
            @Query("id_komoditas") String idKomoditas,
            @Query("jml_komoditas") int jmlKomoditas
    );
}
