package company.tap.ibtikarat.connection;

import company.tap.ibtikarat.responses.SDKSettings;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by AhlaamK on 3/29/20.
 * <p>
 * Copyright (c) 2020    Tap Payments.
 * All rights reserved.
 **/
public interface APIInterface {
    @GET(API_Constants.INIT)
    Call<SDKSettings> init();
}
