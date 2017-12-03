package com.sivesta.androidfarmer.ApiEndPoint;

import com.sivesta.androidfarmer.ApiRespWrapper.ListKomoditasResp;
import com.sivesta.androidfarmer.ApiRespWrapper.ModifyResp;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Ramdan Firdaus on 3/12/2017.
 */

public interface KomoditasEndPoint {

    @FormUrlEncoded
    @POST("komoditas/add")
    Observable<ModifyResp> addKomoditasService(
            @Field("nama") String nama,
            @Field("harga") int harga,
            @Field("stock") int stock,
            @Field("lokasi") String lokasi,
            @Field("type") int komoditasType,
            @Field("jumlah_pohon") int jmlPohon,
            @Field("id_petani") String idPetani,
            @Field("latitude") double latitude,
            @Field("longitude") double longitude
    );

    @GET("komoditas/gets")
    Observable<ListKomoditasResp> getKomoditasByFarmerService(
            @Query("id_petani") String idPetani
    );
}
