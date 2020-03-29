package company.tap.ibtikarat.connection;

import company.tap.ibtikarat.models.Charge;
import company.tap.ibtikarat.requests.CreateChargeRequest;
import company.tap.ibtikarat.responses.SDKSettings;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by AhlaamK on 3/29/20.
 * <p>
 * Copyright (c) 2020    Tap Payments.
 * All rights reserved.
 **/
public interface APIInterface {
    @GET(API_Constants.INIT)
    Call<SDKSettings> init();

    /**
     * Create charge call.
     *
     * @param createChargeRequest the create charge request
     * @return the call
     */
    @POST(API_Constants.CHARGES)
    Call<Charge> createCharge(@Body CreateChargeRequest createChargeRequest);

}
