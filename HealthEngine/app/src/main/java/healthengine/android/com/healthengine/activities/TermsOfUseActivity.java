package healthengine.android.com.healthengine.activities;

import android.content.Intent;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import healthengine.android.com.healthengine.R;


/**
 * Created by jsn.
 */

@EActivity(R.layout.activity_terms_of_use)
public class TermsOfUseActivity extends BaseActivity {

    @ViewById
    WebView terms_of_use_webview;

    String url="https://healthengine.com.au/terms_ios.php";

    @AfterViews
    public void afterUI(){

        terms_of_use_webview.loadUrl(url);
        terms_of_use_webview.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if(url.startsWith("mailto:") || url.startsWith("tel:")){
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(i);
                }
                else{
                    view.loadUrl(url);
                }

                return true;
            }


        });

    }


    @Click(R.id.cancel_action)
    public void finishActivity(){
        finish();
    }

    @Override
    public void onBackPressed() {
        if(terms_of_use_webview.canGoBack()){
            terms_of_use_webview.goBack();
        }
        else{
            super.onBackPressed();
        }

    }
}
