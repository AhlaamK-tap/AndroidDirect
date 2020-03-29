package company.tap.ibtikarat.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

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
            webview.loadUrl(url);
        }
    }
}
