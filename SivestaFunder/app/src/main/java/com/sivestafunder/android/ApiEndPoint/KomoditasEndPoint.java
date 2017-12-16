package com.sivestafunder.android.ApiEndPoint;

import com.sivestafunder.android.ApiRespWrapper.ListKomoditasResp;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface KomoditasEndPoint {

//    @FormUrlEncoded
//    @POST("komoditas/add")
//    Observable<ModifyResp> addKomoditasService(
//            @Field("nama") String nama,
//            @Field("harga") int harga,
//            @Field("stock") int stock,
//            @Field("lokasi") String lokasi,
//            @Field("type") int komoditasType,
//            @Field("jumlah_pohon") int jmlPohon,
//            @Field("id_petani") String idPetani,
//            @Field("latitude") double latitude,
//            @Field("longitude") double longitude,
//            @Field("panjang") int panjang,
//            @Field("lebar") int lebar
//
//    );

    @GET("komoditas/get_komoditas")
    Observable<ListKomoditasResp> getPopularKomoditasService();

    @GET("komoditas/get_komoditas")
    Observable<ListKomoditasResp> getAllKomoditasService();

//    @FormUrlEncoded
//    @POST("komoditas/update")
//    Observable<ModifyResp> updateKomoditasService(
//            @Field("nama") String nama,
//            @Field("harga") int harga,
//            @Field("stock") int stock,
//            @Field("lokasi") String lokasi,
//            @Field("type") int komoditasType,
//            @Field("jumlah_pohon") int jmlPohon,
//            @Field("id_komoditas") String idKomoditas,
//            @Field("latitude") double latitude,
//            @Field("longitude") double longitude,
//            @Field("panjang") int panjang,
//            @Field("lebar") int lebar
//    );
}
