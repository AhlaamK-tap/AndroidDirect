package company.tap.ibtikarat.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import company.tap.ibtikarat.R;

public class WebViewActivity extends AppCompatActivity {
    WebView webview;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        webview = findViewById(R.id.webview);
        if(getIntent()!=null){
            url= getIntent().getStringExtra("webview");
            webview.setWebViewClient(new WebPaymentWebViewClient());
            WebSettings settings = webview.getSettings();
            settings.setJavaScriptEnabled(true);
           webview.loadUrl(url);
        }
    }

    private class WebPaymentWebViewClient extends WebViewClient {
        boolean shouldOverride;
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
       Log.e("onPageStarted","rrrrrrrrrrrrrrrrrrrrrrrrr  >>> onPageStarted");
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
//       Log.d("shouldOverrideUrlLoad",("rrrrrrrrrrrrrrrrrrrrrrrrr");
            Log.e("WebPaymentActivity"," shouldOverrideUrlLoading : " + url);
         //   PaymentDataManager.WebPaymentURLDecision decision = PaymentDataManager.getInstance().decisionForWebPaymentURL(url);
            String decision = url;

          //  boolean shouldOverride = !decision.shouldLoad();
//      Log.d("WebPaymentActivity"," shouldOverrideUrlLoading : decision : " + shouldOverride);
            if (shouldOverride) { // if decision is true and response has TAP_ID
                // call backend to get charge response >> based of charge object type [Authorize - Charge] call retrieveCharge / retrieveAuthorize
                //PaymentDataManager.getInstance().retrieveChargeOrAuthorizeOrSaveCardAPI(getChargeOrAuthorize());
            }
            return shouldOverride;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
       Log.e("onPageFinished","rrrrrrrrrrrrrrrrrrrrrrrrr  >>> onPageFinished");
            super.onPageFinished(view, url);

        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
//       Log.d("onReceivedError","rrrrrrrrrrrrrrrrrrrrrrrrr  >>> error ");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && error!=null) {
                Log.d("onReceivedError"," shouldOverrideUrlLoading : error  : " + error.getDescription());
            }
            view.stopLoading();
            view.loadUrl("about:blank");
        }
    }
}
