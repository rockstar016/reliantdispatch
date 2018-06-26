package com.rock.reliantdispatch.DataStoreLayer;

import com.rock.model.VinAuditResponseModel.VinResponse;
import com.rock.reliantdispatch.Constants.StoreLayerConfig;
import com.rock.reliantdispatch.HttpInterfaces.IVinAuditService;
import com.rock.reliantdispatch.HttpInterfaces.RetrofitClient;

import java.io.IOException;

public class VinInfoStoreLayer {
    static final String VIN_AUDIT_BASE_URL = "http://specifications.vinaudit.com/";
    static final String VIN_AUDIT_API_KEY = "6GZ5554ROZHOU7P";

    public VinResponse loadVinData(String vinKey) {
        VinResponse response  = new VinResponse();
        try {
            response = RetrofitClient.getClient(VIN_AUDIT_BASE_URL)
                    .create(IVinAuditService.class)
                    .loadPost(vinKey, VIN_AUDIT_API_KEY, StoreLayerConfig.JSON_TYPE)
                    .execute()
                    .body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
}
