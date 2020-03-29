package company.tap.ibtikarat.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.math.BigDecimal;

import company.tap.ibtikarat.R;
import company.tap.ibtikarat.connection.APIClient;
import company.tap.ibtikarat.connection.APIInterface;
import company.tap.ibtikarat.connection.AppInfo;
import company.tap.ibtikarat.enums.Merchant;
import company.tap.ibtikarat.models.Charge;
import company.tap.ibtikarat.models.Customer;
import company.tap.ibtikarat.models.PhoneNumber;
import company.tap.ibtikarat.models.SourceRequest;
import company.tap.ibtikarat.models.TrackingURL;
import company.tap.ibtikarat.requests.CreateChargeRequest;
import company.tap.ibtikarat.responses.SDKSettings;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    APIInterface apiInterface;
    Charge resource;
    Charge chargeOrAuthorize;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppInfo.setAuthToken(this,"sk_test_5lnTBxghrvMSJNGibWj6u7oU","");
        apiInterface = APIClient.getClient().create(APIInterface.class);
        callInit();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                callCharge();
            }
        });


    }

    private void callInit(){
        /**
         GET List Resources
         **/
        Call<SDKSettings> call = apiInterface.init();
        call.enqueue(new Callback<SDKSettings>() {
            @Override
            public void onResponse(Call<SDKSettings> call, Response<SDKSettings> response) {


                Log.d("TAG",response.code()+"");

                String displayResponse = "";

                SDKSettings resource = response.body();

                System.out.println("resource = " + resource.getData() + ", response = " + response);

            }

            @Override
            public void onFailure(Call<SDKSettings> call, Throwable t) {
                call.cancel();
            }
        });
    }

    private void callCharge(){
        /**
         GET List Resources
         **/
        SourceRequest source = new SourceRequest("src_kw.knet");
        TrackingURL redirect = new TrackingURL("gosellsdk://return_url");
        Merchant merchant=null;

        CreateChargeRequest chargeRequest = new CreateChargeRequest(
               merchant ,
                BigDecimal.ONE ,
               "KWD",
             BigDecimal.ONE,
               "kwd",
                getCustomer(),
                null,
                null,
                redirect,
                null,
                source,
                null,
                null,
                null,
                false,
                null,
                true,
                null,
                null
        );
        Call<Charge> call = apiInterface.createCharge(chargeRequest);
        call.enqueue(new Callback<Charge>() {
            @Override
            public void onResponse(Call<Charge> call, Response<Charge> response) {


                Log.d("TAG",response.code()+"");

                String displayResponse = "";

                 resource = response.body();
                 setChargeOrAuthorize(response.body());
              //  startWebActivity();
                //  System.out.println("resource = " + resource.getData() + ", response = " + response);

            }

            @Override
            public void onFailure(Call<Charge> call, Throwable t) {
                call.cancel();
                Log.e("failed",t.getLocalizedMessage());
            }
        });
    }

    private Customer getCustomer() { // test customer id cus_Kh1b4220191939i1KP2506448

        Customer customer = null;

        PhoneNumber phoneNumber = customer!=null ? customer.getPhone(): new PhoneNumber("965","69045932");

        return new Customer.CustomerBuilder(null).email("abc@abc.com").firstName("firstname")
                .lastName("lastname").metadata("").phone(new PhoneNumber(phoneNumber.getCountryCode(),phoneNumber.getNumber()))
                .middleName("middlename").build();



    }

    private void startWebActivity(){
        if(resource!=null){
            Intent intent = new Intent(this ,WebViewActivity.class);
            intent.putExtra("webview",resource.getTransaction().getUrl());
            startActivity(intent);

        }
    }

    public void startwebview(View view) {
        if(resource!=null){
            Intent intent = new Intent(this ,WebViewActivity.class);
            intent.putExtra("webview",resource.getTransaction().getUrl());
            intent.putExtra("chargid",resource.getId());
            startActivity(intent);
            finish();

        }
    }
    private void setChargeOrAuthorize(Charge chargeOrAuthorize) {
        this.chargeOrAuthorize = chargeOrAuthorize;
    }

}
