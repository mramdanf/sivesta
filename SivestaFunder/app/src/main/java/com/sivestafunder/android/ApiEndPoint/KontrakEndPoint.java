package com.sivestafunder.android.ApiEndPoint;

import com.sivestafunder.android.ApiRespWrapper.ListNewSeeds;
import com.sivestafunder.android.Models.Kontrak;
import com.sivestafunder.android.Models.SimpleResp;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Sivesta on 22/12/2017.
 */

public interface KontrakEndPoint {
    @POST("kontrak/create")
    Observable<Kontrak> createKontrakService(
            @Body Kontrak kontrak
    );

    @GET("kontrak/kontrak_my_seeds")
    Observable<ListNewSeeds> getKontrakNewSeedsService(
            @Query("id_funders") String idFunder,
            @Query("filter") String filter
    );
}
