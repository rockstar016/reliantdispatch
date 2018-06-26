package com.rock.reliantdispatch.HttpInterfaces;

import com.rock.model.GooglePlaceDetail.GooglePlaceDetailModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ILocationService {
    @GET("maps/api/place/details/json")
    Call<GooglePlaceDetailModel> loadGooglePlaceDetail(@Query("placeid") String placeId, @Query("key") String APIKey, @Query("region") String region);
}
