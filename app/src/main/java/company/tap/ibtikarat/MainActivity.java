package company.tap.ibtikarat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import company.tap.ibtikarat.connection.APIClient;
import company.tap.ibtikarat.connection.APIInterface;
import company.tap.ibtikarat.responses.SDKSettings;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    APIInterface apiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiInterface = APIClient.getClient().create(APIInterface.class);


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
}
