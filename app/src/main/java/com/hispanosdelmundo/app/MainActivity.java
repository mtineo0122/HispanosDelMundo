package com.hispanosdelmundo.app;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private WebView myWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        this.getWindow().requestFeature(Window.FEATURE_ACTION_BAR);

        getWindow().setFeatureInt(Window.FEATURE_ACTION_BAR,
                Window.FEATURE_ACTION_BAR);

//        Toast loadingmess = Toast.makeText(this,
//                "Cargando El Diario de Hoy", Toast.LENGTH_SHORT);
//        loadingmess.show();


        setContentView(R.layout.activity_main);

        myWebView = (WebView) findViewById(R.id.webView);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
//        myWebView.loadUrl("http://www.hispanosdelmundo.com/");
        myWebView.loadUrl("https://testingsitesssblog.wordpress.com/");
        myWebView.setWebViewClient(new WebViewClient());

        myWebView.setInitialScale(1);
        myWebView.getSettings().setBuiltInZoomControls(true);
        myWebView.getSettings().setUseWideViewPort(true);
        myWebView.getSettings().getAllowFileAccess();

        final Activity MyActivity = this;
        myWebView.setWebChromeClient(new WebChromeClient() {
            boolean pressed = false;

            public void onProgressChanged(WebView view, int progress) {
                MyActivity.setTitle("Cargando...");

                if (progress == 100) {
                    MyActivity.setTitle(R.string.app_name);
                    if (!pressed) {
                        Toast.makeText(MyActivity, "Gracias por Preferirnos", Toast.LENGTH_SHORT).show();
                        pressed = true;
                    }
                }
            }
        });
    }
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (event.getAction() == KeyEvent.ACTION_DOWN) {
//            switch (keyCode) {
//                case KeyEvent.KEYCODE_BACK:
//                    if (myWebView.canGoBack()) {
//                        myWebView.goBack();
//                    } else {
//                        finish();
//                    }
//                    return true;
//            }
//
//        }
//        return super.onKeyDown(keyCode, event);
//    }


    @Override
    public void onBackPressed() {
        if (myWebView.isFocused() && myWebView.canGoBack()) {
            myWebView.goBack();
        } else {
            super.onBackPressed();
            finish();
        }
    }

}
