package studyandroid.it.vietung.amthucbonphuong.components.outside_tabhost;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import studyandroid.it.vietung.amthucbonphuong.R;

public class WebviewActivity extends AppCompatActivity {

    private WebView webView;
    private String link;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        link = getIntent().getStringExtra("link_web").toString();

        webView = (WebView) findViewById(R.id.webView);

        webView.getSettings().setSupportZoom(true);
        webView.setInitialScale(1);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        webView.setScrollbarFadingEnabled(false);
        webView.setWebViewClient(new WebViewClient_Custom());

        pDialog = ProgressDialog.show(this,"","Loading .....");

        webView.loadUrl(link);

    }



    class WebViewClient_Custom extends WebViewClient
    {


        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);

            if(pDialog != null)
            {
                pDialog.dismiss();
            }
        }
    }


}
