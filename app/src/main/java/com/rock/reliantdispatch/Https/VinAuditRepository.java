package com.rock.reliantdispatch.Https;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;

import com.rock.reliantdispatch.Model.VinAuditResponseModel.VinResponse;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by rock on 3/24/18.
 */

public class VinAuditRepository {
    static final String VIN_AUDIT_BASE_URL = "http://specifications.vinaudit.com/";
    static final String VIN_AUDIT_API_KEY = "6GZ5554ROZHOU7P";
    static final String VIN_AUDIT_RESPONSE_TYPE = "json";

    public static MutableLiveData<VinResponse> getVinResult(String vinKey) {
        MutableLiveData<VinResponse> data = new MutableLiveData<>();
        try {
            VinResponse response = RetrofitClient.getClient(VIN_AUDIT_BASE_URL)
                                                .create(IVinAuditService.class)
                                                .savePost(vinKey, VIN_AUDIT_API_KEY, VIN_AUDIT_RESPONSE_TYPE)
                                                .execute()
                                                .body();
            new Handler(Looper.getMainLooper()).post(() -> data.setValue(response));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
