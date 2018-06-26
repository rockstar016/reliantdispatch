package com.rock.reliantdispatch.DataStoreLayer;

import com.rock.model.GooglePlaceDetail.GooglePlaceDetailModel;

public class LocationDataStoreLayer {
//    static String googleApiKey = "AIzaSyDxNUOOviLh7S3_Rx_BVOvyJjSTWPiNQhs";

    public GooglePlaceDetailModel loadPlaceDetailFromGoogle(String placeId) {
        GooglePlaceDetailModel response  = new GooglePlaceDetailModel();
//        try {
//            String googlePlaceDetailBaseURL = "https://maps.googleapis.com/";
//
//            Call<GooglePlaceDetailModel> call = RetrofitClient.getClient(googlePlaceDetailBaseURL)
//                    .create(ILocationService.class)
//                    .loadGooglePlaceDetail(placeId, googleApiKey, StoreLayerConfig.COUNTRY_US_REGION);
//             Response res =  call.execute();
//             response = (GooglePlaceDetailModel) res.body();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return response;
    }
}
