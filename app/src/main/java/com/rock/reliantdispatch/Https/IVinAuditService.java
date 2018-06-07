package com.rock.reliantdispatch.Https;

import com.rock.reliantdispatch.Model.VinAuditResponseModel.VinResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by rock on 3/24/18.
 */

public interface IVinAuditService {
    @POST("/getspecifications.php")
    @FormUrlEncoded
    Call<VinResponse> savePost(@Field("vin") String vin,
                               @Field("key") String APIKey,
                               @Field("format") String responseFormat);
}
